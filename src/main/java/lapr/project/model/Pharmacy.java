package lapr.project.model;

import java.util.Objects;
import lapr.project.data.PharmacyDB;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class Pharmacy {

    private Long id;
    private String name;
    private long nipc;
    private long maxScooters;
    private long maxDrones;
    private long moradaID;
    private long maxCreditos;
    private long incCreditos;


    public Pharmacy(Long id, String name, long nipc, long maxScooters, long maxDrones, long moradaID, long maxCred, long incCred) {
        this.id = id;
        this.name = name;
        this.nipc = nipc;
        this.maxScooters = maxScooters;
        this.maxDrones = maxDrones;
        this.moradaID = moradaID;
        this.maxCreditos=maxCred;
        this.incCreditos=incCred;
    }

    public Pharmacy(String name, long nipc, long maxScooters, long maxDrones, long moradaID, long maxCred, long incCred) {
        this.name = name;
        this.nipc = nipc;
        this.maxScooters = maxScooters;
        this.maxDrones = maxDrones;
        this.moradaID = moradaID;
        this.maxCreditos=maxCred;
        this.incCreditos=incCred;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getNipc() {
        return nipc;
    }

    public long getMaxScooters() {
        return maxScooters;
    }

    public long getMaxDrones() {
        return maxDrones;
    }

    public long getMorada() {
        return moradaID;
    }
    
    public long getMaxCreditos() {
        return maxCreditos;
    }

    public long getIncCreditos() {
        return incCreditos;
    }
    
    public void setId(long id) {
        this.id = id;
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
        final Pharmacy other = (Pharmacy) obj;
        if (this.nipc != other.nipc) {
            return false;
        }
        if (this.maxScooters != other.maxScooters) {
            return false;
        }
        if (this.maxDrones != other.maxDrones) {
            return false;
        }
        if (this.moradaID != other.moradaID) {
            return false;
        }
        if (this.maxCreditos != other.maxCreditos){
            return false;
        }
        if (this.incCreditos != other.incCreditos) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
