package lapr.project.model;

import java.sql.Date;

/**
 * @author Duarte
 */
public class Fatura {

    private long id;
    private Date dataemit;
    private long vSemIva;
    private long vIva;
    private long vTotal;
    private long ordemId;

    public Fatura(Date d, long vsIva, long viva, long vtotal, long oid) {
        this.id = 0;
        this.dataemit = new Date(d.getTime());
        this.vSemIva = vsIva;
        this.vIva = viva;
        this.vTotal = vtotal;
        this.ordemId = oid;
    }

    public Fatura(Long id, Date d, long vsIva, long viva, long vtotal, long oid) {
        this.id = id;
        this.dataemit = new Date(d.getTime());
        this.vSemIva = vsIva;
        this.vIva = viva;
        this.vTotal = vtotal;
        this.ordemId = oid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataEmit() {
        return new Date(dataemit.getTime());
    }

    public void setData_emit(Date d) {
        this.dataemit = new Date(d.getTime());
    }

    public long getvSemIva() {
        return vSemIva;
    }

    public long getvIva() {
        return vIva;
    }

    public long getvTotal() {
        return vTotal;
    }

    public long getOrdemId() {
        return ordemId;
    }

}
