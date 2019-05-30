/*Se debe modelar un buscador para contar la cantidad de veces que aparece un número
dentro de un vector distribuido entre las N tareas contador. Además existe un
administrador que decide el número que se desea buscar y se lo envía a los N
contadores para que lo busquen en la parte del vector que poseen.*/


TASK TYPE contador;

contadores = array(1..n) of contador;

TASK body contador
var num, total: int;
var vector[];
BEGIN
	total:= 0;
	administrador.solicitarNumero(num);
	for i:= 1 to vector.length BEGIN
		if (vector[i] == num) BEGIN
			total ++;
		END if;
	END for;
	administrador.enviarCuenta(total);
END;


TASK administrador IS
	ENTRY solicitarNumero(numero: out int);
	ENTRY enviarCuenta(cantidad: in int);
END;

TASK body administrador
var nro, cant: int;
BEGIN
	cant:=0;
	LOOP
		SELECT ACCEPT solicitarNumero(numero: out int) DO
				numero = nro;
			   END solicitarNumero;
		OR WHEN(solicitarNumero`count = 0) ACCEPT enviarCuenta(cantidad: in int) DO
				cant = cant + cantidad;
				END enviarCuenta;
		END SELECT;
	END LOOP;
END;