/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.model.Address;
import lapr.project.model.Order;
import lapr.project.model.ScooterType;
import lapr.project.utils.Edge;
import lapr.project.utils.Graph;
import lapr.project.utils.Link;

/**
 *
 * @author leona
 */
public class EstimateEnergyRequiredForDeliveryRunService {
    
    private final double G = 9.80655;           // gravity (m/s2)
    private final double CRR = 0.0063;          // road rolling resistance coefficient FOR asphalt
    private final double AIR_DENSITY = 1.24;    // at 20ºC (kg/m3)
    private final double AIR_DRAG_COEF = 1.1;   // 
    
    private AddressDB addressDB = new AddressDB();
    
    
    public void buildGraphAnew(Graph<Address, Link> graph, String vertexFilePath, String edgesFilePath, AddressImporter addressImporter, LinkImporter linkImporter){
        GraphImporterService.importVertices(graph, vertexFilePath, addressImporter);
        GraphImporterService.importEdges(graph, edgesFilePath, linkImporter);
        //return graph;
    }
    
    public void loadDifferentEdgeConfig(Graph<Address, Link> graph, String edgesFilePath, LinkImporter linkImporter){
        GraphImporterService.importEdges(graph, edgesFilePath, linkImporter);
        //return graph;
    }
    
    public void simulateDeliveryRun(List<Address> addressList){
        
        /* Compute Path for the addresses */
        
    }
  
    public void computeEdgesWeightByLength(Graph<Address, Link> graph, double frontalArea, double totalMass, double sccoterVelocity){
        
        /* Cycle through all the edges */
        Link link;
        for(Edge<Address,Link> edge : graph.edges()){
            link = edge.getElement();
            /* Compute weight */
            link.setEnergyWattH(computeEnergyComsumptionByEdge(link, totalMass, frontalArea, sccoterVelocity));
            double weight = link.getLength();
            edge.setWeight(weight);
        }
    }
    
    public void computeEdgesWeightByEnergy(Graph<Address, Link> graph, double frontalArea, double totalMass, double sccoterVelocity){
        
        /* Cycle through all the edges */
        Link link;
        for(Edge<Address,Link> edge : graph.edges()){
            link = edge.getElement();
            /* Compute weight */
            double weight = computeEnergyComsumptionByEdge(link, totalMass, frontalArea, sccoterVelocity);
            link.setEnergyWattH(weight);
            edge.setWeight(weight);
        }
    }
    
    public void computeEdgesWeightByEnergyDynamic(Graph<Address, Link> graph, double totalMass, double frontalArea, double slope, 
            double crr, double airDragCoef, double scooter_v, double wind_v, double wind_angle){
        
        /* Cycle through all the edges */
        Link link;
        for(Edge<Address,Link> edge : graph.edges()){
            link = edge.getElement();
            /* Compute weight */
            double weight = computeEnergyComsumption(link, totalMass, frontalArea, slope, crr, airDragCoef, scooter_v, wind_v, wind_angle);
            link.setEnergyWattH(weight);
            System.out.println("Watt/H: " + weight);
            edge.setWeight(weight);
        }
    }
    
    /**
     * Computes soocter velocity(km/h), taking in consideration wind velocity(km/h) and direction(º).
     * 
     * @param scooter_v     (km/h)
     * @param wind_v        (km/h)
     * @param wind_angle    (º)
     * @return scooter velocity (m/s)
     */
    public double computeVelocity(double scooter_v, double wind_v, double wind_angle){
        double result = Math.pow(scooter_v,2) + Math.pow(wind_v,2) + (2*scooter_v*wind_v*Math.cos(wind_angle));
        result = Math.sqrt(result);
        /* Convert from km/h to m/s */
        result = (result*1000)/(3600);
        return result;
    }
    
