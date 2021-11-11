-- Data Definition Language --

delimiter /


--    DROP TABLES
drop table FARMACIAS cascade constraints   /
drop table MORADA cascade constraints   /
drop table TIPO_PARQUES cascade constraints   /
drop table PARQUES_SCOOTERS cascade constraints   /
drop table PARQUES_DRONES cascade constraints   /
drop table PARQUES cascade constraints   /
drop table LUGAR_ESTACIONAMENTO_SCOOTER cascade constraints  /
drop table LUGAR_ESTACIONAMENTO_DRONES cascade constraints  /
drop table UTILIZADORES cascade constraints   /
drop table CARTAO_CREDITO cascade constraints   /
drop table CLIENTES cascade constraints   /
drop table ESTAFETAS cascade constraints   /
drop table ADMINISTRADORES cascade constraints   /
drop table TIPOS_SCOOTER cascade constraints   / 
drop table SCOOTERS cascade constraints   /
drop table SCOOTER_ESTAFETA cascade constraints   /
drop table PRODUTOS cascade constraints   /
drop table STOCK_PRODUTOS cascade constraints   /
drop table ORDENS cascade constraints   /
drop table ORDEM_PRODUTO cascade constraints   /
drop table FATURAS cascade constraints   /
drop table TRAJETOS cascade constraints   /
drop table DETALHE_TRAJETO cascade constraints   /
drop table ESTACIONAMENTO_SCOOTER cascade constraints   /
drop table TAXA_ENTREGA cascade constraints   /
drop table DRONES cascade constraints   /
drop table TIPOS_DRONES cascade constraints /

--    CREATE TABLES

CREATE TABLE MORADA (
    morada_id           number(10)          GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    pais                varchar2(255),
    distrito            varchar(255),
    localidade          varchar2(255),
    postal              varchar2(8)         constraint ch_morada_postal CHECK(REGEXP_LIKE(postal, '^[1-9][0-9]{3}-[0-9]{3}$')),
    latitude            number(20,16)        constraint ch_morada_latitude CHECK( latitude between -90 and 90 ),
    longitude           number(20,16)        constraint ch_morada_longitude CHECK( longitude between -180 and 180)
    )
  /

CREATE TABLE FARMACIAS (
    farmacia_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    nome varchar2(255) NOT NULL,
    NIPC number(9) NOT NULL UNIQUE,
    max_scooters number(10),
    max_drones number(10),
    morada_id REFERENCES MORADA(morada_id),
    creditos_max number(5),
    creditos_por_compra number(5)
    )
/

CREATE TABLE TIPO_PARQUES (
    tipo_parque_id number(10) PRIMARY KEY,
    descricao varchar2(255)
    )
  /

CREATE TABLE PARQUES (
    parque_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    voltagem number(10),
    morada_id REFERENCES MORADA(morada_id),
    farmacia_id REFERENCES FARMACIAS(farmacia_id),
    tipo_parque_id REFERENCES TIPO_PARQUES(tipo_parque_id)
    )
  /

CREATE TABLE PARQUES_SCOOTERS (
    parque_id number(10) PRIMARY KEY,
    total_estacionamentos_scooters number(10),
    CONSTRAINT fk_parque_scooters_id FOREIGN KEY (parque_id) REFERENCES PARQUES(parque_id)
    )
/

CREATE TABLE PARQUES_DRONES (
    parque_id number(10) PRIMARY KEY,
    total_estacionamentos_drones number(10),
    CONSTRAINT fk_parque_drones_id FOREIGN KEY (parque_id) REFERENCES PARQUES(parque_id)
    )
/

--CREATE TABLE ESTACIONAMENTOS (
--    estacionamento_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
--    carga number(10),
--    parque_id REFERENCES PARQUES(parque_id)
--    )
--  /

CREATE TABLE LUGAR_ESTACIONAMENTO_SCOOTER (
    estacionamento_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    carga number(10),
    parque_id REFERENCES PARQUES_SCOOTERS(parque_id)
    )
  /

CREATE TABLE LUGAR_ESTACIONAMENTO_DRONES (
    estacionamento_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    carga number(10),
    parque_id REFERENCES PARQUES_DRONES(parque_id)
    )
  /

CREATE TABLE UTILIZADORES (
    email       varchar2(255)   PRIMARY KEY,
    pass        varchar2(255)   constraint nn_utilizadores_pass not null,
    constraint ck_utilizadores_pk CHECK(REGEXP_LIKE(email, '^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$'))
	)
  /

CREATE TABLE CARTAO_CREDITO (
    cartao_credito_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    numero number(16),
    cvv number(3),
    validade date
    )
  /

