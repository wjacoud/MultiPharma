/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ClienteDB;
import lapr.project.model.Cliente;
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
 * @author leona
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SignUpControllerTest {
    
    @Mock
    private ClienteDB clienteDB;
    
    @InjectMocks
    private SignUpController controller;
    
    private Cliente cliente;
    
    public SignUpControllerTest() {
        controller = new SignUpController();
    }
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        cliente = new Cliente("Joao", 111222333, 0, 1L, "sample@email.pt", 1L);
        when(clienteDB.addCliente(cliente)).thenReturn(1L);
    }

    /**
     * Test of signUp method, of class SignUpController.
     */
    @Test
    public void testSignUp() {
        System.out.println("signUp");
        long expResult =  1L;
        //long result = addressDB.addAddress(new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350));
        long result = controller.signUp("Joao", 111222333, 0, 1L, "sample@email.pt", 1L);
        assertEquals(expResult, result);
    }
    
}
