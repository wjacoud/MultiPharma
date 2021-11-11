package lapr.project.model;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
public class CalculatorExample {

    /**
     * Calculate the sum of two int numbers.
     *
     * @param firstOperand First number to be added
     * @param secondOperand Second number to be added
     * @return Return the sum of both operands.
     */
    public int sum(int firstOperand, int secondOperand) {
        return firstOperand + secondOperand;
    }

    public double distance(Address add1, Address add2) {
        if ((add1.getLatitude() == add2.getLatitude()) && (add1.getLongitude() == add2.getLongitude())) {
            return 0;
        }

        double theta = add1.getLongitude() - add2.getLongitude();
        double distance = Math.sin(Math.toRadians(add1.getLatitude())) * Math.sin(Math.toRadians(add2.getLatitude())) + Math.cos(Math.toRadians(add1.getLatitude())) * Math.cos(Math.toRadians(add2.getLatitude())) * Math.cos(Math.toRadians(theta));

        distance = Math.acos(distance);
        distance = Math.toDegrees(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344;
        distance = distance * 1000;

        return distance;
    }
}
