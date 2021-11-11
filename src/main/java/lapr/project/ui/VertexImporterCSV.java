package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import lapr.project.application.Importer;

public class VertexImporterCSV implements Importer{
    
    private final static String TEMPLATE_HEADER = "id;country;district;locality;zip_code;latitude;longitude";
    private final static String FILE_SEPARATOR = ";";
    private final static String FILE_COMMENT = "#";
    private final static int EXPECTED_NUMBER_OF_COLUMNS = 7;
  
    private Scanner scanner; // for the file to be read
     
    @Override
    public void begin(String filename) throws IOException {
        File fileToImport = new File(filename);
        
        scanner = new Scanner(fileToImport);
        
        /* Ignore comments until Header line */
        String header = scanner.nextLine();
        while(header.startsWith(FILE_COMMENT)){
            header = scanner.nextLine();
        }
        
        if (!header.equals(TEMPLATE_HEADER)) {
            throw new IOException("Header not according to expected.");
        }
    }

    @Override
    public String[] readElement() {
        String line = scanner.nextLine();
        String[] attributes = line.split(FILE_SEPARATOR);
        
        if (attributes.length != EXPECTED_NUMBER_OF_COLUMNS)
            return new String[0];
        return attributes;
    }

    @Override
    public boolean hasNextElement() {
        return scanner.hasNextLine();
    }

    @Override
    public void end() {
        scanner.close();
    }

    @Override
    public void cleanup() {
        if (scanner != null)
            scanner.close();
    }
    
}
