/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author leona
 */
public class TimeEstimateImporterFactoryTest {
    
    private final TimeEstimateImporterFactory factory;
    
    public TimeEstimateImporterFactoryTest() {
        factory = new TimeEstimateImporterFactory();
    }
    
    /**
     * Test of build method, of class TimeEstimateImporterFactory.
     */
    @Test
    public void testBuild1() {
        System.out.println("build");
        FileType format = FileType.CSV;
        //Importer expResult = new TimeEstimateImporterCSV();
        Importer result = factory.build(format);
        assertTrue(result instanceof TimeEstimateImporterCSV);
    }
    
    @Test
    public void testBuild2() {
        System.out.println("build");
        FileType format = null;
        Importer result = null;
        Importer expResult = null;
        try{
            result = factory.build(format);
        }catch(IllegalStateException ise){
            
        }
        assertEquals(expResult, result);
    }
}
