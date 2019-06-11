/*Resolver este ejercicio con PMS o ADA. Se tiene un sistema que administra el uso
de una Supercomputadora por parte de N personas. Las personas tienen diferentes 
categorías (A, B, C). La supercomputadora puede ser usada por una única persona
a la vez. Cuando está libre se debe determinar a quién permitirle su uso de acuerdo
a la prioridad determinada por la categoría: la categoría A es la de mayor prioridad, 
luego la B y por último la C. Dentro de cada categoría se debe respetar el orden
de llegada. Nota: suponga que existe una función Categoría() que le indica a la 
persona de qué tipo es.*/

TASK TYPE Persona;
TASK Supercomputadora IS
	ENTRY usarCategoriaA();
	ENTRY usarCategoriaB();
	ENTRY usarCategoriaC();
	ENTRY liberar();
END Supercomputadora;


personas = array(1 to N) of Persona;

TASK BODY Persona IS
	char categoría;
BEGIN
	categoria = Categoría();

	SELECT WHEN categoria = 'A'; => Supercomputadora.usarCategoriaA();
		OR WHEN categoria = 'B'; => Supercomputadora.usarCategoriaB();
		ELSE => Supercomputadora.usarCategoriaC();
	END SELECT;
	delay();
	Supercomputadora.liberar();
END Persona;

TASK BODY Supercomputadora IS
	boolean libre;
BEGIN
	libre = true;
	LOOP
		SELECT ACCEPT liberar() IS
					libre = true;
				END liberar;
			OR WHEN libre => ACCEPT usarCategoriaA() do
					libre = false;
				END usarCategoriaA;
			OR WHEN libre & usarCategoriaA`count = 0 => ACCEPT usarCategoriaB() do
					libre = false;
				END usarCategoriaB;
			OR WHEN libre & usarCategoriaA`count = 0 & usarCategoriaB`count = 0 =>
					ACCEPT usarCategoriaC() do
						libre = false;
					END usarCategoriaC;
		END SELECT;
	END LOOP;
END Supercomputadora;