delimiter /

--    FUNCOES & PROCEDIMENTOS & TRIGGERS
CREATE OR REPLACE PROCEDURE p_cria_produto_ordem(p_ordem_id in out number, cliente_id number default null,
                           farmacia_id number default null, produto_id NUMBER, qtd NUMBER)
IS
    dummy number;
    parametros_invalidos exception;
    ordem_invalida exception;
BEGIN      
    IF p_ordem_id < 1 THEN 
        IF cliente_id < 1 or farmacia_id < 1 then raise parametros_invalidos;
        END IF;
        insert into ordens(peso, data_criacao, preco, cliente_id, farmacia_id) values(null, SYSDATE, null, cliente_id, farmacia_id);
        select max(ordem_id) into p_ordem_id from ordens;
        insert into ordem_produto(qtd, ordem_id, produto_id) values(qtd, p_ordem_id, produto_id);
    ELSE 
        BEGIN
            select 0 into dummy from ordens where ordens.ordem_id = p_ordem_id;
            EXCEPTION
            when no_data_found then raise ordem_invalida;
        END;
        insert into ordem_produto(qtd, ordem_id, produto_id) values(qtd, p_ordem_id, produto_id);
    END IF;
EXCEPTION
    when parametros_invalidos then raise_application_error(-20010,'cliente ou farmacia id é null');
    when ordem_invalida then raise_application_error(-20011,'ordem indicada nao existe');
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER atualiza_ordem
BEFORE INSERT OR UPDATE ON ordem_produto
FOR EACH ROW
DECLARE
    p_peso number;
    p_preco number;
BEGIN
    --update preco e peso
    select produtos.peso, produtos.preco_unit into p_peso, p_preco from produtos where produtos.produto_id = :new.produto_id;
    update ordens set preco=nvl(preco, 0)+:new.qtd*p_preco, 
                      peso=nvl(peso,0)+ :new.qtd*p_peso 
                      where ordem_id=:new.ordem_id;
    --update stock
    update stock_produtos set qtd=qtd - :new.qtd
                            where produto_id= :new.produto_id and qtd - :new.qtd >=0;
    if sql%rowcount=0 then raise_application_error(-20020, 'Not enough in stock'); end if;
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER atualiza_cliente_credito --definir credito incremento e definir credito necessario para free delivery
BEFORE INSERT ON ordens
FOR EACH ROW
DECLARE
v_cliente number;
v_old_credito number;
v_max_creditos number;
v_cred_inc number;

BEGIN
select c.credito, c.cliente_id into v_old_credito, v_cliente from clientes c where c.cliente_id=:new.cliente_id;
select f.creditos_max, f.creditos_por_compra into v_max_creditos, v_cred_inc from farmacias f where farmacia_id=:new.farmacia_id;
if v_old_credito >= v_max_creditos then 
:new.taxa_entrega_id:=1;
update clientes set credito=credito-v_max_creditos where clientes.cliente_id=v_cliente;
else
select max(taxa_entrega_id) into :new.taxa_entrega_id from taxa_entrega;
end if;

update clientes set credito=credito+v_cred_inc where clientes.cliente_id=v_cliente;
END;
/

-----------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION devolve_ordem(p_ordem_id number) RETURN SYS_REFCURSOR
IS
curOrder SYS_REFCURSOR;
BEGIN
    OPEN curOrder FOR select * from ordens 
    where ordens.ordem_id=p_ordem_id;
RETURN curOrder;
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getClientEmailFromOrder(p_ordem_id NUMBER) RETURN VARCHAR2
IS
p_email VARCHAR2(255);
BEGIN
select u.email into p_email from clientes c, utilizadores u, ordens o where c.cliente_id=o.cliente_id and u.email=c.utilizador_email and o.ordem_id=p_ordem_id;
return p_email;
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION f_get_produtos_ordem(p_ordem_id number) RETURN SYS_REFCURSOR
IS
curprodutos SYS_REFCURSOR;
BEGIN
OPEN curprodutos for select op.ordem_produto_id,prod.nome, prod.peso, prod.preco_unit, op.qtd from ordem_produto op, produtos prod 
                        where op.ordem_id=p_ordem_id and op.produto_id=prod.produto_id;
RETURN curprodutos;
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getFatura(p_fatura_id number) RETURN SYS_REFCURSOR
IS
curfatura SYS_REFCURSOR;
BEGIN
OPEN curfatura for select * from faturas where fatura_id=p_fatura_id;
RETURN curfatura;
END;
/

CREATE OR REPLACE FUNCTION getOrdemFatura(p_fatura_id number) RETURN SYS_REFCURSOR
IS
curordemfatura SYS_REFCURSOR;
BEGIN
OPEN curordemfatura for select o.* from ordens o, faturas f where f.fatura_id=p_fatura_id and f.ordem_id=o.ordem_id;
RETURN curordemfatura;
END;
/

