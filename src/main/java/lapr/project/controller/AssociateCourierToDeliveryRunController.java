package lapr.project.controller;

import lapr.project.application.EstimateEnergyRequiredForDeliveryRunService;
import lapr.project.model.DeliveryRunByScooter;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class AssociateCourierToDeliveryRunController {

    private EstimateEnergyRequiredForDeliveryRunService eERFDRS;

    public boolean AssociateCourierToDeliveryRunController(DeliveryRunByScooter deliveryRunByScooter, Double bateryCharge, Double efficiency) {
        //Current batery charge considering efficiency
        Double currRange = eERFDRS.estimateScooterRange(deliveryRunByScooter.getScooter().getScooterType(), efficiency) * (bateryCharge / 100);

        if (deliveryRunByScooter.getTotalWeightOfOrders() < deliveryRunByScooter.getScooter().getBackpackCapacity()) {
            if (currRange > deliveryRunByScooter.getByEnergySpent().getTotalDistanceTraveled() && currRange > deliveryRunByScooter.getByDistance().getTotalDistanceTraveled()) {
                return true;
            } else if (currRange < deliveryRunByScooter.getByEnergySpent().getTotalDistanceTraveled()) {
                deliveryRunByScooter.setByDistance(null);
                return true;
            } else if (currRange < deliveryRunByScooter.getByDistance().getTotalDistanceTraveled()) {
                deliveryRunByScooter.setByEnergySpent(null);
                return true;
            }
        }
        deliveryRunByScooter.setCourier(null);
        deliveryRunByScooter.setScooter(null);
        return false;
    }
}
