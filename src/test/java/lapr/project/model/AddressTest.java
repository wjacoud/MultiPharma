package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author leona
 */
public class AddressTest {

    private final Address address = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getPk method, of class Address.
     */
    @Test
    public void testGetPk() {
        System.out.println("getPk");
        Address instance = new Address(1L, "Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350);

        Long expResult = 1L;
        Long result = instance.getPk();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Address.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Address instance = address;

        String expResult = "Portugal";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistrict method, of class Address.
     */
    @Test
    public void testGetDistrict() {
        System.out.println("getDistrict");
        Address instance = address;

        String expResult = "Porto";
        String result = instance.getDistrict();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocation method, of class Address.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Address instance = address;

        String expResult = "Prelada";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZipCode method, of class Address.
     */
    @Test
    public void testGetZipCode() {
        System.out.println("getZipCode");
        Address instance = address;

        String expResult = "4250-526";
        String result = instance.getZipCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Address.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Address instance = address;

        double expResult = 41.170350;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class Address.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Address instance = address;

        double expResult = -8.631350;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getElevation method, of class Address.
     */
    @Test
    public void testGetElevation() {
        System.out.println("getElevation");
        Address instance = new Address("Country", "District", "Location", "4250-526", 41.170350, -8.631350, 120.5);
        double expResult = 120.5;
        double result = instance.getElevation();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of compareTo method, of class Address.
     */
    @Test
    public void testCompareTo01() {
        System.out.println("compareTo");
        Address a = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        int expResult = 0;
        int result = a.compareTo(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Address.
     */
    @Test
    public void testCompareTo02() {
        System.out.println("compareTo");
        Address a = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        Address b = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -7.631350, 0.0);
        int expResult = -1;
        int result = a.compareTo(b);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Address.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Address instance = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        Double lat = 41.170350;
        Double lot = -8.631350;
        int expResult = lat.hashCode() + lot.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object obj = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 1.0);
        Address instance = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 1.0);
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
        Object obj = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        Address instance = new Address("Nardia", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
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
        Scooter obj = null;
        Address instance = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
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
        Courier obj = new Courier(1L, "Sebastiao", 1234567, 1234567, 120, "fake@news.us", "pass123");
        Address instance = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
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
        Address instance = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 1.0);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals06() {
        System.out.println("equals05");
        Address instance = new Address("Country", "District", "Location", "4250-526", 41.170350, -8.631350, 0.0);
        Address country = new Address("Country1", "District", "Location", "4250-526", 41.170350, -8.631350, 0.0);
        Address district = new Address("Country", "District1", "Location", "4250-526", 41.170350, -8.631350, 0.0);
        Address location = new Address("Country", "District", "Location1", "4250-526", 41.170350, -8.631350, 0.0);
        Address zipcode = new Address("Country", "District", "Location", "4250-527", 41.170350, -8.631350, 0.0);
        Address latitude = new Address("Country", "District", "Location", "4250-526", 42.170350, -8.631350, 0.0);
        Address longitude = new Address("Country", "District", "Location", "4250-526", 41.170350, -9.631350, 0.0);
        Address elevation = new Address("Country", "District", "Location", "4250-526", 41.170350, -8.631350, 1.0);

        boolean expResult = false;
        boolean resultCountry = instance.equals(country);
        boolean resultDistrict = instance.equals(district);
        boolean resultLocation = instance.equals(location);
        boolean resultZipCode = instance.equals(zipcode);
        boolean resultLatitude = instance.equals(latitude);
        boolean resultLongitude = instance.equals(longitude);
        boolean resultElevation = instance.equals(elevation);

        assertEquals(expResult, resultCountry);
        assertEquals(expResult, resultDistrict);
        assertEquals(expResult, resultLocation);
        assertEquals(expResult, resultZipCode);
        assertEquals(expResult, resultLatitude);
        assertEquals(expResult, resultLongitude);
        //assertEquals(expResult, resultElevation);
    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address instance = new Address(1L, "Country", "District", "Location", "ZipCode", 41.0, -8.0);
        System.out.println(instance);
        String expResult = String.format("Address #%d%nzipCode = %s%nlatitude = %s%nlongitude = %s%n", 1L, "ZipCode", 41.0, -8.0);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
