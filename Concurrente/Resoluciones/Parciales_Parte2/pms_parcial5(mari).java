/*Resolver este ejercicio con PMS o ADA. Se tiene un sistema que administra el uso
de una Supercomputadora por parte de N personas. Las palabras tienen diferentes 
categorías (A, B, C). La supercomputadora puede ser usada por una única persona
a la vez. Cuando está libre se debe determinar a quién permitirle su uso de acuerdo
a la prioridad determinada por la categoría: la categoría A es la de mayor prioridad, 
luego la B y por último la C. Dentro de cada categoría se debe respetar el orden
de llegada. Nota: suponga que existe una función Categoría() que le indica a la 
persona de qué tipo es.*/

Process Coordinador{
	Queue colaCategoriaA;
	Queue colaCategoriaB;
	Queue colaCategoriaC;

	while(true){

		if true; Persona[*] ? solicitarAcceso(id, categoria) -> 
				if (categoria == 'A') -> push(colaCategoriaA, id);
				▣ (categoria == 'B') -> push(colaCategoriaB, id);
				▣ (categoria == 'C') -> push(colaCategoriaC, id);
		▣ (not empty(colaCategoriaA)); SuperComputadora ? libre() -> SuperComputadora ! acceso(pop(colaCategoriaA));
		▣ (empty(colaCategoriaA) AND not empty(colaCategoriaB)) SuperComputadora ? libre() -> 
				SuperComputadora ! acceso(pop(colaCategoriaB));
		▣ (empty(colaCategoriaA) AND empty(colaCategoriaB) AND not empty(colaCategoriaC)); SuperComputadora ? libre() ->
			   SuperComputadora ! acceso(pop(colaCategoriaC));
	}
}

Process Persona[i=1 to N]{
	char categoria;
	categoria = Categoria();

	Coordinador ! solicitarAcceso(i, categoria);
	SuperComputadora ? uso();
	delay();
	SuperComputadora ! liberar();
}

Process SuperComputadora{
	while(true){
		Coordinador ! libre();
		SuperComputadora ? acceso(id);
		Persona[id] ! uso();
		Persona[id] ? liberar();
	}
}