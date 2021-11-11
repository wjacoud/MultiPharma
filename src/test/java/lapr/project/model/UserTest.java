/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class UserTest {
    
    private final User user = new User("email@sample", "password");
    
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = user;
        String expResult = "email@sample";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPass method, of class User.
     */
    @Test
    public void testGetPass() {
        System.out.println("getPass");
        User instance = user;
        String expResult = "password";
        String result = instance.getPass();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User("fake@news.us", "password");
        int expResult = "fake@news.us".hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new User("fake@news.us", "qwerty");
        User instance = new User("fake@news.us", "querty");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = new User("fake@news.us", "qwerty");
        User instance = new User("email@sample", "querty");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Object obj = null;
        User instance = new User("email@sample", "querty");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals04");
        Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);        
        User instance = new User("email@sample", "querty");
        boolean expResult = false;
        boolean result = instance.equals(address);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals05");
        User instance = new User("email@sample", "querty");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class User.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User("email@sample", "querty");
        String expResult = "User{" + "email=email@sample, pass=querty}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
