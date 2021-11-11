package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;

public class EdgeImporterService {

    public List<String> importEstimate(String filename, Importer importer) {
        int numberLines = 0;
        List<String> edges = new ArrayList<>();
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                numberLines++;
                String[] line = importer.readElement();
                edges.add(line[0] + ";" + line[1]);
            }
            importer.end();

        } catch (final IOException e) {
            Logger.getLogger(EdgeImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return edges;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

}
