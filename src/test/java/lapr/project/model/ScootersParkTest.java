package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ScootersParkTest {

    public ScootersParkTest() {
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
     * Test of getMaxScooterCapacity method, of class ScootersPark.
     */
    @Test
    public void testGetMaxScooterCapacity() {
        System.out.println("getMaxScooterCapacity");
        List<ScooterParkingPlace> list = new ArrayList<>();
        list.add(new ScooterParkingPlace(1L, 2L));
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        long expResult = list.size();
        long result = instance.getMaxScooterCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScootersPark.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScootersPark.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        Object obj = new ScootersPark(1L, 10L, 1L, 1L, list);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScootersPark.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        Object obj = new ScootersPark(2L, 10L, 1L, 1L, list);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScootersPark.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        Scooter obj = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScootersPark.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScootersPark.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ScootersPark.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        List<ScooterParkingPlace> list = new ArrayList<>();
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        System.out.println(instance);
        String expResult = "Park{id=1, voltages=10, addressID=1, pharmacyId=1}\n"
                + " + ScootersPark{listScooterParkingPlace=[]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListScooterParkingPlace method, of class ScootersPark.
     */
    @Test
    public void testGetListScooterParkingPlace() {
        System.out.println("getListScooterParkingPlace");
        List<ScooterParkingPlace> list = new ArrayList<>();
        list.add(new ScooterParkingPlace(1L, 2L));
        ScootersPark instance = new ScootersPark(1L, 10L, 1L, 1L, list);
        List<ScooterParkingPlace> expResult = new ArrayList<>();
        expResult.add(new ScooterParkingPlace(1L, 2L));
        List<ScooterParkingPlace> result = instance.getListScooterParkingPlace();
        assertEquals(expResult, result);
    }

}
