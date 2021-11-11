package lapr.project.model;

import java.util.Objects;
/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class Cliente {

    private Long id;
    private String name;
    private long nif;
    private Integer credito;
    private long moradaID;
    private String utilizadorID;
    private long cartaoCreditoID;

    public Cliente(long id, String name, long nif, int credito, long moradaID, String utilizadorID, long cartaoCreditoID) {
        this.id = id;
        this.name = name;
        this.nif=nif;
        this.credito = credito;
        this.moradaID = moradaID;
        this.utilizadorID = utilizadorID;
        this.cartaoCreditoID = cartaoCreditoID;
    }
    
    public Cliente(String name, long nif, int credito, long moradaID, String utilizadorID, long cartaoCreditoID) {
        this.id = null;
        this.name = name;
        this.nif=nif;
        this.credito = credito;
        this.moradaID = moradaID;
        this.utilizadorID = utilizadorID;
        this.cartaoCreditoID = cartaoCreditoID;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public long getNif() {
        return nif;
    }

    public Integer getCredito() {
        return credito;
    }
    
    public long getMorada() {
        return moradaID;
    }

    public String getUtilizadorID() {
        return utilizadorID;
    }

    public long getCartaoCreditoID() {
        return cartaoCreditoID;
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        if (!Objects.equals(this.credito, other.credito)) {
            return false;
        }
        if (!Objects.equals(this.moradaID, other.moradaID)) {
            return false;
        }
        if (!Objects.equals(this.utilizadorID, other.utilizadorID)) {
            return false;
        }
        if (!Objects.equals(this.cartaoCreditoID, other.cartaoCreditoID)) {
            return false;
        }
        
        return true;
    }
    
    public String resgataCredito() {
        return name + " has " + credito.toString() + " credits.";
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", name=" + name + ", credito=" + credito + ", moradaID=" + moradaID + ", utilizadorID=" + utilizadorID + ", cartaoCreditoID=" + cartaoCreditoID + '}';
    }
}
