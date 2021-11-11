# **Aluno [1171317](../)** - US-98 - Notificar o Estafeta com uma estimativa do tempo restante para carregamento completo

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-98** 
As an Administrator
I want to have the system notify Couriers with an estimate of time until a fully charged battery,
so that he can schedule his next runs. 

De acordo com o caderno de encargos e com as respostas dadas no Fórum para Esclarecimento de Requisitos (perspetiva de cliente do sistema), contido na página de LAPR3, foram identificadas as seguintes **regras de negócio**:


# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

Esta US possui duas dependências, nomeadamente as seguintes tarefas: **NANB12-99 [Task] - Establish format for lock_[datetime].data and estimate_[datetime].data files** e **NANB12-97 [Task] - Implement service for notifications (by email)**. Com a presente US fecha-se um ciclo de User Stories que teve inicio na **NANB12-47 [US]- Implement C component with Assembly Modules**. 

Esta US procura num diretorio predefinido, por ficheiros dos tipo **estimate_[datetime].data.flag**. Caso encontre um destes ficheiros, tenta abrir o ficheiro correspondente **.data**, que armazena valor da estimativa calculada pela aplicação em C. Por sua vez, no caso de uma leitura de ficheiro bem sucedida, e apenas na presença de um estafeta válido, uma notificação (por email) será enviada ao estafeta, com o valor da estimativa. 

## 2.1. Diagrama de Casos de Uso (UCD)

![us98_notifyCourierOfTimeEstimateToFullCharge_uc_diagram](us98_notifyCourierOfTimeEstimateToFullCharge_uc_diagram.svg)

# 3. Design

_Nesta secção o estudante deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, o estudante deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade._

_Para além das secções sugeridas, podem ser incluídas outras._

## 3.1. Realização da Funcionalidade (SD)

_Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade._

![us98_notifyCourierOfTimeEstimateToFullCharge_cd](us98_notifyCourierOfTimeEstimateToFullCharge_cd.svg)

## 3.2. Diagrama de Classes (CD)

_Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade._

![us98_notifyCourierOfTimeEstimateToFullCharge_sd](us98_notifyCourierOfTimeEstimateToFullCharge_sd.svg)

## 3.3. Modelo Relacional (RM)

Não se verificou qualquer necessidade de persistencia no desenvolvimento da presente US.

# 4. Implementação

_Nesta secção o estudante deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;_

_Recomenda-se que organize este conteúdo por subsecções._

## 4.1. Testes

_Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos._

**Teste 1:** Verificar que não é possível criar uma instância da classe X com valores nulos.

    ...

## 4.2. Código


# 5. Integração

_Nesta secção o estudante deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema._

# 6. Observações

_Nesta secção sugere-se que o estudante apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados._
