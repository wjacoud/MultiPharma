/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Date;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Duarte
 */
public class FaturaTest {

    private Date d = Date.valueOf("2025-08-11");
    private long id = 1;
    //private Order o = new Order(1, 2, d, 20, 1, 1, 1, new ArrayList<>());
    private Fatura f = new Fatura(1L, Date.valueOf("2025-08-11"), 0, 0, 0, 1);

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getId method, of class Fatura.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Fatura f2 = new Fatura(Date.valueOf("2025-08-11"), 0, 0, 0, 1);
        long expResult = 0;
        long result = f2.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Fatura.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        f.setId(2);
        long expResult = 2;
        long result = f.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGetData_emit method, of class Fatura.
     */
    @Test
    public void testGetData_emit() {
        System.out.println("getData_emit");
        Date expResult = d;
        Date result = f.getDataEmit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGetData_emit method, of class Fatura.
     */
    @Test
    public void testSetData_emit() {
        System.out.println("setData_emit");
        f.setData_emit(Date.valueOf("2025-08-11"));
        Date expResult = Date.valueOf("2025-08-11");
        Date result = f.getDataEmit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getvSemIva method, of class Fatura.
     */
    @Test
    public void testGetvSemIva() {
        System.out.println("getvSemIva");
        Fatura f2 = new Fatura(Date.valueOf("2025-08-11"), 1L, 2L, 3L, 1L);
        long expResult = 1L;
        long result = f2.getvSemIva();
        assertEquals(expResult, result);

    }

    /**
     * Test of getvIva method, of class Fatura.
     */
    @Test
    public void testGetvIva() {
        System.out.println("getvIva");
        Fatura f2 = new Fatura(Date.valueOf("2025-08-11"), 1L, 2L, 3L, 1L);
        long expResult = 2L;
        long result = f2.getvIva();
        assertEquals(expResult, result);
    }

    /**
     * Test of getvTotal method, of class Fatura.
     */
    @Test
    public void testGetvTotal() {
        System.out.println("getvTotal");
        Fatura f2 = new Fatura(Date.valueOf("2025-08-11"), 1L, 2L, 3L, 1L);
        long expResult = 3L;
        long result = f2.getvTotal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrdemId method, of class Fatura.
     */
    @Test
    public void testGetOrdemId() {
        System.out.println("getOrdemId");
        Fatura f2 = new Fatura(Date.valueOf("2025-08-11"), 1L, 2L, 3L, 1L);
        long expResult = 1L;
        long result = f2.getOrdemId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDataEmit method, of class Fatura.
     */
    @org.junit.Test
    public void testGetDataEmit() {
        System.out.println("getDataEmit");
        Fatura instance = null;
        Date expResult = null;
        Date result = instance.getDataEmit();
        assertEquals(expResult, result);
    }
}
