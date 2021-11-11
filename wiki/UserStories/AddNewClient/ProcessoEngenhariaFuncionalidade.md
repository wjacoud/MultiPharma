# **Aluno [1200630](../)** - US-3 - Adicionar um novo Cliente
# 1. Requisitos

**US-3** 

Como um usuário não registrado,
quero adicionar minhas informações pessoais
para que eu possa ser corretamente identificado pela farmácia como um novo cliente.

Critérios de aceitação:
1. Dado que as credenciais são necessárias
Quando o usuário não registrado está se inscrevendo
Em seguida, um e-mail exclusivo deve ser associado (para login)

2. Dado que as credenciais são necessárias
Quando o usuário não registrado está se inscrevendo
Em seguida, uma senha segura deve ser associada a pelo menos # caracteres (para login)

3. Dado que as informações pessoais são necessárias
Quando o usuário não registrado está se inscrevendo
Em seguida, nome, e-mail, endereço residencial e localização GPS, número do cartão de crédito, data de validade e CCV


# 2. Análise

Esta user storie define a criação de uma classe de objetos Cliente para o armazenamento adequado de seus dados. 
Foi desenhada uma classe de controlo para gerir e associar as informações das instâncias a base de dados.
Também está relacionada as classes cartão credito, address, User.

# 3. Design

A Classe ClienteDB foi desenhada para deter as características do acesso a base de dados dos cartões de credito. Para controlar os objetos relacionados.

Classe principal, classe de base de dados, classe de controlo, classe de testes unitários e testes de controlo.
public class CartaoCredito
public class CartaoCreditoDB extends DataHandler


# 4. Implementação

Atributos

    private Long id
    private String name
    private Integer credito
    private long moradaID
    private String utilizadorID
    private long cartaoCreditoID

Construtores

    public Cliente(long id, String name, int credito, long moradaID, String utilizadorID, long cartaoCreditoID)
    public Cliente(String name, int credito, long moradaID, String utilizadorID, long cartaoCreditoID) 

Gets

    public long getId()
    public String getName() 
    public Integer getCredito()
    public long getMorada()
    public String getUtilizadorID() 
    public long getCartaoCreditoID()

    public int hashCode()
    public boolean equals(Object obj)

    public String resgataCredito() // US-19

    public String toString()

## 4.1. Testes

Exemplos de testes unitários

	public class ClienteTest

    public void testGetId() {
        System.out.println("getId");
        Cliente instance = new Cliente(1, "Wilson", 00, 1, "email@sample.pt", 1);
        long expResult = 1;
        long result = instance.getId();
        assertEquals(expResult, result);
    }


# 5. Integração

A implementação	associa-se a um cartao credito, user, address por meio de uma chave estrangeira a ser inserida na tabela de clientes.

# 6. Observações

Método resgataCredito associado a US-19