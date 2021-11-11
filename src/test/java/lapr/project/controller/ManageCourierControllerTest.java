package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
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
public class ManageCourierControllerTest {

    private Courier expectedCourierResult;

    @Mock
    private CourierDB courierDB;

    @InjectMocks
    private ManageCourierController instance;

    public ManageCourierControllerTest() {
        instance = new ManageCourierController();
    }

    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expectedCourierResult = new Courier(1L, "Fernando Mendes", 123456789L, 123456789L, 120L, "espetaculo@rtp.pt", "precocerto");
        when(courierDB.addCourier("Fernando Mendes", 123456789L, 123456789L, 120L, "espetaculo@rtp.pt", "precocerto")).thenReturn(1L);
    }

    /**
     * Test of addCourier method, of class ManageCourierController.
     */
    @Test
    public void testAddCourier() {
        System.out.println("addCourier");
        
        String ename = "Fernando Mendes";
        long nif = 123456789L;
        long niss = 123456789L;
        long weight = 120L;
        String emailUser = "espetaculo@rtp.pt";
        String passUser = "precocerto";
        long expResult = 1L;
        
        Courier result = instance.addCourier("Fernando Mendes", 123456789L, 123456789L, 120L, "espetaculo@rtp.pt", "precocerto");
        assertEquals(expectedCourierResult, result);
    }

 

}
