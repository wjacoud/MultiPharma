# **Aluno (../)** - US-12 - Registar um novo Cartão de Credito
# 1. Requisitos

**US-12** 

Como administrador,
QUERO adicionar um produto de um catálogo de produtos,
PARA QUE o catálogo seja atualizado.

Critérios de aceitação:
1. Dado que a farmácia pode vender novos produtos
Quando um novo produto está para ser vendido
Então, um novo produto deve estar disponível para compra.

Esta user storie define a criação de uma classe de objetos Produto para o armazenamento adequado de seus atributos. A Classe ProdutoDB foi desenhada para deter as características do acesso a base de dados dos Produtos. Para controlar os objetos relacionados, foi desenhada a classe ManageProdutoController.

# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

_Recomenda-se que organize este conteúdo por subsecções._

Foi criado uma classe de controlo para gerir e associar as informações das instâncias a base de dados.

# 3. Design

_Nesta secção o estudante deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, o estudante deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade._

_Para além das secções sugeridas, podem ser incluídas outras._

## 3.1. Realização da Funcionalidade (SD)

_Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade._


## 3.2. Diagrama de Classes (CD)

_Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade._

## 3.3. Modelo Relacional (RM)


# 4. Implementação

_Nesta secção o estudante deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;_

_Recomenda-se que organize este conteúdo por subsecções._

## 4.1. Testes

_Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos._

 @Test
    public void testGetId() {
        System.out.println("getId");
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }
