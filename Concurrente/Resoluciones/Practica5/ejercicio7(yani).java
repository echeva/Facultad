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
	ENDLOOP; 
END;

TASK BODY Empleade
	var reclamo:integer
BEGIN
	LOOP
		Recepcionista.pedirReclamo(reclamo);
	ENDLOOP;
END;

TASK BODY Recepcionista
BEGIN
	LOOP
		SELECT ACCEPT pedirNumeroDeReclamo(random(1,20));
			   WHEN pedirNumeroDeReclamo`count = 0; ACCEPT pedirReclamo
	ENDLOOP;

END;