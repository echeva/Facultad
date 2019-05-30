/*2) Se quiere modelar la cola de un banco que atiENDe un solo empleado, los clientes llegan
y si esperan más de 10 minutos se retiran.*/

TASK empleado is 
	ENTRY atender();
END;

TASK body empleado
BEGIN
	LOOP
		ACCEPT atender() is
			delay(3); //atiENDe
		END; 	
	END;
END


TASK TYPE cliente;

clientes = array(1..n) of cliente;

TASK body cliente
BEGIN
	SELECT empleado.atender();
	OR DELAY 10; //si pasaron 10' se desencola
END;