/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

/**
 *
 * @author leona
 */
public class TimeEstimateImporterFactory {
    
    public Importer build(FileType format) {
     if (format == null) {
         throw new IllegalStateException("Unknown format");
     }

     switch (format) {
     case CSV:
         return new TimeEstimateImporterCSV();
     }
     throw new IllegalStateException("Unknown format");
    }   
}