    public double computeEnergyComsumptionByEdge(Link link, double totalMass, double frontalArea, double scooter_v){
        
        double totalRoadLoad;
        double roadSlopeForce;
        double roadLoadFrictionForce;
        double aerodynamicDragForce;
        
        //roadSlopeForce = totalMass*G*Math.sin(Math.toRadians(Math.atan(link.getRoad_slope_angle()/100)));
        roadSlopeForce = computeRoadSlopeForce(totalMass, link.getRoad_slope_perc());
        //System.out.println("roadSlopeForce: " + roadSlopeForce);
        
        //roadLoadFrictionForce = totalMass*G*link.getRoad_rolling_resistance_coefficient()*Math.cos(Math.toRadians(Math.atan(link.getRoad_slope_angle()/100)));
        roadLoadFrictionForce = computeRoadLoadFrictionForce(totalMass, CRR, link.getRoad_slope_perc());
        //System.out.println("roadLoadFrictionForce: " + roadLoadFrictionForce);
        
        double velocity = computeVelocity(scooter_v, link.getWind_velovity(), link.getWing_angle());
        aerodynamicDragForce = computeAerodynamicDragForce(frontalArea, AIR_DRAG_COEF, velocity);
        //System.out.println("aerodynamicDragForce: " + aerodynamicDragForce);
        
        totalRoadLoad = roadSlopeForce + roadLoadFrictionForce + aerodynamicDragForce;
        
        /* Ptot(Total power) =  Ftot*scooter_v*/
        double totalPower = totalRoadLoad*velocity;
        /* compute delta time in seconds */
        double deltaTime = link.getLength()/((scooter_v*1000)/(3600));
        return (totalPower*deltaTime)/3600;
    }
    
    public double computeEnergyComsumption(Link link, double totalMass, double frontalArea, double slope, 
            double crr, double airDragCoef, double scooter_v, double wind_v, double wind_angle){
        
        double totalRoadLoad;
        double roadSlopeForce;
        double roadLoadFrictionForce;
        double aerodynamicDragForce;
        
        roadSlopeForce = computeRoadSlopeForce(totalMass, slope);
        System.out.println("roadSlopeForce: " + roadSlopeForce);
        
        roadLoadFrictionForce = computeRoadLoadFrictionForce(totalMass, crr, slope);
        System.out.println("roadLoadFrictionForce: " + roadLoadFrictionForce);
        
        double velocity = computeVelocity(scooter_v, wind_v, wind_angle);
        aerodynamicDragForce = computeAerodynamicDragForce(frontalArea, airDragCoef, velocity);
        System.out.println("aerodynamicDragForce: " + aerodynamicDragForce);
       
        totalRoadLoad = roadSlopeForce + roadLoadFrictionForce + aerodynamicDragForce;
        
        /* Ptot(Total power) =  Ftot*scooter_v*/
        double totalPower = totalRoadLoad*velocity;
        /* compute delta time in seconds */
        double deltaTime = link.getLength()/((scooter_v*1000)/(3600));
        return (totalPower*deltaTime)/3600;
    }
    
    private double computeRoadSlopeForce(double totalMass, double slope){
        /* Fs = mt*g*sin(rad) & Tan-1(Slope Percent/100) */
        return totalMass*G*Math.sin(Math.toRadians(Math.atan(slope/100)));
    }
    
    private double computeRoadLoadFrictionForce(double totalMass, double crr, double slope){
        /* Fr = mt*g*crr*cos(rad) & Tan-1(Slope Percent/100) */
        return totalMass*G*crr*Math.cos(Math.toRadians(Math.atan(slope/100)));
    }
    
    private double computeAerodynamicDragForce(double frontalArea, double airDragCoef, double velocity){
        /* Fa = 1/2*(p*Cd*A*v2) */
        return 0.5*(AIR_DENSITY*airDragCoef*frontalArea*Math.pow(velocity, 2));
    }
    
    /**
     * Computes de max range for a given scooter type.
     * 
     * @param scooterType
     * @param efficiency    // (in %)
     * @return (in km)
     */
    public double estimateScooterRange(ScooterType scooterType, double efficiency){
        double time = scooterType.getMaxBattery()/scooterType.getPowerUnit();
        return (time*scooterType.getAverageSpeed())*(efficiency/100);
    }
    
    public double estimateEnergySpentForPath(Graph<Address, Link> graph, LinkedList<Address> path){
        double totalEnergy = 0;
        int i;
        for(i=0; i<path.size()-1; i++){
            Edge<Address, Link> edge = graph.getEdge(path.get(i), path.get(i+1));
            Link link = edge.getElement();
            totalEnergy += link.getEnergyWattH();
        }
        return totalEnergy;
    }
}
