/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
package lapr.project.data;

import java.math.BigDecimal;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import lapr.project.model.CartaoCredito;

public class CartaoCreditoDB extends DataHandler {

    /**
     * Devolve o registo de Cartao de Credito especificado existente na tabela
     * "CARTAO_CREDITO".
     *
     * @param id o identificador do Cartao de Credito.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public CartaoCredito getCartaoCredito(long id) {
        /* Objeto "callStmt" para invocar a função "getCartaoCredito" armazenada na BD.
         *
         * FUNCTION getCliente(id NUMBER) RETURN pkgCliente.ref_cursor
         * PACKAGE pkgCartaoCredito AS TYPE ref_cursor IS REF CURSOR; END pkgCartaoCredito;
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCartaoCredito(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getCartaoCredito".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getCartaoCredito".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long cartaoCreditoID = rSet.getLong(1);
                long cartaoCreditoNumero = rSet.getLong(2);
                long cartaoCreditoCvv = rSet.getLong(3);
                Date cartaoCreditoValidade = rSet.getDate(4);

                callStmt.close();
                closeAll();

                return new CartaoCredito(cartaoCreditoID, cartaoCreditoNumero, cartaoCreditoCvv, cartaoCreditoValidade);
            }
        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Credit Card with ID:" + id);
    }

    public long addCartaoCredito(CartaoCredito cartaoCredito) {
        long id = addCartaoCredito(cartaoCredito.getNumero(), cartaoCredito.getCvv(), cartaoCredito.getValidade());
        return id;
    }

    /**
     * Adiciona um cliente especificado à tabela "Clientes".
     *
     * @param id o identificador do Cartao de Credito.
     * @param numero o numero do Cartao de Credito.
     * @param cvv o cvv do Cartao de Credito.
     * @param validade a validade do Cartao de Credito.
     */
    private long addCartaoCredito(long numero, long cvv, Date validade) {

        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addCartaoCredito" armazenado
             *  na BD.
             *
             *  FUNCTION addCartaoCredito(id NUMBER, numero NUMBER, cvv NUMBER, validade DATE)
             *  ?? PACKAGE pkgCartaoCredito AS TYPE ref_cursor IS REF CURSOR; END pkgCartaoCredito; ?? 
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addCartaoCredito(?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setLong(2, numero);
            callStmt.setLong(3, cvv);
            callStmt.setDate(4, validade);

            callStmt.execute();
            BigDecimal number = (BigDecimal) callStmt.getObject(1);

            callStmt.close();
            closeAll();

            return number.longValue();
        } catch (SQLException e) {
            closeAll();
            System.out.println(e.getMessage());
            return -1L;
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o marinheiro especificado da tabela "Cartao_credito".
     *
     * @param cid o identificador do Cartao de Credito a remover.
     */
    public void removeCartaoCredito(long id) {

        try {
            openConnection();
            /*
             *  Objeto "callStmt" para invocar o procedimento "removeCartaoCredito"
             *  armazenado na BD.
             *
             *  PROCEDURE removeCartaoCredito(id NUMBER)
             *  PACKAGE pkgCartaoCredito AS TYPE ref_cursor IS REF CURSOR; END pkgCartaoCredito;
             */
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCartaoCredito(?) }"); //TODO

            callStmt.setLong(1, id);

            callStmt.execute();

            callStmt.close();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }
}
