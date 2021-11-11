package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.DronesParkingPlace;
import lapr.project.model.ScooterParkingPlace;
import oracle.jdbc.OracleTypes;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class ParkDB extends DataHandler {

    /**
     * Obter uma lista de lugares de estacionamento de scooters especificado à
     * tabela "LUGAR_ESTACIONAMENTO_SCOOTER".
     *
     * @param id o do parque.
     */
    public List<ScooterParkingPlace> getScooterParkingPlace(long id) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "getScooterParkingPlace" armazenado
             *  na BD.
             *
             *  PROCEDURE getScooterParkingPlace(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getLugarEscationamentoScooters(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setLong(2, id);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            List<ScooterParkingPlace> listScooterParkingPlace = new ArrayList<>();
            while (rSet.next()) {
                long estacionamentoID = rSet.getLong(1);
                long carga = rSet.getLong(2);

                ScooterParkingPlace scooterParkingPlace = new ScooterParkingPlace(estacionamentoID, carga);
                listScooterParkingPlace.add(scooterParkingPlace);
            }

            callStmt.close();
            closeAll();

            return listScooterParkingPlace;
        } catch (SQLException e) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }
    }

    /**
     * Obter uma lista de lugares de estacionamento de drones especificado à
     * tabela "LUGAR_ESTACIONAMENTO_DRONES".
     *
     * @param id o do parque.
     */
    public List<DronesParkingPlace> getDronesParkingPlace(long id) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "getScooterParkingPlace" armazenado
             *  na BD.
             *
             *  PROCEDURE getScooterParkingPlace(id LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getLugarEscationamentoDrones(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setLong(2, id);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            List<DronesParkingPlace> listDronesParkingPlace = new ArrayList<>();
            while (rSet.next()) {
                long estacionamentoID = rSet.getLong(1);
                long carga = rSet.getLong(2);

                DronesParkingPlace dronesParkingPlace = new DronesParkingPlace(estacionamentoID, carga);
                listDronesParkingPlace.add(dronesParkingPlace);
            }

            callStmt.close();
            closeAll();

            return listDronesParkingPlace;
        } catch (SQLException e) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
            closeAll();
            return null;
        }
    }

    /**
     * Adicionar um parque especificado à tabela "Parque".
     *
     * @param backpackCapacity o a capacidade da mochila da scooter.
     * @param pharmacyID para o id da farmacia.
     * @param scooterTypeID para o id do tipo da scooter.
     */
    public long addParkForScooters(long addressID, long pharmacyID, long voltage, long maxScooterCapacity, long charge, long noCharge) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addScooter" armazenado
             *  na BD.
             *
             *  PROCEDURE addScooter(id LONG, backpackCapacity LONG, pharmacyID LONG, scooterTypeID LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addScootersPark(?,?,?,?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setLong(2, addressID);
            callStmt.setLong(3, pharmacyID);
            callStmt.setLong(4, voltage);
            callStmt.setLong(5, maxScooterCapacity);
            callStmt.setLong(6, charge);
            callStmt.setLong(7, noCharge);

            callStmt.execute();

            Long scooterID = callStmt.getLong(1);

            callStmt.close();
            closeAll();

            return scooterID;
        } catch (SQLException e) {
            closeAll();
            System.out.println(e);
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return -1L;
        }
    }

    /**
     * Adicionar um parque especificado à tabela "Parque".
     *
     * @param backpackCapacity o a capacidade da mochila da scooter.
     * @param pharmacyID para o id da farmacia.
     * @param scooterTypeID para o id do tipo da scooter.
     */
    public long addParkForDrones(long addressID, long pharmacyID, long voltage, long maxScooterCapacity, long charge, long noCharge) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addScooter" armazenado
             *  na BD.
             *
             *  PROCEDURE addScooter(id LONG, backpackCapacity LONG, pharmacyID LONG, scooterTypeID LONG)
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addDronesPark(?,?,?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setLong(2, addressID);
            callStmt.setLong(3, pharmacyID);
            callStmt.setLong(4, voltage);
            callStmt.setLong(5, maxScooterCapacity);
            callStmt.setLong(6, charge);

            callStmt.execute();

            Long scooterID = callStmt.getLong(1);

            callStmt.close();
            closeAll();

            return scooterID;
        } catch (SQLException e) {
            closeAll();
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return -1L;
        }
    }

}
