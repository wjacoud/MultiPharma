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
public class ParkTest {

    public ParkTest() {
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
     * Test of getId method, of class Park.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Park instance = new Park(1L, 100L, 1L, 1L);
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVoltages method, of class Park.
     */
    @Test
    public void testGetVoltages() {
        System.out.println("getVoltages");
        Park instance = new Park(1L, 100L, 1L, 1L);
        long expResult = 100L;
        long result = instance.getVoltages();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddressID method, of class Park.
     */
    @Test
    public void testGetAddressID() {
        System.out.println("getAddressID");
        Park instance = new Park(1L, 100L, 1L, 1L);
        long expResult = 1L;
        long result = instance.getAddressID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyId method, of class Park.
     */
    @Test
    public void testGetPharmacyId() {
        System.out.println("getPharmacyId");
        Park instance = new Park(1L, 100L, 1L, 1L);
        long expResult = 1L;
        long result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Park.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Park instance = new Park(1L, 100L, 1L, 1L);
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Park instance = new Park(1L, 100L, 1L, 1L);
        Object obj = new Park(1L, 100L, 1L, 1L);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Park instance = new Park(1L, 100L, 1L, 1L);
        Object obj = new Park(2L, 100L, 1L, 1L);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Park instance = new Park(1L, 100L, 1L, 1L);
        Park obj = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        Park instance = new Park(1L, 100L, 1L, 1L);
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        Park instance = new Park(1L, 100L, 1L, 1L);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Park.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Park instance = new Park(1L, 100L, 1L, 1L);
        System.out.println(instance);
        String expResult = "Park{id=1, voltages=100, addressID=1, pharmacyId=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
