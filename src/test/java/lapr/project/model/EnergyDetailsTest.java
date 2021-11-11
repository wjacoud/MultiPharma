package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class EnergyDetailsTest {

    public EnergyDetailsTest() {
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
     * Test of getPath method, of class EnergyDetails.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);

        LinkedList<Address> expResult = new LinkedList<>(path);
        LinkedList<Address> result = instance.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalEnergySpent method, of class EnergyDetails.
     */
    @Test
    public void testGetTotalEnergySpent() {
        System.out.println("getTotalEnergySpent");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);

        double expResult = 100.0;
        double result = instance.getTotalEnergySpent();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getTotalDistanceTraveled method, of class EnergyDetails.
     */
    @Test
    public void testGetTotalDistanceTraveled() {
        System.out.println("getTotalDistanceTraveled");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);

        double expResult = 200.0;
        double result = instance.getTotalDistanceTraveled();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of hashCode method, of class EnergyDetails.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);

        Double energy = 100.0;
        Double kms = 200.0;

        int expResult = path.hashCode() + energy.hashCode() + kms.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);
        Object obj = new EnergyDetails(path, 100.0, 200.0);
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
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);
        Object obj = new EnergyDetails(new LinkedList<>(), 100.0, 200.0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new EnergyDetails(path, 101.0, 200.0);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new EnergyDetails(path, 100.0, 201.0);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Scooter obj = null;
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);
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
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);
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
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails instance = new EnergyDetails(path, 100.0, 200.0);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

}
