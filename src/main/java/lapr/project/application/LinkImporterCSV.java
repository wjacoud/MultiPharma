/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import lapr.project.utils.Link;

/**
 *
 * @author leona
 */
public class LinkImporterCSV implements LinkImporter{

    private final static String TEMPLATE_HEADER = "FROM_ID;TO_ID;WIND_VELOCITY;WIND_ANGLE;RR_COEFFICIENT;SLOP";
    private final static String FILE_SEPARATOR = ";";
    private final static String FILE_COMMENT = "#";
    private final static int EXPECTED_NUMBER_OF_COLUMNS = 6;
  
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
        if (!header.equalsIgnoreCase(TEMPLATE_HEADER)) {
            throw new IOException("Header not according to expected.");
        }
    }

    @Override
    public Link readElement() {
        String line = scanner.nextLine();
        String[] attributes = line.split(FILE_SEPARATOR);
        
        if (attributes.length != EXPECTED_NUMBER_OF_COLUMNS)            
            return null;
        
        Link link;
        try {
            link = new Link(
                    Long.parseLong(attributes[0]),  // from id
                    Long.parseLong(attributes[1]),  // to id
                    Double.parseDouble(attributes[2]),  // wind velocity
                    Double.valueOf(attributes[3]),  // wind angle
                    Double.valueOf(attributes[4]),  // rr coefficient
                    Double.valueOf(attributes[5]) );  // slope
            return link;
        } catch (IllegalArgumentException e) {
            return null;
        }
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
