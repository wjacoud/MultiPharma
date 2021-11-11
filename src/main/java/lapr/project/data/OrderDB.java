package lapr.project.data;

import lapr.project.model.Order;
import lapr.project.model.Produto;
import oracle.jdbc.OracleTypes;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Duarte
 */
public class OrderDB extends DataHandler {

    public Order getOrder(long id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call devolve_ordem(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Especifica o parâmetro de entrada da função "getOrder".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getOrder".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                double peso = rSet.getDouble(2);
                Date data = rSet.getDate(3);
                double preco = rSet.getDouble(4);
                long txe = rSet.getLong(5);
                long clienteId = rSet.getLong(6);
                long farmaciaId = rSet.getLong(7);

                callStmt.close();
                closeAll();

                return new Order(id, peso, data, preco, txe, clienteId, farmaciaId, new ArrayList<>()); //txe=10
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    public long addOrder(long id, long idcliente, long idfarmacia, ArrayList<Produto> a) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call p_cria_produto_ordem(?,?,?,?,?) }");

            callStmt.setLong(1, id);
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setLong(2, idcliente);
            callStmt.setLong(3, idfarmacia);

            for (Produto p : a) {
                callStmt.setLong(1, id);
                callStmt.setLong(4, p.getId());
                callStmt.setLong(5, p.getQtd());
                callStmt.execute();
                id = callStmt.getLong(1);
            }

            callStmt.close();
            closeAll();

            return id;
        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Error adding order to DB");
    }

    public ArrayList<Produto> getProdutosOrder(long ordemid) {
        ArrayList<Produto> a = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call f_get_produtos_ordem(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Especifica o parâmetro de entrada da função "f_get_produtos_ordem".
            callStmt.setLong(2, ordemid);

            // Executa a invocação da função "f_get_produtos_ordem".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                long pid = rSet.getLong(1);
                String pnome = rSet.getString(2);
                long ppeso = rSet.getLong(3);
                long ppreco = rSet.getLong(4);
                long qtd = rSet.getLong(5);
                a.add(new Produto(pid, pnome, ppeso, ppreco, qtd));
                Produto t=new Produto(pid, pnome, ppeso, ppreco, qtd);
                System.out.println(t.getId()+" "+t.getNome()+" "+t.getPeso()+" "+t.getPreco()+" "+t.getQtd());
            }

            callStmt.close();
            closeAll();

            return a;
        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        
        throw new IllegalArgumentException("No Products with OrderId:" + ordemid);
    }

    public String getClientEmailByOrderId(long ordemid) {
        String email = "";
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ ?=call getClientEmailFromOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            callStmt.setLong(2, ordemid);

            // Executa a invocação da função "getClientEmailFromOrder".
            callStmt.execute();

            email = callStmt.getString(1);
            
            callStmt.close();
            closeAll();

        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        return email;
    }

    public Long addFatura(long ordemid) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call p_cria_fatura(?,?) }");

            callStmt.setLong(1, ordemid);
            callStmt.registerOutParameter(2, OracleTypes.NUMBER);

            callStmt.execute();

            Long faturaid = callStmt.getLong(2);

            callStmt.close();
            closeAll();

            return faturaid;
        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Error found");
    }

    public ArrayList<Order> getOrdersToBeDelivered(long idFarmaciaProcurar) {
        ArrayList<Order> orderList = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrdersToBeDelivered(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setLong(2, idFarmaciaProcurar);

            // Executa a invocação da função "getOrdersToBeDelivered".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                long id = rSet.getLong(1);
                long peso = rSet.getLong(2);
                Date dataCriacao = rSet.getDate(3);
                long preco = rSet.getLong(4);
                long txe = rSet.getLong(5);
                long clienteId = rSet.getLong(6);
                long farmaciaId = rSet.getLong(7);
                orderList.add(new Order(id, peso, dataCriacao, preco, txe, clienteId, farmaciaId, new ArrayList<>()));
            }
            callStmt.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } finally {
            closeAll();
        }
        return orderList;
    }

}
