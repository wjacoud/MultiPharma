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
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class TaxaEntregaTest {
    
    public TaxaEntregaTest() {
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
     * Test of getId method, of class TaxaEntrega.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        long expResult = 1;
        long result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class TaxaEntrega.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        instance.setId(0L);
        long expResult = 0;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData_inicio method, of class TaxaEntrega.
     */
    @Test
    public void testGetData_inicio() {
        System.out.println("getData_inicio");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        Date expResult = Date.valueOf("2021-01-01");
        Date result = instance.getData_inicio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData_fim method, of class TaxaEntrega.
     */
    @Test
    public void testGetData_fim() {
        System.out.println("getData_fim");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        Date expResult = Date.valueOf("2021-12-01");
        Date result = instance.getData_fim();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValor method, of class TaxaEntrega.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        long expResult = 10;
        long result = instance.getValor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescricao method, of class TaxaEntrega.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        String expResult = "Taxa Unica";
        String result = instance.getDescricao();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class TaxaEntrega.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        Long id = 1L;
        int expResult = id.hashCode();;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = null;
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Object obj = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Scooter obj = null;
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals06() {
        System.out.println("equals06 !Data_inicio");
        Object obj = new TaxaEntrega(1, Date.valueOf("2021-01-02"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals07() {
        System.out.println("equals07 !Data_fim");
        Object obj = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-02"), 10, "Taxa Unica");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals08() {
        System.out.println("equals08 !Valor");
        Object obj = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 11, "Taxa Unica");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class TaxaEntrega.
     */
    @Test
    public void testEquals09() {
        System.out.println("equals09 !Descricao");
        Object obj = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 11, "Taxa Setor 1");
        TaxaEntrega instance = new TaxaEntrega(1, Date.valueOf("2021-01-01"), Date.valueOf("2021-12-01"), 10, "Taxa Unica");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
