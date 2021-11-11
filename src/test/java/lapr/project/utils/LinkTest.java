/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author leona
 */
public class LinkTest {
    
    private final Link link = new Link(1L, 2L, 5, 20, 0.0063, 3);
    public LinkTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * Test of getFromAddressId method, of class Link.
     */
    @Test
    public void testGetFromAddressId() {
        System.out.println("getFromAddressId");
        Link instance = link;
        Long expResult = 1L;
        Long result = instance.getFromAddressId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getToAddressId method, of class Link.
     */
    @Test
    public void testGetToAddressId() {
        System.out.println("getToAddressId");
        Link instance = link;
        Long expResult = 2L;
        Long result = instance.getToAddressId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLength method, of class Link.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        Link instance = link;
        double expResult = 0.0;
        double result = instance.getLength();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWind_velovity method, of class Link.
     */
    @Test
    public void testGetWind_velovity() {
        System.out.println("getWind_velovity");
        Link instance = link;
        double expResult = 5;
        double result = instance.getWind_velovity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWing_angle method, of class Link.
     */
    @Test
    public void testGetWing_angle() {
        System.out.println("getWing_angle");
        Link instance = link;
        double expResult = 20;
        double result = instance.getWing_angle();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRoad_rolling_resistance_coefficient method, of class Link.
     */
    @Test
    public void testGetRoad_rolling_resistance_coefficient() {
        System.out.println("getRoad_rolling_resistance_coefficient");
        Link instance = link;
        double expResult = 0.0063;
        double result = instance.getRoad_rolling_resistance_coefficient();
        assertEquals(expResult, result, 0.0000);
    }

    /**
     * Test of getRoad_slope_angle method, of class Link.
     */
    @Test
    public void testGetRoad_slope_perc() {
        System.out.println("getRoad_slope_angle");
        Link instance = link;
        double expResult = 3;
        double result = instance.getRoad_slope_perc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getEnergyWattH method, of class Link.
     */
    @org.junit.Test
    public void testGetEnergyWattH() {
        System.out.println("getEnergyWattH");
        Link instance = link;
        double expResult = 0.0;
        double result = instance.getEnergyWattH();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLength method, of class Link.
     */
    @org.junit.Test
    public void testSetLength() {
        System.out.println("setLength");
        double length = 100;
        Link instance = link;
        instance.setLength(length);
        assertEquals(length, instance.getLength(), 100);
    }

    /**
     * Test of setEnergyWattH method, of class Link.
     */
    @org.junit.Test
    public void testSetEnergyWattH() {
        System.out.println("setEnergyWattH");
        double energyWattH = 24.0;
        Link instance = link;
        instance.setEnergyWattH(energyWattH);
        assertEquals(energyWattH, instance.getEnergyWattH(), 0.00);
    }
    
}
