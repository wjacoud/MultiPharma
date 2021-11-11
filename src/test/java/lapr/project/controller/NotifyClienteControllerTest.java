package lapr.project.controller;

import lapr.project.data.DronesDB;
import lapr.project.model.Drones;
import lapr.project.model.DronesType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotifyClienteControllerTest {
    
    public NotifyClienteControllerTest() {
    }
    
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
//    /**
//     * Test of notifyClienteOrderSubmit method, of class NotifyClienteController.
//     */
//    @Test
//    public void testNotifyClienteOrderSubmit() {
//        System.out.println("notifyClienteOrderSubmit");
//        long ordemId = 0L;
//        long faturaid = 0L;
//        NotifyClienteController instance = new NotifyClienteController();
//        String expResult = "";
//        String result = instance.notifyClienteOrderSubmit(ordemId, faturaid);
//        assertEquals(expResult, result);
//    }
    
}
