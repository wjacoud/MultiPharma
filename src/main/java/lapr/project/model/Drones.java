package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class Drones {

    private final Long id;
    private final long backpackCapacity;
    private final boolean operatingStatus;
    private final long pharmacyID;
    private final DronesType dronesType;

    public Drones(Long id, long backpackCapacity, boolean operatingStatus, long pharmacyID, DronesType dronesType) {
        this.id = id;
        this.backpackCapacity = backpackCapacity;
        this.operatingStatus = operatingStatus;
        this.pharmacyID = pharmacyID;
        this.dronesType = dronesType;
    }

    public Long getId() {
        return this.id;
    }

    public long getBackpackCapacity() {
        return this.backpackCapacity;
    }

    public boolean isDroneOperating() {
        return this.operatingStatus;
    }

    public long getPharmacyID() {
        return this.pharmacyID;
    }

    public DronesType getDronesType() {
        return this.dronesType.copyDroneType();
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

        final Drones other = (Drones) obj;

        return this.id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Drones{" + "id=" + this.id + ", backpackCapacity=" + this.backpackCapacity + ", operatingStatus=" + this.operatingStatus + ", pharmacyID=" + this.pharmacyID + ", dronesType=" + this.dronesType + '}';
    }

}
