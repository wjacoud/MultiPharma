package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ScooterParkingPlaceTest {

    public ScooterParkingPlaceTest() {
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
     * Test of getId method, of class ScooterParkingPlace.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCharge method, of class ScooterParkingPlace.
     */
    @Test
    public void testGetCharge() {
        System.out.println("getCharge");
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        long expResult = 20L;
        long result = instance.getCharge();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScooterParkingPlace.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterParkingPlace.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = new ScooterParkingPlace(1L, 20L);
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterParkingPlace.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Object obj = new ScooterParkingPlace(2L, 20L);
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterParkingPlace.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        ScooterParkingPlace obj = null;
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterParkingPlace.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterParkingPlace.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ScooterParkingPlace.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ScooterParkingPlace instance = new ScooterParkingPlace(1L, 20L);
        String expResult = "ScooterParkingPlace{id=1, charge=20}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
