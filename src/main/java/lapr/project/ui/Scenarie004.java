package lapr.project.ui;

import java.util.List;
import lapr.project.controller.ManageDronesController;
import lapr.project.controller.ManageParkController;
import lapr.project.model.DronesPark;
import lapr.project.model.DronesParkingPlace;
import lapr.project.model.ScooterParkingPlace;
import lapr.project.model.ScootersPark;

/**
 * Criar parques
 */
public class Scenarie004 {

    public Scenarie004() {
    }

    public boolean run() {
        ManageParkController mpc = new ManageParkController();
        
        ScooterParkImporterCSV piCSV = new ScooterParkImporterCSV();
        ScooterParkImporterService piService = new ScooterParkImporterService();

        List<ScootersPark> listScootersPark = piService.importEstimate(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/scooterspark.csv"), piCSV);

        for (ScootersPark scootersPark : listScootersPark) {
            int noChargingPark = 0;
            long charging = 0L;
            for (ScooterParkingPlace parkingPlace : scootersPark.getListScooterParkingPlace()) {
                if (parkingPlace.getCharge() == 0) {
                    noChargingPark++;
                } else {
                    charging = parkingPlace.getCharge();
                }
            }
            
            mpc.addParkForScooters(scootersPark.getAddressID(), scootersPark.getPharmacyId(), scootersPark.getVoltages(), scootersPark.getMaxScooterCapacity(), charging, noChargingPark);
        }
        
        DroneParkImporterCSV diCSV = new DroneParkImporterCSV();
        DroneParkImporterService diService = new DroneParkImporterService();

        List<DronesPark> listDronesPark = diService.importEstimate(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/dronespark.csv"), diCSV);

        for (DronesPark dronesPark : listDronesPark) {
            int noChargingPark = 0;
            long charging = 0L;
            for (DronesParkingPlace parkingPlace : dronesPark.getListDronesParkingPlace()) {
                if (parkingPlace.getCharge() == 0) {
                    noChargingPark++;
                } else {
                    charging = parkingPlace.getCharge();
                }
            }
            
            mpc.addParkForDrones(dronesPark.getAddressID(), dronesPark.getPharmacyId(), dronesPark.getVoltages(), dronesPark.getMaxDroneCapacity(), charging, noChargingPark);
        }

        return true;
    }
}
