# **Aluno [1140858](../)** - US-25 - Remover uma Scooter da Farmacia

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-25** Como administrador, quero remover uma scooter assim essa scooter não pode mais ser usada.

Esta é umas das mais simples US, e permite a remoção de uma scooter, neste caso, uma scooter elétrica que serão usadas por estafetas nas intregas de bens comprados nas farmácias.
Assim que elas sejam removidas, talvez por avaria ou fim de vida do bem.
A validação necessario para apagar a scooter é verificar se essa scooter existe em sistema.

# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

_Recomenda-se que organize este conteúdo por subsecções._

Utilizamos a classe de controlor criada anteriormente para criar a scooter.
Nesta user case foi criado o metodo de adicionar scooters.
O controller cria um DataHandler específico para lidar com os metodos relacionados com as Scooters.
Enviamos o id da scooter para ser apagada.
As validações ocorrem na função que validam se o ID da scooter existem e assim validam se a scooter foi removida com sucesso.

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
    ...

## 4.2. Código
Promenores de Java
Este código é muito simplista e não tem nenhum ponto revelante.

Promenores de SQL
O sql tem 1 exceptions que validam se existe o id da scooter existe e com isso sabemos que a scooter existe.

# 5. Integração

_Nesta secção o estudante deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema._

# 6. Observações

_Nesta secção sugere-se que o estudante apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados._
