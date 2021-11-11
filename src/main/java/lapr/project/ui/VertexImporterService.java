package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.Address;

public class VertexImporterService {

    private final static int ID_INDEX = 0;
    private final static int COUNTRY_INDEX = 1;
    private final static int DISTRICT_INDEX = 2;
    private final static int LOCALITY_INDEX = 3;
    private final static int ZIP_CODE_INDEX = 4;
    private final static int LATITUDE_INDEX = 5;
    private final static int LONGITUDE_INDEX = 6;

    public List<Address> importEstimate(String filename, Importer importer) {
        int numberLines = 0;
        List<Address> vertex = new ArrayList<>();
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                numberLines++;
                String[] line = importer.readElement();
                Address address = new Address(Long.parseLong(line[ID_INDEX]), line[COUNTRY_INDEX],
                        line[DISTRICT_INDEX], line[LOCALITY_INDEX], line[ZIP_CODE_INDEX],
                        Double.parseDouble(line[LATITUDE_INDEX]), Double.parseDouble(line[LONGITUDE_INDEX]));
                vertex.add(address);
            }
            importer.end();

        } catch (final IOException e) {
            Logger.getLogger(VertexImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return vertex;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

}