--Criar fatura de uma ordem
CREATE OR REPLACE PROCEDURE cria_fatura(p_ordem_id number, faturaid OUT number)
IS
dummy number;
dummy2 number;
v_valor_total number;
v_valor_sem_iva number;
v_iva number;
BEGIN
    select 0 into dummy from ordens where ordens.ordem_id = p_ordem_id; 
    select o.ordem_id, sum(p.iva*p.preco_unit * op.qtd*0.01) into dummy2, v_iva from ordens o, produtos p, ordem_produto op 
        where p.produto_id=op.produto_id and op.ordem_id=o.ordem_id and o.ordem_id=p_ordem_id
        group by o.ordem_id;
    select o.preco + t.valor into v_valor_sem_iva from ordens o, taxa_entrega t
        where o.ordem_id=p_ordem_id and t.taxa_entrega_id=o.taxa_entrega_id;
    v_valor_total:=v_iva+v_valor_sem_iva;
    insert into faturas(data_emit, valor_sem_iva, valor_iva, valor_total, ordem_id) 
        values(SYSDATE, v_valor_sem_iva, v_iva, v_valor_total, p_ordem_id);
    select max(fatura_id) into faturaid from faturas;
    
EXCEPTION
    when no_data_found then raise_application_error(-20001,'Ordem nao existe');
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE cria_produto(pid OUT number, nome varchar2, peso number, preco_unit number, iva number)
IS
BEGIN
    insert into produtos(nome, peso, preco_unit, iva) values(nome, peso, preco_unit, iva);
    select max(produto_id) into pid from produtos;
    
EXCEPTION
    when no_data_found then raise_application_error(-20111,'Produto não inserido');
END;
 /
--------------------------------------------------------------------------------
CREATE or REPLACE FUNCTION allPharmacyAddress RETURN SYS_REFCURSOR
IS
address_cursor SYS_REFCURSOR;
BEGIN
OPEN address_cursor FOR
        SELECT f.farmacia_id, m.* FROM morada m, farmacias f where f.morada_id=m.morada_id;
    RETURN address_cursor;
END;
/
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION addScooter(capacidadeMochila NUMBER, pharmacyID NUMBER, scooterTypeID NUMBER)
RETURN NUMBER
IS
    TOTAL_PHARMACY  INT;
	TOTAL_SPOTS	INT;
    TOTAL_SCOOTER_TYPE  INT;
	TOTAL_SCOOTER_PHARMACY_HAVE INT;
	
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_SCOOTER_TYPE_EXCEPTION   EXCEPTION;
	NO_SPACE_FOR_NEW_SCOOTER	EXCEPTION;
	
	RETURN_ID_VALUE NUMBER;
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_PHARMACY <> 1 THEN
		RAISE NO_PHARMACY_EXCEPTION;
	END IF;
	
	SELECT COUNT(ts.TIPO_SCOOTER_ID) INTO TOTAL_SCOOTER_TYPE
	FROM TIPOS_SCOOTER ts
	WHERE ts.TIPO_SCOOTER_ID = scooterTypeID;
    
        IF TOTAL_SCOOTER_TYPE <> 1 THEN
            RAISE NO_SCOOTER_TYPE_EXCEPTION;
        END IF;
	
	SELECT COUNT(s.scooter_id) INTO TOTAL_SCOOTER_PHARMACY_HAVE
	FROM SCOOTERS s, FARMACIAS f
	WHERE f.farmacia_id = s.farmacia_id
	AND f.farmacia_id = pharmacyID
        AND s.operacional LIKE 'SIM';

	SELECT f.max_scooters INTO TOTAL_SPOTS
	FROM FARMACIAS f
	WHERE f.farmacia_id = pharmacyID;
	
	IF TOTAL_SCOOTER_PHARMACY_HAVE + 1 > TOTAL_SPOTS THEN
		RAISE NO_SPACE_FOR_NEW_SCOOTER;
	END IF;
    
    INSERT INTO SCOOTERS (capacidade_mochila, operacional, farmacia_id, tipo_scooter_id) VALUES(capacidadeMochila, 'SIM', pharmacyID, scooterTypeID);
    SELECT MAX(S.scooter_id) INTO RETURN_ID_VALUE FROM SCOOTERS S;
    RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_SCOOTER_TYPE_EXCEPTION THEN
            raise_application_error(-20006, 'No Scooter Type found with that ID!');
		WHEN NO_SPACE_FOR_NEW_SCOOTER THEN
            raise_application_error(-20006, 'No space for new scooter!');
