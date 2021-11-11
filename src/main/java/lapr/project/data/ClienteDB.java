/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
package lapr.project.data;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lapr.project.model.Cliente;

public class ClienteDB extends DataHandler {

    /**
     * Devolve o registo de Cliente especificado existente na tabela "Clientes".
     *
     * @param id o identificador do Cliente.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public Cliente getCliente(long id) {
        /* Objeto "callStmt" para invocar a função "getCliente" armazenada na BD.
         *
         * FUNCTION getCliente(id NUMBER) RETURN pkgCliente.ref_cursor
         * PACKAGE pkgCliente AS TYPE ref_cursor IS REF CURSOR; END pkgCliente;
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCliente(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getCliente".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getCliente".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long clienteID = rSet.getLong(1);
                String clienteName = rSet.getString(2);
                long clientenif=rSet.getLong(3);
                int clienteCredito = rSet.getInt(4);
                long clienteMoradaID = rSet.getLong(5);
                String clienteUtilizadorID = rSet.getString(6);
                long clienteCartaoCreditoID = rSet.getLong(7);
                
                callStmt.close();
                closeAll();
                
                return new Cliente(clienteID, clienteName, clientenif, clienteCredito, clienteMoradaID, clienteUtilizadorID, clienteCartaoCreditoID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with ID:" + id);
    }

    public long addCliente(Cliente cliente) {
        return addCliente(cliente.getName(), cliente.getNif(), cliente.getCredito(), cliente.getMorada(), cliente.getUtilizadorID(), cliente.getCartaoCreditoID());
    }

    /**
     * Adiciona um cliente especificado à tabela "Clientes".
     *
     * @param cid o identificador do cliente.
     * @param name o nome do cliente.
     * @param credito o credito do cliente.
     * @param moradaID o id da morada do cliente.
     * @param utilizadorID o id/email do utilizador.
     * @param cartaoCreditoID o cartao de credito do cliente.
     */
    private long addCliente(String name, long nif, int credito, long moradaID, String utilizadorID, long cartaoCreditoID) {

        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addCliente" armazenado
             *  na BD.
             *
             *  FUNCTION addCliente(id NUMBER, name VARCHAR, credito NUMBER, morada NUMBER, utilizadorID NUMBER, cartaoCreditoID NUMBER)
             *  ?? PACKAGE pkgCliente AS TYPE ref_cursor IS REF CURSOR; END pkgCliente; ?? 
             */
            CallableStatement callStmt = getConnection().prepareCall("{? = call addCliente(?,?,?,?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, name);
            callStmt.setLong(3, nif);
            callStmt.setInt(4, credito);
            callStmt.setLong(5, moradaID);
            callStmt.setString(6, utilizadorID);
            callStmt.setLong(7, cartaoCreditoID);

            callStmt.execute();
            
            long clientID = callStmt.getLong(1);
            
            callStmt.close();
            closeAll();
            
            return clientID;
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getErrorCode() == 20001)
                System.out.println("Error: Unable to Sign-Up - Null arguments!");
            if(e.getErrorCode() == 20002)
                System.out.println("Error: Unable to Sign-Up - Unknown arguments!");
            System.out.println(e.getErrorCode());
            closeAll();
            throw new IllegalArgumentException("Unable to insert new Client, invalid arguments!");
        } 
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o cliente especificado da tabela "Clientes".
     *
     * @param cid o identificador do Cliente a remover.
     */
    public void removeCliente(long cid) {

        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeCliente"
             *  armazenado na BD.
             *
             *  PROCEDURE removeCliente(cid NUMBER)
             *  PACKAGE pkgCliente AS TYPE ref_cursor IS REF CURSOR; END pkgCliente;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCliente(?) }");

            callStmt.setLong(1, cid);

            callStmt.execute();

            callStmt.close();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

    }

}
