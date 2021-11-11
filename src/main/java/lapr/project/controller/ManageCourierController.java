package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ManageCourierController {

    private CourierDB courierDB;

    public ManageCourierController() {
        this.courierDB = new CourierDB();
    }

    public Courier addCourier(String ename, long nif, long niss, long weight, String emailUser, String passUser) {
        try {
            long id = this.courierDB.addCourier(ename, nif, niss, weight, emailUser, passUser);
            Courier courier = new Courier(id, ename, nif, niss, weight, emailUser, passUser);
            Logger.getLogger(ManageCourierController.class.getName()).log(Level.FINER, null, "\t... Courier added with ID: " + courier.getId());
            return courier;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageCourierController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
}
