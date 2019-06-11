/*Resolver este ejercicio con PMS o ADA. Se tiene un sistema que administra el uso
de una Supercomputadora por parte de N personas. Las palabras tienen diferentes 
categorías (A, B, C). La supercomputadora puede ser usada por una única persona
a la vez. Cuando está libre se debe determinar a quién permitirle su uso de acuerdo
a la prioridad determinada por la categoría: la categoría A es la de mayor prioridad, 
luego la B y por último la C. Dentro de cada categoría se debe respetar el orden
de llegada. Nota: suponga que existe una función Categoría() que le indica a la 
persona de qué tipo es.*/

TASK TYPE Persona IS
	ENTRY usarSuperComputadora();
END;

personas = array(1..N) of Persona;

TASK BODY Persona IS
	char categoria;
BEGIN
	categoria:= Categoria();
	if (categoria == 'A') then 
		SuperComputadora.usoCategoriaA();
		else if(categoria == 'B') then 
			SuperComputadora.usoCategoriaB();
			else if(categoria == 'C') then 
				SuperComputadora.usoCategoriaC();
	delay();
	SuperComputadora.liberar();
END Persona;

TASK SuperComputadora IS
	ENTRY usoCategoriaA();
	ENTRY usoCategoriaB();
	ENTRY usoCategoriaC();
	ENTRY liberar();
END;

TASK BODY SuperComputadora IS
	boolean libre;
BEGIN
	SELECT ACCEPT liberar(); libre:= true;
	OR WHEN(usoCategoriaA`count != 0 AND libre) ACCEPT usoCategoriaA(); libre:= false;
	OR WHEN(usoCategoriaB`count != 0 AND usoCategoriaA`count = 0 AND libre) ACCEPT usoCategoriaB(); libre:= false;
	OR WHEN(usoCategoriaC`count != 0 AND usoCategoriaA`count = 0 AND usoCategoriaB`count = 0 AND libre) 
		ACCEPT usoCategoriaB(); libre:= false;
	END SELECT;
END SuperComputadora;