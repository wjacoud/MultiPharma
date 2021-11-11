package lapr.project.data;

import java.math.BigDecimal;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Pharmacy;

/**
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class PharmacyDB extends DataHandler {

    /**
     * Devolve o registo de farmacia especificada existente na tabela
     * "FARMACIAS".
     *
     * @param id o identificador do farmacia.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public Pharmacy getPharmacy(long id) {
        /* Objeto "callStmt" para invocar a função "getPharmacy" armazenada na BD.
         *
         * FUNCTION getCliente(id NUMBER) RETURN pkgCliente.ref_cursor
         * PACKAGE pkgCliente AS TYPE ref_cursor IS REF CURSOR; END pkgCliente;
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getPharmacy".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getPharmacy".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long pharmacyID = rSet.getLong(1);
                String pharmacyName = rSet.getString(2);
                long pharmacyNipc = rSet.getLong(3);
                long pharmacyMaxScooters = rSet.getLong(4);
                long pharmacyMaxDrones = rSet.getLong(5);
                long pharmacyMorada = rSet.getLong(6);
                long pharmacyMaxCred = rSet.getLong(7);
                long pharmacyIncCred = rSet.getLong(8);

                callStmt.close();
                closeAll();

                return new Pharmacy(pharmacyID, pharmacyName, pharmacyNipc, pharmacyMaxScooters, pharmacyMaxDrones, pharmacyMorada, pharmacyMaxCred, pharmacyIncCred);
            }
        } catch (SQLException e) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with ID:" + id);
    }

    public long addPharmacy(Pharmacy pharmacy) {
        long id = addPharmacy(pharmacy.getName(), pharmacy.getNipc(), pharmacy.getMaxScooters(), pharmacy.getMaxDrones(), pharmacy.getMorada(), pharmacy.getMaxCreditos(), pharmacy.getIncCreditos());
        return id;
    }

    /**
     * Adiciona uma farmacia especificado à tabela "FARMACIAS".
     *
     * @param cid o identificador da farmacia.
     * @param name o nome do farmacia.
     * @param credito o nipc do farmacia.
     * @param morada a morada do farmacia.
     */
    private long addPharmacy(String name, long nipc, long maxScooters, long maxDrones, long moradaID, long maxCred, long incCred) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addPharmacy" armazenado
             *  na BD.
             *
             *  Function addPharmacy(id NUMBER, name VARCHAR, nipc NUMBER, morada NUMBER)
             *  ?? PACKAGE pkgPharmacy AS TYPE ref_cursor IS REF CURSOR; END pkgPharmacy; ?? 
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addPharmacy(?,?,?,?,?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setString(2, name);
            callStmt.setLong(3, nipc);
            callStmt.setLong(4, maxScooters);
            callStmt.setLong(5, maxDrones);
            callStmt.setLong(6, moradaID);
            callStmt.setLong(7, maxCred);
            callStmt.setLong(8, incCred);

            callStmt.execute();
            BigDecimal number = (BigDecimal) callStmt.getObject(1);

            callStmt.close();
            closeAll();

            return number.longValue();
        } catch (SQLException e) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            closeAll();
            return -1L;
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove a farmacia especificado da tabela "Farmacias".
     *
     * @param id o identificador da farmacia a remover.
     */
    public void removePharmacy(long id) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "removePharmacy"
             *  armazenado na BD.
             *
             *  PROCEDURE removePharmacy(id NUMBER)
             *  PACKAGE pkgPharmacy AS TYPE ref_cursor IS REF CURSOR; END pkgPharmacy;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacy(?) }");

            callStmt.setLong(1, id);

            callStmt.execute();

            callStmt.close();
            closeAll();
        } catch (SQLException e) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

}
