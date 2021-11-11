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
public class ScooterTypeTest {

    public ScooterTypeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getId method, of class ScooterType.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class ScooterType.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        long expResult = 25L;
        long result = instance.getWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of copyScooterType method, of class ScooterType.
     */
    @Test
    public void testCopyScooterType() {
        System.out.println("copyScooterType");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        ScooterType expResult = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        ScooterType result = instance.copyScooterType();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScooterType.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
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
        Object obj = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
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
        Object obj = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        ScooterType instance = new ScooterType(2L, 25L, 30L, 50L, 500L, 350L);;
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
        ScooterType obj = null;
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
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
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
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
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);;
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ScooterType.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        String expResult = "ScooterType{id=1, weight=25, averageSpeed=30, frontArea=50, maxBattery=500, powerUnit=350}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAverageSpeed method, of class ScooterType.
     */
    @Test
    public void testGetAverageSpeed() {
        System.out.println("getAverageSpeed");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        long expResult = 30L;
        long result = instance.getAverageSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFrontArea method, of class ScooterType.
     */
    @Test
    public void testGetFrontArea() {
        System.out.println("getFrontArea");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        long expResult = 50L;
        long result = instance.getFrontArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxBattery method, of class ScooterType.
     */
    @Test
    public void testGetMaxBattery() {
        System.out.println("getMaxBattery");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        long expResult = 500L;
        long result = instance.getMaxBattery();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPowerUnit method, of class ScooterType.
     */
    @Test
    public void testGetPowerUnit() {
        System.out.println("getPowerUnit");
        ScooterType instance = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        long expResult = 350L;
        long result = instance.getPowerUnit();
        assertEquals(expResult, result);
    }


}
