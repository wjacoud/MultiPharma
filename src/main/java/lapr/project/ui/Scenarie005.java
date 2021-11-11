package lapr.project.ui;

import java.util.List;
import lapr.project.controller.ManageCourierController;
import lapr.project.model.Courier;

/**
 * Criar Courier 
 */
public class Scenarie005 {

    public Scenarie005() {
    }

    public boolean run() {
        CourierImporterCSV ciCSV = new CourierImporterCSV();
        CourierImporterService ciService = new CourierImporterService();

        List<Courier> listCourier = ciService.importCouriers(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/courier.csv"), ciCSV);

        ManageCourierController mcc = new ManageCourierController();
        
        for (Courier courier : listCourier) {
            mcc.addCourier(courier.getEname(), courier.getNif(), courier.getNiss(), courier.getWeight(), courier.getUser().getEmail(), courier.getUser().getPass());
        }
        
        return true;
    }
}
