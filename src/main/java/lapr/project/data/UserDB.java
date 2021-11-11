package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

/**
 * @author leona
 */
public class UserDB extends DataHandler {

    public User getUserByClientID(long id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUtilizadorByClienteID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getUserByClientID".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getUserByClientID".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String email = rSet.getString(1);
                String password = rSet.getString(2);

                callStmt.close();
                closeAll();

                return new User(email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No User associated with Client ID:" + id);
    }

    public User getUserByID(long id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUtilizador(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "gerUtilizador".
            callStmt.setLong(2, id);

            // Executa a invocação da função "gerUtilizador".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String email = rSet.getString(1);
                String password = rSet.getString(2);

                callStmt.close();
                closeAll();

                return new User(email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No User with ID:" + id);
    }

    public String addUser(User user) {
        return addUser(user.getEmail(), user.getPass());
    }

    public String addUser(String email, String password) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{? = call addUtilizador(?,?)}");

            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            callStmt.setString(2, email);
            callStmt.setString(3, password);

            callStmt.execute();

            // Guarda o inteiro retornado num objeto "ResultSet".
            String userID = callStmt.getString(1);

            callStmt.close();
            closeAll();

            return userID;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            throw new IllegalArgumentException("Unable to insert User, invalid arguments!");
        }
    }

}
