package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.ParkDB;
import lapr.project.model.DronesPark;
import lapr.project.model.DronesParkingPlace;
import lapr.project.model.ScooterParkingPlace;
import lapr.project.model.ScootersPark;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ManageParkController {

    private ParkDB parkDB;

    public ManageParkController() {
        this.parkDB = new ParkDB();
    }

    public ScootersPark addParkForScooters(long addressID, long pharmacyID, long voltage, long maxScooterCapacity, long charge, long noCharge) {
        try {
            long id = this.parkDB.addParkForScooters(addressID, pharmacyID, voltage, maxScooterCapacity, charge, noCharge);
            List<ScooterParkingPlace> listScootersParkingPlace = new ArrayList<>(this.parkDB.getScooterParkingPlace(id));
            ScootersPark scooterPark = new ScootersPark(id, addressID, pharmacyID, voltage, listScootersParkingPlace);
            return scooterPark;
        } catch (IllegalArgumentException iae) {
            System.out.println(iae);
            Logger.getLogger(ManageScooterController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }

    public DronesPark addParkForDrones(long addressID, long pharmacyID, long voltage, long maxScooterCapacity, long charge, long noCharge) {
        try {
            long id = this.parkDB.addParkForDrones(addressID, pharmacyID, voltage, maxScooterCapacity, charge, noCharge);
            List<DronesParkingPlace> listDronesParkingPlace = new ArrayList<>(this.parkDB.getDronesParkingPlace(id));
            DronesPark scooterPark = new DronesPark(id, addressID, pharmacyID, voltage, listDronesParkingPlace);
            return scooterPark;
        } catch (IllegalArgumentException iae) {
            System.out.println(iae);
            Logger.getLogger(ManageScooterController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
}
