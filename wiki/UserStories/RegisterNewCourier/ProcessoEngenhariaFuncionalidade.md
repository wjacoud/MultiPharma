# **Aluno [1140858](../)** - US-26 - Registar um novo estafeta

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-26** Como administrador, quero registrar mensageiros no sistema para que eu possa ter funcionários capazes de fazer entregas

Esta é é uma user storie bastante semelhante ao registar uma nova scooter, são ambas bastante simples.
Esta funcionalidade permite o registo de um novo estafeta numa determinada farmacia que seram as pessoas que iram realizar as entregas recorrendo as scooters disponiveis. 
Esta US depende de outros requisitos, ou seja, para um estafeta ser criada é preciso regitar uma conta de utilizador no sistema.

# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

_Recomenda-se que organize este conteúdo por subsecções._

Foi criado uma classe de controlor para gerar as operações sobre os estafetas.
Nesta user case foi criado o metodo de adicionar estafetas em conjunto com os utilizadores.
O controller cria um DataHandler específico para lidar com os metodos relacionados com os Estafetas.
Como as scooters tem Ids gerados automaticamente, ao ser criado uma nova scooter, a função retorna o Id que foi associado a essa scooter.
As validações ocorrem na função que validam se os IDs da farmacia e os IDs dos utilizadores existem e assim validam a criação do novo estafeta.

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

**Teste 1:** Verificar se a criação do utilizador ocorreu com sucesso.

**Teste 2:** Verificar se a criação do estafeta ocorreu com sucesso.

    ...

## 4.2. Código
Promenores de Java
Este código é muito simplista e não tem nenhum ponto revelante.

# 5. Integração

_Nesta secção o estudante deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema._

# 6. Observações

_Nesta secção sugere-se que o estudante apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados._
