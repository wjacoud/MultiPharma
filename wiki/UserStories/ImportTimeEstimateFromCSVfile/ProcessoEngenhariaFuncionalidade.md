# **Aluno [1171317](../)** - US-22 - Importar estimativa de tempo até carregamento completo a partir de ficheiro CSV

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-22** 
As an Administrator,
I want the system to be able to read time estimates from CSV files
so that I have time estimates to notify couriers with 

De acordo com o caderno de encargos e com as respostas dadas no Fórum para Esclarecimento de Requisitos (perspetiva de cliente do sistema), contido na página de LAPR3, foram identificadas as seguintes **regras de negócio**:


# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

Esta US dá seguimento à **US-47 - Implement C component with Assembly Modules**, no sentido em que lida com os ficheiros produzidos pela mesma, **estimate_[datetime].data**, contendo uma estimativa do tempo total de carregamento de uma scooter estacionada. Quanto à implementação, projeta-se a criação de uma interface e de um serviço de leitura de ficheiros CSV. Desta interface, seria oportuno se fosse implementada de forma poder ser usada por outras US que necessitassem igualmente de importação a partir de ficheiros CSV. 

## 2.1. Diagrama de Casos de Uso (UCD)
![us22_importTimeEstimateFromCSVfile_uc_diagram](us22_importTimeEstimateFromCSVfile_uc_diagram.svg)

# 3. Design

_Nesta secção o estudante deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, o estudante deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade._

_Para além das secções sugeridas, podem ser incluídas outras._

## 3.1. Realização da Funcionalidade (SD)

_Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade._

![us22_importTimeEstimateFromCSVfile_cd](us22_importTimeEstimateFromCSVfile_cd.svg)

## 3.2. Diagrama de Classes (CD)

_Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade._

![us22_importTimeEstimateFromCSVfile_sd](us22_importTimeEstimateFromCSVfile_sd.svg)

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
