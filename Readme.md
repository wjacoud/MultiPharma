# ISEP - Instituto Superior de Engenharia do Porto
# Lab/Project 3 [LAPR3] - 2020/2021

# Sistema de gestão de produtos, clientes, encomendas e entregas para uma companhia farmacêutica

## Membros de equipa - GRUPO 10

- Carlos Moutinho – 1140858
- Duarte Valente – 1181489
- Leonardo Coelho – 1171317
- Wilson Junior – 1200630

## Prefácio
Serve o presente relatório para apresentar o trabalho desenvolvido no âmbito da cadeira de
laboratório de projeto do terceiro ano do curso de licenciatura em engenharia informática.
Este relatório aborda de um modo geral algumas metodologias usadas no desenvolvimento de software,
quer a nível pessoal quer em grupo. Os itens apresentados a seguir foram submetidos a processos
e conceitos distintos de acordo com as melhores práticas da engenharia de software.

    - Levantamento de Requisitos
    - Análise dos Requisitos
    - Design e Modelação
    - Implementação de classes, métodos, controladores e testes.
    - Execução dos testes e correção de falhas.
    - Composição e organização da documentação.

Foi utilizada a metodologia Scrum na gestão do projeto, onde o mesmo foi dividido em
três sprints e mais de 150 user stories. A seguir é possível ter acesso ao conteúdo de cada uma delas.
    
## Índice

- [Modelo Dominio](DomainModel/domain model.svg)
- [Modelo Relacional](RelationalModel/LAPR RelationalModel.svg)
- [Use Case Diagram](UseCaseDiagram/UseCaseDiagram.svg)
- [Sprint Reviews](Sprints/SprintReviews.md)
- [User Stories](UserStories/*)

## User Stories

- [US 1 - Criar um novo  registo de Cliente](UserStories/SignUpController/ProcessoEngenhariaFuncionalidade.md)
- [US 3 - Novo Cliente](UserStories/AddNewClient/ProcessoEngenhariaFuncionalidade.md)
- [US 4 - Associar uma Morada e respetivas coordenadas GPS ao registo de um novo CLiente](UserStories/AssociateAddressToClientRegistry/ProcessoEngenhariaFuncionalidade.md)
- [US 5 - Associar um cartão de crédito a um novo CLiente](UserStories/AssociateCreditCardRegistry/ProcessoEngenhariaFuncionalidade.md)
- [US 7 - Criar Parques e associar à Farmácia](UserStories/AddParkToPharmacy/ProcessoEngenhariaFuncionalidade.md)
- [US 19 - Mostrar Creditos de um cliente](UserStories/SeeClientCredits/ProcessoEngenhariaFuncionalidade.md)
- [US 20 - Definir uma taxa de entrega](UserStories/AddNewTaxaEntrega/ProcessoEngenhariaFuncionalidade.md)
- [US 22 - Importação da Estimativa de tempo para carga completa de um arquivo CSV](UserStories/ImportTimeEstimateFromCSVfile/ProcessoEngenhariaFuncionalidade.md)
- [US 23 - Associar Scooter a uma farmácia](UserStories/AssotiateScooterToPharmacy/ProcessoEngenhariaFuncionalidade.md)
- [US 24 - Update Scooter](UserStories/UpdateScooter/ProcessoEngenhariaFuncionalidade.md)
- [US 25 - Remove Scooter](UserStories/RemoveScooter/ProcessoEngenhariaFuncionalidade.md)
- [US 26 - Registar um novo estafeta](UserStories/RegisterNewCourier/ProcessoEngenhariaFuncionalidade.md)
- [US 34 - Importar grafo a partir de ficheiros CSV](UserStories/ImportGraphFromCSVfiles/ProcessoEngenhariaFuncionalidade.md)
- [US 39 - Optimizar rota de entrega](UserStories/OptimiseTheDeliveryPath/ProcessoEngenhariaFuncionalidade.md)
- [US 41 - Estimat energia necessária para realizar uma Delivery Run](UserStories/EstimateAmountOfEnergyRequiredToPerformDeliveryRun/ProcessoEngenhariaFuncionalidade.md)
- [US 46 - Executar uma entrega](UserStories/MakeDeliveryRun/ProcessoEngenhariaFuncionalidade.md)
- [US 47 - Implementar Componente C e Modulos Assembly](UserStories/DockSimulator/ProcessoEngenhariaFuncionalidade.md)
- [US 59 - Adicionar uma nova Farmácia](UserStories/AddNewPharmacy/ProcessoEngenhariaFuncionalidade.md)
- [US 60 - Adicionar um novo produto](UserStories/AddNewProduto/ProcessoEngenhariaFuncionalidade.md)
- [US 64 - Fazer um pedido](UserStories/MakeOrder/ProcessoEngenhariaFuncionalidade.md)
- [US 77 - Associar um utilizador ao registo](UserStories/AssociateUserToClientRegistry/ProcessoEngenhariaFuncionalidade.md)
- [US 78 - Calcular estimativa de tempo de carregamento](UserStories/EstimateTimeForFullCharge/ProcessoEngenhariaFuncionalidade.md)
- [US 89 - Atualizar um produto em estoque](UserStories/UpdateStockProduto/ProcessoEngenhariaFuncionalidade.md)
- [US 98 - Notificar o Estafeta com uma estimativa do tempo restante para carregamento completo](UserStories/NotifyCourierOfTimeEstimateToFullCharge/ProcessoEngenhariaFuncionalidade.md)
- [US 158 - Remover um Drone da Farmacia](UserStories/RemoveDrones/ProcessoEngenhariaFuncionalidade.md)
- [US 159 - Atualizar uma Scooter da Farmacia](UserStories/UpdateDrone/ProcessoEngenhariaFuncionalidade.md)
- [US 162 - Associar Drone a uma Farmacia](UserStories/AssotiateDronesToPharmacy/ProcessoEngenhariaFuncionalidade.md)
