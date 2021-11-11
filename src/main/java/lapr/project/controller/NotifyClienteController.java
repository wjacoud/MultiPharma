
package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.OrderDB;
import lapr.project.application.SSLEmailService;
import java.util.ArrayList;
import lapr.project.model.Produto;

/**
 *
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
public class NotifyClienteController {
    
    private OrderDB odb=new OrderDB();
    private ManageFaturaController mfc=new ManageFaturaController();
    
    
    public String notifyClienteOrderSubmit(long ordemId, long faturaid) {
        
        try {
            
           // Order o=odb.getOrder(ordemId);
           // Pharmacy f=fdb.getPharmacy(o.getFarmaciaId());
            /* get Courier Email by his id */
            String email = odb.getClientEmailByOrderId(ordemId);
            
            String EMAIL_SUBJECT="Order successfully submited";
            /* Build Email subject & content */
            String toEmail = email;
            

            String body=mfc.printFatura(faturaid);
    
           //         String.format("Scooter ID: %d, was parked successfuly.\nIs now charging with a time estimate to full charge of %dh:%dm.\n",
           //         service.getScooterId(), hour, minutes);

            /* Send Email */
            SSLEmailService ssl = new SSLEmailService();
            ssl.sendEmail(toEmail, EMAIL_SUBJECT, body);
            return body;
        } catch (IllegalStateException | IllegalArgumentException ise) {
            Logger.getLogger(NotifyClienteController.class.getName()).log(Level.SEVERE, null, ise);
        }
        return "";
    }
}
