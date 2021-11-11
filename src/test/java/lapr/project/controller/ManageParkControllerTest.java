package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ParkDB;
import lapr.project.model.DronesPark;
import lapr.project.model.DronesParkingPlace;
import lapr.project.model.ScooterParkingPlace;
import lapr.project.model.ScootersPark;
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
public class ManageParkControllerTest {

    private ScootersPark expecetedScooterParkResult;
    private DronesPark expectedDronesParkResult;

    @Mock
    private ParkDB parkDB;

    @InjectMocks
    private ManageParkController instance;

    public ManageParkControllerTest() {
        instance = new ManageParkController();
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<ScooterParkingPlace> listScooterParkingPlace = new ArrayList<>();
        listScooterParkingPlace.add(new ScooterParkingPlace(1L, 10L));
        listScooterParkingPlace.add(new ScooterParkingPlace(2L, 10L));
        expecetedScooterParkResult = new ScootersPark(1L, 5L, 1L, 1L, listScooterParkingPlace);

        when(parkDB.addParkForScooters(1L, 1L, 20L, 2L, 10L, 1L)).thenReturn(1L);
        when(parkDB.getScooterParkingPlace(1L)).thenReturn(listScooterParkingPlace);

        List<DronesParkingPlace> listDronesParkingPlace = new ArrayList<>();
        listDronesParkingPlace.add(new DronesParkingPlace(1L, 10L));
        listDronesParkingPlace.add(new DronesParkingPlace(2L, 10L));
        expectedDronesParkResult = new DronesPark(1L, 5L, 1L, 1L, listDronesParkingPlace);

        when(parkDB.addParkForDrones(1L, 1L, 20L, 2L, 10L, 1L)).thenReturn(1L);
        when(parkDB.getDronesParkingPlace(1L)).thenReturn(listDronesParkingPlace);
    }

    /**
     * Test of addParkForScooters method, of class ManageParkController.
     */
    @Test
    public void testAddParkForScooters() {
        System.out.println("addParkForScooters");
        long charge = 10L;
        List<ScooterParkingPlace> listScooterParkingPlace = new ArrayList<>();
        listScooterParkingPlace.add(new ScooterParkingPlace(1L, charge));
        listScooterParkingPlace.add(new ScooterParkingPlace(2L, charge));

        long addressID = 1L;
        long pharmacyID = 5L;
        long voltage = 20L;
        long maxScooterCapacity = 2L;

        ScootersPark expResult = new ScootersPark(addressID, pharmacyID, voltage, maxScooterCapacity, listScooterParkingPlace);
        ScootersPark result = instance.addParkForScooters(1L, 1L, 20L, 2L, 10L, 1L);
        assertEquals(expResult, result);
    }

    /**
     * Test of addParkForDrones method, of class ManageParkController.
     */
    @Test
    public void testAddParkForDrones() {
        System.out.println("addParkForDrones");
        long charge = 10L;
        List<DronesParkingPlace> listDronesParkingPlace = new ArrayList<>();
        listDronesParkingPlace.add(new DronesParkingPlace(1L, charge));
        listDronesParkingPlace.add(new DronesParkingPlace(2L, charge));

        long addressID = 1L;
        long pharmacyID = 5L;
        long voltage = 20L;
        long maxScooterCapacity = 2L;

        DronesPark expResult = new DronesPark(addressID, pharmacyID, voltage, maxScooterCapacity, listDronesParkingPlace);
        DronesPark result = instance.addParkForDrones(1L, 1L, 20L, 2L, 10L, 1L);
        assertEquals(expResult, result);
    }

}
