package lapr.project.data;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DeliveryDB extends DataHandler {

    public long addDelivery(Date dateUso, Date dataFim, long scooterID, long estafetaID, long pesoTotal, long energiaGasta) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call ? = makeRun(?,?,?,?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setDate(2, dateUso);
            callStmt.setDate(3, dataFim);
            callStmt.setLong(4, scooterID);
            callStmt.setLong(5, estafetaID);
            callStmt.setLong(6, pesoTotal);
            callStmt.setLong(7, energiaGasta);

            callStmt.execute();
            Long trajetosID = callStmt.getLong(1);

            callStmt.close();
            closeAll();
            
            return trajetosID;
        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return -1L;
        }
    }

    public void generatedRouteDetail(Date dataEntrega, long orderID, long trajectoID) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call generatedRouteDetail(?,?,?) }");

            callStmt.setDate(1, dataEntrega);
            callStmt.setLong(2, orderID);
            callStmt.setLong(3, trajectoID);

            callStmt.execute();
            
            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Error found");
    }
}
