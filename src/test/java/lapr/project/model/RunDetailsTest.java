package lapr.project.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class RunDetailsTest {

    public RunDetailsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDeliveryDate method, of class RunDetails.
     */
    @Test
    public void testGetDeliveryDate() {
        System.out.println("getDeliveryDate");
        
        Date deliveryDate = new Date(0);
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        double energySpent = 300;
        LinkedList<Address> pathTaken = new LinkedList<>();
        RunDetails instance = new RunDetails(deliveryDate, order, energySpent, pathTaken);
        
        Date expResult = new Date(0);
        Date result = instance.getDeliveryDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrder method, of class RunDetails.
     */
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        Date deliveryDate = new Date(0);
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        double energySpent = 300;
        LinkedList<Address> pathTaken = new LinkedList<>();
        RunDetails instance = new RunDetails(deliveryDate, order, energySpent, pathTaken);
        
        Order expResult = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        Order result = instance.getOrder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergySpent method, of class RunDetails.
     */
    @Test
    public void testGetEnergySpent() {
        System.out.println("getEnergySpent");
        Date deliveryDate = new Date(0);
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        double energySpent = 300;
        LinkedList<Address> pathTaken = new LinkedList<>();
        RunDetails instance = new RunDetails(deliveryDate, order, energySpent, pathTaken);
        
        double expResult = 300;
        double result = instance.getEnergySpent();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPathTaken method, of class RunDetails.
     */
    @Test
    public void testGetPathTaken() {
        System.out.println("getPathTaken");
        Date deliveryDate = new Date(0);
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        double energySpent = 300;
        LinkedList<Address> pathTaken = new LinkedList<>();
        RunDetails instance = new RunDetails(deliveryDate, order, energySpent, pathTaken);
        
        LinkedList<Address> expResult = new LinkedList<>();
        LinkedList<Address> result = instance.getPathTaken();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class RunDetails.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Date deliveryDate = new Date(0);
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        double energySpent = 300;

        LinkedList<Address> pathTaken = new LinkedList<>();
        RunDetails instance =  new RunDetails(deliveryDate, order, energySpent, pathTaken);

        String expResult = "RunDetails{deliveryDate=1970-01-01, order=1, energySpent=300.0\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
