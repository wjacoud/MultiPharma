package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class Park {

    private final Long id;
    private final long voltages;
    private final long addressID;
    private final long pharmacyId;

    public Park(Long id, long voltages, long addressID, long pharmacyId) {
        this.id = id;
        this.voltages = voltages;
        this.addressID = addressID;
        this.pharmacyId = pharmacyId;
    }

    public Long getId() {
        return this.id;
    }

    public long getVoltages() {
        return this.voltages;
    }

    public long getAddressID() {
        return this.addressID;
    }

    public long getPharmacyId() {
        return this.pharmacyId;
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

        final Park other = (Park) obj;

        return this.id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Park{" + "id=" + id + ", voltages=" + voltages + ", addressID=" + addressID + ", pharmacyId=" + pharmacyId + '}';
    }
}
