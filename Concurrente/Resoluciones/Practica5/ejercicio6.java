/*6) Hay un sistema web para reservas de pasajes de micro donde existen N Clientes que
solicitan un pasaje para un cierto destino; espera hasta que el servidor le indique el
número de asiento reservado (-1 si no hubiese disponibles); y luego (si había asiento
disponible) el cliente imprime su pasaje. El servidor atiende los pedidos de acuerdo al
orden de llegada, cuando un cliente le solicita un pasaje a un cierto destino, busca un
asiento disponible para ese destino y luego le indica a ese cliente el asiento reservado (o
-1 si no hubiese ninguno disponible).*/

TASK TYPE cliente;
clientes= array(1..N) of cliente;

TASK servidor IS
	ENTRY solicitarPasaje(destino: IN string, asiento: OUT int); 
END;

TASK BODY cliente
var string destino;
var int asiento;
BEGIN
	destino:= "Mi destino";
	servidor.solicitarPasaje(destino, asiento);
	if (asiento != -1) {
		//imprime su pasaje	
	}
END;

TASK BODY servidor
var string[] pasajes;
var pasajes[] destinos; 
BEGIN
	LOOP
		ACCEPT solicitarPasaje(destino: IN string, asiento: OUT int) IS
			   //consulta si hay asientos disponibles para el destino solicitado
			   arreglo = destinos[destino];
			   'buscar el proximo libre' 
		END ACCEPT;
	END LOOP;
END;