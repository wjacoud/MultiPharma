package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.Scooter;
import lapr.project.model.ScooterType;

public class ScooterImporterService {

    // Clients attributes
    private final static int BACKPACK_CAPACITY_INDEX = 0;
    private final static int PHARMACY_ID_INDEX = 1;
    private final static int SCOOTER_TYPE_ID_INDEX = 2;

    public List<Scooter> importScooters(String filename, Importer importer) {
        List<Scooter> listScooter = new ArrayList<>();
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                String[] line = importer.readElement();
                // Courier
                listScooter.add(new Scooter(0L, Long.parseLong(line[BACKPACK_CAPACITY_INDEX]), true, Long.parseLong(line[PHARMACY_ID_INDEX]), new ScooterType(Long.parseLong(line[SCOOTER_TYPE_ID_INDEX]), 0L, 0L, 0L, 0L, 0L)));
            }
            importer.end();
        } catch (final IOException e) {
            Logger.getLogger(ScooterImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return listScooter;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

}
