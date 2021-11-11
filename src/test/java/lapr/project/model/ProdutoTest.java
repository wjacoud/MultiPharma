/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Duarte
 */
public class ProdutoTest {

  

    /**
     * Test of getId method, of class Produto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Produto.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long expResult = 2L;
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        
        instance.setId(expResult);
        long result= instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQtd method, of class Produto.
     */
    @Test
    public void testGetQtd() {
        System.out.println("getQtd");
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        long expResult = 2L;
        long result = instance.getQtd();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQtd method, of class Produto.
     */
    @Test
    public void testSetQtd() {
        System.out.println("setQtd");
        long expResult = 3L;
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        instance.setQtd(expResult);
        long result= instance.getQtd();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNome method, of class Produto.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Produto instance = new Produto(1, "covid", 1, 30, 2);
        String expResult = "covid";
        String result = instance.getNome();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNome method, of class Produto.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String expResult = "gripe";
        Produto instance = new Produto(1, "covid", 1, 30, 2);
        instance.setNome(expResult);
        String result= instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPeso method, of class Produto.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        long expResult = 1L;
        long result = instance.getPeso();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPeso method, of class Produto.
     */
    @Test
    public void testSetPeso() {
        System.out.println("setPeso");
        long expResult = 2L;
        Produto instance= new Produto(1, "covid", 1, 30, 2);
        
        instance.setPeso(expResult);
        long result= instance.getPeso();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPreco method, of class Produto.
     */
    @Test
    public void testGetPreco() {
        System.out.println("getPreco");
        Produto instance = new Produto(1, "covid", 1, 30, 2);
        long expResult = 30L;
        long result = instance.getPreco();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPreco method, of class Produto.
     */
    @Test
    public void testSetPreco() {
        System.out.println("setPreco");
        long expResult = 40L;
        Produto instance = new Produto(1, "covid", 1, 30, 2);
        instance.setPreco(expResult);
        long result = instance.getPreco();
        assertEquals(expResult, result);
    }
    
      /**
     * Test of hashCode method, of class Produto.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Produto instance = new Produto(1L, "v", 2, 30, 4);
        Long id = 1L;
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Produto.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Produto instance = new Produto(1L, "v", 2, 30, 4);
        Produto obj = new Produto(1L, "v", 2, 30, 4);
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
        Produto instance = new Produto(1L, "v", 2, 30, 4);
        Produto obj = new Produto(2L, "v", 2, 30, 4);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Produto instance = new Produto(1L, "v", 2, 30, 4);
        Produto obj =null;
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
        Produto instance = new Produto(1L, "v", 2, 30, 4);
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
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
        Produto instance = new Produto(1L, "v", 2, 30, 4);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
    
}