END;
/
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION addDrones(capacidadeMochila NUMBER, pharmacyID NUMBER, droneTypeID NUMBER)
RETURN NUMBER
IS
    TOTAL_PHARMACY  INT;
	TOTAL_SPOTS	INT;
    TOTAL_DRONE_TYPE  INT;
	TOTAL_DRONE_PHARMACY_HAVE INT;
	
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_DRONE_TYPE_EXCEPTION   EXCEPTION;
	NO_SPACE_FOR_NEW_DRONE	EXCEPTION;
	
	RETURN_ID_VALUE NUMBER;
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_PHARMACY <> 1 THEN
		RAISE NO_PHARMACY_EXCEPTION;
	END IF;
	
	SELECT COUNT(ts.TIPO_DRONES_ID) INTO TOTAL_DRONE_TYPE
	FROM TIPOS_DRONES ts
	WHERE ts.TIPO_DRONES_ID = droneTypeID;
    
    IF TOTAL_DRONE_TYPE <> 1 THEN
		RAISE NO_DRONE_TYPE_EXCEPTION;
    END IF;
	
	SELECT COUNT(s.drones_id) INTO TOTAL_DRONE_PHARMACY_HAVE
	FROM DRONES s, FARMACIAS f
	WHERE f.farmacia_id = s.farmacia_id
	AND f.farmacia_id = pharmacyID
        AND s.operacional LIKE 'SIM';

	SELECT f.max_DRONES INTO TOTAL_SPOTS
	FROM FARMACIAS f
	WHERE f.farmacia_id = pharmacyID;
	
	IF TOTAL_DRONE_PHARMACY_HAVE + 1 > TOTAL_SPOTS THEN
		RAISE NO_SPACE_FOR_NEW_DRONE;
	END IF;
    
    INSERT INTO DRONES (capacidade_mochila, operacional, farmacia_id, TIPO_DRONES_ID) VALUES(capacidadeMochila, 'SIM', pharmacyID, droneTypeID);
    SELECT MAX(S.drones_id) INTO RETURN_ID_VALUE FROM DRONES S;
    RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_DRONE_TYPE_EXCEPTION THEN
            raise_application_error(-20006, 'No Drone Type found with that ID!');
		WHEN NO_SPACE_FOR_NEW_DRONE THEN
            raise_application_error(-20006, 'No space for new Drone!');
END;
/
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION addCliente(p_cname VARCHAR2, p_nif NUMBER, p_credito NUMBER, p_morada_id NUMBER, p_utilizador_email VARCHAR2, p_cartao_credito_id NUMBER)
RETURN NUMBER
IS
    RETURN_ID_VALUE NUMBER;
    TOTAL_MORADA  INT;
    TOTAL_UTILIZADOR  INT;
    TOTAL_CARTAOCREDITO  INT;
    NO_MORADA_EXCEPTION   EXCEPTION;
    NO_UTILIZADOR_EXCEPTION   EXCEPTION;
    NO_CARTAOCREDITO_TYPE_EXCEPTION   EXCEPTION;
BEGIN
	SELECT COUNT(m.morada_id) INTO TOTAL_MORADA
	FROM MORADA m
	WHERE m.morada_id = p_morada_id;
	
	SELECT COUNT(u.email) INTO TOTAL_UTILIZADOR
	FROM UTILIZADORES u
	WHERE u.email = p_utilizador_email;

         SELECT COUNT(c.cartao_credito_id) INTO TOTAL_CARTAOCREDITO
	FROM CARTAO_CREDITO c
	WHERE c.cartao_credito_id = p_cartao_credito_id;
	
	IF TOTAL_MORADA <> 1 THEN
            RAISE NO_MORADA_EXCEPTION;
	END IF;
    
         IF TOTAL_UTILIZADOR <> 1 THEN
            RAISE NO_UTILIZADOR_EXCEPTION;
         END IF;

         IF TOTAL_CARTAOCREDITO <> 1 THEN
            RAISE NO_CARTAOCREDITO_TYPE_EXCEPTION;
         END IF;
    
    INSERT INTO CLIENTES (cname, nif, credito, morada_id, utilizador_email, cartao_credito_id) VALUES(p_cname, p_nif, p_credito, p_morada_id, p_utilizador_email, p_cartao_credito_id);
    SELECT MAX(cl.cliente_id) INTO RETURN_ID_VALUE FROM CLIENTES cl;
    RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_MORADA_EXCEPTION THEN
            raise_application_error(-20002, 'No adress found with that ID!');
        WHEN NO_UTILIZADOR_EXCEPTION THEN
            raise_application_error(-20003, 'No User found with that ID!');
        WHEN NO_CARTAOCREDITO_TYPE_EXCEPTION THEN
            raise_application_error(-20004, 'No Credit Card Type found with that ID!');
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getCliente(p_id   clientes.cliente_id%type)
RETURN sys_refcursor
AS
    cursor_cliente       sys_refcursor;
BEGIN
    open cursor_cliente
    for select c.* from clientes c where c.cliente_id = p_id;
    return cursor_cliente;
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER trgAtributosClienteInvalidos
before insert or update
on clientes
for each row
declare
    morada_counter          integer :=0;
    utiliz_counter          integer :=0;
    cartao_counter          integer :=0;
    argumentos_invalidos    exception;
begin
    
    select count(*) into utiliz_counter from utilizadores u where u.email = :new.utilizador_email;
    select count(*) into morada_counter from morada m where m.morada_id = :new.morada_id;
    select count(*) into cartao_counter from cartao_credito cc where cc.cartao_credito_id = :new.cartao_credito_id;
    
    if utiliz_counter != 1 or cartao_counter != 1 or morada_counter != 1 then
        --delete from utilizadores u where u.email = :new.utilizador_email;
        delete from morada m where m.morada_id = :new.morada_id;
        --delete from cartao_credito cc where cc.cartao_credito_id = :new.cartao_credito_id;
        raise argumentos_invalidos;    
    end if;
    
