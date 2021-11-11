# **Aluno [1140858](../)** - US-24 - Atualizar uma Scooter da Farmacia

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-24** Como Administrador, quero atualizar as informações relacionadas a uma scooter para que todos os veículos estejam atualizados.

Esta é umas das mais simples US, e permite a atualizacao de uma scooter, neste caso, uma scooter elétrica que serão usadas por estafetas nas intregas de bens comprados nas farmácias.
Assim que elas sejam atualizadas, essa informação deve ser persistida e tambem actualizada para todos os estafetas.
A actualização pode ser relacionada por uma ma inserção dos dados, de alteração da capacidade da mochila ou até a modificação da scooter.
A validação necessario para atualizar a scooter é verificar se essa scooter id existe em sistema, se a farmacia id existe em sistema, se o tipo de scooter id existe em sistema.

# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

_Recomenda-se que organize este conteúdo por subsecções._

Utilizamos a classe de controlor criada anteriormente para criar a scooter.
Nesta user case foi criado o metodo de atualizar as scooters.
O controller cria um DataHandler específico para lidar com os metodos relacionados com as Scooters.
Enviamos os dados que representam uma scooter e assim é atualizada a scooter numa unica forma ou acção.
As validações ocorrem na função que validam se o ID da scooter existem, se o ID da farmacia existe e se o ID da scooter type existe.

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

**Teste 1:** Verificar se a definição da ID da Scooter existe.

**Teste 2:** Verificar se a definição da ID da Scooter type existe.

**Teste 3:** Verificar se a definição da ID da Farmaci aexiste.
    ...

## 4.2. Código
Promenores de Java
Este código é muito simplista e não tem nenhum ponto revelante.

Promenores de SQL
O sql tem 3 exceptions que validam se existe os id existem e com isso sabemos que a scooter existe.

# 5. Integração

_Nesta secção o estudante deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema._

# 6. Observações

_Nesta secção sugere-se que o estudante apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados._
