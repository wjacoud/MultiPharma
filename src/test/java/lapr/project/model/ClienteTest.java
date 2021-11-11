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
public class ClienteTest {

    public ClienteTest() {
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
     * Test of getId method, of class Cliente.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        long expResult = 1;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Cliente.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        String expResult = "Wilson";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Cliente.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNif");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        long expResult = 111222333;
        long result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCredito method, of class Cliente.
     */
    @Test
    public void testGetCredito() {
        System.out.println("getCredito");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 2, 1, "email@sample.pt", 1);
        int expResult = 2;
        int result = instance.getCredito();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMorada method, of class Cliente.
     */
    @Test
    public void testGetMorada() {
        System.out.println("getMorada");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        long expResult = 1;
        long result = instance.getMorada();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUtilizadorID method, of class Cliente.
     */
    @Test
    public void testGetUtilizadorID() {
        System.out.println("getUtilizadorID");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        String expResult = "email@sample.pt";
        String result = instance.getUtilizadorID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCartaoCreditoID method, of class Cliente.
     */
    @Test
    public void testGetCartaoCreditoID() {
        System.out.println("getCartaoCreditoID");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        long expResult = 1;
        long result = instance.getCartaoCreditoID();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Cliente.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Cliente instance = new Cliente(2, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Long id = 2L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals02() {//id
        System.out.println("equals02");
        Object obj = new Cliente(2, "Wilson2", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson2", 111222333, 00, 1, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals03() {//null
        System.out.println("equals03");
        Scooter obj = null;
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals04() {//other class
        System.out.println("equals04");
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals05() {//itself
        System.out.println("equals05");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals06() { //nome
        System.out.println("equals06");
        Object obj = new Cliente(1, "Wilson2", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals07() { //nif
        System.out.println("equals07");
        Object obj = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222334, 00, 1, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals08() { //credito
        System.out.println("equals08");
        Object obj = new Cliente(1, "Wilson", 111222333, 1, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222334, 00, 1, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals09() { //morada
        System.out.println("equals09");
        Object obj = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222334, 00, 2, "email@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals10() { //email
        System.out.println("equals10");
        Object obj = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222334, 00, 1, "email1@sample.pt", 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals011() { //cartaocredito
        System.out.println("equals11");
        Object obj = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222334, 00, 1, "email@sample.pt", 3);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals012() { //cartaocredito 
        System.out.println("equals12");
        Object obj = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        Cliente instance = new Cliente(1, "Wilson", 111222333, 1, 1, "email@sample.pt", 3);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        // morada 
        instance = new Cliente(1, "Wilson", 111222333, 00, 2, "email@sample.pt", 3);
        boolean expResult2 = false;
        boolean result2 = instance.equals(obj);
        assertEquals(expResult2, result2);
        
        // Utilizador 
        instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email2@sample.pt", 1);
        boolean expResult3 = false;
        boolean result3 = instance.equals(obj);
        assertEquals(expResult3, result3);
        
        // cartao credito 
        instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 2);
        boolean expResult4 = false;
        boolean result4 = instance.equals(obj);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of resgataCredito() method, of class Cliente.
     */
    @Test
    public void testResgataCredito() {
        System.out.println("resgataCredito");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        String expResult = "Wilson has 0 credits.";
        String result = instance.resgataCredito();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Cliente.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cliente instance = new Cliente(1, "Wilson", 111222333, 00, 1, "email@sample.pt", 1);
        System.out.println(instance);
        String expResult = "Cliente{id=1, name=Wilson, credito=0, moradaID=1, utilizadorID=email@sample.pt, cartaoCreditoID=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
