package lapr.project.ui;

import lapr.project.data.DataHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
 import lapr.project.application.GraphImporterService;
import java.util.logging.Logger;
import lapr.project.controller.ManageFaturaController;
import lapr.project.model.Address;
import lapr.project.data.AddressDB;
import lapr.project.controller.NotifyClienteController;
import lapr.project.data.AddressDB;
import lapr.project.data.OrderDB;
import lapr.project.model.Address;
import lapr.project.model.Produto;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        /*CalculatorExample calculatorExample = new CalculatorExample();
        int value = calculatorExample.sum(3, 5);

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, String.valueOf(value));
        }*/

        //load database properties
        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initial Database Setup
        DataHandler dh = new DataHandler();
        //
        dh.scriptRunner("target/classes/Create_Tables.sql"); // Criacao das tabelas
        dh.scriptRunner("target/classes/Queries.sql"); // Criacao dos procedimentos
        dh.scriptRunner("target/classes/Inserts.sql"); // Criacao dos inserts nas tabelas

        
        ScenariosManager scm=new ScenariosManager();
        scm.buildScenarios();
        //System.out.println("\nBase de dados criada ...");

                // Adiciona scooter 
//        Scooter sc = new Scooter(1, 100, 1, 20);
//        sc.save();
//        System.out.println("\t... Scouter Adicionado.");
//        
//        // Adiciona cliente 
//        Cliente cliente = new Cliente(1 ,"Wilson" , 00, 1, 1, 1);
//        cliente.save();
//        System.out.println("\t... Cliente Adicionado.");
        
        // Adiciona cartao 
//        AssociateCartaoCreditoToClientRegistryController addCartaoCreditoController = new AssociateCartaoCreditoToClientRegistryController();
//        long cartaoCreditoDB = addCartaoCreditoController.addCartaoCredito(44445555 , 456, Date.valueOf("2021-01-01"));
//        CartaoCredito cartaoCredito = addCartaoCreditoController.getCartaoCredito(cartaoCreditoDB);
//        System.out.println(cartaoCredito.toString());
        
        
        // Adiciona  morada 
//        AssociateAddressToClientRegistryController addAddressController = new AssociateAddressToClientRegistryController();
//        addAddressController.addAddress("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350);
//        Address address = addAddressController.
//        
//        ManageScooterController msc = new ManageScooterController();
//        msc.addScooter(100, 1, 1);*/
 /*CourierDB courierDB = new CourierDB();
        courierDB.addCourier(new Courier("Sebastiao", 1234567, 1234567, 120, "SuperFast", "pass123", "fake@news.us"));
         */
        //ManageDeliveryRun mdr = new ManageDeliveryRun();
        //LinkedList<Address> finalPathOfDelivered = new LinkedList<>();
        //double distancia = mdr.getOptimiseDeliveryPath(finalPathOfDelivered, 1L, 10.0, 100.0);
        //Date dateUso = new Date(Calendar.getInstance().getTime().getTime());
        //mdr.makeDeliveryRun(finalPathOfDelivered, dateUso, dateUso, 1, 1, 4, 20);
//        Graph<Address, String> mapaPorto = new Graph<>(false);
//        
//        Address Trindade = new Address(1L, "Portugal", "Porto",  "Trindade", "4420-000", 41.15227, -8.60929);
//        Address Clerigos = new Address(2L, "Portugal", "Porto",  "Clerigos", "4420-000", 41.14582, -8.61398);
//        Address Majestic = new Address(3L, "Portugal", "Porto",  "Majestic", "4420-000", 41.14723, -8.60657);
//        Address Bolhao = new Address(4L, "Portugal", "Porto",  "Bolhao", "4420-000", 41.14871, -8.60746);
//        Address Se = new Address(5L, "Portugal", "Porto",  "Se", "4420-000", 41.14331, -8.60914);
//        Address CaisDaRibeira = new Address(6L, "Portugal", "Porto",  "Cais da Ribeira", "4420-000", 41.14063, -8.61118);
//        
//        mapaPorto.insertVertex(Trindade);
//        mapaPorto.insertVertex(Clerigos);
//        mapaPorto.insertVertex(Majestic);
//        mapaPorto.insertVertex(Bolhao);
//        mapaPorto.insertVertex(Se);
//        mapaPorto.insertVertex(CaisDaRibeira);
//        
//        mapaPorto.insertEdge(Trindade, Clerigos, "Trindade - Clerigos", 818);
//        mapaPorto.insertEdge(Clerigos, Bolhao, "Clerigos - Bolhao", 633);
//        mapaPorto.insertEdge(Clerigos, Majestic, "Clerigos - Majestic", 640);
//        mapaPorto.insertEdge(Bolhao, CaisDaRibeira, "Bolhao - Cais da Ribeira", 950);
//        mapaPorto.insertEdge(Majestic, CaisDaRibeira, "Majestic - Cais da Ribeira", 829);
//
//        ArrayList<Address> stops = new ArrayList<>();
//        stops.add(CaisDaRibeira);
//        stops.add(Bolhao);
//        
//        LinkedList<Address> finalPath = new LinkedList<>();
//        double result = GraphAlgorithms.findOptimiseWithCustomeStops(mapaPorto, Majestic, Majestic, stops, finalPath);
//        System.out.println(result);

//        ManageCourierController msc = new ManageCourierController();
//        Courier newCourier = msc.addCourier("nome1", 1234211, 1234211, 100, "carlos@a.COM", "qyets");
//        System.out.println(newCourier.getId());
//        System.out.println("End of program!");

//        ManageDeliveryRunController mdr = new ManageDeliveryRunController();
//        double kms = mdr.getOptimiseDeliveryPath();
        //System.out.println(kms);
        
//        Scenarie001 sc001 = new Scenarie001();
//        System.out.println(sc001.run());

        //Scenarie002 sc002 = new Scenarie002();
        //sc002.run();
        
        
    }
}
