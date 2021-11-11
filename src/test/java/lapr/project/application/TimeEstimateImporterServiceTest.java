/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author leona
 */
public class TimeEstimateImporterServiceTest {
    
    private final TimeEstimateImporterService service;
    private final String TEST_FILE_PATH = "/src/test/resources/estimate_2021_01_16_10_54_12.data";
    private final String full_test_file_path;
    
    public TimeEstimateImporterServiceTest() {
        service = new TimeEstimateImporterService();
        full_test_file_path = String.format("%s%s", System.getProperty("user.dir"), TEST_FILE_PATH);
    }    

    /**
     * Test of importEstimate method, of class TimeEstimateImporterService.
     */
    @Test
    public void testImportEstimate1() {
        System.out.println("importEstimate");
        String filename = full_test_file_path;
        Importer importer = new TimeEstimateImporterCSV();
        double expResult = 0.68;
        double result = service.importEstimate(filename, importer);
        assertEquals(expResult, result, 0.00000);
    }
    
    @Test
    public void testImportEstimate2() {
        System.out.println("importEstimate");
        String filename = "";
        Importer importer = new TimeEstimateImporterCSV();
        double expResult = -1.0;
        double result = service.importEstimate(filename, importer);
        assertEquals(expResult, result, 0.00000);
    }

    /**
     * Test of deleteFile method, of class TimeEstimateImporterService.
     */
    @Test
    public void testDeleteFile() {
        System.out.println("deleteFile");
        /*String filename = "";
        TimeEstimateImporterService instance = new TimeEstimateImporterService();
        boolean expResult = false;
        boolean result = instance.deleteFile(filename);
        assertEquals(expResult, result);*/
    }
    
}
