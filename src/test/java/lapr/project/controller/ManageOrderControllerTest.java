/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import lapr.project.data.AddressDB;
import lapr.project.model.Produto;
import lapr.project.data.OrderDB;
import lapr.project.model.Address;
import lapr.project.model.Order;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManageOrderControllerTest {

    private ArrayList<Produto> a=new ArrayList<>();
    
    private Order o=new Order(1L, 2D, Date.valueOf("2025-08-11"), 20D, 1L, 1L, 1L, a);
    private Address add1=new Address(1L, "pt", "porto", "gaia", "4422-222", 11.5555, -11.111);
    @Mock
    private OrderDB orderDB;
    
    @Mock
    private AddressDB adb;
    
    @InjectMocks
    private ManageOrderController instance1;
    
    private ManageOrderControllerTest() {
        instance1= new ManageOrderController();
    }
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HashMap<Long, Address> hsm=new HashMap<>();
        hsm.put(1L, add1);
        //o=new Order(1L, 2D, Date.valueOf("2025-08-11"), 20D, 1L, 1L, 1L, a);
        when(orderDB.addOrder(0L, 1L, 1L, a)).thenReturn(1L);
        doReturn(o).when(orderDB).getOrder(1L);
        doReturn(add1).when(adb).getAddressByClientID(1L);
        doReturn(hsm).when(adb).getAllPharmacyAddress();

    }

    /**
     * Test of addOrder method, of class ManageOrderController.
     */
    @Test
    public void testAddOrder() {
        //addOrder(long clienteId, long farmaciaId, ArrayList<Produto> a)
        System.out.println("addOrder");
        Order result = instance1.addOrder(1L, a);
        //Order result = instance1.addOrder(2D, Date.valueOf("2025-08-11"), 20D, 1L, 1L, 1L, a);
        
        assertEquals(1, result.getId());
    }
    
}
