/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;
import java.sql.Date;
import java.util.ArrayList;
import lapr.project.data.ClienteDB;
import lapr.project.data.FaturaDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Cliente;
import lapr.project.model.Fatura;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Produto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
/**
 *
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManageFaturaControllerTest {
    
    private Fatura expectedFatura=new Fatura(1L, Date.valueOf("2025-08-11"), 0, 0, 0, 1);
    private Order expectedOrder=new Order(1L, 0, Date.valueOf("2025-08-11"), 0, 2, 1, 1, new ArrayList<>());
    private Pharmacy expectedPharmacy=new Pharmacy(1L, "Farmacia da Boavista", 333111444, 20, 20, 2, 50, 10);
    private Cliente expectedCliente=new Cliente(1L, "Joao", 111222333, 0, 1, "joao@gmail.com", 1L);

    @Mock
    private FaturaDB faturaDB;
    @Mock   
    private ClienteDB clientedb;
    @Mock
    private OrderDB orderdb;
    @Mock  
    private PharmacyDB pharmacydb;

    @InjectMocks
    private ManageFaturaController instance;
    
    public ManageFaturaControllerTest() {
        instance= new ManageFaturaController();
    }
    
    @BeforeAll 
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedOrder.addProdToList(new Produto(1L, "p1", 1, 10, 2));
        //expectedFatura = new Fatura(Date.valueOf("2025-08-11"), new Order(1, 2, Date.valueOf("2025-08-11"), 20, 1, 1, 1, new ArrayList<>()));
        //expectedFatura.setId(1L);
        when(faturaDB.addFatura(expectedFatura.getOrdemId())).thenReturn(1L);
        doReturn(expectedFatura).when(faturaDB).getFatura(1L);
        
        doReturn(expectedOrder).when(orderdb).getOrder(1L);
        doReturn(expectedPharmacy).when(pharmacydb).getPharmacy(1L);
        doReturn(expectedCliente).when(clientedb).getCliente(1L);
        doReturn(expectedOrder.getProdList()).when(orderdb).getProdutosOrder(1L);
        
    }


    /**
     * Test of addFatura method, of class ManageFaturaController.
     */
    @Test
    public void testAddFatura() {
        System.out.println("addFatura");
        //Date dataemit = Date.valueOf("2025-08-11");
        //Order o = new Order(1, 2, Date.valueOf("2025-08-11"), 20, 1, 1, 1, new ArrayList<>());
        long expResult = expectedFatura.getId();
        Fatura result = instance.addFatura(1);
        assertEquals(expResult, result.getId());
    }
    
     /**
     * Test of addFatura method, of class ManageFaturaController.
     */
    @Test
    public void testPrintFatura() {
        System.out.println("printFatura");
        
        
        String expResult="Fatura nº "+expectedFatura.getId()+"\nFarmacia: "+expectedPharmacy.getName()+"\nNIPC nº "+expectedPharmacy.getNipc()+"\nCliente: "+expectedCliente.getName()+"\nNIF nº "+expectedCliente.getNif();
        for (Produto p: expectedOrder.getProdList())
        expResult=expResult+"\n"+p.getNome()+" - "+p.getQtd()+" unidades";
    
        expResult=expResult+"\nValor sem iva: "+expectedFatura.getvSemIva()+"\nValor IVA total: "+expectedFatura.getvIva()+"\nValor total a pagar: "+expectedFatura.getvTotal()+"\nEmitida em: "+expectedFatura.getDataEmit();
    
        
        
        //Date dataemit = Date.valueOf("2025-08-11");
        //Order o = new Order(1, 2, Date.valueOf("2025-08-11"), 20, 1, 1, 1, new ArrayList<>());
        String result = instance.printFatura(1);
        assertEquals(expResult, result);
    }
    
}
