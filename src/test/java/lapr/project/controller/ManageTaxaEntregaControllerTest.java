package lapr.project.controller;

import java.sql.Date;
import lapr.project.data.TaxaEntregaDB;
import lapr.project.model.TaxaEntrega;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author Wilson Jacoud <1140858@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManageTaxaEntregaControllerTest {

    private TaxaEntrega expectedTaxaEntregaResult;
    
    @Mock
    private TaxaEntregaDB taxaEntregaDB;

    @InjectMocks
    private ManageTaxaEntregaController instance;

    public ManageTaxaEntregaControllerTest() {
        instance = new ManageTaxaEntregaController();
    }

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedTaxaEntregaResult = new TaxaEntrega(0L, Date.valueOf("2025-08-11"), Date.valueOf("2025-08-11"), 10, "Taxa Unica");
        when(taxaEntregaDB.addTaxaEntrega(new TaxaEntrega(1L, Date.valueOf("2025-08-11"), Date.valueOf("2025-08-11"), 10, "Taxa Unica"))).thenReturn(0L);
        
    }

    /**
     * Test of addTaxaEntrega method, of class ManageTaxaEntregaController.
     */
    @Test
    public void testAddTaxaEntrega() {
        System.out.println("addTaxaEntrega");
        
        Date data_inicio = Date.valueOf("2025-08-11");
        Date data_fim = Date.valueOf("2025-08-11");
        long valor = 5L;
        String descricao = "Taxa Unica";
        
        
        
        TaxaEntrega result = instance.addTaxaEntrega(data_inicio, data_fim, valor, descricao);
        assertEquals(expectedTaxaEntregaResult.getId(), result.getId());
    }

}
