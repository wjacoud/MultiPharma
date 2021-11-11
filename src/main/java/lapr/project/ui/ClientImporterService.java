package lapr.project.ui;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.application.Importer;
import lapr.project.model.Address;
import lapr.project.model.CartaoCredito;
import lapr.project.model.Cliente;
import lapr.project.model.User;

public class ClientImporterService {

    // Clients attributes
    private final static int NAME_INDEX = 0;
    private final static int NIF_INDEX = 1;
    private final static int CREDIT_INDEX = 2;

    // Address attributes
    private final static int COUNTRY_INDEX = 3;
    private final static int DISTRICT_INDEX = 4;
    private final static int LOCALITY_INDEX = 5;
    private final static int ZIP_CODE_INDEX = 6;
    private final static int LATITUDE_INDEX = 7;
    private final static int LONGITUDE_INDEX = 8;

    // User attributes
    private final static int EMAIL_INDEX = 9;
    private final static int PASS_INDEX = 10;

    // Credit Card attributes 
    private final static int NUMBER_INDEX = 11;
    private final static int CVV_INDEX = 12;
    private final static int VALIDATE_INDEX = 13;

    private List<Cliente> listClient = new ArrayList<>();
    private List<Address> listAddress = new ArrayList<>();
    private List<CartaoCredito> listCreditCard = new ArrayList<>();
    private List<User> listUser = new ArrayList<>();

    public int importEstimate(String filename, Importer importer) {
        int numberLines = 0;
        try {
            importer.begin(filename);

            while (importer.hasNextElement()) {
                numberLines++;
                String[] line = importer.readElement();
                // Address
                listAddress.add(new Address(line[COUNTRY_INDEX], line[DISTRICT_INDEX], line[LOCALITY_INDEX], line[ZIP_CODE_INDEX], Double.parseDouble(line[LATITUDE_INDEX]), Double.parseDouble(line[LONGITUDE_INDEX]), 0.0));

                // User
                listUser.add(new User(line[EMAIL_INDEX], line[PASS_INDEX]));

                // Credit Card
                //Date date1 = new SimpleDateFormat("yyyy").parse(sDate1);  
                listCreditCard.add(new CartaoCredito(Long.parseLong(line[NUMBER_INDEX]), Long.parseLong(line[CVV_INDEX]), Date.valueOf(line[VALIDATE_INDEX])));

                listClient.add(new Cliente(-1L, line[NAME_INDEX], Long.parseLong(line[NIF_INDEX]), Integer.parseInt(line[CREDIT_INDEX]), 0, "naosei", 0));
            }
            importer.end();

        } catch (final IOException e) {
            Logger.getLogger(ClientImporterService.class.getName()).log(Level.SEVERE, null, "\tProblem importing estimate: " + e.getMessage() + "\n");
        } finally {
            importer.cleanup();
        }
        return numberLines;
    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();
    }

    public List<Cliente> getListClient() {
        return new ArrayList<>(listClient);
    }

    public List<Address> getListAddress() {
        return new ArrayList<>(listAddress);
    }

    public List<CartaoCredito> getListCreditCard() {
        return new ArrayList<>(listCreditCard);
    }

    public List<User> getListUser() {
        return new ArrayList<>(listUser);
    }

}
