package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import lapr.project.model.ScooterType;
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
public class ManageScooterControllerTest {

    private Scooter expectedScooterResult;
    private ScooterType expectedScooterTypeResult;

    @Mock
    private ScooterDB scooterDB;

    @InjectMocks
    private ManageScooterController instance;

    public ManageScooterControllerTest() {
        instance = new ManageScooterController();
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedScooterTypeResult = new ScooterType(1L, 25L, 30L, 50L, 500L, 350L);
        expectedScooterResult = new Scooter(1L, 1L, true, 1L, expectedScooterTypeResult);
        when(scooterDB.addScooter(5L, 1L, 1L)).thenReturn(1L);
        when(scooterDB.getScooterType(1L)).thenReturn(expectedScooterTypeResult);

        when(scooterDB.updateScooter(1L, 100L, 1L, 1L)).thenReturn(true);
        when(scooterDB.updateScooter(2L, 100L, 1L, 1L)).thenReturn(false);

        when(scooterDB.removeScooter(1L)).thenReturn(true);
        when(scooterDB.removeScooter(0L)).thenReturn(false);
    }

    /**
     * Test of addScooter method, of class ManageScooterController.
     */
    @Test
    public void testAddScooter() {
        System.out.println("addScooter");

        long backpackCapacity = 5L;
        long scooterTypeID = 1L;
        long pharmacyID = 1L;

        Scooter result = instance.addScooter(backpackCapacity, scooterTypeID, pharmacyID);
        assertEquals(expectedScooterResult.getId(), result.getId());
    }

    /**
     * Test of removeScooter method, of class ManageScooterController.
     */
    @Test
    public void testRemoveScooter() {
        System.out.println("removeScooter");
        long scooterID = 1L;

        boolean expResult1 = true;
        boolean result1 = instance.removeScooter(scooterID);
        assertEquals(expResult1, result1);

        long scooterID2 = 0L;
        boolean expResult2 = false;
        boolean result2 = instance.removeScooter(scooterID2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of updateScooter method, of class ManageScooterController.
     */
    @Test
    public void testUpdateScooter() {
        System.out.println("updateScooter");
        long id = 1L;
        long backpackCapacity = 100L;
        long pharmacyID = 1L;
        long scooterTypeID = 1L;

        boolean expResult1 = true;
        boolean result = instance.updateScooter(id, backpackCapacity, pharmacyID, scooterTypeID);
        assertEquals(expResult1, result);
        
        long id2 = 2L;

        boolean expResult2 = false;
        boolean result2 = instance.updateScooter(id2, backpackCapacity, pharmacyID, scooterTypeID);
        assertEquals(expResult2, result2);
    }

}
