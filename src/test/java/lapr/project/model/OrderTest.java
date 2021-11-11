/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;

import java.util.ArrayList;
import java.sql.Date;
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
public class OrderTest { // Not Passing tests
    private Order instance= new Order();
    private Date d1=Date.valueOf("2024-05-22");

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
 
    @Before
    void setUp() throws ParseException{
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrderGetId() {
        System.out.println("Order getId");
        long expectedId = 1;
        instance=new Order();
        instance.setId(expectedId);
       
       
        long result = instance.getId();
        assertEquals(expectedId, result);
    }

    @Test
    public void testOrderGetPeso() {
        System.out.println("Order getPeso");
          long expectedPeso = 3;
        instance=new Order();
        instance.setPeso(expectedPeso);
       
       
        double result = instance.getPeso();
        assertEquals(expectedPeso, result);
    }

    @Test
    public void testOrderSetDataCriacao() {
    System.out.println("Order setDataCriacao");

    instance.setDataCriacao(d1);
    
    Date result=instance.getDataCriacao();
    assertEquals(d1, result);
    }
    
    @Test
    public void testOrderGetDataCriacao() {
        System.out.println("Order getDataCriacao");
       
        instance.setDataCriacao(d1);
        Date expResult = d1;
        Date result = instance.getDataCriacao();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPreco() {
        System.out.println("Order getPreco");
        long expectedPreco = 20;
        instance=new Order();
        instance.setPreco(expectedPreco);
       
       
        double result = instance.getPreco();
        assertEquals(expectedPreco, result);
    }
    
    @Test
    public void testGetClienteId() {
        System.out.println("Order getClienteId");
        long expectedClienteId = 1;
        instance=new Order();
        instance.setClienteId(expectedClienteId);
       
       
        long result = instance.getClienteId();
        assertEquals(expectedClienteId, result);
    }
    
    @Test
    public void testGetTxeId() {
        System.out.println("Order getClienteId");
        long expectedTxeId = 1;
        instance=new Order();
        instance.setTxEtrega(expectedTxeId);
       
       
        long result = instance.getTxEntrega();
        assertEquals(expectedTxeId, result);
    }
    
    @Test
    public void testGetFarmaciaId() {
        System.out.println("Order getFarmacia");
        long expectedFarmaciaId = 1;
        instance.setFarmaciaId(expectedFarmaciaId);
       
       
        long result = instance.getFarmaciaId();
        assertEquals(expectedFarmaciaId, result);
    }
    
    @Test
    public void testSetProdList(){
    System.out.println("Order setProdList");
    ArrayList<Produto> l= new ArrayList<>();
    long p1Id=2;
    long p1Qtd=1;
    long p2Id=4;
    long p2Qtd=2;
    Produto p1=new Produto(p1Id, "covid", 2, 20, p1Qtd);
    Produto p2 =new Produto(p2Id, "gripe", 2, 10, p2Qtd);
    l.add(p1);
    l.add(p2);
    instance.setProdList(l);
    
    int expectedListLength=2;
    assertEquals(expectedListLength, instance.getProdList().size());
    assertEquals(p1Id, instance.getProdList().get(0).getId());
    assertEquals("covid", instance.getProdList().get(0).getNome());
    assertEquals(2, instance.getProdList().get(0).getPeso());
    assertEquals(20, instance.getProdList().get(0).getPreco());
    assertEquals(p1Qtd, instance.getProdList().get(0).getQtd());
    assertEquals(p2Id, instance.getProdList().get(1).getId());
    assertEquals("gripe", instance.getProdList().get(1).getNome());
    assertEquals(2, instance.getProdList().get(1).getPeso());
    assertEquals(10, instance.getProdList().get(1).getPreco());
    assertEquals(p2Qtd, instance.getProdList().get(1).getQtd());

    
    }

    /**
     * Test of addProdToList method, of class Order.
     */
    @Test
    public void testAddProdToList() {
        System.out.println("Order addProdToList");
        Produto p = new Produto(1, "covid", 2, 20, 1);
        //Order instance = new Order();
        instance.setProdList(new ArrayList<>());
        instance.addProdToList(p);
        assertEquals(p, instance.getProdList().get(0));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getProdList method, of class Order.
     */
    @Test
    public void testGetProdList() {
        System.out.println("Order getProdList");
        //Order instance = new Order();
        Produto p = new Produto(1, "covid", 2, 20, 1);
        
        instance.setProdList(new ArrayList<>());
        instance.addProdToList(p);
        ArrayList<Produto> testArray = new ArrayList<>();
        testArray.add(p);
        
        ArrayList<Produto> expResult = testArray;
        ArrayList<Produto> result = instance.getProdList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of hashCode method, of class Scooter.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        Long id = 1L;
        int expResult = id.hashCode();
        int result = order.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Order order = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        Object obj = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        boolean expResult = true;
        boolean result = order.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Order instance = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        Object obj = new Order(2L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
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
        Order instance = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        Order obj = null;
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
        Order instance = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
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
        Order instance = new Order(1L, 1, new Date(0), 100.0, 1L, 1L, 1l, new ArrayList<>());
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

}
