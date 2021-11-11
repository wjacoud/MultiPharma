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
public class ManageDronesControllerTest {

    private Drones expectedDronesResult;
    private DronesType expectedDronesTypeResult;

    @Mock
    private DronesDB DronesDB;

    @InjectMocks
    private ManageDronesController instance;

    public ManageDronesControllerTest() {
        instance = new ManageDronesController();
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedDronesTypeResult = new DronesType(1L, 25L, 30L, 50L, 500L, 350L);
        expectedDronesResult = new Drones(1L, 1L, true, 1L, expectedDronesTypeResult);
        when(DronesDB.addDrones(5L, 1L, 1L)).thenReturn(1L);
        when(DronesDB.getDronesType(1L)).thenReturn(expectedDronesTypeResult);

        when(DronesDB.updateDrone(1L, 100L, 1L, 1L)).thenReturn(true);
        when(DronesDB.updateDrone(2L, 100L, 1L, 1L)).thenReturn(false);

        when(DronesDB.removeDrone(1L)).thenReturn(true);
        when(DronesDB.removeDrone(0L)).thenReturn(false);
    }

    /**
     * Test of addDrones method, of class ManageDronesController.
     */
    @Test
    public void testAddDrones() {
        System.out.println("addDrones");

        long backpackCapacity = 5L;
        long DronesTypeID = 1L;
        long pharmacyID = 1L;

        Drones result = instance.addDrones(backpackCapacity, DronesTypeID, pharmacyID);
        assertEquals(expectedDronesResult.getId(), result.getId());
    }

    /**
     * Test of removeDrones method, of class ManageDronesController.
     */
    @Test
    public void testRemoveDrones() {
        System.out.println("removeDrones");
        long DronesID = 1L;

        boolean expResult1 = true;
        boolean result1 = instance.removeDrone(DronesID);
        assertEquals(expResult1, result1);

        long DronesID2 = 0L;
        boolean expResult2 = false;
        boolean result2 = instance.removeDrone(DronesID2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of updateDrones method, of class ManageDronesController.
     */
    @Test
    public void testUpdateDrones() {
        System.out.println("updateDrones");
        long id = 1L;
        long backpackCapacity = 100L;
        long pharmacyID = 1L;
        long DronesTypeID = 1L;

        boolean expResult1 = true;
        boolean result = instance.updateDrone(id, backpackCapacity, pharmacyID, DronesTypeID);
        assertEquals(expResult1, result);
        
        long id2 = 2L;

        boolean expResult2 = false;
        boolean result2 = instance.updateDrone(id2, backpackCapacity, pharmacyID, DronesTypeID);
        assertEquals(expResult2, result2);
    }

}
