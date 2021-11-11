/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author leona
 */
public class TimeEstimateImporterCSVTest {
    
    private final TimeEstimateImporterCSV importerCSV;
    private final String TEST_FILE_PATH = "/src/test/resources/estimate_2021_01_16_10_54_12.data";
    private final String TEST_ERROR_FILE_PATH = "/src/test/resources/estimate_0000_00_00_00_00_00.data";
    private final String TEST_FLAG_FILE_PATH = "/src/test/resources/estimate_2021_01_16_10_54_12.data.flag";
    private final String full_test_file_path;
    private final String full_test_error_file_path;
    private final String full_test_flag_file_path;
    
    public TimeEstimateImporterCSVTest() {
        importerCSV = new TimeEstimateImporterCSV();
        full_test_file_path = String.format("%s%s", System.getProperty("user.dir"), TEST_FILE_PATH);
        full_test_flag_file_path = String.format("%s%s", System.getProperty("user.dir"), TEST_FLAG_FILE_PATH);
        full_test_error_file_path = String.format("%s%s", System.getProperty("user.dir"), TEST_ERROR_FILE_PATH);
    }
    
    
    /**
     * Test of begin method, of class TimeEstimateImporterCSV.
     */
    public void testBegin1() throws Exception {
        System.out.println("begin");
        String filename = "";
        assertThrows(IOException.class,
            ()->{ 
                importerCSV.begin(filename); 
            });
    }
    
    /**
     * Test of begin method, of class TimeEstimateImporterCSV.
     */
    public void testBegin2() throws Exception {
        System.out.println("begin");
        String filename = full_test_file_path;
        importerCSV.begin(filename);
        assertTrue(true);
    }

    /**
     * Test of readElement method, of class TimeEstimateImporterCSV.
     */
    @Test
    public void testReadElement() throws Exception{
        System.out.println("readElement");
        
        String filename = full_test_error_file_path;
        importerCSV.begin(filename);
        
        String[] expResult = new String[0];
        String[] result = importerCSV.readElement();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of hasNextElement method, of class TimeEstimateImporterCSV.
     */
    @Test
    public void testHasNextElement() {
        System.out.println("hasNextElement");
        try{
            String filename = full_test_file_path;
            importerCSV.begin(filename);
        }catch(IOException ioe){
            
        }
        importerCSV.hasNextElement();
        importerCSV.readElement();
        boolean result = importerCSV.hasNextElement();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of end method, of class TimeEstimateImporterCSV.
     */
    @Test
    public void testEnd() {
        System.out.println("end");
        try{
            String filename = full_test_file_path;
            importerCSV.begin(filename);
        }catch(IOException ioe){
          
        }
        importerCSV.end();
    }

    /**
     * Test of cleanup method, of class TimeEstimateImporterCSV.
     */
    @Test
    public void testCleanup() {
        System.out.println("cleanup");
        try{
            String filename = full_test_file_path;
            importerCSV.begin(filename);
        }catch(IOException ioe){
            
        }
        importerCSV.end();
        importerCSV.cleanup();
    }
    
}