exception
    when argumentos_invalidos then
        raise_application_error(-20002,'Unable to Sign-Up - unknown attributes!');
        delete from morada m where m.morada_id = :new.morada_id;
        rollback;
end trgAtributosClienteInvalidos;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION addTaxaEntrega(p_data_inicio DATE, p_data_fim DATE, p_valor NUMBER, p_descricao VARCHAR2)
RETURN NUMBER
IS
    RETURN_ID_VALUE NUMBER;
BEGIN
    INSERT INTO TAXA_ENTREGA(data_inicio, data_fim, valor, descricao) VALUES(p_data_inicio, p_data_fim, p_valor, p_descricao);
    SELECT MAX(taxa_entrega_id) INTO RETURN_ID_VALUE FROM TAXA_ENTREGA;
    RETURN  RETURN_ID_VALUE;
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getTaxaEntrega(p_id   taxa_entrega.taxa_entrega_id%type)
RETURN sys_refcursor
AS
    cursor_taxa_entrega      sys_refcursor;
BEGIN
    open cursor_taxa_entrega
    for select t.* from taxa_entrega t where t.taxa_entrega_id = p_id;
    return cursor_taxa_entrega;
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION addCartaoCredito(p_numero NUMBER, p_cvv NUMBER, p_validade DATE)
RETURN NUMBER
IS
    RETURN_ID_VALUE NUMBER;
    NO_VALIDITY_EXCEPTION EXCEPTION;
BEGIN
    IF p_validade < SYSDATE THEN
            RAISE NO_VALIDITY_EXCEPTION;
    END IF;
    INSERT INTO CARTAO_CREDITO(numero, cvv, validade) VALUES( p_numero, p_cvv, p_validade);
    SELECT MAX(cartao_credito_id) INTO RETURN_ID_VALUE FROM CARTAO_CREDITO;
    RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_VALIDITY_EXCEPTION THEN
            raise_application_error(-20030, 'INVALID CREDIT CARD!');
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getCartaoCredito(p_id   cartao_credito.cartao_credito_id%type)
RETURN sys_refcursor
AS
    cursor_cartao_credito       sys_refcursor;
BEGIN
    open cursor_cartao_credito
    for select c.* from cartao_credito c where c.cartao_credito_id = p_id;
    return cursor_cartao_credito;
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getCartaoCreditoByClienteID(p_id   cartao_credito.cartao_credito_id%type)
RETURN sys_refcursor
AS
    cursor_cartao_credito      sys_refcursor;
BEGIN
    open cursor_cartao_credito
    for select cc.* from cartao_credito cc, clientes c where cc.cartao_credito_id = c.cartao_credito_id and c.cliente_id = p_id;
    return cursor_cartao_credito;
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
-- FUNCAO - devolve a Morada correspondente ao ID passado por parametro * 
CREATE OR REPLACE FUNCTION getMorada(p_id   morada.morada_id%type)
RETURN sys_refcursor
AS
    cursor_morada       sys_refcursor;
BEGIN
    open cursor_morada 
    for select m.* from morada m where m.morada_id = p_id;
    return cursor_morada;
--EXCEPTION
--    when NO_DATA_FOUND then
--        raise_application_error(-20001, 'No Address was found!'); * 
END;
 /
--------------------------------------------------------------------------------

-- FUNCAO - devolve a Morada correspondente ao ID do Cliente passado por parametro * 
CREATE OR REPLACE FUNCTION getMoradaByClienteID(p_id   clientes.cliente_id%type)
RETURN sys_refcursor
AS
    cursor_morada       sys_refcursor;
BEGIN
    open cursor_morada 
    for select m.* from morada m, clientes c where m.morada_id = c.morada_id and c.cliente_id = p_id;
    return cursor_morada;
--EXCEPTION
--    when NO_DATA_FOUND then
--        raise_application_error(-20001, 'No Address was found!'); * 
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION addMorada(p_pais   morada.pais%type, p_distrito  morada.distrito%type, p_localidade   morada.localidade%type,
                                        p_postal   morada.postal%type, p_latitude   morada.latitude%type, p_longitude   morada.longitude%type)
RETURN NUMBER
AS
    nova_morada_id      NUMBER;
BEGIN
    insert into morada (pais, distrito, localidade, postal, latitude, longitude) values (p_pais, p_distrito, p_localidade, p_postal, p_latitude, p_longitude);
    select max(morada_id) into nova_morada_id from morada;
    return nova_morada_id;
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
-- FUNCAO - devolve o Utilizador correspondente ao ID passado por parametro * 
CREATE OR REPLACE FUNCTION getUtilizador(p_id   Utilizadores.email%type)
RETURN sys_refcursor
AS
    cursor_utilizador       sys_refcursor;
BEGIN
    open cursor_utilizador 
    for select u.* from utilizadores u where u.email = p_id;
    return cursor_utilizador;
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
-- FUNCAO - devolve a Utilizador correspondente ao ID do Cliente passado por parametro 
CREATE OR REPLACE FUNCTION getUtilizadorByClienteID(p_id   clientes.cliente_id%type)
RETURN sys_refcursor
AS
    cursor_utilizador       sys_refcursor;
