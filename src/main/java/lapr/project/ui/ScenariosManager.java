/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.controller.NotifyCourierWithTimeEstimateController;
import lapr.project.data.DataHandler;

/**
 *
 * @author leona
 */
public class ScenariosManager extends DataHandler {

    public void buildScenarios() throws SQLException {

        System.out.println("Cenário nr 1:\nAdiciona novo cliente");
        Scenarie001 sc001 = new Scenarie001();

        PreparedStatement prepStmt = getConnection().prepareStatement("select count(*) from clientes");
        ResultSet rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total users before: " + rSet.getInt(1));
        }
        closeAll();

        System.out.println(sc001.run());

        prepStmt = getConnection().prepareStatement("select count(*) from clientes");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total users after: " + rSet.getInt(1));
        }
        closeAll();

        /* print da divisor (e.g. ------ ) */
        System.out.println("------------------------------------------------------");

        System.out.println("Cenário nr 2:\nAdiciona Orders, emite fatura, envia notificacao por mail");
        Scenarie002 sc002 = new Scenarie002();
        sc002.run();

        /* print da divisor (e.g. ------ ) */
        System.out.println("------------------------------------------------------");

        System.out.println("Cenário nr 3:");
        LandScenarios landScenarios = new LandScenarios();
        landScenarios.start();

        /* print da divisor (e.g. ------ ) */
        System.out.println("------------------------------------------------------");

        System.out.println("Cenário nr 4:\nCriação Parques");
        prepStmt = getConnection().prepareStatement("select count(*) from parques");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total parks before: " + rSet.getInt(1));
        }
        closeAll();
        Scenarie004 sc004 = new Scenarie004();
        sc004.run();
        prepStmt = getConnection().prepareStatement("select count(*) from parques");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total parks after: " + rSet.getInt(1));
        }
        closeAll();

        /* print da divisor (e.g. ------ ) */
        System.out.println("------------------------------------------------------");

        System.out.println("Cenário nr 5:\nCriação de estafetas");
        prepStmt = getConnection().prepareStatement("select count(*) from estafetas");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total courier before: " + rSet.getInt(1));
        }
        closeAll();
        Scenarie005 sc005 = new Scenarie005();
        sc005.run();
        prepStmt = getConnection().prepareStatement("select count(*) from estafetas");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total courier after: " + rSet.getInt(1));
        }

        /* print da divisor (e.g. ------ ) */
        System.out.println("------------------------------------------------------");

        System.out.println("Cenário nr 6:\nCriação de scooters");
        prepStmt = getConnection().prepareStatement("select count(*) from scooters");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total scooters before: " + rSet.getInt(1));
        }
        Scenarie006 sc006 = new Scenarie006();
        sc006.run();
        prepStmt = getConnection().prepareStatement("select count(*) from scooters");
        rSet = prepStmt.executeQuery();
        if (rSet.next()) {
            System.out.println("Total scooters after: " + rSet.getInt(1));
        }
        
        /* print da divisor (e.g. ------ ) */
        System.out.println("------------------------------------------------------");

        System.out.println("Cenário nr 7:\n** Notify courier of the scooter parking success **");
        NotifyCourierWithTimeEstimateController controller = new NotifyCourierWithTimeEstimateController();
        controller.notifyCourierOfTimeEstimate();
    }
}
