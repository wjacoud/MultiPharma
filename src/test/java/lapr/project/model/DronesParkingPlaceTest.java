/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class DronesParkingPlaceTest {
    
    public DronesParkingPlaceTest() {
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
     * Test of getId method, of class DronesParkingPlace.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCharge method, of class DronesParkingPlace.
     */
    @Test
    public void testGetCharge() {
        System.out.println("getCharge");
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
        long expResult = 20L;
        long result = instance.getCharge();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class DronesParkingPlace.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
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
        Object obj = new DronesParkingPlace(1L, 20L);
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
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
        Object obj = new DronesParkingPlace(2L, 20L);
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
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
        DronesParkingPlace obj = null;
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
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
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
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
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class DronesParkingPlace.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DronesParkingPlace instance = new DronesParkingPlace(1L, 20L);
        String expResult = "DronesParkingPlace{id=1, charge=20}";
        System.out.println(instance);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
