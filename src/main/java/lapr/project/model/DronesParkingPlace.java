package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DronesParkingPlace {
    
    private final Long id;
    private final long charge;

    public DronesParkingPlace(Long id, Long charge) {
        this.id = id;
        this.charge = charge;
    }

    public Long getId() {
        return this.id;
    }

    public long getCharge() {
        return this.charge;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
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
        
        final DronesParkingPlace other = (DronesParkingPlace) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "DronesParkingPlace{" + "id=" + id + ", charge=" + charge + '}';
    }
}
