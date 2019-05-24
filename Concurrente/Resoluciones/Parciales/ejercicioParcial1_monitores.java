/*Resolver este ejercicio con semáforos o Monitores.
En un centro de genética se debe administrar el uso de dos máquinas secuenciadoras
de AND con diferentes características; donde cada máquina se puede usar por un único
investigador a la vez. Existen N investigadores que deben secuenciar una muestra
de AND cada uno. Para esto, algunos de los investigadores pueden usar cualquiera
de las máquinas, y otros pueden usar una de ellas en particular. Cada investigador,
saca turno en cada una de las máquinas que le puede servir, y espera a que le llegue
el turno en una de ellas, la usa y se retira. 
Nota: suponga que existe una función ElegirMaquina() que retorna 1, 2 o 3 (1 indica
que solo debe sacar turno en la máquina 1, 2 que solo debe sacar turno en la máquina 2; 
3 indica que debe sacar turno en ambas máquinas). Maximizar la concurrencia.
*/ 

//El signal despierta a un proceso pero no le da prioridad, ya que lo despierta 
//para que compita por el procesador. 
Monitor Cola[i= 1 to 2]{
	Queue cola;
	cond maquina;

	Procedure sacarTurno(int id){
		cola.push(id);
		SIGNAL(maquina);
	}

	Procedure proximo(){
		if (cola.empty()) {
			wait(maquina);
		}else{
			return cola.pop();
		}
	}
}

Monitor Estado[i=1 to N]{
	string estado = 'Llegué';
	int maquina = 0;
	cond investigador;

	Procedure leerEstado(){
		return estado;
	}

	Procedure miMaquina(){
		return maquina;
	}

	Procedure cambiarEstado(i){
		switch estado{
			case 'Llegué':
				estado = 'Esperando';
				wait(investigador);
			case 'Esperando':
				estado = 'Atendido';
				maquina = i;
				SIGNAL(investigador);
			case 'Atendido':
				estado = 'Ya se fue';	
		}

	}
}

Process investigadores[i=1 to N]{
	cola = ElegirMaquina(i);
	if ((cola == 1) or (cola = 3)){
		cola[1].sacarTurno(i);
	}
	if (( cola == 2) or (cola == 3)){
		cola[2].sacarTurno(i);
	}
	estado[id].cambiarEstado(i);
	secuenciar(estado[i].miMaquina());
	--me voy--
}

Process maquina[i= 1 to N]{
	while true{
		investigador = cola[i].proximo();
		estado[investigador].cambiarEstado(i);
	}
}