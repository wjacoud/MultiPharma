/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.AddressDB;
import lapr.project.model.Address;

/**
 *
 * @author leona
 */
public class AssociateAddressToClientRegistryController {
    
    private AddressDB addressDB = new AddressDB();
    
    public long addAddress(final String country, final String district, final String location, 
                            final String zipCode, final double latitude, final double longitude){
       
        final Address newAddress = new Address(country, district, location, zipCode, latitude, longitude, 0.0);
        try{
            long newId = this.addressDB.addAddress(newAddress);
            Logger.getLogger(AssociateAddressToClientRegistryController.class.getName()).log(Level.FINE, null, "\tA new Address was added with ID: " + newId + "\n");
            return newId;            
        }catch(IllegalArgumentException iae){
            Logger.getLogger(AssociateAddressToClientRegistryController.class.getName()).log(Level.SEVERE, null, iae.getMessage());            
            return 0L;
        }
    }
}
