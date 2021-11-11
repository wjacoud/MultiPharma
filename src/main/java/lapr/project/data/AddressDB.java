/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import lapr.project.model.Address;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author leona
 */
public class AddressDB extends DataHandler{
    
    public Address getAddressByClientID(long id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getMoradaByClienteID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getMoradaByClienteID".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getMoradaByClienteID".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long addressID = rSet.getLong(1);
                String country = rSet.getString(2);
                String district = rSet.getString(3);
                String location = rSet.getString(4);
                String zipCode = rSet.getString(5);
                double latitude = rSet.getDouble(6);
                double longitude = rSet.getDouble(7);
                
                return new Address(addressID, country, district, location, zipCode, latitude, longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
        throw new IllegalArgumentException("No Address associated with Client ID:" + id);
    }
    
    public Address getAddressByPharmacyID(long id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getMoradaByPharmacyID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getMoradaByPharmacyID".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getMoradaByPharmacyID".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long addressID = rSet.getLong(1);
                String country = rSet.getString(2);
                String district = rSet.getString(3);
                String location = rSet.getString(4);
                String zipCode = rSet.getString(5);
                double latitude = rSet.getDouble(6);
                double longitude = rSet.getDouble(7);
                
                return new Address(addressID, country, district, location, zipCode, latitude, longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
        throw new IllegalArgumentException("No Address associated with Pharmacy ID:" + id);
    }
    
    public Address getAddressByID(long id) {
       
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getMorada(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getAddress".
            callStmt.setLong(2, id);

            // Executa a invocação da função "getAddress".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                long addressID = rSet.getLong(1);
                String country = rSet.getString(2);
                String district = rSet.getString(3);
                String location = rSet.getString(4);
                String zipCode = rSet.getString(5);
                double latitude = rSet.getDouble(6);
                double longitude = rSet.getDouble(7);

                return new Address(addressID, country, district, location, zipCode, latitude, longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
        throw new IllegalArgumentException("No Address with ID:" + id);
    }
    
    public long addAddress(Address address) {
        return addAddress(address.getCountry(), address.getDistrict(), address.getLocation(), address.getZipCode(), address.getLatitude(), address.getLongitude());
    }
    
    private long addAddress(String country, String district, String location,
                            String zipCode, double latitude, double longitude) {
        try {
            openConnection();
            
            CallableStatement callStmt = getConnection().prepareCall("{? = call addMorada(?,?,?,?,?,?)}");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setString(2, country);
            callStmt.setString(3, district);
            callStmt.setString(4, location);
            callStmt.setString(5, zipCode);
            callStmt.setDouble(6, latitude);
            callStmt.setDouble(7, longitude);

            callStmt.execute();
            
            // Guarda o inteiro retornado num objeto "ResultSet".
            long addressID = callStmt.getLong(1);
            closeAll();
            
            return addressID;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            throw new IllegalArgumentException("Unable to insert Address, invalid arguments!");
        }finally{
            closeAll();
        }
    }
    
    public HashMap<Long, Address> getAllAddress() {
        HashMap<Long, Address> addressHashMap = new HashMap<>();
        CallableStatement callStmt = null;
        
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllAddress }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Executa a invocação da função "getAllAddress".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                long id = rSet.getLong(1);
                String country = rSet.getString(2);
                String district = rSet.getString(3);
                String location = rSet.getString(4);
                String zipCode = rSet.getString(5);
                double latitude = rSet.getDouble(6);
                double longitude = rSet.getDouble(7);
                addressHashMap.put(id, new Address(id, country, district, location, zipCode, latitude, longitude));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return addressHashMap;
    }
    
        public HashMap<Long, Address> getAllPharmacyAddress() {
        HashMap<Long, Address> addressHashMap = new HashMap<>();
        CallableStatement callStmt = null;
        
        try {
            callStmt = getConnection().prepareCall("{ ? = call allPharmacyAddress }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Executa a invocação da função "getAllPharmacyAddress".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                long fid = rSet.getLong(1);
                long mid = rSet.getLong(2);
                String country = rSet.getString(3);
                String district = rSet.getString(4);
                String location = rSet.getString(5);
                String zipCode = rSet.getString(6);
                double latitude = rSet.getDouble(7);
                double longitude = rSet.getDouble(8);
                addressHashMap.put(fid, new Address(mid, country, district, location, zipCode, latitude, longitude));
            }
            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return addressHashMap;
    }
    
}
