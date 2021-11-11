package lapr.project.ui;

import java.util.List;
import lapr.project.controller.ManageCourierController;
import lapr.project.controller.ManageDronesController;
import lapr.project.controller.ManageScooterController;
import lapr.project.model.Courier;
import lapr.project.model.Drones;
import lapr.project.model.Scooter;

/**
 * Criar Scooters e Drones
 */
public class Scenarie006 {

    public Scenarie006() {
    }

    public boolean run() {
        ScooterImporterCSV siCSV = new ScooterImporterCSV();
        ScooterImporterService siService = new ScooterImporterService();

        List<Scooter> listScooter = siService.importScooters(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/scooters.csv"), siCSV);

        ManageScooterController msc = new ManageScooterController();
        
        for (Scooter scooter : listScooter) {
            msc.addScooter(scooter.getId(), scooter.getPharmacyID(), scooter.getScooterType().getId());
        }

        DroneImporterCSV diCSV = new DroneImporterCSV();
        DroneImporterService diService = new DroneImporterService();

        List<Drones> listDrones = diService.importDrones(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/drones.csv"), diCSV);

        ManageDronesController mdc = new ManageDronesController();

        for (Drones drone : listDrones) {
            mdc.addDrones(drone.getId(), drone.getPharmacyID(), drone.getDronesType().getId());
        }

        return true;
    }
}
