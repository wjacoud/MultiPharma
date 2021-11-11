/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.UserDB;
import lapr.project.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author leona
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssociateUserToClientRegistryControllerTest {
    
    @Mock
    private UserDB userDB;
    
    @InjectMocks
    private AssociateUserToClientRegistryController controller;
    
    private User user;
    
    public AssociateUserToClientRegistryControllerTest() {
        controller = new AssociateUserToClientRegistryController();
    }
   
    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        user = new User("sample@email.pt", "qwerty");
        when(userDB.addUser(user)).thenReturn("sample@email.pt");
    }
   
    /**
     * Test of addUser method, of class AssociateUserToClientRegistryController.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String expResult =  "sample@email.pt";
        String result = controller.addUser("sample@email.pt", "qwerty");
        assertEquals(expResult, result);
    }
    
}
