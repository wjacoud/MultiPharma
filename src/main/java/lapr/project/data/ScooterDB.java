package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.ScooterType;
import oracle.jdbc.OracleTypes;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ScooterDB extends DataHandler {

    /**
     * Obter uma scooter type especificado à tabela "tipo_scooter".
     *
     * @param id o do tipo de scooter.
     */
    public ScooterType getScooterType(long id) {

        try {
            ScooterType newScooterType = null;
            /*
             *  Objeto "callStmt" para invocar o procedimento "getScooterType" armazenado
             *  na BD.
             *
             *  PROCEDURE getScooterType(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getScooterType(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setLong(2, id);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                long scooterTypeID = rSet.getLong(1);
                long peso = rSet.getLong(2);
                long velocidade_media = rSet.getLong(3);
                long area_frontral = rSet.getLong(4);
                long bateria_maxima = rSet.getLong(5);
                long potencia_motor = rSet.getLong(6);

                newScooterType = new ScooterType(scooterTypeID, peso, velocidade_media,
                        area_frontral, bateria_maxima, potencia_motor);
            }

            callStmt.close();
            closeAll();

            return newScooterType;
        } catch (SQLException e) {
            closeAll();
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }
    }

    /**
     * Adiciona uma scooter especificado à tabela "Scooter".
     *
     * @param backpackCapacity o a capacidade da mochila da scooter.
     * @param pharmacyID para o id da farmacia.
     * @param scooterTypeID para o id do tipo da scooter.
     */
    public long addScooter(long backpackCapacity, long pharmacyID, long scooterTypeID) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addScooter" armazenado
             *  na BD.
             *
             *  PROCEDURE addScooter(id LONG, backpackCapacity LONG, pharmacyID LONG, scooterTypeID LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addScooter(?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setLong(2, backpackCapacity);
            callStmt.setLong(3, pharmacyID);
            callStmt.setLong(4, scooterTypeID);

            callStmt.execute();

            Long scooterID = callStmt.getLong(1);

            callStmt.close();
            closeAll();

            return scooterID;
        } catch (SQLException e) {
            System.out.println(e);
            closeAll();
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return -1L;
        }
    }

    /**
     * Actualizar a informacao de uma Scooter especificado à tabela "Scooter".
     *
     * @param id o identificador da scooter
     * @param backpackCapacity o capacidade da mochila da scooter
     * @param pharmacyID o idenfiticador da scooter
     * @param scooterTypeID o identificador do type da scooter
     * @return boolean o resultsdo da operacao
     */
    public boolean updateScooter(long id, long backpackCapacity, long pharmacyID, long scooterTypeID) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeScooter" armazenado
             *  na BD.
             *
             *  PROCEDURE removeScooter(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call updateScooter(?,?,?,?) }");

            callStmt.setLong(1, id);
            callStmt.setLong(2, backpackCapacity);
            callStmt.setLong(3, pharmacyID);
            callStmt.setLong(4, scooterTypeID);

            callStmt.execute();

            callStmt.close();
            closeAll();

            return true;
        } catch (SQLException e) {
            closeAll();
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }
    }

    /**
     * Remover uma scooter especificado à tabela "Scooter".
     *
     * @param id o id da scooter a apagar.
     */
    public boolean removeScooter(long id) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeScooter" armazenado
             *  na BD.
             *
             *  PROCEDURE removeScooter(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");

            callStmt.setLong(1, id);

            callStmt.execute();

            callStmt.close();
            closeAll();

            return true;
        } catch (SQLException e) {
            closeAll();
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }
    }

}
