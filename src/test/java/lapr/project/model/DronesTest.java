package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DronesTest {

    public DronesTest() {
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
     * Test of getId method, of class Drones.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 1L, true, 1L, dronesType);
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBackpackCapacity method, of class Drones.
     */
    @Test
    public void testGetBackpackCapacity() {
        System.out.println("getBackpackCapacity");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 1L, true, 1L, dronesType);
        long expResult = 1L;
        long result = instance.getBackpackCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of isDroneOperating method, of class Drones.
     */
    @Test
    public void testIsDroneOperating() {
        System.out.println("isDroneOperating");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 1L, true, 1L, dronesType);
        boolean expResult = true;
        boolean result = instance.isDroneOperating();
        assertEquals(expResult, result);

        Drones instance2 = new Drones(1L, 1L, false, 1L, dronesType);
        boolean expResult2 = false;
        boolean result2 = instance2.isDroneOperating();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getPharmacyID method, of class Drones.
     */
    @Test
    public void testGetPharmacyID() {
        System.out.println("getPharmacyID");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 1L, true, 1L, dronesType);
        long expResult = 1L;
        long result = instance.getPharmacyID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDronesType method, of class Drones.
     */
    @Test
    public void testGetDronesType() {
        System.out.println("getDronesType");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 1L, true, 1L, dronesType);
        DronesType expResult = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        DronesType result = instance.getDronesType();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Drones.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 1L, true, 1L, dronesType);
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 5L, true, 1L, dronesType);
        Object obj = new Drones(1L, 5L, true, 1L, dronesType);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 5L, true, 1L, dronesType);
        Object obj = new Drones(2L, 5L, true, 1L, dronesType);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 5L, true, 1L, dronesType);
        Drones obj = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 5L, true, 1L, dronesType);
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 5L, true, 1L, dronesType);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Drones.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DronesType dronesType = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        Drones instance = new Drones(1L, 5L, true, 1L, dronesType);
        String expResult = "Drones{id=1, backpackCapacity=5, operatingStatus=true, pharmacyID=1, dronesType=DronesType{id=1, weight=25, averageSpeed=30, frontArea=50, maxBattery=500, powerUnit=350}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
