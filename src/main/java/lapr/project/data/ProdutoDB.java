package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
public class ProdutoDB extends DataHandler {

    public long addProduto(String nome, long peso, long preco) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call cria_produto(?,?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, nome);
            callStmt.setLong(3, peso);
            callStmt.setLong(4, preco);

            callStmt.execute();

            Long pid = callStmt.getLong(1);

            callStmt.close();
            closeAll();

            return pid;
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Error found");
    }

}
