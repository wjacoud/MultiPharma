package lapr.project.model;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DronesPark extends Park {
    
    public final List<DronesParkingPlace> listDronesParkingPlace;

    public DronesPark(Long id, long voltages, long addressID, long pharmacyId, List<DronesParkingPlace> listScooterParkingPlace) {
        super(id, voltages, addressID, pharmacyId);
        this.listDronesParkingPlace = new ArrayList<>(listScooterParkingPlace);
    }

    public long getMaxDroneCapacity() {
        return this.listDronesParkingPlace.size();
    }

    public List<DronesParkingPlace> getListDronesParkingPlace() {
        return listDronesParkingPlace;
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
        
        final DronesPark other = (DronesPark) obj;
        
        if (this.getId() != other.getId()) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\n + DronesPark{" + "listDronesParkingPlace=" + listDronesParkingPlace + '}';
    }
}
