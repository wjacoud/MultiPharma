/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author leona
 */
public class TimeEstimateImporterCSV implements Importer{
    
    private final static String TEMPLATE_HEADER = "SLOT_ID;SCOOTER_ID;COURIER_ID;ESTIMATE";
    private final static String FILE_SEPARATOR = ";";
    private final static String FILE_COMMENT = "#";
    private final static int EXPECTED_NUMBER_OF_COLUMNS = 4;
  
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
