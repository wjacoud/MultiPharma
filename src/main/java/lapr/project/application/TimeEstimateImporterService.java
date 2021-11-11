/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leona
 */
public class TimeEstimateImporterService {
    
    private final int IDX_SCOOTER_ID = 1;
    private final int IDX_COURIER_ID = 2;
    private final int IDX_ESTIMATE = 3;
    
    private long courierId = 0L;
    private long scooterId = 0L;
    
    
    public double importEstimate(String filename, Importer importer){
        
        double estimate = -1;                
        try {
            importer.begin(filename);
            /* Despite only reading one line of content */
            while(importer.hasNextElement()) {
                String[] line = importer.readElement();
                /* Save estimate on DB? ... */
                /* Get attrivutes from line */
                estimate = Double.valueOf(line[IDX_ESTIMATE]);
                courierId = Long.valueOf(line[IDX_COURIER_ID]);
                scooterId = Long.valueOf(line[IDX_SCOOTER_ID]);
                
            }
            importer.end();
            
        } catch (final IOException e) {
            Logger.getLogger(TimeEstimateImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return estimate;
    }
    
    public boolean deleteFile(String filename){
        File file = new File(filename);
        return file.delete();
    }
    
    public long getCourierId(){
        return this.courierId;
    }
    
    public long getScooterId(){
        return this.scooterId;
    }
}
