package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.Courier;

public class CourierImporterService {

    // Clients attributes
    private final static int NAME_INDEX = 0;
    private final static int NIF_INDEX = 1;
    private final static int NISS_INDEX = 2;
    private final static int WEIGHT_INDEX = 3;
    private final static int EMAIL_INDEX = 4;
    private final static int PASSWORD_INDEX = 5;

    public List<Courier> importCouriers(String filename, Importer importer) {
        List<Courier> listCourier = new ArrayList<>();
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                String[] line = importer.readElement();
                // Courier
                // , line[DISTRICT_INDEX], line[LOCALITY_INDEX], line[ZIP_CODE_INDEX], Double.parseDouble(line[LATITUDE_INDEX]), Double.parseDouble(line[LONGITUDE_INDEX]), 0.0)
                listCourier.add(new Courier(0L, line[NAME_INDEX], Long.parseLong(line[NIF_INDEX]), 
                        Long.parseLong(line[NISS_INDEX]), Long.parseLong(line[WEIGHT_INDEX]),
                        line[EMAIL_INDEX], line[PASSWORD_INDEX]));
            }
            importer.end();

        } catch (final IOException e) {
            Logger.getLogger(CourierImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return listCourier;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

}
