/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.CartaoCreditoDB;
import lapr.project.model.CartaoCredito;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */

public class AssociateCartaoCreditoToClientRegistryController {
    
    private CartaoCreditoDB cartaoCreditoDB;

    public AssociateCartaoCreditoToClientRegistryController() {
        cartaoCreditoDB = new CartaoCreditoDB(); 
    }
    
    public long addCartaoCredito(final long numero, final long cvv, final Date validade){
        
        final CartaoCredito newCartaoCredito = new CartaoCredito(numero, cvv, validade);
        try{
            long novoId = this.cartaoCreditoDB.addCartaoCredito(newCartaoCredito);
            Logger.getLogger(AssociateCartaoCreditoToClientRegistryController.class.getName()).log(Level.FINE, "\tA new Credit Card was added with ID: " + novoId);
            return novoId;
        }catch(IllegalArgumentException iae){
            Logger.getLogger(AssociateCartaoCreditoToClientRegistryController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return -1;
        }
    }
}

