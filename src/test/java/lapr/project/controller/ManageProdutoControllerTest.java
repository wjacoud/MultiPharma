/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ProdutoDB;
import lapr.project.model.Produto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Duarte Valente <1181489@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManageProdutoControllerTest {
    
    private Produto expectedProduto;

    @Mock
    private ProdutoDB produtoDB;

    @InjectMocks
    private ManageProdutoController instance1;
    
    private ManageProdutoControllerTest() {
        instance1= new ManageProdutoController();
    }
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedProduto = new Produto(1L, "vacina1", 2L, 20L, 0L);
        when(produtoDB.addProduto("vacina1", 2L, 20L)).thenReturn(1L);
    }

    /**
     * Test of addProduto method, of class ManageProdutoController.
     */
    @Test
     void testAddProduto() {
        System.out.println("addProduto");
        String nome = "vacina1";
        long peso = 2L;
        long preco = 20L;
        
        //long result = produtoDB.addProduto(nome, peso, preco);
        
        Produto result = instance1.addProduto(nome, peso, preco);
        assertEquals(expectedProduto.getId(), result.getId());
    }
    
}
