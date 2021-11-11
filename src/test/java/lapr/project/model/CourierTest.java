package lapr.project.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class CourierTest {
    
    public CourierTest() {
    }

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
     * Test of getId method, of class Courier.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEname method, of class Courier.
     */
    @Test
    public void testGetEname() {
        System.out.println("getEname");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        String expResult = "Sebastiao";
        String result = instance.getEname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Courier.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        long expResult = 1234567;
        long result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNiss method, of class Courier.
     */
    @Test
    public void testGetNiss() {
        System.out.println("getNiss");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        long expResult = 1234567;
        long result = instance.getNiss();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class Courier.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        long expResult = 120;
        long result = instance.getWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Courier.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        User expResult = new User("fake@news.us", "pass123");
        User result = instance.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Courier.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Long id = instance.getId();
        int expResult = id.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Object obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Object obj = new Courier(2L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals03");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Scooter obj = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals04");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        User obj = new User("email@email.com", "qwerty");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals05");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class Courier.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Courier instance = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        System.out.println(instance);
        String expResult = "Courier{id=1, ename=Sebastiao, nif=1234567, niss=1234567, weight=120, user=User{email=fake@news.us, pass=pass123}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
