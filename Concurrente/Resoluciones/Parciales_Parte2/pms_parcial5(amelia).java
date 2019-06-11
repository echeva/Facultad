/*Resolver este ejercicio con PMS o ADA. Se tiene un sistema que administra el uso
de una Supercomputadora por parte de N personas. Las personas tienen diferentes 
categorías (A, B, C). La supercomputadora puede ser usada por una única persona
a la vez. Cuando está libre se debe determinar a quién permitirle su uso de acuerdo
a la prioridad determinada por la categoría: la categoría A es la de mayor prioridad, 
luego la B y por último la C. Dentro de cada categoría se debe respetar el orden
de llegada. Nota: suponga que existe una función Categoría() que le indica a la 
persona de qué tipo es.*/


Process Persona[i= 1 to N]{
	char categoría;

	categoría = Categoría();
	Administrador ! quieroUsarComputadora(categoria, id);
	Supercomputadora ? usarSupercomputadora();
	delay() //Uso de la computadora
	Supercomputadora ! liberarSuper();

}

Process Supercomputadora{
	int persona;

	while true{
		Administrador ! SupercomputadoraLibre();
		Administrador ? recibirPersona(persona);
		Persona[persona] ! usarSupercomputadora();
		Persona[persona] ? liberarSuper();
	}
}

Process Administrador{
	char categoria;
	int id;
	Queue colaA;
	Queue colaB;
	Queue colaC;

	while true {
		if true; Persona[*] ? quieroUsarComputadora(categoria, id) -> 
						if (categoria == 'A') {
							push(colaA, id);
						}else{
							if categoria == 'B'{
								push(colaB, id);
							}else{
								push(colaC, id);
							}
						}
		▣ !empty(colaA) & !empty(colaB) & !empty(colaC); 
			Supercomputadora ? SupercomputadoraLibre() -> if !empty(colaA){
				Supercomputadora ! recibirPersona(pop(colaA));
			}else{
				if !empty(colaB){
					Supercomputadora ! recibirPersona(pop(colaB));
				}else{
					Supercomputadora ! recibirPersona(pop(colaC));
				}
			}
		end if;
	}
}
