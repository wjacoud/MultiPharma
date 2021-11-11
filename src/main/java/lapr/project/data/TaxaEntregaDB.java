package lapr.project.data;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import lapr.project.model.TaxaEntrega;

/**
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
public class TaxaEntregaDB extends DataHandler {

    /**
     * Devolve o registo de Taxa de Entrega especificado existente na tabela
     * "Taxa_Entrega".
     *
     * @param id o identificador do Taxa de Entrega.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public TaxaEntrega getTaxaEntrega(long id) {
        /* Objeto "callStmt" para invocar a função "getTaxaEntrega" armazenada na BD.
         *
         * FUNCTION getCliente(id NUMBER) RETURN pkgCliente.ref_cursor
         * PACKAGE pkgTaxaEntrega AS TYPE ref_cursor IS REF CURSOR; END pkgTaxaEntrega;
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getTaxaEntrega(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getTaxaEntrega".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getTaxaEntrega".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long TaxaEntregaID = rSet.getLong(1);
                Date TaxaEntregadataInicio = rSet.getDate(2);
                Date TaxaEntregadataFim = rSet.getDate(3);
                long TaxaEntregaValor = rSet.getLong(4);
                String TaxaEntregaDescricao = rSet.getString(5);

                callStmt.close();
                closeAll();

                return new TaxaEntrega(TaxaEntregaID, TaxaEntregadataInicio, TaxaEntregadataFim, TaxaEntregaValor, TaxaEntregaDescricao);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaxaEntregaDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Delivery Fee with ID:" + id);
    }

    public long addTaxaEntrega(TaxaEntrega taxaEntrega) {
        long id = addTaxaEntrega(taxaEntrega.getData_inicio(), taxaEntrega.getData_fim(), taxaEntrega.getValor(), taxaEntrega.getDescricao());
        return id;
    }

    /**
     * Adiciona uma taxa especificado à tabela "Taxa_Entrega".
     *
     * @param dataInicio o identificador da Taxa de Entrega.
     * @param dataFim a data de inicio da Taxa de Entrega.
     * @param Valor a data de fim da Taxa de Entrega.
     * @param descricao a descicao da Taxa de Entrega.
     */
    private long addTaxaEntrega(Date dataInicio, Date dataFim, long valor, String descricao) {
        try {
            /*
             *  Objeto "callStmt" para invocar o procedimento "addTaxaEntrega" armazenado
             *  na BD.
             *
             *  FUNCTION addTaxaEntrega(id NUMBER, numero NUMBER, cvv NUMBER, validade DATE)
             *  ?? PACKAGE pkgTaxaEntrega AS TYPE ref_cursor IS REF CURSOR; END pkgTaxaEntrega; ?? 
             */
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call addTaxaEntrega(?,?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setDate(2, dataInicio);
            callStmt.setDate(3, dataFim);
            callStmt.setLong(4, valor);
            callStmt.setString(5, descricao);

            callStmt.execute();
            BigDecimal number = (BigDecimal) callStmt.getObject(1);
            
            callStmt.close();
            closeAll();
            
            return number.longValue();
        } catch (SQLException e) {
            Logger.getLogger(TaxaEntregaDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return -1L;
        }
    }
}
