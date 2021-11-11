package lapr.project.model;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ScootersPark extends Park {

    public final List<ScooterParkingPlace> listScooterParkingPlace;

    public ScootersPark(Long id, long voltages, long addressID, long pharmacyId, List<ScooterParkingPlace> listScooterParkingPlace) {
        super(id, voltages, addressID, pharmacyId);
        this.listScooterParkingPlace = new ArrayList<>(listScooterParkingPlace);
    }

    public long getMaxScooterCapacity() {
        return this.listScooterParkingPlace.size();
    }

    public List<ScooterParkingPlace> getListScooterParkingPlace() {
        return new ArrayList<>(listScooterParkingPlace);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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

        final ScootersPark other = (ScootersPark) obj;

        if (this.getId() != other.getId()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\n + ScootersPark{" + "listScooterParkingPlace=" + listScooterParkingPlace + '}';
    }
}
