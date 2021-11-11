/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.UserDB;
import lapr.project.model.User;

/**
 *
 * @author leona
 */
public class AssociateUserToClientRegistryController {

    private UserDB userDB = new UserDB();

    public String addUser(final String email, final String password) {

        final User newUser = new User(email, password);
        try {
            String newEmail = userDB.addUser(newUser);
            Logger.getLogger(AssociateUserToClientRegistryController.class.getName()).log(Level.SEVERE, null, "\tA new User was added with Email: " + newEmail + "\n");
            return newEmail;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageOrderController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
}
