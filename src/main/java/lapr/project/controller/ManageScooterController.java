package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ManageScooterController {

    private ScooterDB scooterDB;

    public ManageScooterController() {
        this.scooterDB = new ScooterDB();
    }

    public Scooter addScooter(long backpackCapacity, long pharmacyID, long scooterTypeID) {
        boolean operationStatus = true;
        try {
            long id = this.scooterDB.addScooter(backpackCapacity, pharmacyID, scooterTypeID);
            Scooter scooter = new Scooter(id, backpackCapacity, operationStatus, pharmacyID, this.scooterDB.getScooterType(scooterTypeID));
            return scooter;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageScooterController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }

    public boolean updateScooter(long id, long backpackCapacity, long pharmacyID, long scooterTypeID) {
        boolean result = this.scooterDB.updateScooter(id, backpackCapacity, pharmacyID, scooterTypeID);
        return result;
    }

    public boolean removeScooter(long scooterID) {
        boolean result = this.scooterDB.removeScooter(scooterID);
        return result;
    }
}
