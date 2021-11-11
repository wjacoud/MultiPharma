/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;


import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.ClienteDB;
import lapr.project.data.FaturaDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Cliente;
import lapr.project.model.Fatura;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Produto;

/**
 *
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
public class ManageFaturaController {
    private FaturaDB faturadb;
    private ClienteDB clientedb;
    private OrderDB orderdb;
    private PharmacyDB pharmacydb;

    public ManageFaturaController() {
        this.faturadb = new FaturaDB();
        this.clientedb=new ClienteDB();
        this.orderdb=new OrderDB();
        this.pharmacydb= new PharmacyDB();
    }

    public Fatura addFatura(long ordemid) {
        
        try {
            long newid = this.faturadb.addFatura(ordemid);
            Fatura f= this.faturadb.getFatura(newid);
            Logger.getLogger(ManageFaturaController.class.getName()).log(Level.FINE, null, "Fatura with id: " + f.getId()+ "added for order with id"+ ordemid);
            return f;
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(ManageFaturaController.class.getName()).log(Level.SEVERE, null, iae.getMessage());
            return null;
        }
    }
    public String printFatura(long faturaid){
    String result="";
    Fatura ft=this.faturadb.getFatura(faturaid);
    Order o=this.orderdb.getOrder(ft.getOrdemId());
    o.setProdList(this.orderdb.getProdutosOrder(o.getId()));
    Pharmacy f=this.pharmacydb.getPharmacy(o.getFarmaciaId());
    Cliente c=this.clientedb.getCliente(o.getClienteId());
    
    result="Fatura nº "+ft.getId()+"\nFarmacia: "+f.getName()+"\nNIPC nº "+f.getNipc()+"\nCliente: "+c.getName()+"\nNIF nº "+c.getNif();

    for (Produto p: o.getProdList())
     result=result+"\n"+p.getNome()+" - "+p.getQtd()+" unidades";
    
    result=result+"\nValor sem iva: "+ft.getvSemIva()+"\nValor IVA total: "+ft.getvIva()+"\nValor total a pagar: "+ft.getvTotal()+"\nEmitida em: "+ft.getDataEmit();
    return result;
    }
}
