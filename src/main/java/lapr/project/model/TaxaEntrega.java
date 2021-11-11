/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class TaxaEntrega {

    private Long id;
    private Date dataInicio;
    private Date dataFim;
    private long valor;
    private String descricao;

    public TaxaEntrega(long id, Date dataInicio, Date dataFim, long valor, String descricao) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.descricao = descricao;
    }

    public TaxaEntrega(Date dataInicio, Date dataFim, long valor, String descricao) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
       this.id = id;
    }

    public Date getData_inicio() {
        return dataInicio;
    }

    public Date getData_fim() {
        return dataFim;
    }

    public long getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
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
        final TaxaEntrega other = (TaxaEntrega) obj;
        if (this.valor != other.valor) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataFim, other.dataFim)) {
            return false;
        }
        return true;
    }
}
