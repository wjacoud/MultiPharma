
package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;
/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class ManagePharmacyController {
    private PharmacyDB pharmacyDB;

    public ManagePharmacyController() {
        this.pharmacyDB  = new PharmacyDB();
    }
    
    public Pharmacy addPharmacy(String name, long nipc, long maxScooters, long maxDrones, long moradaID, long maxCred, long incCred) {
            Pharmacy newPharmacy = new Pharmacy(name, nipc, maxScooters, maxDrones, moradaID, maxCred, incCred);
        try {
            long id = this.pharmacyDB.addPharmacy(newPharmacy);
            newPharmacy.setId(id);
            Logger.getLogger(ManagePharmacyController.class.getName()).log(Level.SEVERE, null,"\t... Farmacia Adicionada Com o ID: " + newPharmacy.getId());
            return newPharmacy;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManagePharmacyController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
}
