/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class CartaoCreditoTest {

    public CartaoCreditoTest() {
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
     * Test of getId method, of class CartaoCredito.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        CartaoCredito instance = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        long expResult = 1;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumero method, of class CartaoCredito.
     */
    @Test
    public void testGetNumero() {
        System.out.println("getNumero");
        CartaoCredito instance = new CartaoCredito(44445555, 456, Date.valueOf("2021-01-01"));
        long expResult = 44445555;
        long result = instance.getNumero();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCvv method, of class CartaoCredito.
     */
    @Test
    public void testGetCvv() {
        System.out.println("getCvv");
        CartaoCredito instance = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        long expResult = 456;
        long result = instance.getCvv();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValidade method, of class CartaoCredito.
     */
    @Test
    public void testGetValidade() {
        System.out.println("getValidade");
        CartaoCredito instance = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        Date expResult = Date.valueOf("2021-01-01");
        Date result = instance.getValidade();
        assertEquals(expResult, result);
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CartaoCredito instance = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CartaoCredito.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        CartaoCredito instance = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CartaoCredito.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02 !ID");
        Object obj = new CartaoCredito(1, 44445555, 456, Date.valueOf("2021-01-01"));
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CartaoCredito.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Scooter obj = null;
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CartaoCredito.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CartaoCredito.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of classCartaoCredito.
     */
    @Test
    public void testEquals06() {
        System.out.println("equals06 !Numero");
        Object obj = new CartaoCredito(2, 44445553, 456, Date.valueOf("2021-01-01"));
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of classCartaoCredito.
     */
    @Test
    public void testEquals07() {
        System.out.println("equals07 !cvv");
        Object obj = new CartaoCredito(2, 44445555, 457, Date.valueOf("2021-01-01"));
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of classCartaoCredito.
     */
    @Test
    public void testEquals08() {
        System.out.println("equals08 !Validade");
        Object obj = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-02"));
        CartaoCredito instance = new CartaoCredito(2, 44445555, 456, Date.valueOf("2021-01-01"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
