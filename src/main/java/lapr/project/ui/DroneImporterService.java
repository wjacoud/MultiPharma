package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.Drones;
import lapr.project.model.DronesType;

public class DroneImporterService {

    // Clients attributes
    private final static int BACKPACK_CAPACITY_INDEX = 0;
    private final static int PHARMACY_ID_INDEX = 1;
    private final static int DRONE_TYPE_ID_INDEX = 2;

    public List<Drones> importDrones(String filename, Importer importer) {
        List<Drones> listDrones = new ArrayList<>();
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                String[] line = importer.readElement();
                // Drone
                listDrones.add(new Drones(0L, Long.parseLong(line[BACKPACK_CAPACITY_INDEX]), true, Long.parseLong(line[PHARMACY_ID_INDEX]), new DronesType(Long.parseLong(line[DRONE_TYPE_ID_INDEX]), 0L, 0L, 0L, 0L, 0L)));
            }
            importer.end();
        } catch (final IOException e) {
            Logger.getLogger(DroneImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return listDrones;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

}
