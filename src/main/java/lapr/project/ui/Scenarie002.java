package lapr.project.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lapr.project.application.GraphImporterService;
import lapr.project.application.LinkImporter;
import lapr.project.controller.ManageFaturaController;
import lapr.project.controller.ManageOrderController;
import lapr.project.controller.NotifyClienteController;
import lapr.project.data.AddressDB;
import lapr.project.model.Address;
import lapr.project.model.Fatura;
import lapr.project.model.Order;
import lapr.project.utils.Graph;
import lapr.project.utils.Link;

/**
 * Criar ordens 
 */
public class Scenarie002 {
    
    //private String edgesFilePath=String.format("%s%s", System.getProperty("user.dir"), "/test_csv/edges.csv");
    
    public Scenarie002() {
    }
    
    public boolean run() {
        MakeOrderImporterCSV oCSV = new MakeOrderImporterCSV();
        MakeOrderImporterService oService = new MakeOrderImporterService();
        
        
        
        List<Order> lo= oService.importOrder(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/orders.csv"), oCSV);
        //System.out.println(lo.size());
        ManageOrderController mO= new ManageOrderController();
        ManageFaturaController mF=new ManageFaturaController();
        NotifyClienteController ncc=new NotifyClienteController();
        
        for(Order o: lo){
        
        Order newO= mO.addOrder(o.getClienteId(), o.getProdList());
            System.out.println(newO.getId());
        Fatura f= mF.addFatura(newO.getId());
          System.out.println(f.getId());
        ncc.notifyClienteOrderSubmit(newO.getId(), f.getId());
                
        }
        
        return true;
    }
}
