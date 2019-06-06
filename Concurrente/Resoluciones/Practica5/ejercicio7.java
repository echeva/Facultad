/*7) Hay una empresa de servicios que tiene un Recepcionista, N Clientes que hacen
reclamos y E Empleados que los resuelven. Cada vez que un cliente debe hacer un
reclamo, espera a que el Recepcionista lo atienda y le dé un Número de Reclamo, y
luego continúa trabajando. Los empleados cuando están libres le piden un reclamo para
resolver al Recepcionista y lo resuelven. El Recepcionista recibe los reclamos de los
clientes y les entrega el Número de Reclamo, o bien atiende los pedidos de más trabajo
de los Empleados (cuando hay reclamos sin resolver le entrega uno al empleado para
que trabaje); siempre le debe dar prioridad a los pedidos de los Empleados. Nota: los
clientes, empleados y recepcionista trabajan infinitamente.*/

TASK Recepcionista IS
	ENTRY pedirNumeroDeReclamo(numero: OUT integer);
	ENTRY pedirReclamo(numero: OUT integer);
END;

TASK TYPE Cliente;
TASK TYPE Empleade;

clientes = array(1..N) of Cliente;
empleades = array(1..E) of Empleade;

TASK BODY Cliente
	var numero: integer;
BEGIN
	LOOP
		Recepcionista.pedirNumeroDeReclamo(numero);
		--trabajando--
	END LOOP; 
END;

TASK BODY Empleade
	var reclamo:integer
BEGIN
	LOOP
		Recepcionista.pedirReclamo(reclamo);
		--atiende reclamo--
	END LOOP;
END;

TASK BODY Recepcionista
var Queue reclamos;
BEGIN
	LOOP
		SELECT WHEN(pedirReclamo`count = 0) ACCEPT pedirNumeroDeReclamo(out numero) IS
					numero = ramdom(1,29);
					push(reclamos, numero);
				END pedirNumeroDeReclamo;
			OR WHEN !empty(reclamos) ACCEPT pedirReclamo(numero) IS
				numero = pop(reclamos);
			END pedirReclamo;
		END SELECT;	
	END LOOP;

END;