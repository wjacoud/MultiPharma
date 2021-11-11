package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ScooterTest {

    public ScooterTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Scooter.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPharmacyID method, of class Scooter.
     */
    @Test
    public void testGetPharmacyID() {
        System.out.println("getPharmacyID");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        long expResult = 1;
        long result = instance.getPharmacyID();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Scooter.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        Object obj = new Scooter(1L, 5L, true, 1L, scooterType);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        Object obj = new Scooter(1L, 5L, true, 1L, scooterType);
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
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        Object obj = new Scooter(2L, 5L, true, 1L, scooterType);
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
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        Scooter obj = null;
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
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
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
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of getscooterType method, of class Scooter.
     */
    @Test
    public void testGetBackpackCapacity() {
        System.out.println("getBackpackCapacity");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        long expResult = 5L;
        long result = instance.getBackpackCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Scooter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        String expResult = "Scooter{id=1, backpackCapacity=5, operatingStatus=true, pharmacyID=1, scooterType=ScooterType{id=1, weight=25, averageSpeed=30, frontArea=50, maxBattery=500, powerUnit=350}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterType method, of class Scooter.
     */
    @Test
    public void testGetScooterType() {
        System.out.println("getScooterType");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        ScooterType expResult = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        ScooterType result = instance.getScooterType();
        assertEquals(expResult, result);
    }

    /**
     * Test of isDroneOperating method, of class Scooter.
     */
    @Test
    public void testIsDroneOperating() {
        System.out.println("isDroneOperating");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        boolean expResult = true;
        boolean result = instance.isDroneOperating();
        assertEquals(expResult, result);
        
        ScooterType scooterType2 = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance2 = new Scooter(1L, 5L, false, 1L, scooterType);
        boolean expResult2 = false;
        boolean result2 = instance2.isDroneOperating();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of copyDroneType method, of class Scooter.
     */
    @Test
    public void testCopyDroneType() {
        System.out.println("copyDroneType");
        ScooterType scooterType = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        Scooter instance = new Scooter(1L, 5L, true, 1L, scooterType);
        ScooterType expResult = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        ScooterType result = instance.copyDroneType();
        assertEquals(expResult, result);
    }

}
