# **Aluno [1200630](../)** - US-59 - Adicionar uma nova Farmácia
# 1. Requisitos

**US-59** 

Como administrador,
Eu quero poder adicionar novas farmácias
Para que eu possa vender produtos de diferentes farmácias

Critérios de aceitação:
1. Dado que existem mais de uma farmácia
Ao fazer um pedido
Então o pedido deve ser para uma farmácia específica com um endereço diferente das outras

Esta user storie define a criação de uma classe de objetos Pharmacy para o armazenamento adequado de seus atributos. A Classe PharmacyDB foi desenhada para deter as características do acesso a base de dados dos cartões de credito. Para controlar os objetos relacionados, foi desenhada a classe ManagePharmacyController.


# 2. Análise

Esta user storie define a criação de uma classe de objetos Pharmacy e PharmacyDB para o armazenamento adequado de seus dados. 
Foi desenhada uma classe de controlo para gerir e associar as informações das instâncias a base de dados.

# 3. Design

A PharmacyDB foi desenhada para deter as características do acesso a base de dados das Farmácias. Para controlar os objetos relacionados.

Classe principal, classe de base de dados, classe de controlo, classe de testes unitários e testes de controlo.
public class CartaoCredito
public class CartaoCreditoDB extends DataHandler


# 4. Implementação

Atributos

    private Long id
    private String name
    private long nipc
    private long maxScooters
    private long maxDrones
    private long moradaID

Construtores

    public Pharmacy(Long id, String name, long nipc, long maxScooters, long maxDrones, long moradaID)
    public Pharmacy(String name, long nipc, long maxScooters, long maxDrones, long moradaID

Gets

    public long getId()
    public String getName() 
    public Integer getNipc()
    public long getMorada()
    public String getMaxScooters() 
    public long getMaxDrones()

    public int hashCode()
    public boolean equals(Object obj)


## 4.1. Testes

Exemplos de testes unitários

	public class PharmacyTest

    public void testGetId() {
        System.out.println("getId");
        Cliente instance = new Pharmacy(1, "Rouberi", 00, 1, "email@sample.pt", 1);
        long expResult = 1;
        long result = instance.getId();
        assertEquals(expResult, result);
    }


# 5. Integração

A implementação	associa-se a uma morada, address por meio de uma chave estrangeira.

# 6. Observações

Associada a um ou mais de um parks