package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CalculatorExample Class Unit Testing.
 *
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
public class CalculatorExampleTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Ensure second operand can assume a negative value.
     */
    @Test
    public void ensureSecondNegativeOperandWorks() {
        int expected = 5;
        int firstOperand = 10;
        int secondOperand = -5;
        CalculatorExample calculator = new CalculatorExample();
        int result = calculator.sum(firstOperand, secondOperand);
        assertEquals(expected, result);
    }

    /**
     * Test of sum method, of class CalculatorExample.
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        int firstOperand = 1;
        int secondOperand = 1;
        CalculatorExample instance = new CalculatorExample();
        int expResult = 2;
        int result = instance.sum(firstOperand, secondOperand);
        assertEquals(expResult, result);
    }

    /**
     * Test of distance method, of class CalculatorExample.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        Address add1 = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        Address add2 = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        CalculatorExample instance = new CalculatorExample();
        double expResult = 0.0;
        double result = instance.distance(add1, add2);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of distance method, of class CalculatorExample.
     */
    @Test
    public void testDistance02() {
        System.out.println("distance");
        Address add1 = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        Address add2 = new Address("Portugal", "Porto", "Prelada", "4250-526", 42.170350, -8.631350, 0.0);
        CalculatorExample instance = new CalculatorExample();
        double expResult = 111189.5769599889;
        double result = instance.distance(add1, add2);
        assertEquals(expResult, result, 0.001);
    }
    
     /**
     * Test of distance method, of class CalculatorExample.
     */
    @Test
    public void testDistance03() {
        System.out.println("distance");
        Address add1 = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -8.631350, 0.0);
        Address add2 = new Address("Portugal", "Porto", "Prelada", "4250-526", 41.170350, -9.631350, 0.0);
        CalculatorExample instance = new CalculatorExample();
        double expResult = 83698.12450255833;
        double result = instance.distance(add1, add2);
        assertEquals(expResult, result, 0.001);
    }

}

