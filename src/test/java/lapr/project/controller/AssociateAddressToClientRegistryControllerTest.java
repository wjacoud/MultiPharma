/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.model.Address;
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
public class AssociateAddressToClientRegistryControllerTest {

    @Mock
    private AddressDB addressDB;

    @InjectMocks
    private AssociateAddressToClientRegistryController controller;

    private Address address;

    public AssociateAddressToClientRegistryControllerTest() {
        controller = new AssociateAddressToClientRegistryController();
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        address = new Address("Portugal", "Porto", "Prelada", "4350-526", 41.21988872649243, -8.560844244287741, 0.0);
        when(addressDB.addAddress(address)).thenReturn(1L);
    }

    /**
     * Test of addAddress method, of class
     * AssociateAddressToClientRegistryController.
     */
    @Test
    public void testAddAddress() {
        System.out.println("addAddress");
        long expResult = 1L;
        //long result = addressDB.addAddress(new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350));
        long result = controller.addAddress("Portugal", "Porto", "Prelada", "4350-526", 41.21988872649243, -8.560844244287741);
        assertEquals(expResult, result);

    }
}
