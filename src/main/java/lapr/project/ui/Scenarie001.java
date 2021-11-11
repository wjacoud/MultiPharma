package lapr.project.ui;

import java.util.List;
import lapr.project.controller.AssociateAddressToClientRegistryController;
import lapr.project.controller.AssociateCartaoCreditoToClientRegistryController;
import lapr.project.controller.AssociateUserToClientRegistryController;
import lapr.project.controller.SignUpController;
import lapr.project.model.Address;
import lapr.project.model.CartaoCredito;
import lapr.project.model.Cliente;
import lapr.project.model.User;

/**
 * Criar utilizadores 
 */
public class Scenarie001 {

    public Scenarie001() {
    }

    public boolean run() {
        ClientImporterCSV ciCSV = new ClientImporterCSV();
        ClientImporterService ciService = new ClientImporterService();
        int number = ciService.importEstimate(String.format("%s%s", System.getProperty("user.dir"), "/test_csv/client.csv"), ciCSV);
        List<Cliente> listClient = ciService.getListClient();
        List<Address> listAddress = ciService.getListAddress();
        List<CartaoCredito> listCreditCard = ciService.getListCreditCard();
        List<User> listUser = ciService.getListUser();

        AssociateAddressToClientRegistryController aacrC = new AssociateAddressToClientRegistryController();
        AssociateCartaoCreditoToClientRegistryController acccrC = new AssociateCartaoCreditoToClientRegistryController();
        AssociateUserToClientRegistryController aucrC = new AssociateUserToClientRegistryController();
        SignUpController suC = new SignUpController();

        for (int i = 0; i < number; i++) {
            long moradaID = aacrC.addAddress(listAddress.get(i).getCountry(), listAddress.get(i).getDistrict(), listAddress.get(i).getLocation(), listAddress.get(i).getZipCode(), listAddress.get(i).getLatitude(), listAddress.get(i).getLongitude());
            long cartaoCreditoID = acccrC.addCartaoCredito(listCreditCard.get(i).getNumero(), listCreditCard.get(i).getCvv(), listCreditCard.get(i).getValidade());
            String userID = aucrC.addUser(listUser.get(i).getEmail(), listUser.get(i).getPass());
            suC.signUp(listClient.get(i).getName(), listClient.get(i).getNif(), listClient.get(i).getCredito(), moradaID, userID, cartaoCreditoID);
        }

        return true;
    }
}
