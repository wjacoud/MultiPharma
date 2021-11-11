//package lapr.project.controller;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import lapr.project.data.AddressDB;
//import lapr.project.data.DeliveryDB;
//import lapr.project.data.OrderDB;
//import lapr.project.data.ScooterDB;
//import lapr.project.model.Address;
//import lapr.project.model.DeliveryByScooter;
//import lapr.project.model.Order;
//import lapr.project.model.Scooter;
//import lapr.project.model.ScooterType;
//import lapr.project.utils.Graph;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import org.mockito.MockitoAnnotations;
//
///**
// * @author Carlos Moutinho <1140858@isep.ipp.pt>
// */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ManageDeliveryRunControllerTest {
//
//    /*private Graph<Address, String> expectedGraph;
//    private HashMap<Long, Address> expectedAddressHashMap;
//    private HashMap<Long, Long> expectedAddressByClientHashMap;*/
//    @Mock
//    private AddressDB addressDB;
//    @Mock
//    private OrderDB orderDB;
//    @Mock
//    private DeliveryDB deliveryDB;
//    @Mock
//    private DeliveryByScooter deliveryByScooter;
//
//    @InjectMocks
//    private ManageDeliveryRunController instance;
//
//    public ManageDeliveryRunControllerTest() {
//        instance = new ManageDeliveryRunController();
//    }
//
//    @BeforeAll
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        HashMap<Long, Address> addressHashMap = new HashMap<>();
//        Address address = new Address(1L, "Portugal", "Distrito", "Porto", "4420-222", 41.0, -8.0);
//        when(addressDB.getAllAddress()).thenReturn(addressHashMap);
//        when(addressDB.getAddressByClientID(1L)).thenReturn(address);
//        
//        ArrayList<Order> listOrder = new ArrayList<>();
//        when(orderDB.getOrdersToBeDelivered(1L)).thenReturn(listOrder);
//        when(deliveryByScooter.getOptimiseDeliveryPath()).thenReturn(0.0);
//    }
//
//    /**
//     * Test of getOptimiseDeliveryPath method, of class
//     * ManageDeliveryRunController.
//     */
//    @Test
//    public void testGetOptimiseDeliveryPath() {
//        System.out.println("getOptimiseDeliveryPath");
//        double expResult = 0.0;
//        double result = instance.getOptimiseDeliveryPath();
//        assertEquals(expResult, result, 0.0);
//    }
//
//    /**
//     * Test of makeDeliveryRun method, of class ManageDeliveryRunController.
//     */
//    @Test
//    public void testMakeDeliveryRun() {
//        System.out.println("makeDeliveryRun");
//        LinkedList<Address> finalPathOfDelivered = null;
//        Date dateUso = null;
//        Date dataFim = null;
//        long scooterID = 0L;
//        long estafetaID = 0L;
//        long pesoTotal = 0L;
//        long energiaGasta = 0L;
//        ManageDeliveryRunController instance = new ManageDeliveryRunController();
//        instance.makeDeliveryRun(finalPathOfDelivered, dateUso, dataFim, scooterID, estafetaID, pesoTotal, energiaGasta);
//    }
//
//}
