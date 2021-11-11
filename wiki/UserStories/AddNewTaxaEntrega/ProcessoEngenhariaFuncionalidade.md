# **Aluno [1200630](../)** - US-20 - Registar uma Taxa de Entrega
# 1. Requisitos

**US-20** 

Como administrador,
QUERO estabelecer uma taxa de entrega,
PARA QUE eu possa lucrar com isso.

Critérios de aceitação:
1. Dado que pode haver várias taxas de entrega ativas ao mesmo tempo
Ao estabelecer uma nova taxa
Então eles devem ser distinguíveis entre si (esta assunção pode nao ser verdade)

Observação
Neste momento é uma tarifa fixa para atrair os clientes. Contudo, pode mudar no futuro. - (Fórum #: Taxa de Entrega)

Esta user storie define a criação de uma classe de objetos TaxaEntrega para o armazenamento adequado de seus atributos. A Classe TaxaEntregaDB foi desenhada para deter as características do acesso a base de dados dos cartões de credito. Para controlar os objetos relacionados, foi desenhada a classe ManagePTaxaEntregaController.


# 2. Análise

Esta user storie define a criação de uma classe de objetos TaxaEntrega e TaxaEntregaDB para o armazenamento adequado de seus dados. 
Foi desenhada uma classe de controlo para gerir e associar as informações das instâncias a base de dados.

# 3. Design

A TaxaEntregaDB foi desenhada para deter as características do acesso a base de dados das Taxas de Entrega. Para controlar os objetos relacionados.

Classe principal, classe de base de dados, classe de controlo, classe de testes unitários e testes de controlo.
public class TaxaEntrega
public class CTaxaEntregaDB extends DataHandler


# 4. Implementação

Atributos

    private Long id
    private Date dataInicio
    private Date dataFim
    private long valor
    private String descricao

Construtores

    public TaxaEntrega(long id, Date dataInicio, Date dataFim, long valor, String descricao)
    public TaxaEntrega(Date dataInicio, Date dataFim, long valor, String descricao)

Gets

    public Long getId()
    public void setId(Long id)
    public Date getData_inicio()
    public Date getData_fim() 
    public long getValor()
    public String getDescricao() 


    public int hashCode()
    public boolean equals(Object obj)


## 4.1. Testes

Exemplos de testes unitários

	public class TaxaEntregaTest

    public void testGetId() {
        System.out.println("getId");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        long expResult = 1;
        long result = instance.getId();
        assertEquals(expResult, result);
    }


# 5. Integração

A implementação	associa-se a um pedido.

