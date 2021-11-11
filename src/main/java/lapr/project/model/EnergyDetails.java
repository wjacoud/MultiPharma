package lapr.project.model;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class EnergyDetails {

    public LinkedList<Address> path;
    public Double totalEnergySpent;
    public Double totalDistanceTraveled;

    public EnergyDetails(LinkedList<Address> path, double totalEnergySpent, double totalDistanceTraveled) {
        this.path = new LinkedList<>(path);
        this.totalEnergySpent = totalEnergySpent;
        this.totalDistanceTraveled = totalDistanceTraveled;
    }

    public LinkedList<Address> getPath() {
        return this.path;
    }

    public double getTotalEnergySpent() {
        return this.totalEnergySpent;
    }

    public double getTotalDistanceTraveled() {
        return this.totalDistanceTraveled;
    }

    @Override
    public int hashCode() {
        return this.path.hashCode() + this.totalEnergySpent.hashCode() +
                this.totalDistanceTraveled.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final EnergyDetails other = (EnergyDetails) obj;
        if (Double.doubleToLongBits(this.totalEnergySpent) != Double.doubleToLongBits(other.totalEnergySpent)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalDistanceTraveled) != Double.doubleToLongBits(other.totalDistanceTraveled)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "EnergyDetail{" + "path=" + path + ", totalEnergySpent=" + totalEnergySpent + ", totalDistanceTraveled=" + totalDistanceTraveled + '}';
    }
    
}
