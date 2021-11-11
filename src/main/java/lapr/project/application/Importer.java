/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.io.IOException;

/**
 *
 * @author leona
 */
public interface Importer {
    
    /**
     * Initiate the import process. The implementation should open the underlying resource (e.g., file) and read the
     * document "comments" (and ignore them) plus the "header". 
     *
     * @param filename
     * @throws java.io.IOException
     */
    void begin(String filename) throws IOException;

    /**
     * Read one single line from the resource. Returns a String[] with all the 
     * content from a single line separated by ';'. 
     * If not able to produce a String[], then return null.
     * 
     * @return 
     */
    String[] readElement();
    
    /**
     * Indicates if there is another line to read or if we have reached the 
     * end of the resource.
     * 
     * @return true if there is next line
     */
    boolean hasNextElement();

    /**
     * Indicates that there are no more data to import. The implementation should 
     * create any "document closing" element it might need and close the underlying resource.
     */
    void end();

    /**
     * Gives the importer implementation a chance to cleanup in case some exception has occurred.
     */
    void cleanup();
    
}
