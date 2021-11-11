/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import lapr.project.model.Address;

/**
 *
 * @author leona
 */
public class AddressImporterCSV implements AddressImporter{
                                              
    private final static String TEMPLATE_HEADER = "ID;COUNTRY;DISTRICT;LOCATION;ZIPCODE;LATITUDE;LONGITUDE;ELEVATION";
    private final static String FILE_SEPARATOR = ";";
    private final static String FILE_COMMENT = "#";
    private final static int EXPECTED_NUMBER_OF_COLUMNS = 8;
  
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
    public Address readElement() {
        String line = scanner.nextLine();
        String[] attributes = line.split(FILE_SEPARATOR);
        
        if (attributes.length != EXPECTED_NUMBER_OF_COLUMNS)            
            return null;
        
        Address address;
        try {
            address = new Address(
                    attributes[1],  // country
                    attributes[2],  // district
                    attributes[3],  // location
                    attributes[4],  // zip-code
                    Double.valueOf(attributes[5]),  // latitude
                    Double.valueOf(attributes[6]),  // longitude
                    Double.valueOf(attributes[7]) ); // elevation
            return address;
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
