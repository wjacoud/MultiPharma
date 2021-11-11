package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DeliveryRunByScooter {

    private final double totalWeightOfOrders;
    private EnergyDetails byDistance;
    private EnergyDetails byEnergySpent;
    private Courier courier;
    private Scooter scooter;

    public DeliveryRunByScooter(double totalWeightOfOrders, EnergyDetails byDistance, EnergyDetails byEnergySpent) {
        this.totalWeightOfOrders = totalWeightOfOrders;
        this.byDistance = byDistance;
        this.byEnergySpent = byEnergySpent;
        this.courier = null;
        this.scooter = null;
    }

    public double getTotalWeightOfOrders() {
        return this.totalWeightOfOrders;
    }

    public EnergyDetails getByDistance() {
        return this.byDistance;
    }

    public EnergyDetails getByEnergySpent() {
        return this.byEnergySpent;
    }
    
    public void setByDistance(EnergyDetails byDistance) {
        this.byDistance = byDistance;
    }
    
    public void setByEnergySpent(EnergyDetails byEnergySpent) {
        this.byEnergySpent = byEnergySpent;
    }

    public Courier getCourier() {
        return this.courier;
    }

    public Scooter getScooter() {
        return this.scooter;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

}
