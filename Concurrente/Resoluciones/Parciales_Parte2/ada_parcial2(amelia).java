/* En un campo dividido en 4 secciones (Norte, Sur, Este y Oeste), se deben hacer
rollos de pasto, para lo cual se cuenta con 4 máquinas enrolladoras y un coordinador.
El coordinador le indica a cada máquina la sección en la cual le toca trabajar 
(cada máquina va a una sección diferente) y al final imprime (el coordinador)
la cantidad total de rollos realizados en todo el campo. Nota: maximizar 
la concurrencia.*/

TASK TYPE Enrrolladora;

TASK Coordinador IS
	ENTRY solicitarSeccion(seccion: OUT String);
	ENTRY informarCantidad(cantidad: IN int);
END Coordinador;

enrolladoras = array(1 to 4) of Enrrolladora;

TASK BODY Enrrolladora IS
	String seccion;
	int cantidad = 0; 
BEGIN
	Coordinador.solicitarSeccion(seccion);
	WHILE (not EOF) 
		enrrollar();
		cantidad ++;
	END WHILE;
	Coordinador.informarCantidad(cantidad);
END Enrrolladora;

TASK BODY Coordinador IS
	int cant = 0;
	int cantTotal = 0;
BEGIN
	WHILE cant < 4
		SELECT ACCEPT solicitarSeccion(seccion: OUT String) do
				seccion = DarSeccion();
			END solicitarSeccion;
			OR ACCEPT informarCantidad(cantidad: IN int) do
					cantTotal = cantTotal + cantidad;
				END informarCantidad;
				cant = cant + 1;
		END SELECT;
	END WHILE;
	print('Cantidad total: ', cant);
END Coordinador;