CREATE TABLE CLIENTES (
    cliente_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    cname varchar2(255),
    nif number(9) UNIQUE,
    credito number(10),
    morada_id REFERENCES MORADA(morada_id),
    utilizador_email REFERENCES Utilizadores (email), 
    cartao_credito_id REFERENCES Cartao_Credito(cartao_credito_id)
    )
  /
                 
CREATE TABLE ESTAFETAS (
    estafeta_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    ename varchar2(255),
    nif number(9),
    niss number(20),
    peso number(10),
    utilizador_email REFERENCES Utilizadores (email)
    )
  /
                     
CREATE TABLE ADMINISTRADORES (
    admin_id number(10),
    utilizador_email REFERENCES Utilizadores (email),
    farmacia_id REFERENCES Farmacias (farmacia_id)
    )
  /

CREATE TABLE TIPOS_SCOOTER (
    tipo_scooter_id number(10) PRIMARY KEY,
    peso number(10),
    velocidade_media number(20),
    area_frontal number(20),
    bateria_maxima number(20),
    potencia_motor number(20)
    )
  /

CREATE TABLE SCOOTERS (
    scooter_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    capacidade_mochila number(10),
    operacional varchar2(4),
    farmacia_id REFERENCES FARMACIAS(farmacia_id),
    tipo_scooter_id REFERENCES TIPOS_SCOOTER (tipo_scooter_id)
    )
  /

CREATE TABLE TIPOS_DRONES (
    tipo_drones_id number(10) PRIMARY KEY,
    peso number(10),
    velocidade_media number(20),
    area_frontal number(20),
    bateria_maxima number(20),
    potencia_motor number(20)
    )
  /

CREATE TABLE DRONES (
    drones_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    capacidade_mochila number(10),
    operacional varchar2(4),
    farmacia_id REFERENCES FARMACIAS(farmacia_id),
    tipo_drones_id REFERENCES TIPOS_DRONES (tipo_drones_id)
    )
  /

--associação estafeta e scooter
CREATE TABLE SCOOTER_ESTAFETA (
    scooter_estafeta_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    data_uso date,
    scooter_id REFERENCES SCOOTERS (scooter_id),
    estafeta_id REFERENCES ESTAFETAS (estafeta_id)
    )
  /

CREATE TABLE PRODUTOS (
    produto_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    nome varchar2(255),
    peso number(10),
    preco_unit number(10),
    iva number(10)
    )
  /

CREATE TABLE STOCK_PRODUTOS (
    qtd number(10),
    produto_id REFERENCES PRODUTOS (produto_id),
    farmacia_id REFERENCES FARMACIAS(farmacia_id),
    constraint stock_produtos_pk primary key (produto_id, farmacia_id) 
    )
  /

CREATE TABLE TAXA_ENTREGA (
    taxa_entrega_id number(10)GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    data_inicio date,
    data_fim date,
    valor number(10),
    descricao varchar2(255)
    )
  /

CREATE TABLE ORDENS (
    ordem_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY ,
    peso number(8,2),
    data_criacao timestamp,
    preco number(8,2),
    taxa_entrega_id REFERENCES TAXA_ENTREGA(taxa_entrega_id),
    cliente_id REFERENCES CLIENTES (cliente_id),
    farmacia_id REFERENCES FARMACIAS(farmacia_id)
    )
  /

CREATE TABLE ORDEM_PRODUTO (
    ordem_produto_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    qtd number(10),
    ordem_id REFERENCES ORDENS (ordem_id),
    produto_id REFERENCES PRODUTOS (produto_id)
    )
  /

CREATE TABLE FATURAS (
    fatura_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    data_emit date,
    valor_sem_iva number(8,2),
    valor_iva number(8,2),
    valor_total number(8,2),    
    ordem_id REFERENCES ORDENS (ordem_id)
    )
  /

CREATE TABLE TRAJETOS (
    trajeto_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    date_inicio timestamp,
    data_fim timestamp,
    peso_total number(10),
    energia_gasta number(20),
    estafeta_id REFERENCES ESTAFETAS (estafeta_id)
    )
  /

CREATE TABLE DETALHE_TRAJETO (
    detalhe_trajeto_id number(10)GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    data_entrega timestamp,
    order_id REFERENCES ORDENS (ordem_id),
    trajeto_id REFERENCES TRAJETOS (trajeto_id)
    )
  /

CREATE TABLE ESTACIONAMENTO_SCOOTER (
    estacionamento_scooter_id number(10) GENERATED AS IDENTITY NOCACHE PRIMARY KEY,
    trancado number(1),
    data_entrada timestamp,
    data_saida timestamp,
    tempo_carregamento number(10),
    estacionamento_id REFERENCES LUGAR_ESTACIONAMENTO_SCOOTER(estacionamento_id),
    scooter_id REFERENCES SCOOTERS(scooter_id)
    )
  /