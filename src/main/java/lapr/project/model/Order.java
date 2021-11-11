/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Duarte
 */
public class Order {

    private Long id;
    private double peso;
    private Date dataCriacao;
    private double preco;
    private long taxaEntrega;
    private long clienteId;
    private long farmaciaId;
    private ArrayList<Produto> prod;

    public Order() {
        
    }
    
    public Order(long clienteId, ArrayList<Produto> a) {
        this.clienteId = clienteId;
        this.prod = new ArrayList<>(a);
    }

    public Order(long id, double peso, Date dataCriacao, double preco, long txe, long clienteId, long farmaciaId, ArrayList<Produto> a) {
        this.id = id;
        this.peso = peso;
        this.dataCriacao = new Date(dataCriacao.getTime());
        this.preco = preco;
        this.taxaEntrega=txe;
        this.clienteId = clienteId;
        this.farmaciaId = farmaciaId;
        this.prod = new ArrayList<>(a);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getDataCriacao() {
        return new Date(dataCriacao.getTime());
    }

    public void setDataCriacao(Date data) {
        this.dataCriacao = new Date(data.getTime());;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public long getTxEntrega() {
        return this.taxaEntrega;
    }

    public void setTxEtrega(long txe) {
        this.taxaEntrega = txe;
    }
    
    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public long getFarmaciaId() {
        return farmaciaId;
    }

    public void setFarmaciaId(long farmaciaId) {
        this.farmaciaId = farmaciaId;
    }

    public void setProdList(ArrayList<Produto> l) {
        this.prod = new ArrayList<>(l);
    }

    public void addProdToList(Produto p) {
        this.prod.add(p);
    }

    public ArrayList<Produto> getProdList() {
        return new ArrayList<>(this.prod);
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
        
        final Order other = (Order) obj;
        
       return this.id.equals(other.getId());
    }

    
}
