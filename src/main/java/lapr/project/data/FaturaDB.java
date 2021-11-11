package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Fatura;
import lapr.project.model.Produto;
import oracle.jdbc.OracleTypes;

/**
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
public class FaturaDB extends DataHandler{
    
    public Long addFatura(long ordemid){
        
    try {
            CallableStatement callStmt = getConnection().prepareCall("{ call cria_fatura(?,?) }");

            callStmt.setLong(1, ordemid);
            callStmt.registerOutParameter(2, OracleTypes.NUMBER);
            
            callStmt.execute();
            
            Long faturaid=callStmt.getLong(2);
            
            callStmt.close();
            closeAll();
            
            return faturaid;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    throw new IllegalArgumentException("Error found");
    }
    
        public Fatura getFatura(long faturaid) {
        Fatura ft=null;
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getFatura(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Especifica o parâmetro de entrada da função "getFatura".
            callStmt.setLong(2, faturaid);

            // Executa a invocação da função "getFatura".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                long fid = rSet.getLong(1);
                Date dataemit = rSet.getDate(2);
                long vSemIva = rSet.getLong(3);
                long vIva = rSet.getLong(4);
                long vTotal = rSet.getLong(5);
                long oId = rSet.getLong(6);
               
                ft=new Fatura(fid, dataemit, vSemIva, vIva, vTotal, oId);
                //System.out.println(t.getId()+" "+t.getNome()+" "+t.getPeso()+" "+t.getPreco()+" "+t.getQtd());
            }

            callStmt.close();
            closeAll();

            return ft;
        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        
        throw new IllegalArgumentException("No Products with OrderId:" + faturaid);
    }
}
