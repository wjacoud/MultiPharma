package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.DronesDB;
import lapr.project.model.Drones;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ManageDronesController {

    private DronesDB dronesDB;

    public ManageDronesController() {
        this.dronesDB = new DronesDB();
    }

    public Drones addDrones(long backpackCapacity, long pharmacyID, long droneTypeID) {
        boolean operationStatus = true;
        try {
            long id = this.dronesDB.addDrones(backpackCapacity, pharmacyID, droneTypeID);
            Drones drone = new Drones(id, backpackCapacity, operationStatus, pharmacyID, this.dronesDB.getDronesType(droneTypeID));
            return drone;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageDronesController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
    
    public boolean updateDrone(long id, long backpackCapacity, long pharmacyID, long dronesTypeID) {
        boolean result = this.dronesDB.updateDrone(id, backpackCapacity, pharmacyID, dronesTypeID);
        return result;
    }
    
    public boolean removeDrone(long dronesID) {
        boolean result = this.dronesDB.removeDrone(dronesID);
        return result;
    }
}
