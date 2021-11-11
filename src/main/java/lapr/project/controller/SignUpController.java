/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.ClienteDB;
import lapr.project.model.Cliente;

/**
 *
 * @author leona
 */
public class SignUpController {
    
   private ClienteDB clientDB = new ClienteDB();
    
    public long signUp(final String name, final long nif, final int credits, final long addressID, final String userID, final long creditCardID){
        
        final Cliente newClient = new Cliente(name, nif, credits, addressID, userID, creditCardID);
        try{
            long newId = this.clientDB.addCliente(newClient);
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, "\tA new Client was added with ID: " + newId + "\n");
            return newId; 
        }catch(IllegalArgumentException iae){
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, iae.getMessage());            
            return 0L;
        }
    }
}
