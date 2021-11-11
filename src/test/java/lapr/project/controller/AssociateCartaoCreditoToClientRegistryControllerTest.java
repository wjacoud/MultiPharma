/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.Date;
import lapr.project.data.CartaoCreditoDB;
import lapr.project.model.CartaoCredito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssociateCartaoCreditoToClientRegistryControllerTest {
    
    private CartaoCredito expectedCartaoCredito;

    @Mock
    private CartaoCreditoDB cartaoCreditoDB;

    @InjectMocks
    private AssociateCartaoCreditoToClientRegistryController instance1;
    
    private AssociateCartaoCreditoToClientRegistryControllerTest() {
        instance1= new AssociateCartaoCreditoToClientRegistryController();
    }
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedCartaoCredito = new CartaoCredito(1L, 44445555, 456, Date.valueOf("2021-01-01"));
        when(cartaoCreditoDB.addCartaoCredito(new CartaoCredito(1L, 44445555, 456, Date.valueOf("2021-01-01")))).thenReturn(1L);
    }

    /**
     * Test of addCartaoCredito method, of class ManageCartaoCreditoController.
     */
    @Test
     void testAddCartaoCredito() {
         System.out.println("addCartaoCredito");
         long expResult =  expectedCartaoCredito.getId();
         long result = cartaoCreditoDB.addCartaoCredito(new CartaoCredito(1L, 44445555, 456, Date.valueOf("2021-01-01")));
         assertEquals(expResult, result);
    }
}
