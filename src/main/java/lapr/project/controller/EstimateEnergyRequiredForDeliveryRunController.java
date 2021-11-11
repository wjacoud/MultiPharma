/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.application.EstimateEnergyRequiredForDeliveryRunService;
import lapr.project.data.AddressDB;
import lapr.project.model.Address;
import lapr.project.model.DeliveryRunByScooter;
import lapr.project.model.EnergyDetails;
import lapr.project.model.Order;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import lapr.project.utils.Link;

/**
 *
 * @author leona
 */
public class EstimateEnergyRequiredForDeliveryRunController {
    
    private double DEFAULT_FRONTAL_AREA = 0.3;
    private double DEFAULT_SCOOTER_WEIGHT = 80;
    private double DEFAULT_COURIER_WEIGHT = 50;
    private double DEFAULT_SCOOTER_V = 30;
    
    private AddressDB addressDB = new AddressDB();
    
    private EstimateEnergyRequiredForDeliveryRunService service = new EstimateEnergyRequiredForDeliveryRunService();
    
    public DeliveryRunByScooter simulateDeliveryRun(Graph<Address, Link> graph, List<Order> ordersList){
        
/*-----------------------------------------------------------------------------*/       
        /* Get Pharmacy ID */
        if (ordersList.isEmpty())
            return null;
        
        /* Check whether the orders are from the same farmacy */
        long pharmacyID = ordersList.get(0).getFarmaciaId();
        for(Order order : ordersList){
            if(pharmacyID != order.getFarmaciaId())
                return null;
        }
        
        /* Get Pharmacy address (v_orig = v_dest) */
        Address pharmacyAddress = addressDB.getAddressByPharmacyID(pharmacyID); 
        
        LinkedList<Address> addressList = new LinkedList<>();
        double ordersWeight = 0;
        /* Get orders addresses */
        for(Order order : ordersList){
            long clientId= order.getClienteId();
            Address address = addressDB.getAddressByClientID(clientId); 
            addressList.add(address);
            ordersWeight += order.getPeso();
        }
        if (addressList.isEmpty())
            return null;
        
        /* Get Shortest path */
        LinkedList<Address> shortestPath = new LinkedList<>();
        double totalWeight = DEFAULT_COURIER_WEIGHT + DEFAULT_SCOOTER_WEIGHT + ordersWeight;
        service.computeEdgesWeightByLength(graph, DEFAULT_FRONTAL_AREA, totalWeight, DEFAULT_SCOOTER_V);
        double shortestPathDist = GraphAlgorithms.shortestPathPassingThrough(graph, pharmacyAddress, pharmacyAddress, addressList, shortestPath);
        double shortestPathEnergy = service.estimateEnergySpentForPath(graph, shortestPath);
        
        /* Print path */
//        for(Address address : shortestPath){
//            System.out.print(address.getLocation() + " - ");
//        }System.out.println("\n");
        
        /* Get Most Efficient path */
        LinkedList<Address> mostEfficientPath = new LinkedList<>();
        service.computeEdgesWeightByEnergy(graph, DEFAULT_FRONTAL_AREA, totalWeight, DEFAULT_SCOOTER_V);
        double mostEfficientPathDist = GraphAlgorithms.shortestPathPassingThrough(graph, pharmacyAddress, pharmacyAddress, addressList, mostEfficientPath);
        double mostEfficientPathEnergy = service.estimateEnergySpentForPath(graph, mostEfficientPath);
        
        /* Print path */
//        for(Address address : mostEfficientPath){
//            System.out.print(address.getLocation() + " - ");
//        }System.out.println("\n");      
        
        /* Build of energy details */
        EnergyDetails energyDetailShortestPath = new EnergyDetails(shortestPath, shortestPathEnergy, shortestPathDist);
        EnergyDetails energyDetailMostEfficientPath = new EnergyDetails(mostEfficientPath, mostEfficientPathEnergy, mostEfficientPathDist);
        
        DeliveryRunByScooter deliveryRun = new DeliveryRunByScooter(ordersWeight, energyDetailShortestPath, energyDetailMostEfficientPath);
        
        return deliveryRun;//service.
    }
}
