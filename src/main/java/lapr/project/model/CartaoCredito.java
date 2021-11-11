package lapr.project.model;

import java.sql.Date;
import java.util.Objects;
import lapr.project.data.CartaoCreditoDB;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class CartaoCredito {

    private Long id;
    private long numero;
    private long cvv;
    private Date validade;

    public CartaoCredito(long id, long numero, long cvv, Date validade) {
        this.id = id;
        this.numero = numero;
        this.cvv = cvv;
        this.validade = new Date(validade.getTime());
    }
    
    public CartaoCredito(long numero, long cvv, Date validade) {
        this.numero = numero;
        this.cvv = cvv;
        this.validade = new Date(validade.getTime());
    }

    public long getId() {
        return this.id;
    }

    public long getNumero() {
        return this.numero;
    }

    public long getCvv() {
        return this.cvv;
    }

    public Date getValidade() {
        return new Date(validade.getTime());
    }
      @Override
    public int hashCode() {
        return this.id.hashCode();
    }
      @Override // equals Credit
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
        final CartaoCredito other = (CartaoCredito) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (this.cvv != other.cvv) {
            return false;
        }
        if (!Objects.equals(this.validade, other.validade)) {
            return false;
        }
        return true;
    }
}