BEGIN
    open cursor_utilizador 
    for select u.* from utilizadores u, clientes c where u.email = c.utilizador_email and c.cliente_id = p_id;
    return cursor_utilizador;
END;
 /
--------------------------------------------------------------------------------------------------------------------------------
-- FUNCAO - Tenta inserir na tablea Utilizadores um novo utilizador cujos atributos recebe por paramtros 
CREATE OR REPLACE FUNCTION addUtilizador(p_email    Utilizadores.email%type, p_pass   Utilizadores.pass%type)
RETURN VARCHAR
AS
BEGIN
    insert into utilizadores (email, pass) values (p_email, p_pass);
    return p_email;
END;
 /
--------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION addCourier(ename VARCHAR2, nif NUMBER, niss NUMBER, peso NUMBER, emailUser VARCHAR2, passUser VARCHAR2)
RETURN NUMBER
IS
	emailID		VARCHAR2(255);
	courierID	NUMBER;
	
BEGIN
    INSERT INTO UTILIZADORES (email, pass) VALUES(emailUser, passUser);
    SELECT MAX(U.email) INTO emailID FROM UTILIZADORES U;
	
    INSERT INTO ESTAFETAS (ename, nif, niss, peso, utilizador_email) VALUES(ename, nif, niss, peso, emailID);
    SELECT MAX(E.estafeta_id) INTO courierID FROM ESTAFETAS E;
	
    RETURN  courierID;
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getOrdersToBeDelivered(farmaciaId FARMACIAS.FARMACIA_ID%type)
RETURN sys_refcursor
AS
    cursor_orders   sys_refcursor;
BEGIN
    OPEN cursor_orders FOR
        SELECT O.ORDEM_ID, O.PESO, O.DATA_CRIACAO, O.PRECO, O.TAXA_ENTREGA_ID, O.CLIENTE_ID, O.FARMACIA_ID
        FROM ORDENS O
        WHERE O.ORDEM_ID NOT IN  (SELECT DT.ORDER_ID
                                    FROM DETALHE_TRAJETO DT
                                    WHERE O.ORDEM_ID = DT.ORDER_ID)
		AND O.FARMACIA_ID = farmaciaId
        ORDER BY O.DATA_CRIACAO DESC;
    RETURN cursor_orders;
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION addPharmacy(p_nome VARCHAR2, p_NIPC NUMBER, p_max_scooters NUMBER, p_max_drones NUMBER, p_morada_id NUMBER, p_creditos_max NUMBER, p_creditos_por_compra NUMBER)
RETURN NUMBER
IS
    RETURN_ID_VALUE NUMBER;
    TOTAL_MORADA  INT;
    NO_MORADA_EXCEPTION   EXCEPTION;
BEGIN
	SELECT COUNT(m.morada_id) INTO TOTAL_MORADA
	FROM MORADA m
	WHERE m.morada_id = p_morada_id;
	
	IF TOTAL_MORADA <> 1 THEN
            RAISE NO_MORADA_EXCEPTION;
	END IF;
    
INSERT INTO FARMACIAS (nome, NIPC, max_scooters, max_drones, morada_id, creditos_max, creditos_por_compra) 
VALUES(p_nome, p_NIPC, p_max_scooters, p_max_drones, p_morada_id, p_creditos_max, p_creditos_por_compra);
    SELECT MAX(F.farmacia_id) INTO RETURN_ID_VALUE FROM FARMACIAS F;
    RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_MORADA_EXCEPTION THEN
            raise_application_error(-20007, 'No Adress found with that ID!');
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getPharmacy(p_id   farmacias.farmacia_id%type)
RETURN sys_refcursor
AS
    cursor_farmacia       sys_refcursor;
BEGIN
    open cursor_farmacia
    for select f.* from farmacias f where f.farmacia_id = p_id;
    return cursor_farmacia;
END;
 /
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllAddress
RETURN sys_refcursor
AS
    cursor_address   sys_refcursor;
BEGIN
    OPEN cursor_address FOR
        SELECT * FROM Morada;
    RETURN cursor_address;
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION makeRun(dateUso DATE, dataFim DATE, scooterID NUMBER, estafetaID NUMBER, pesoTotal NUMBER, energiaGasta NUMBER)
RETURN NUMBER
AS
    VAR_ES_ID       NUMBER;
    trajectosID     NUMBER;
