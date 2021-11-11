CREATE OR REPLACE FUNCTION addScootersPark(addressID NUMBER, pharmacyID NUMBER, voltage NUMBER, maxScooterCapacity NUMBER, charge NUMBER)
RETURN NUMBER
IS
    TOTAL_PHARMACY  INT;
	TOTAL_ADDRESS	INT;
    --PARK_SCOOTERS_ID	INT;
	RETURN_ID_VALUE NUMBER;
	NO_PHARMACY_EXCEPTION   EXCEPTION;
    NO_ADDRESS_EXCEPTION   EXCEPTION;
	
BEGIN
	SELECT COUNT(f.FARMACIA_ID) INTO TOTAL_PHARMACY
	FROM FARMACIAS f
	WHERE f.FARMACIA_ID = pharmacyID;
	
	IF TOTAL_PHARMACY <> 1 THEN
            RAISE NO_PHARMACY_EXCEPTION;
	END IF;
	
	SELECT COUNT(m.morada_id) INTO TOTAL_ADDRESS
	FROM MORADA m
	WHERE m.morada_id = addressID;
	
	IF TOTAL_ADDRESS <> 1 THEN
            RAISE NO_ADDRESS_EXCEPTION;
	END IF;
    
    --SELECT TP.tipo_parque_id INTO PARK_SCOOTERS_ID FROM tp.TIPO_PARQUES WHERE;
    
    INSERT INTO PARQUES (voltagem, morada_id, farmacia_id, tipo_parque_id) VALUES (voltage, addressID, pharmacyID, 1);
    SELECT MAX(P.parque_id) INTO RETURN_ID_VALUE FROM PARQUES P;
    
	INSERT INTO PARQUES_SCOOTERS (parque_id, total_estacionamentos_scooters) VALUES (RETURN_ID_VALUE, maxScooterCapacity);
    
    FOR indx IN 1 .. maxScooterCapacity
    LOOP
        INSERT INTO LUGAR_ESTACIONAMENTO_SCOOTER (carga, parque_id) VALUES (charge, RETURN_ID_VALUE);
    END LOOP;
    	
	RETURN  RETURN_ID_VALUE;

    EXCEPTION
        WHEN NO_PHARMACY_EXCEPTION THEN
            raise_application_error(-20005, 'No Pharmacy found with that ID!');
        WHEN NO_ADDRESS_EXCEPTION THEN
            raise_application_error(-20006, 'No Scooter Type found with that ID!');
END;
