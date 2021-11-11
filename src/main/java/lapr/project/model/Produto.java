/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Duarte
 */
public class Produto {
    private Long id;
    private String nome;
    private long peso;
    private long preco;
    private long qtd;
   
    public Produto(long id, long qtd){
    this.id=id;
    this.qtd=qtd;
    }
    public Produto(long id, String nome, long peso, long preco, long qtd){
    this.id=id;
    this.qtd=qtd;
    this.nome=nome;
    this.peso=peso;
    this.preco=preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPeso() {
        return this.peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

    public long getPreco() {
        return this.preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }


    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
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

        final Produto other = (Produto) obj;

        return this.id==other.getId();
    }
    
}
