package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.Address;
import lapr.project.model.CartaoCredito;
import lapr.project.model.Cliente;
import lapr.project.model.Order;
import lapr.project.model.Produto;
import lapr.project.model.User;

public class MakeOrderImporterService {

    // Order attributes
    private final static int CID_INDEX = 0;
//    private final static int FID_INDEX = 1;

    // Product attributes
    private final static int PID_INDEX = 1;
    private final static int PQTD_INDEX = 2;
    

    //private List<Cliente> listClient = new ArrayList<>();
    //private List<Address> listAddress = new ArrayList<>();
    //private List<CartaoCredito> listCreditCard = new ArrayList<>();
    //private List<User> listUser = new ArrayList<>();

    public List<Order> importOrder(String filename, Importer importer) {
        int numberLines = 1;
        List<Order> oList= new ArrayList<>();
        try {
            
            importer.begin(filename);
            

//            
//            Order o=new Order();
//            Produto p;
            String[] line;
            if(importer.hasNextElement()){
                line = importer.readElement();
                Order o = new Order(Long.parseLong(line[CID_INDEX]), new ArrayList<>());
                Produto p = new Produto(Long.parseLong(line[PID_INDEX]), Long.parseLong(line[PQTD_INDEX]));
                o.addProdToList(p);
                oList.add(o);
                System.out.println(numberLines);
                System.out.println(o.getClienteId()+" "+o.getFarmaciaId()+" "+p.getId()+" "+ p.getQtd());
            
            
                while (importer.hasNextElement()) {
                    numberLines++;
                    System.out.println(numberLines);
                    
                    //String[] line = importer.readElement();
                    line = importer.readElement();


                    if (Long.parseLong(line[CID_INDEX]) == o.getClienteId()) {
                        p = new Produto(Long.parseLong(line[PID_INDEX]), Long.parseLong(line[PQTD_INDEX]));
                        o.addProdToList(p);
                    } else {
                        o = new Order(Long.parseLong(line[CID_INDEX]), new ArrayList<>());
                        p = new Produto(Long.parseLong(line[PID_INDEX]), Long.parseLong(line[PQTD_INDEX]));
                        o.addProdToList(p);
                        oList.add(o);
                    }

    //                
    //                if (Long.parseLong(line[FID_INDEX]) == farmacia_id && Long.parseLong(line[CID_INDEX]) == cliente_id) {
    //                    p = new Produto(Long.parseLong(line[PID_INDEX]), Long.parseLong(line[PQTD_INDEX]));
    //                    o.addProdToList(p);
    //                } else {
    //                    o = new Order(Long.parseLong(line[CID_INDEX]), Long.parseLong(line[FID_INDEX]), new ArrayList<>());
    //                    p = new Produto(Long.parseLong(line[PID_INDEX]), Long.parseLong(line[PQTD_INDEX]));
    //                    o.addProdToList(p);
    //                    oList.add(o);
    //                }
    //                
                    //farmacia_id = Long.parseLong(line[FID_INDEX]);
                    //cliente_id=Long.parseLong(line[CID_INDEX]);

                    System.out.println(o.getClienteId()+" "+p.getId()+" "+ p.getQtd());

                }
            }
            System.out.println(oList.size());
            importer.end();
            
        } catch (final IOException e) {
            Logger.getLogger(MakeOrderImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return oList;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

//    public List<Cliente> getListClient() {
//        return new ArrayList<>(listClient);
//    }
//
//    public List<Address> getListAddress() {
//        return new ArrayList<>(listAddress);
//    }
//
//    public List<CartaoCredito> getListCreditCard() {
//        return new ArrayList<>(listCreditCard);
//    }
//
//    public List<User> getListUser() {
//        return new ArrayList<>(listUser);
//    }

}
