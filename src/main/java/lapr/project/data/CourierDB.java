package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class CourierDB extends DataHandler {

    /**
     * Adiciona um Courier especificado à tabela "Courier".
     *
     * @param sid o identificador da scouter.
     * @param peso o "pesp" da scouter.
     * @param type o tipo da scouter.
     * @param pharmacyID o tipo da scouter.
     */
    public long addCourier(String ename, long nif, long niss, long weight, String emailUser, String passUser) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addCourier" armazenado
             *  na BD.
             *
             *  PROCEDURE addCourier(ename VARCHAR, nif LONG, niss LONG, weight LONG, emailUser VARCHAR, passUser VARCHAR)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addCourier(?,?,?,?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setString(2, ename);
            callStmt.setLong(3, nif);
            callStmt.setLong(4, niss);
            callStmt.setLong(5, weight);
            callStmt.setString(6, emailUser);
            callStmt.setString(7, passUser);

            callStmt.execute();

            Long courierID = callStmt.getLong(1);

            callStmt.close();
            closeAll();

            return courierID;
        } catch (SQLException e) {
            closeAll();
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return -1L;
        }
    }
    
    public String getCourierEmailByID(long id) {
        
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getEmailEstafetaById(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            // Especifica o parâmetro de entrada da função "getEmailEstafetaById".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getEmailEstafetaById".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            String courierEmail = callStmt.getString(1);
            
            callStmt.close();
            closeAll();
            
            return courierEmail;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
        throw new IllegalArgumentException("No Courier with ID: " + id);
        
    }
    

}
