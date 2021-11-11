package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class Scooter {

    private final Long id;
    private final long backpackCapacity;
    private final boolean operatingStatus;
    private final long pharmacyID;
    private final ScooterType scooterType;

    public Scooter(Long id, long backpackCapacity, boolean operatingStatus, long pharmacyID, ScooterType scooterType) {
        this.id = id;
        this.backpackCapacity = backpackCapacity;
        this.operatingStatus = operatingStatus;
        this.pharmacyID = pharmacyID;
        this.scooterType = scooterType;
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

    public ScooterType getScooterType() {
        return this.scooterType;
    }

    public ScooterType copyDroneType() {
        return this.scooterType.copyScooterType();
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

        final Scooter other = (Scooter) obj;

        return this.id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Scooter{" + "id=" + this.id + ", backpackCapacity=" + this.backpackCapacity + ", operatingStatus=" + this.operatingStatus + ", pharmacyID=" + this.pharmacyID + ", scooterType=" + this.scooterType + '}';
    }
    
}
