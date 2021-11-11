/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.application.SSLEmailService;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.FileType;
import lapr.project.application.Importer;
import lapr.project.application.TimeEstimateImporterFactory;
import lapr.project.application.TimeEstimateImporterService;
import lapr.project.data.CourierDB;

/**
 *
 * @author leona
 */
public class NotifyCourierWithTimeEstimateController {
    
    private final TimeEstimateImporterService service = new TimeEstimateImporterService();
    private final TimeEstimateImporterFactory factory = new TimeEstimateImporterFactory();
    private final String ESTIMATE_FOLDER_PATH = "\\src_c\\files_estimate";
    private final String FLAG_FILE_EXTENSION = ".flag";
    private final String PATH_DELIMITER = "\\";
    private final String EMAIL_SUBJECT = "Notification | Scooter Dock Report";
    
    private CourierDB courierDB = new CourierDB();
    
    public void notifyCourierOfTimeEstimate(){
        
        Importer importer;
        double estimate;
        
        /* Read files in directory */
        String estimate_dir_path = String.format("%s%s", System.getProperty("user.dir"), ESTIMATE_FOLDER_PATH);
        String filename;
        
        File dir = new File(estimate_dir_path);
        //List of all files and directories
        String contents[] = dir.list();
        for(String content : contents) {
            
            /* FALTA - validar formato do nome do ficheiro */
            if (content.endsWith(FLAG_FILE_EXTENSION) ){
                /* Build .data file name */
                String tmp = content.substring(0, content.length() - FLAG_FILE_EXTENSION.length());
                filename = estimate_dir_path + PATH_DELIMITER + tmp;
                
                try{
                    /* Reads from .data file */
                    importer = factory.build(FileType.CSV);
                    estimate = service.importEstimate(filename, importer);
                    
                    /* get Courier Email by his id */
                    String email = courierDB.getCourierEmailByID(service.getCourierId());
                    
                    /* Build Email subject & content */
                    String toEmail = "1171317@isep.ipp.pt";
                    int hour = (int) estimate;
                    double min = estimate - hour;
                    min *= 60;
                    int minutes = (int) min;
                    String body = String.format("Scooter ID: %d, was parked successfuly.\nIs now charging with a time estimate to full charge of %dh:%dm.\n",
                            service.getScooterId(), hour, minutes);
                    
                    /* Send Email */
                    SSLEmailService ssl = new SSLEmailService();
                    ssl.sendEmail(toEmail, EMAIL_SUBJECT, body);
                    
                }catch(IllegalStateException | IllegalArgumentException ise){
                    Logger.getLogger(NotifyCourierWithTimeEstimateController.class.getName()).log(Level.SEVERE, null, ise);
                }
            }
        }
    }
}
