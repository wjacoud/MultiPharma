# **Aluno [1140858](../)** - US-162 - Associar um novo Drone a Farmacia

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-162*** Como administrador, eu pretendo associar/registrar um drone na farmácia para que haja viaturas para viabilizar a entrega.

Esta é umas das mais simples US, e permite a criação de um novo veículo, neste caso, um drone. Esta user storie é muito semelhante a user storie US-23 "Associar uma nova Scooter a Farmacia"

# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

_Recomenda-se que organize este conteúdo por subsecções._

Foi criado uma classe de controlor para gerar as operações sobre os drones.
Nesta user case foi criado o metodo de adicionar drones.
O controller cria um DataHandler específico para lidar com os metodos relacionados com os drones.
Como os drones tem Ids gerados automaticamente, ao ser criado um novo drone, a função retorna o Id que foi associado a esse drone.
As validações ocorrem na função que validam se os IDs da farmacia e do tipo de drones existem e assim validam a criação do drone.

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

**Teste 1:** Verificar se a definição da ID do drone ocorre correctamente.

**Teste 2:** Verificar se a definição do Tipo da drone ocorre correctamente.

**Teste 3:** Verificar se a definição do ID da Farmacia da Scooter ocorre correctamente.

    ...

## 4.2. Código
Promenores de Java
Este código é muito simplista e não tem nenhum ponto revelante.

Promenores de SQL
O sql tem 2 exceptions que validam se existe a farmacia a que queremos associar um drone e uma para validar o tipo de scooter associada.

# 5. Integração

_Nesta secção o estudante deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema._

# 6. Observações

_Nesta secção sugere-se que o estudante apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados._
