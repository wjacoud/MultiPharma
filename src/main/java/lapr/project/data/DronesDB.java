package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.DronesType;
import oracle.jdbc.OracleTypes;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DronesDB extends DataHandler {

    /**
     * Obter um drone type especificado à tabela "tipo_drones".
     *
     * @param id o do tipo de drone.
     */
    public DronesType getDronesType(long id) {
        DronesType newDronesType = null;
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "getDronesType" armazenado
             *  na BD.
             *
             *  PROCEDURE getDronesType(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getDronesType(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setLong(2, id);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                long droneTypeID = rSet.getLong(1);
                long peso = rSet.getLong(2);
                long velocidade_media = rSet.getLong(3);
                long area_frontral = rSet.getLong(4);
                long bateria_maxima = rSet.getLong(5);
                long potencia_motor = rSet.getLong(6);

                newDronesType = new DronesType(droneTypeID, peso, velocidade_media,
                        area_frontral, bateria_maxima, potencia_motor);
            }

            callStmt.close();
        } catch (SQLException e) {
            Logger.getLogger(DronesDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
            return newDronesType;
        }
    }

    /**
     * Adicionar um drone especificado à tabela "Drones".
     *
     * @param backpackCapacity o a capacidade da mochila do drone.
     * @param pharmacyID para o id da farmacia.
     * @param dronesTypeID para o id do tipo do drone.
     */
    public long addDrones(long backpackCapacity, long pharmacyID, long dronesTypeID) {
        Long droneID = -1L;
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addDrone" armazenado
             *  na BD.
             *
             *  PROCEDURE addDrone(id LONG, backpackCapacity LONG, pharmacyID LONG, dronesTypeID LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addDrones(?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setLong(2, backpackCapacity);
            callStmt.setLong(3, pharmacyID);
            callStmt.setLong(4, dronesTypeID);

            callStmt.execute();

            droneID = callStmt.getLong(1);

            callStmt.close();
        } catch (SQLException e) {
            Logger.getLogger(DronesDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
            return droneID;
        }
    }

    /**
     * Actualizar a informacao de um drone especificado à tabela "Drone".
     *
     * @param id o identificador da drone
     * @param backpackCapacity o capacidade da mochila da drone
     * @param pharmacyID o idenfiticador da drone
     * @param droneTypeID o identificador do type da drone
     * @return boolean o resultsdo da operacao
     */
    public boolean updateDrone(long id, long backpackCapacity, long pharmacyID, long droneTypeID) {
        boolean updateStatus = false;
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "updateDrone" armazenado
             *  na BD.
             *
             *  PROCEDURE updateDrone(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call updateDrone(?,?,?,?) }");

            callStmt.setLong(1, id);
            callStmt.setLong(2, backpackCapacity);
            callStmt.setLong(3, pharmacyID);
            callStmt.setLong(4, droneTypeID);

            callStmt.execute();

            callStmt.close();

            updateStatus = true;
        } catch (SQLException e) {
            Logger.getLogger(DronesDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
            return updateStatus;
        }
    }

    /**
     * Remover um drone especificado à tabela "Drone".
     *
     * @param id o id da drone a apagar.
     */
    public boolean removeDrone(long id) {
        boolean removeStatus = false;
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeDroner" armazenado
             *  na BD.
             *
             *  PROCEDURE removeDroner(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?) }");

            callStmt.setLong(1, id);

            callStmt.execute();

            callStmt.close();

            removeStatus = true;
        } catch (SQLException e) {
            Logger.getLogger(DronesDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
            return removeStatus;
        }
    }

}
