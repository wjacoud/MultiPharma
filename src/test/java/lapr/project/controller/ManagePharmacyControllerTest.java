/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;
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
 * @author Wilson Jacoud <1200630@isep.ipp.pt>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagePharmacyControllerTest {
    
    private Pharmacy expectedPharmacy;

    @Mock
    private PharmacyDB pharmacyDB;

    @InjectMocks
    private ManagePharmacyController instance1;
    
    private ManagePharmacyControllerTest() {
        instance1= new ManagePharmacyController();
    }
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedPharmacy = new Pharmacy(1L, "Roubeirinho", 2, 10, 10, 10, 50, 10);
        when(pharmacyDB.addPharmacy(new Pharmacy("Roubeirinho", 2, 10, 10, 10, 50, 10))).thenReturn(1L);
    }

    /**
     * Test of addPharmacy method, of class ManagePharmacyController.
     */
    @Test
     void testAddPharmacy() {
        System.out.println("addPharmacy");
        String name = "Roubeirinho";
        long nipc = 2;
        long maxScooters = 10;
        long maxDrones = 10;
        long moradaID = 10;
        long maxCred = 50;
        long incCred = 10;
        
        //long result = pharmacyDB.addPharmacy(name, nipc, maxScooters, maxDrones, moradaID);
        
        Pharmacy result = instance1.addPharmacy(name, nipc, maxScooters, maxDrones, moradaID, maxCred, incCred);
        assertEquals(expectedPharmacy.getId(), result.getId());
    }
    
}
