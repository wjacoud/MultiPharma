# **Aluno [1140858](../)** - US-7 - Crie Parques e associar à Farmácia correta

# 1. Requisitos

_Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos._

**US-7** Como administrador, quero inserir as informações relacionadas a um parque e associar esse parque a uma farmácia assim esse parque pode ser usado por correio ou drones.

Esta é uma user storie relativamente mais complexa que as user stories que fiz anteriormente, ex adicionar scooters e adicionar courier.
Ao adicionar o parque a uma farmacia temos em primeiro lugar decidir que tipo de parque é que queremos atribuir, esse parque pode ser para estacionar e guardar scooters ou pode ser para estacionar e guardar drones.
Actualmente no caderno de encargos não existe grande varidade de atributos de um parque para outro mas da forma como esta estruturado na base de dados, estamos precavidos para futuras alterações.
Ao criarmos um parque, temos que definir quantos lugares de estacionamento iremos criar.
Para tal temos uma entidade lugar estacionamento scooter que ira existir em numeros iguais ao numero de lugares de estacionamento que indicamos ao criar um parque.

# 2. Análise

_Neste secção o estudante deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados._

_Recomenda-se que organize este conteúdo por subsecções._

Criamos uma classe de controlador para criar e gerir os parques.
Nesta user case foi criado o metodo de adicionar parques de scooters e parques de drones.
Foi criada uma classe de parques com os identificadores e atributos dos parques.
Foi criada uma classe que extende a class parque para identificar os parques de scooters e os parques dos drones.
Foi criada uma classe de lugar de estacionamento de scooters e uma classe de lugar de estacionamento de drones.
O controller cria um DataHandler específico para lidar com os metodos relacionados com os parques em geral.
Enviamos a informação referente ao parque e o seu tipo para o datahandler e recebemos o id que a base de dados atribuiu ao objecto, e ai criamos um novo parque.


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

**Teste 1:**
    ...

## 4.2. Código
Promenores de Java
Existe uma classe Mae, a classe Parques que tem a informação comum aos dois tipos diferentes de parques.
Existe uma classe ScooterPark e DronesPark que extende a class Parques.
Cada class ScooterPark e DronesParks tem uma lista de lugares de estacionamento, ScooterParkingPlace e DronesParkingPlace.
Esta lista representa os lugares de estacionamento usados.

Promenores de SQL

# 5. Integração

_Nesta secção o estudante deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema._

# 6. Observações

_Nesta secção sugere-se que o estudante apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados._
