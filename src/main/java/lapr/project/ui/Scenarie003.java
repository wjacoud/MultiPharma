package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import lapr.project.application.AddressImporterCSV;
import lapr.project.application.EstimateEnergyRequiredForDeliveryRunService;
import lapr.project.application.LinkImporterCSV;
import lapr.project.controller.EstimateEnergyRequiredForDeliveryRunController;
import lapr.project.data.OrderDB;
import lapr.project.model.Address;
import lapr.project.model.DeliveryRunByScooter;
import lapr.project.model.Order;
import lapr.project.utils.Graph;
import lapr.project.utils.Link;

/**
 * Criar entregas
 */
public class Scenarie003 {
    
    private final EstimateEnergyRequiredForDeliveryRunService service = new EstimateEnergyRequiredForDeliveryRunService();
    private String full_vertex_filePath;
    private String full_edge_filePath;
    
    
    public Scenarie003() {
        System.out.println("** Land Scenarios **");
    }

    public void run() {
        
        /* Build vertices file path */
        
        String vertex_filePath = "\\test_csv\\vertices.csv";
        full_vertex_filePath = buildFullFilePath(vertex_filePath);
        
        /* Build edges file path */
        String edge_filePath = "\\test_csv\\mainEdgesTerrestres.csv";
        full_edge_filePath = buildFullFilePath(edge_filePath);
 
        /* Build Graph */
        Graph<Address, Link> graph = new Graph<>(false);
        AddressImporterCSV addressImporter = new AddressImporterCSV();
        LinkImporterCSV linkImporter = new LinkImporterCSV();
        service.buildGraphAnew(graph, full_vertex_filePath, full_edge_filePath, addressImporter, linkImporter);
        
        List<Order> ordersList = new ArrayList<>();
        OrderDB orderDB = new OrderDB();
        Order order1 = orderDB.getOrder(3L);
        Order order2 = orderDB.getOrder(2L);
        ordersList.add(order1);
        ordersList.add(order2);
        EstimateEnergyRequiredForDeliveryRunController controller = new EstimateEnergyRequiredForDeliveryRunController();
        DeliveryRunByScooter deliveryRun = controller.simulateDeliveryRun(graph, ordersList);
        
        /* Print path */
        System.out.println("Shortest Path ------------------------------");
        for(Address address : deliveryRun.getByDistance().getPath()){
            System.out.print(address.getLocation() + " - ");
        }
        System.out.println("\nTotal distance: " + deliveryRun.getByDistance().getTotalDistanceTraveled());
        System.out.println("Total energy spent: " + deliveryRun.getByDistance().getTotalEnergySpent());
        
        /* Print path */
        System.out.println("\nMost Efficient Path ------------------------------");
        for(Address address : deliveryRun.getByEnergySpent().getPath()){
            System.out.print(address.getLocation() + " - ");
        }
        System.out.println("\nTotal distance: " + deliveryRun.getByEnergySpent().getTotalDistanceTraveled());
        System.out.println("Total energy spent: " + deliveryRun.getByEnergySpent().getTotalEnergySpent());
    }

    private String buildFullFilePath(String filePath){
        return String.format("%s%s", System.getProperty("user.dir"), filePath);
    }
}