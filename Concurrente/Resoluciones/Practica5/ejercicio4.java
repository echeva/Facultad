/*4) Se debe controlar el acceso a una base de datos. Existen A procesos de Tipo 1, B
procesos de Tipo 2 y C procesos de Tipo 3 que trabajan indefinidamente de la siguiente
manera:
• Proceso Tipo 1: intenta escribir, si no lo logro en 2 minutos, espera 5 minutos y
vuelve a intentarlo.
• Proceso Tipo 2: intenta escribir, si no lo logra en 5 minutos, intenta leer, si no lo
logra en 5 minutos vuelve a comenzar.
• Proceso Tipo 3: intenta leer, si no puede inmediatamente entonces espera hasta
poder escribir.
Un proceso que quiera escribir podrá acceder si no hay ningún otro proceso en la base de
datos, al acceder escribe y avisa que termino de escribir. Un proceso que quiera leer
podrá acceder si no hay procesos que escriban, al acceder lee y avisa que termino de
leer. Siempre se le debe dar prioridad al pedido de acceso para escribir sobre el pedido
de acceso para leer.*/

TASK TYPE procesoTipo1;
TASK TYPE procesoTipo2;
TASK TYPE procesoTipo3;
TASK administrador;

procesosTipo1 = array(1..A) of procesoTipo1;
procesosTipo2 = array(1..B) of procesoTipo2;
procesosTipo3 = array(1..C) of procesoTipo3;

TASK body procesoTipo1
var ok: boolean;
BEGIN
	ok:= false;
	LOOP (not ok)
		SELECT administrador.quieroEscribir();
			   delay(); //escribiendo...
			   ok:= true;
			   administrador.liberarBase();
		OR DELAY 2
			Delay 5;
	END LOOP;
END;

TASK body procesoTipo2
var ok: boolean;
BEGIN
	ok:= false;
	LOOP (not ok)
		SELECT administrador.quieroEscribir();
			   delay();
			   ok:= true;
			   administrador.liberarBase();
		OR DELAY 5
			SELECT administrador.quieroLeer();
				   delay();
				   ok:= true;
				   administrador.liberarBase();
			OR DELAY 5
			END SELECT;
		END SELECT;
	END LOOP;
END;

TASK body procesoTipo3
var ok: boolean;
BEGIN
	ok:= false;
	LOOP (not ok)
		SELECT administrador.quieroLeer();
			   delay();
			   ok:= true;
			   administrador.liberarBase();
		OR administrador.quieroEscribir();
		   delay();
		   ok:= true;
		   administrador.liberarBase();
		END SELECT;
	END LOOP;
END;

TASK body administrador
var libre: boolean;
BEGIN
	libre:= true;
	LOOP 
		SELECT ACCEPT liberarBase();
			   libre:= true;
		OR WHEN(quieroEscribir`count = 0 AND libre) ACCEPT quieroLeer(); libre:= false;
		OR WHEN(libre) ACCEPT quieroEscribir(); libre:= false;
		END SELECT;
	END LOOP;
END;