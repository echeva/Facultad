/* En un banco se tiene un sistema que administra el uso de una sala de reuniones
por parte de N clientes. Los clientes se clasifican en Habitulaes y Temporales.
La sala puede ser usada por un único cliente a la vez, y cuando está libre se debe
determinar a quién permitirle su uso dando prioridad a los clientes Habituales. 
Dentro de cada clase de cliente se debe respetar el orden de llegada. Nota: suponga
que existe una funcion Tipo() que le indica al cliente de qué tipo es.*/

TASK TYPE Cliente;

TASK Sala IS
	ENTRY usarHabitual();
	ENTRY usarTemporal();
END Sala;

clientes = array(1 to N) of Cliente;


TASK BODY Cliente IS
	tipo: string;
BEGIN
	tipo = Tipo();
	if tipo == 'Habitual'{
		Sala.usarHabitual();
	}else{
		Sala.usarTemporal();
	}
END Cliente;

TASK BODY Sala IS
	LOOP 
		SELECT ACCEPT usarHabitual();
				--usar sala --
			END usarHabitual;
			OR WHEN usarHabitual`count == 0; ACCEPT usarTemporal();
				--usar sala --
			END usarTemporal;
	END LOOP;
END Sala;