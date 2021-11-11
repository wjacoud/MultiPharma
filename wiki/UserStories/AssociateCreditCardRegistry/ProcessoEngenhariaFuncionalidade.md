# **Aluno [1200630](../)** - US-5 - Registar um novo Cartão de Credito
# 1. Requisitos

**US-5** 

Como um usuário não registrado, desejo fornecer um número de cartão de crédito, data de validade e CCV de modo que eu cobro pelos meus pedidos.
O site pode exibir as informações do cartão de crédito quando o usuário está conectado
O site pode armazenar apenas uma informação de cartão de crédito para cada usuário.
O site só aceita informações de cartão de crédito válidas.


# 2. Análise

Esta user storie define a criação de uma classe de objetos CartaoCredito para o armazenamento adequado de seus dados. 
Foi desenhada uma classe de controlo para gerir e associar as informações das instâncias a base de dados.

# 3. Design

A Classe CartaoCreditoDB foi desenhada para deter as características do acesso a base de dados dos cartões de credito. Para controlar os objetos relacionados, foi desenhada a classe ManageCartaoCreditoController.

Classe principal, classe de base de dados, classe de controlo, classe de testes unitários e testes de controlo.
public class CartaoCredito
public class CartaoCreditoDB extends DataHandler
public class AssociateCartaoCreditoToClientRegistryController

SQL FUNCTIONS EPROCEDURES

-ADDCARTAOCREDITO

-GETCARTAOCREDITO

# 4. Implementação

Atributos

    private Long id
    private long numero
    private long cvv
    private Date validade

Construtores

    public CartaoCredito(long id, long numero, long cvv, Date validade) Instância Completa.
    public CartaoCredito(long numero, long cvv, Date validade) Instância sem id, Atribuído na DB.

Gets

    public long getId()
    public long getNumero()
    public long getCvv()
    public Date getValidade()

    public int hashCode()
    public boolean equals(Object obj)

## 4.1. Testes

Exemplos de testes unitários

public class CartaoCreditoTest

    @Test
        public void testGetId() {
            System.out.println("getId");
            CartaoCredito instance = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
            long expResult = 1;
            long result = instance.getId();
            assertEquals(expResult, result);
        }

Exemplos de testes de controlo

    public class AssociateCartaoCreditoToClientRegistryControllerTest
    @Test
         void testAddCartaoCredito() {
             System.out.println("addCartaoCredito");
             long expResult =  expectedCartaoCredito.getId();
             long result = cartaoCreditoDB.addCartaoCredito(new CartaoCredito(1L, 44445555, 456, Date.valueOf("2021-01-01")));
             assertEquals(expResult, result);
        }

## 4.2. Código

    public long addCartaoCredito(final long numero, final long cvv, final Date validade){        
        final CartaoCredito newCartaoCredito = new CartaoCredito(numero, cvv, validade);
        try{
            long novoId = this.cartaoCreditoDB.addCartaoCredito(newCartaoCredito);
            Logger.getLogger(AssociateCartaoCreditoToClientRegistryController.class.getName()).log(Level.FINE, "\tA new Credit Card was added with ID: " + novoId);
            return novoId;
        }catch(IllegalArgumentException iae){
            Logger.getLogger(AssociateCartaoCreditoToClientRegistryController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return -1;
        }
    }

# 5. Integração

A implementação	associa-se a um cliente por meio de uma chave estrangeira a ser inserida na tabela de clientes.

# 6. Observações

É efetuada uma validação do cartão de credito por meio de uma função SQL a verificar a data de validade do mesmo.

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
