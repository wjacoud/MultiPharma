/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 *
 * @author leona
 */
public class Link {
    
    private Long fromAddressId;
    private Long toAddressId;
    private double length;
    private double wind_velovity;
    private double wing_angle;
    private double road_rolling_resistance_coefficient;
    private double road_slope_perc;
    private double energyWattH;

    public Link(Long fromAddressId, Long toAddressId, double wind_velovity, double wing_angle, double road_rolling_resistance_coefficient, double road_slope_angle) {
        
        checkAttributes(fromAddressId, toAddressId, wind_velovity, wing_angle, road_rolling_resistance_coefficient, road_slope_angle);
        
        this.fromAddressId = fromAddressId;
        this.toAddressId = toAddressId;
        this.length = 0.0;
        this.wind_velovity = wind_velovity;
        this.wing_angle = wing_angle;
        this.road_rolling_resistance_coefficient = road_rolling_resistance_coefficient;
        this.road_slope_perc = road_slope_angle;
        energyWattH = 0.0;
    }

    public Long getFromAddressId() {
        return this.fromAddressId;
    }

    public Long getToAddressId() {
        return this.toAddressId;
    }

    public double getLength() {
        return this.length;
    }

    public double getWind_velovity() {
        return this.wind_velovity;
    }

    public double getWing_angle() {
        return this.wing_angle;
    }

    public double getRoad_rolling_resistance_coefficient() {
        return this.road_rolling_resistance_coefficient;
    }

    public double getRoad_slope_perc() {
        return this.road_slope_perc;
    }

    public double getEnergyWattH() {
        return energyWattH;
    }
    
    public void setLength(double length) {
        this.length = length;
    }

    public void setEnergyWattH(double energyWattH) {
        this.energyWattH = energyWattH;
    }

    private static void checkAttributes(Long fromAddressId, Long toAddressId, double wind_velovity, double wing_angle, double road_rolling_resistance_coefficient, double road_slope_angle){
        
        if(fromAddressId == null
            || toAddressId == null
            || fromAddressId <= 0L 
            || toAddressId <= 0L
            || wind_velovity < 0
            || wing_angle < 0 
            || road_rolling_resistance_coefficient < 0
            || road_slope_angle > 180 
            || road_slope_angle < 0 ){
            throw new IllegalArgumentException("Error: Link with Invalid arguments!");
        }
    }        
}
