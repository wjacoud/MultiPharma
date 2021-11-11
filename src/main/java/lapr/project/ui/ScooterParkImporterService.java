package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.ScooterParkingPlace;
import lapr.project.model.ScootersPark;
 
public class ScooterParkImporterService {

    // 	charge	noCharge
    // Clients attributes
    private final static int ADDRESS_ID_INDEX = 0;
    private final static int PHARMACY_ID_INDEX = 1;
    private final static int VOLTAGE_ID_INDEX = 2;
    private final static int CHARGE_INDEX = 3;
    private final static int NO_CHARGE_INDEX = 4;

    public List<ScootersPark> importEstimate(String filename, Importer importer) {
        List<ScootersPark> listScootersPark = new ArrayList<>();
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                String[] line = importer.readElement();
                List<ScooterParkingPlace> listScooterParkingPlace = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(line[NO_CHARGE_INDEX]); i++) {
                    long estacionamentoID = 0;
                    long carga = 0;
                    ScooterParkingPlace scooterParkingPlace = new ScooterParkingPlace(estacionamentoID, carga);
                    listScooterParkingPlace.add(scooterParkingPlace);
                }
                for (int i = 0; i < Integer.parseInt(line[NO_CHARGE_INDEX]); i++) {
                    long estacionamentoID = 0;
                    long carga = Long.parseLong(line[CHARGE_INDEX]);
                    ScooterParkingPlace scooterParkingPlace = new ScooterParkingPlace(estacionamentoID, carga);
                    listScooterParkingPlace.add(scooterParkingPlace);
                }
                
                ScootersPark scooterPark = new ScootersPark(0L,
                        Long.parseLong(line[VOLTAGE_ID_INDEX]),
                        Long.parseLong(line[ADDRESS_ID_INDEX]),
                        Long.parseLong(line[PHARMACY_ID_INDEX]),
                        listScooterParkingPlace);
                
                listScootersPark.add(scooterPark);
            }

            importer.end();

        } catch (final IOException e) {
            Logger.getLogger(ScooterParkImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return listScootersPark;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

}