BEGIN
    SELECT ES.ESTACIONAMENTO_SCOOTER_ID INTO VAR_ES_ID
    FROM ESTACIONAMENTO_SCOOTER ES
    WHERE ES.SCOOTER_ID = scooterID
    AND ES.DATA_SAIDA IS NULL
    ORDER BY ES.DATA_ENTRADA DESC;

    UPDATE ESTACIONAMENTO_SCOOTER SET DATA_SAIDA = dateUso WHERE ESTACIONAMENTO_SCOOTER_ID = VAR_ES_ID;

    INSERT INTO SCOOTER_ESTAFETA (data_uso, scooter_id, estafeta_id) VALUES (dateUso, scooterID, estafetaID);
    INSERT INTO TRAJETOS (date_inicio, data_fim, peso_total, energia_gasta, estafeta_id) VALUES (dateUso, dataFim, pesoTotal, energiaGasta, estafetaID);
    SELECT MAX(E.estafeta_id) INTO trajectosID FROM ESTAFETAS E;

    RETURN trajectosID;
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE generatedRouteDetail(dataEntrega DETALHE_TRAJETO.data_entrega%TYPE, orderID NUMBER, trajectoID NUMBER)
IS
BEGIN
    INSERT INTO DETALHE_TRAJETO (data_entrega, order_id, trajeto_id) VALUES (dataEntrega, orderID, trajectoID);
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getMoradaByClienteID(p_id   clientes.cliente_id%type)
RETURN sys_refcursor
AS
    cursor_morada       sys_refcursor;
BEGIN
    open cursor_morada 
    for select m.* from morada m, clientes c where m.morada_id = c.morada_id and c.cliente_id = p_id;
    return cursor_morada;
--EXCEPTION
--    when NO_DATA_FOUND then
--        raise_application_error(-20001, 'No Address was found!'); * 
END;
 /
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getMoradaByPharmacyID(p_id   FARMACIAS.FARMACIA_ID%type)
RETURN sys_refcursor
AS
    cursor_morada       sys_refcursor;
BEGIN
    open cursor_morada 
    for select m.* from morada m, FARMACIAS f where m.morada_id = f.morada_id and f.FARMACIA_ID = p_id;
    return cursor_morada;
--EXCEPTION
--    when NO_DATA_FOUND then
--        raise_application_error(-20001, 'No Address was found!'); * 
END;
 /
--------------------------------------------------------------------------------

create or replace FUNCTION getScooterType(stID TIPOS_SCOOTER.tipo_scooter_id%type)
RETURN sys_refcursor
AS
    cursor_scooter_type       sys_refcursor;
BEGIN
    OPEN cursor_scooter_type FOR
		SELECT st.*
		FROM TIPOS_SCOOTER st
		WHERE st.tipo_scooter_id = stID;
    return cursor_scooter_type;
END;
 /

CREATE OR REPLACE FUNCTION getDronesType(stID TIPOS_DRONES.tipo_drones_id%type)
RETURN sys_refcursor
AS
    cursor_drone_type       sys_refcursor;
BEGIN
    OPEN cursor_drone_type FOR
		SELECT st.*
		FROM TIPOS_DRONES st
		WHERE st.tipo_drones_id = stID;
    return cursor_drone_type;
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE removeScooter(scooterID NUMBER)
AS
    TOTAL_SCOOTER  INT;
    NO_SCOOTER_EXCEPTION   EXCEPTION;
	
BEGIN
    SELECT COUNT(S.scooter_id) INTO TOTAL_SCOOTER
	FROM SCOOTERS S
	WHERE S.scooter_id = scooterID;
	
	IF TOTAL_SCOOTER <> 1 THEN
            RAISE NO_SCOOTER_EXCEPTION;
	END IF;
	
    --DELETE FROM SCOOTERS WHERE scooter_id = scooterID;
    UPDATE SCOOTERS SET operacional = 'NAO' WHERE scooter_id = scooterID;
    
    EXCEPTION
        WHEN NO_SCOOTER_EXCEPTION THEN raise_application_error(-20010,'Scooter id é invalido');
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE removeDrone(droneID NUMBER)
AS
    TOTAL_DRONE  INT;
    NO_SCOOTER_EXCEPTION   EXCEPTION;
	
BEGIN
    SELECT COUNT(S.drones_id) INTO TOTAL_DRONE
	FROM DRONES S
	WHERE S.drones_id = droneID;
	
	IF TOTAL_DRONE <> 1 THEN
            RAISE NO_SCOOTER_EXCEPTION;
	END IF;
	
    --DELETE FROM DRONES WHERE drones_id = droneID;
    UPDATE DRONES SET operacional = 'NAO' WHERE drones_id = droneID;
    
    EXCEPTION
        WHEN NO_SCOOTER_EXCEPTION THEN raise_application_error(-20010,'Drone id é invalido');
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE updateScooter(scooterID NUMBER, capacidadeMochila NUMBER, pharmacyID NUMBER, scooterTypeID NUMBER)
AS
    TOTAL_SCOOTER   INT;
    TOTAL_PHARMACY  INT;
    TOTAL_SCOOTER_TYPE  INT;
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_SCOOTER_EXCEPTION    EXCEPTION;
    NO_SCOOTER_TYPE_EXCEPTION   EXCEPTION;
    
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
    SELECT COUNT(s.scooter_id) INTO TOTAL_SCOOTER
	FROM SCOOTERS s
	WHERE s.scooter_id = scooterID;
    
	SELECT COUNT(ts.TIPO_SCOOTER_ID) INTO TOTAL_SCOOTER_TYPE
	FROM TIPOS_SCOOTER ts
	WHERE ts.TIPO_SCOOTER_ID = scooterTypeID;
	
	IF TOTAL_PHARMACY <> 1 THEN
            RAISE NO_PHARMACY_EXCEPTION;
	END IF;
    
    IF TOTAL_SCOOTER <> 1 THEN
        RAISE NO_SCOOTER_EXCEPTION;
    END IF;
    
    IF TOTAL_SCOOTER_TYPE <> 1 THEN
        RAISE NO_SCOOTER_TYPE_EXCEPTION;
    END IF;
    
    UPDATE SCOOTERS SET capacidade_mochila = capacidadeMochila WHERE scooter_id = scooterID;
    UPDATE SCOOTERS SET farmacia_id = pharmacyID WHERE scooter_id = scooterID;
    UPDATE SCOOTERS SET tipo_scooter_id = scooterTypeID WHERE scooter_id = scooterID;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_SCOOTER_EXCEPTION THEN
            raise_application_error(-20006, 'No Scooter found with that ID!');
        WHEN NO_SCOOTER_TYPE_EXCEPTION THEN
            raise_application_error(-20006, 'No Scooter Type found with that ID!');
