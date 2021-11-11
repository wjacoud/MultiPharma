package lapr.project.model;

import java.sql.Date;
import java.util.LinkedList;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class RunDetails {

    public final Date deliveryDate;
    public final Order order;
    public final double energySpent;
    public final LinkedList<Address> pathTaken;

    public RunDetails(Date deliveryDate, Order order, double energySpent, LinkedList<Address> pathTaken) {
        this.deliveryDate = deliveryDate;
        this.order = order;
        this.energySpent = energySpent;
        this.pathTaken = new LinkedList<>(pathTaken);
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public Order getOrder() {
        return this.order;
    }

    public double getEnergySpent() {
        return this.energySpent;
    }

    public LinkedList<Address> getPathTaken() {
        return this.pathTaken;
    }

    @Override
    public String toString() {
        String text = "RunDetails{" + "deliveryDate=" + deliveryDate + ", order=" + order.getId() + ", energySpent=" + energySpent + "\n";
        
        for (Address address : pathTaken) {
            text += address.getLocation() + " -> ";
        }
        
        return text;
    }

}
