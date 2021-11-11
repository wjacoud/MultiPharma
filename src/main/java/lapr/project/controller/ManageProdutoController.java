/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;


import java.util.logging.Level;
import lapr.project.data.ProdutoDB;
import lapr.project.model.Produto;
import java.util.logging.Logger;
/**
 *
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
public class ManageProdutoController {
     private ProdutoDB produtodb;

    public ManageProdutoController() {
        this.produtodb = new ProdutoDB();
    }
    
    public Produto addProduto(String nome, long peso, long preco){
        
      try {
            long newid = this.produtodb.addProduto(nome, peso, preco);
            Produto newProd = new Produto(newid, nome, peso, preco, 0L);
            Logger.getLogger(ManageProdutoController.class.getName()).log(Level.SEVERE, null, "\t... Product added with id: " + newid);
            return newProd;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageProdutoController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
}