END;
/
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE updateDrone(droneID NUMBER, capacidadeMochila NUMBER, pharmacyID NUMBER, droneTypeID NUMBER)
AS
    TOTAL_DRONE   INT;
    TOTAL_PHARMACY  INT;
    TOTAL_DRONE_TYPE  INT;
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_DRONE_EXCEPTION    EXCEPTION;
    NO_DRONE_TYPE_EXCEPTION   EXCEPTION;
    
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
    SELECT COUNT(s.drones_id) INTO TOTAL_DRONE
	FROM DRONES s
	WHERE s.drones_id = droneID;
    
	SELECT COUNT(ts.TIPO_drones_id) INTO TOTAL_DRONE_TYPE
	FROM TIPOS_DRONES ts
	WHERE ts.TIPO_drones_id = droneTypeID;
	
	IF TOTAL_PHARMACY <> 1 THEN
            RAISE NO_PHARMACY_EXCEPTION;
	END IF;
    
    IF TOTAL_DRONE <> 1 THEN
        RAISE NO_DRONE_EXCEPTION;
    END IF;
    
    IF TOTAL_DRONE_TYPE <> 1 THEN
        RAISE NO_DRONE_TYPE_EXCEPTION;
    END IF;
    
    UPDATE DRONES SET capacidade_mochila = capacidadeMochila WHERE drones_id = droneID;
    UPDATE DRONES SET farmacia_id = pharmacyID WHERE drones_id = droneID;
    UPDATE DRONES SET tipo_drones_id = droneTypeID WHERE drones_id = droneID;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_DRONE_EXCEPTION THEN
            raise_application_error(-20006, 'No Drone found with that ID!');
        WHEN NO_DRONE_TYPE_EXCEPTION THEN
            raise_application_error(-20006, 'No Drone Type found with that ID!');
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getLugarEscationamentoScooters(parqueID   LUGAR_ESTACIONAMENTO_SCOOTER.parque_id%type)
RETURN sys_refcursor
AS
    cursor_lugar_estacionamento_scooter sys_refcursor;
BEGIN
    OPEN cursor_lugar_estacionamento_scooter FOR
        SELECT * 
        FROM LUGAR_ESTACIONAMENTO_SCOOTER
        WHERE parque_id = parqueID;
    RETURN cursor_lugar_estacionamento_scooter;
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION addScootersPark(addressID NUMBER, pharmacyID NUMBER, voltage NUMBER, maxScooterCapacity NUMBER, charge NUMBER, noCharge NUMBER)
RETURN NUMBER
IS
    TOTAL_PHARMACY  INT;
	TOTAL_ADDRESS	INT;
	TOTAL_STOPS_IN_USE 	INT;
    MAX_STOPS   INT;
	RETURN_ID_VALUE NUMBER;
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_ADDRESS_EXCEPTION   EXCEPTION;
    NO_MORE_SPACE_FOR_SCOOTER_EXCEPTION EXCEPTION;
	
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_PHARMACY <> 1 THEN
            RAISE NO_PHARMACY_EXCEPTION;
	END IF;
	
	SELECT COUNT(m.morada_id) INTO TOTAL_ADDRESS
	FROM MORADA m
	WHERE m.morada_id = addressID;
	
	IF TOTAL_ADDRESS <> 1 THEN
            RAISE NO_ADDRESS_EXCEPTION;
	END IF;
	
	SELECT SUM(ps.total_estacionamentos_scooters) INTO TOTAL_STOPS_IN_USE
    FROM PARQUES_SCOOTERS ps, PARQUES p
    WHERE ps.parque_id = p.parque_id
    AND p.farmacia_id = pharmacyID;
    
    SELECT f.max_scooters INTO MAX_STOPS
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_STOPS_IN_USE + maxScooterCapacity > MAX_STOPS THEN
	    RAISE NO_MORE_SPACE_FOR_SCOOTER_EXCEPTION;
	END IF;
    
    --SELECT TP.tipo_parque_id INTO PARK_SCOOTERS_ID FROM tp.TIPO_PARQUES WHERE;
    
    INSERT INTO PARQUES (voltagem, morada_id, farmacia_id, tipo_parque_id) VALUES (voltage, addressID, pharmacyID, 1);
    SELECT MAX(P.parque_id) INTO RETURN_ID_VALUE FROM PARQUES P;
    
	INSERT INTO PARQUES_SCOOTERS (parque_id, total_estacionamentos_scooters) VALUES (RETURN_ID_VALUE, maxScooterCapacity);
    
    FOR indx IN 1 .. noCharge
    LOOP
        INSERT INTO LUGAR_ESTACIONAMENTO_SCOOTER (carga, parque_id) VALUES (0, RETURN_ID_VALUE);
    END LOOP;
	
	 FOR indx IN 1 .. maxScooterCapacity - noCharge
    LOOP
        INSERT INTO LUGAR_ESTACIONAMENTO_SCOOTER (carga, parque_id) VALUES (charge, RETURN_ID_VALUE);
    END LOOP;
    	
	RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_ADDRESS_EXCEPTION THEN
            raise_application_error(-20006, 'No Scooter Type found with that ID!');
        WHEN NO_MORE_SPACE_FOR_SCOOTER_EXCEPTION THEN
            raise_application_error(-20006, 'Reached the maximum capacity to manage scooters in this pharmacy!');
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getLugarEscationamentoDrones(parqueID   LUGAR_ESTACIONAMENTO_DRONES.parque_id%type)
RETURN sys_refcursor
AS
    cursor_lugar_estacionamento_drones sys_refcursor;
