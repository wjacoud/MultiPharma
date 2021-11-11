/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.LinkedList;
import java.util.List;
import lapr.project.application.AddressImporterCSV;
import lapr.project.application.EstimateEnergyRequiredForDeliveryRunService;
import lapr.project.application.LinkImporterCSV;
import lapr.project.data.AddressDB;
import lapr.project.model.Address;
import lapr.project.model.Order;
import lapr.project.utils.Edge;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import lapr.project.utils.Link;

/**
 *
 * @author leona
 */
public class ScenarieT {
    /*
    * Fixed Parameters:
    */
    private final double DEFAULT_MASS_SCOOTER = 50;
    private final double DEFAULT_MASS_COURIER = 80;
    private final double DEFAULT_SCOOTER_MEAN_VELOCITY = 30;
    private final double DEFAULT_MECHANICAL_PERFORMANCE = 100;
    private final double DEFAULT_ELECTRIC_PERFORMANCE = 100;
    private final double PAYLOAD_LIMIT = 25;
    private final double DEFAULT_FRONTAL_AREA = 0.3;
    private final double DEFAULT_AERODYNAMIC_RESIT_COEFFICIENT = 1.1;
    
    
    
    private final EstimateEnergyRequiredForDeliveryRunService service = new EstimateEnergyRequiredForDeliveryRunService();
    private final AddressDB addressDB = new AddressDB();
    private final String full_vertex_filePath;
    private final String full_edge_filePath;
    private long pharmacy_Id;
    
    public ScenarieT(String vertex_filePath, String edge_filePath, long pharmacyID) {
        
        /* Build vertices file path */
        full_vertex_filePath = buildFullFilePath(vertex_filePath);
        
        /* Build edges file path */
        full_edge_filePath = buildFullFilePath(edge_filePath);
 
        pharmacy_Id = pharmacyID;
        /**
         * Variable Parameters:
         * 
         * Scooter power = 3kw
         * Total distance = 15km
         * Sope = 0%
         * Crr = 0.5
         * Wind speed = 0
         * Wind angle = 0
         * Scooter capacity = 60%
         */
    }
 
    public void run(List<Order> ordersList, double capacity, double scooterPower, double additionalLoad, LinkedList<Address> tmpAddressList){
        
        /* 1. Build Graph with edge_scenarieT1.1 */
        Graph<Address, Link> graph = new Graph<>(false);
        AddressImporterCSV addressImporter = new AddressImporterCSV();
        LinkImporterCSV linkImporter = new LinkImporterCSV();
        service.buildGraphAnew(graph, full_vertex_filePath, full_edge_filePath, addressImporter, linkImporter);
        
        /* 2. Check whether the orders are from the same farmacy */
        if (pharmacy_Id == 0L){
            pharmacy_Id = ordersList.get(0).getFarmaciaId();
            for(Order order : ordersList){
                if(pharmacy_Id != order.getFarmaciaId())
                    return;
            }
        }
        
        /* 3. Get Pharmacy address (v_orig = v_dest) */
        Address pharmacyAddress = addressDB.getAddressByPharmacyID(pharmacy_Id); 
        
        /* 4. Get orders addresses */        
        LinkedList<Address> addressList = new LinkedList<>();
        double ordersWeight = 0;
        if(tmpAddressList.isEmpty()){
            for(Order order : ordersList){
                long clientId= order.getClienteId();
                Address address = addressDB.getAddressByClientID(clientId); 
                addressList.add(address);
                ordersWeight += order.getPeso();
            }
            if (addressList.isEmpty())
                return;
        }else
            addressList = tmpAddressList;
        
       
        /* 5. Get Shortest path */
        LinkedList<Address> shortestPath = new LinkedList<>();
        double totalWeight = DEFAULT_MASS_COURIER + DEFAULT_MASS_SCOOTER + ordersWeight + additionalLoad;
        service.computeEdgesWeightByLength(graph, DEFAULT_FRONTAL_AREA, totalWeight, DEFAULT_SCOOTER_MEAN_VELOCITY);
        double shortestPathDist = GraphAlgorithms.shortestPathPassingThrough(graph, pharmacyAddress, pharmacyAddress, addressList, shortestPath);
        double shortestPathEnergy = service.estimateEnergySpentForPath(graph, shortestPath);
        
        System.out.print("Shortest path: ");
        printPath(shortestPath);
        System.out.printf("Total distance = %.2f Km\nEnergy Required: %.2f Wh\n\n", shortestPathDist/1000, shortestPathEnergy);
        
        /* 6. Get Most Efficient path */
        LinkedList<Address> mostEfficientPath = new LinkedList<>();
        service.computeEdgesWeightByEnergy(graph, DEFAULT_FRONTAL_AREA, totalWeight, DEFAULT_SCOOTER_MEAN_VELOCITY);
        GraphAlgorithms.shortestPathPassingThrough(graph, pharmacyAddress, pharmacyAddress, addressList, mostEfficientPath);
        double mostEfficientPathDist = estimateTotalPathDistance(graph, mostEfficientPath);
        double mostEfficientPathEnergy = service.estimateEnergySpentForPath(graph, mostEfficientPath);
        
        System.out.print("Most efficient path: ");
        printPath(mostEfficientPath);
        System.out.printf("Total distance = %.2f Km\nEnergy Required: %.2f Wh\n\n", mostEfficientPathDist/1000, mostEfficientPathEnergy);
        
        /* 7. Check if simulation can be perfomed... */
        double time = capacity/scooterPower;
        double scooterRange = (time*DEFAULT_SCOOTER_MEAN_VELOCITY)*(DEFAULT_ELECTRIC_PERFORMANCE/100);
        System.out.printf("Scooter's range: %.2f Km\n", scooterRange);   
        
        if (ordersWeight <= PAYLOAD_LIMIT && ( (shortestPathDist/1000) < scooterRange || (mostEfficientPathDist/1000) < scooterRange) )
            System.out.println("Result: Delivery is possible!");
        else
            System.out.println("Result: Delivery is not possible!");
        
//        for(Edge<Address, Link> edge : graph.edges()){
//            Link link = edge.getElement();
//            System.out.println(String.format("%s - %s \tdistance: %.3f", edge.getVOrig().getLocation(), edge.getVDest().getLocation(), link.getLength()/1000));
//        }
        
    }
    
    private String buildFullFilePath(String filePath){
        return String.format("%s%s", System.getProperty("user.dir"), filePath);
    }
    
    private void printPath(LinkedList<Address> path){
        for(Address address : path)
            System.out.print(address.getLocation()+" - ");
        System.out.println("");
    }
  
    private double estimateTotalPathDistance(Graph<Address, Link> graph, LinkedList<Address> path){
        double totalDistance = 0;
        int i;
        for(i=0; i<path.size()-1; i++){
            Edge<Address, Link> edge = graph.getEdge(path.get(i), path.get(i+1));
            Link link = edge.getElement();
            totalDistance += link.getLength();
        }
        return totalDistance;
    }
}
