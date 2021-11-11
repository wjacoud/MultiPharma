package lapr.project.model;

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
public class DeliveryRunByScooterTest {

    public DeliveryRunByScooterTest() {
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
     * Test of getTotalWeightOfOrders method, of class DeliveryRunByScooter.
     */
    @Test
    public void testGetTotalWeightOfOrders() {
        System.out.println("getTotalWeightOfOrders");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        double expResult = 20.0;
        double result = instance.getTotalWeightOfOrders();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getByDistance method, of class DeliveryRunByScooter.
     */
    @Test
    public void testGetByDistance() {
        System.out.println("getByDistance");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        EnergyDetails expResult = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails result = instance.getByDistance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getByEnergySpent method, of class DeliveryRunByScooter.
     */
    @Test
    public void testGetByEnergySpent() {
        System.out.println("getByEnergySpent");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        EnergyDetails expResult = new EnergyDetails(path, 200.0, 100.0);
        EnergyDetails result = instance.getByEnergySpent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourier method, of class DeliveryRunByScooter.
     */
    @Test
    public void testGetCourier() {
        System.out.println("getCourier");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        instance.setCourier(new Courier(1L, "ename", 123456789, 123456789, 100, "email@sapo.pt", "qwerty"));

        Courier expResult = new Courier(1L, "ename", 123456789, 123456789, 100, "email@sapo.pt", "qwerty");
        Courier result = instance.getCourier();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooter method, of class DeliveryRunByScooter.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        instance.setScooter(new Scooter(1L, 100L, true, 1L, new ScooterType(1L, 100L, 100L, 100L, 100L, 100L)));

        Scooter expResult = new Scooter(1L, 100L, true, 1L, new ScooterType(1L, 100L, 100L, 100L, 100L, 100L));
        Scooter result = instance.getScooter();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCourier method, of class DeliveryRunByScooter.
     */
    @Test
    public void testSetCourier() {
        System.out.println("setCourier");
        Courier courier = new Courier(1L, "ename", 123456789, 123456789, 100, "email@sapo.pt", "qwerty");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        instance.setCourier(courier);
        assertEquals(courier, instance.getCourier());
    }

    /**
     * Test of setScooter method, of class DeliveryRunByScooter.
     */
    @Test
    public void testSetScooter() {
        System.out.println("setScooter");
        Scooter scooter = new Scooter(1L, 100L, true, 1L, new ScooterType(1L, 100L, 100L, 100L, 100L, 100L));
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        LinkedList<Address> path = new LinkedList<>();
        path.add(address);
        EnergyDetails byDistance = new EnergyDetails(path, 100.0, 200.0);
        EnergyDetails byEnergySpent = new EnergyDetails(path, 200.0, 100.0);
        DeliveryRunByScooter instance = new DeliveryRunByScooter(20.0, byDistance, byEnergySpent);
        instance.setScooter(scooter);
        assertEquals(scooter, instance.getScooter());
    }

}
