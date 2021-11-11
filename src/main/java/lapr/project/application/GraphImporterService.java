/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.HashMap;
import lapr.project.model.Address;
import lapr.project.utils.Link;
import lapr.project.utils.Graph;

/**
 * @author leona
 */
public class GraphImporterService {
    
    private static final HashMap<Long, Address> addressMap = new HashMap<>();
    
    public static<V,E> void importVertices(Graph<Address,Link> graph, String filename, AddressImporter importer){
       
        try{
            importer.begin(filename);
            
            Long counter = 1L;
            while(importer.hasNextElement()) {
                Address address = importer.readElement();
                if (address != null) {
                    graph.insertVertex(address);
                    addressMap.put(counter, address);
                    counter++;
                }
            }
            importer.end();
        }catch(IllegalArgumentException iae){
            Logger.getLogger(GraphImporterService.class.getName()).log(Level.FINE, null, iae.getMessage());
        }catch(IOException ioe){
            Logger.getLogger(GraphImporterService.class.getName()).log(Level.FINE, null, "Error: Problem importing vertices!\n");
        }finally{
            importer.cleanup();
        }
    }
    
    public static<V,E> void importEdges(Graph<Address,Link> graph, String filename, LinkImporter importer){
       
        try{
            importer.begin(filename);
            
            while(importer.hasNextElement()) {
                Link link = importer.readElement();
                
                if (link != null) {
                    
                    Address from = addressMap.get(link.getFromAddressId());
                    Address to = addressMap.get(link.getToAddressId());
                    
                    if (from != null && to != null){
                        
                        /* Compute distance between addresses - Set length of segment */
                        link.setLength(distance(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude()));
                    
                        /* Compute Weight here ?? */
                        double weight = 0.0;
                        graph.insertEdge(from, to, link, weight);
                    }
                }
            }
            importer.end();
        }catch(IllegalArgumentException iae){
            Logger.getLogger(GraphImporterService.class.getName()).log(Level.FINE, null, iae.getMessage());
        }catch(IOException ioe){
            Logger.getLogger(GraphImporterService.class.getName()).log(Level.FINE, null, "Error: Problem importing edges!\n");
        }finally{
            importer.cleanup();
        }
    }
    
     /**
     * Calcula e devolve a distância entre duas coordenadas.
     * @param lat1 Latitude da primeira coordenada
     * @param lon1 Longitude da primeira coordenada
     * @param lat2 Latitude da segunda coordenada
     * @param lon2 Longitude da segunda coordenada
     * @return distância entre as duas coordenadas (double)
     */
    public static double distance (double lat1, double lon1, double lat2, double lon2) {
        // shortest distance over the earth’s surface
        // https://www.movable-type.co.uk/scripts/latlong.html
        final double R = 6371e3;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2)
            + Math.cos(theta1) * Math.cos(theta2)
            * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // distância em metros
        return d;
    }
}
