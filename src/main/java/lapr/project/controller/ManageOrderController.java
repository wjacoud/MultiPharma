/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.GraphImporterService;
import lapr.project.data.AddressDB;
import lapr.project.data.OrderDB;
import lapr.project.model.Address;
import lapr.project.model.Order;
import lapr.project.model.Produto;
import lapr.project.utils.Graph;
import lapr.project.utils.Link;

/**
 *
 * @author Duarte
 */
public class ManageOrderController {
    private OrderDB orderdb;
    private AddressDB adb;
    public ManageOrderController() {
        this.orderdb = new OrderDB();
        this.adb= new AddressDB();
    }

    public Order addOrder(long clienteId, ArrayList<Produto> a) {

        try {
        
        Address clientAddress=adb.getAddressByClientID(clienteId);

        HashMap<Long, Address> mapAddr=adb.getAllPharmacyAddress();
        
        double g;
        long fid=0;
        double min=999999999;
        for(Long i: mapAddr.keySet()){
            g=GraphImporterService.distance(mapAddr.get(i).getLatitude(), mapAddr.get(i).getLongitude(), clientAddress.getLatitude(), clientAddress.getLongitude());
            if(min>g){
               min=g;
               fid=i;
           }
        }   
            long newid = orderdb.addOrder(0, clienteId, fid, a);
            Order newOrder = orderdb.getOrder(newid);
            Logger.getLogger(ManageOrderController.class.getName()).log(Level.FINE, null, "\t... Order added with id: " + newOrder.getId());
            return newOrder;
        } catch (IllegalArgumentException iae) {
             Logger.getLogger(ManageOrderController.class.getName()).log(Level.SEVERE, null, iae.getMessage());;
            return null;
        }
    }
    
    
}
