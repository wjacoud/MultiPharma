/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.OrderDB;
import lapr.project.model.Address;
import lapr.project.model.Order;

/**
 *
 * @author leona
 */
public class LandScenarios {
    
    private ScenarieT scenarieT;
    private double capacity;
    private double scooterPower;
    private double additionalLoad;
    private String vertex_filePath;
    private String edge_filePath;
    private long pharmacyId;
    LinkedList<Address> tmpAddressList = new LinkedList<>();
    
    public void start(){
               
        System.out.println("** Land Scenarios **");
        
        List<Order> ordersList = new ArrayList<>();
        OrderDB orderDB = new OrderDB();
        Order order1 = orderDB.getOrder(4L);
        Order order2 = orderDB.getOrder(5L);
        ordersList.add(order1);     // Centro Comercial Plaza
        ordersList.add(order2);     // Centro Comercial New City
        
        AddressDB addressDB = new AddressDB();
        
/* -- Scenarie T1.1 ----------------------------------------------------------- */
        System.out.println("\n-- T1.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT1.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
        
/* -- Scenarie T2.1 ----------------------------------------------------------- */
        System.out.println("\n-- T2.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT2.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
        
/* -- Scenarie T3.1 ----------------------------------------------------------- */
        System.out.println("\n-- T3.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT3.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
        
/* -- Scenarie T4.1 ----------------------------------------------------------- */
        System.out.println("\n-- T4.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT4.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 50; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);

/* -- Scenarie T5.1 ----------------------------------------------------------- */
        System.out.println("\n-- T5.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT5.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 50; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);

/* -- Scenarie T6.1 ----------------------------------------------------------- */
        System.out.println("\n-- T6.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT6.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);

/* -- Scenarie T7.1 ----------------------------------------------------------- */
        System.out.println("\n-- T7.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\edges_scenarieT7.1.csv";
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, 0L);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
        
/*-- Cenários de Comparação ---------------------------------------------------------------------------------------------- */        
        
/* -- Scenarie C1.0 ----------------------------------------------------------- */
        System.out.println("\n-- C1.0:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 4L; // farmacia sa
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(19L)); // “Posto de Correios de Conde Ferreira”.
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);

/* -- Scenarie C1.1 ----------------------------------------------------------- */
        System.out.println("\n-- C1.1:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 4L; // farmacia sa
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(19L)); // “Posto de Correios de Conde Ferreira”.
        tmpAddressList.add(addressDB.getAddressByID(21L)); // “Alameda Shop & Spot”   
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);

/* -- Scenarie C1.2 ----------------------------------------------------------- */
        System.out.println("\n-- C1.2:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 4L; // farmacia sa
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(20L)); // “Centro Comercial do Carvalhido”;.
        tmpAddressList.add(addressDB.getAddressByID(18L)); // “CTT”  
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
                
/* -- Scenarie C2.0 ----------------------------------------------------------- */
        System.out.println("\n-- C2.0:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 1L; // farmacia sousa torres
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(12L)); // “Posto Correios Forno”
        tmpAddressList.add(addressDB.getAddressByID(11L)); // “Centro de distribuição dos Correios de Ermesinde”        
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);

/* -- Scenarie C2.1 ----------------------------------------------------------- */
//        System.out.println("\n-- C2.1:");
//        vertex_filePath = "\\test_csv\\vertices.csv";
//        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
//        pharmacyId = 4L; 
//        capacity = 2.8;     // kw/h
//        capacity = 0.6*capacity;
//        scooterPower = 3;   // kw
//        additionalLoad = 0; // kg
//        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
//        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
                   
/* -- Scenarie C3.0 ----------------------------------------------------------- */
        System.out.println("\n-- C3.0:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 5L; // farmacia avenida
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(26L)); // “Correios CTT Campo Alegre”
        tmpAddressList.add(addressDB.getAddressByID(17L)); // “Pereiró Post Office”
        tmpAddressList.add(addressDB.getAddressByID(20L)); // “Centro Comercial do Carvalhido” 
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
       
/* -- Scenarie C4.0 ----------------------------------------------------------- */
        System.out.println("\n-- C4.0:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 2L; // Farmácia Guifões
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(10L)); // “Mar Shopping Matosinhos”
        tmpAddressList.add(addressDB.getAddressByID(15L)); // “Centro Comercial New City”
        tmpAddressList.add(addressDB.getAddressByID(14L)); // “Centro Comercial Parque”
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
                 
/* -- Scenarie C5.0 ----------------------------------------------------------- */
        System.out.println("\n-- C5.0:");
        vertex_filePath = "\\test_csv\\vertices.csv";
        edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        pharmacyId = 7L; // farmacia sa da bandeira 
        capacity = 2.8;     // kw/h
        capacity = 0.6*capacity;
        scooterPower = 3;   // kw
        additionalLoad = 0; // kg
        tmpAddressList.clear();
        tmpAddressList.add(addressDB.getAddressByID(30L)); // “Espiral Colossal”
        tmpAddressList.add(addressDB.getAddressByID(28L)); // “Centro Comercial Stop”
        scenarieT = new ScenarieT(vertex_filePath, edge_filePath, pharmacyId);
        scenarieT.run(ordersList, capacity, scooterPower, additionalLoad, tmpAddressList);
                         
    }
}
