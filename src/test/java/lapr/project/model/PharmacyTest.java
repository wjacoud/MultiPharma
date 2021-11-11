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
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class PharmacyTest {

    public PharmacyTest() {
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
     * Test of getId method, of class Pharmacy.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        long expResult = 10;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Pharmacy.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        String expResult = "Routeirinho";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNipc method, of class Pharmacy.
     */
    @Test
    public void testGetNipc() {
        System.out.println("getNipc");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        long expResult = 55555;
        long result = instance.getNipc();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMaxScooters method, of class Pharmacy.
     */
    @Test
    public void testGetMaxScooters() {
        System.out.println("getNipc");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        long expResult = 1;
        long result = instance.getMaxScooters();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMaxDrones method, of class Pharmacy.
     */
    @Test
    public void testGetMaxDrones() {
        System.out.println("getNipc");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        long expResult = 1;
        long result = instance.getMaxDrones();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMorada method, of class Pharmacy.
     */
    @Test
    public void testGetMorada() {
        System.out.println("getMorada");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        long expResult = 1;
        long result = instance.getMorada();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Pharmacy.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        Long id = 10L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Object obj = new Pharmacy(12L, "Routeirinho2", 55555, 1, 1, 1, 50, 10);
        Pharmacy instance =new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Pharmacy obj = null;
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals06() {
        System.out.println("equals06");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals07() {
        System.out.println("equals07");
        Object obj = new Pharmacy(10L, "Routeirinho2", 55555, 1, 1, 1, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals08() {
        System.out.println("equals08");
        Object obj = new Pharmacy(10L, "Routeirinho", 55554, 1, 1, 1, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals09() {
        System.out.println("equals09");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 2, 1, 1, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals10() {
        System.out.println("equals10");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 1, 2, 1, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals11() {
        System.out.println("equals11");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 1, 50, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals12() {
        System.out.println("equals13");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 60, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals13() {
        System.out.println("equals14");
        Object obj = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 50, 10);
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 50, 20);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxCreditos method, of class Pharmacy.
     */
    @Test
    public void testGetMaxCreditos() {
        System.out.println("getMaxCreditos");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 50, 20);
        long expResult = 50L;
        long result = instance.getMaxCreditos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIncCreditos method, of class Pharmacy.
     */
    @Test
    public void testGetIncCreditos() {
        System.out.println("getIncCreditos");
        Pharmacy instance = new Pharmacy(10L, "Routeirinho", 55555, 1, 1, 2, 50, 20);
        long expResult = 20L;
        long result = instance.getIncCreditos();
        assertEquals(expResult, result);
    }

}
