/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.TaxaEntregaDB;
import lapr.project.model.TaxaEntrega;

/**
 *
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */

public class ManageTaxaEntregaController {
    private TaxaEntregaDB taxaEntregaDB;

    public ManageTaxaEntregaController() {
        this.taxaEntregaDB  = new TaxaEntregaDB();
    }
    
    public TaxaEntrega addTaxaEntrega(Date data_inicio, Date data_fim, long valor, String descricao) {
            TaxaEntrega newTaxaEntrega = new TaxaEntrega(data_inicio, data_fim, valor, descricao);
        try {
            long id = this.taxaEntregaDB.addTaxaEntrega(newTaxaEntrega);
            newTaxaEntrega.setId(id);
            Logger.getLogger(ManageTaxaEntregaController.class.getName()).log(Level.SEVERE, null,"\t... Farmacia Adicionada Com o ID: " + newTaxaEntrega.getId());
            return newTaxaEntrega;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageTaxaEntregaController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
}