BEGIN
    OPEN cursor_lugar_estacionamento_drones FOR
        SELECT * 
        FROM LUGAR_ESTACIONAMENTO_DRONES
        WHERE parque_id = parqueID;
    RETURN cursor_lugar_estacionamento_drones;
END;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION addDronesPark(addressID NUMBER, pharmacyID NUMBER, voltage NUMBER, maxDroneCapacity NUMBER, charge NUMBER, noCharge NUMBER)
RETURN NUMBER
IS
    TOTAL_PHARMACY  INT;
	TOTAL_ADDRESS	INT;
	TOTAL_STOPS_IN_USE 	INT;
    MAX_STOPS   INT;
	RETURN_ID_VALUE NUMBER;
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_ADDRESS_EXCEPTION   EXCEPTION;
    NO_MORE_SPACE_FOR_SCOOTER_EXCEPTION EXCEPTION;
	
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_PHARMACY <> 1 THEN
            RAISE NO_PHARMACY_EXCEPTION;
	END IF;
	
	SELECT COUNT(m.morada_id) INTO TOTAL_ADDRESS
	FROM MORADA m
	WHERE m.morada_id = addressID;
	
	IF TOTAL_ADDRESS <> 1 THEN
            RAISE NO_ADDRESS_EXCEPTION;
	END IF;
	
	SELECT SUM(ps.total_estacionamentos_drones) INTO TOTAL_STOPS_IN_USE
    FROM PARQUES_DRONES ps, PARQUES p
    WHERE ps.parque_id = p.parque_id
    AND p.farmacia_id = 1;
    
    SELECT f.max_drones INTO MAX_STOPS
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_STOPS_IN_USE + maxDroneCapacity > MAX_STOPS THEN
	    RAISE NO_MORE_SPACE_FOR_SCOOTER_EXCEPTION;
	END IF;
    
    --SELECT TP.tipo_parque_id INTO PARK_SCOOTERS_ID FROM tp.TIPO_PARQUES WHERE;
    
    INSERT INTO PARQUES (voltagem, morada_id, farmacia_id, tipo_parque_id) VALUES (voltage, addressID, pharmacyID, 1);
    SELECT MAX(P.parque_id) INTO RETURN_ID_VALUE FROM PARQUES P;
	INSERT INTO PARQUES_DRONES (parque_id, total_estacionamentos_drones) VALUES (RETURN_ID_VALUE, maxDroneCapacity);
    
    FOR indx IN 1 .. noCharge
    LOOP
        INSERT INTO LUGAR_ESTACIONAMENTO_DRONES (carga, parque_id) VALUES (0, RETURN_ID_VALUE);
    END LOOP;
	
	 FOR indx IN 1 .. maxDroneCapacity - noCharge
    LOOP
        INSERT INTO LUGAR_ESTACIONAMENTO_DRONES (carga, parque_id) VALUES (charge, RETURN_ID_VALUE);
    END LOOP;
    	
	RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_ADDRESS_EXCEPTION THEN
            raise_application_error(-20006, 'No Drones Type found with that ID!');
        WHEN NO_MORE_SPACE_FOR_SCOOTER_EXCEPTION THEN
            raise_application_error(-20006, 'Reached the maximum capacity to manage drones in this pharmacy!');
END;
/

-- FUNCAO - devolve o Email do Estafeta correspondente ao ID passado por parametro * 
CREATE OR REPLACE FUNCTION getEmailEstafetaById(p_id   estafetas.estafeta_id%type)
RETURN varchar2
AS
    tmp_email       varchar2(255);
BEGIN
    select e.utilizador_email into tmp_email 
    from estafetas e 
    where e.estafeta_id = p_id;

    return tmp_email;
END;
/