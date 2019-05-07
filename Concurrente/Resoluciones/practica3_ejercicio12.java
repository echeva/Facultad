/*12.Resolver el uso de un equipo de videoconferencia que puede ser usado por una única
persona a la vez. Hay P Personas que utilizan este equipo (una única vez cada uno) para
su trabajo de acuerdo a su prioridad. La prioridad de cada persona está dada por un
número entero positivo. Además existe un Administrador que cada 3 hs. incrementa en 1
la prioridad de todas las personas que están esperando por usar el equipo.*/

Monitor Equipo{

	Queue cola;
	cond persona[P];

	Procedure solicitar(id, prioridad){
		cola.pushConPrioridad(id, prioridad);
		wait(persona[id]);
	}

	Procedure obtener(){
		if !(cola.empty){ //Creo que la cola nunca debería estar vacía. Si no hay nadie en la cola, nadie va a llamar a este proceso.
			id = cola.popConPrioridad();
			SIGNAL(persona[id]);
		}
	}

	Procedure incrementarPrioridad(){
		Queue cola_aux;

		while (!cola.empty){
			persona = cola.pop();
			cola_aux.pushConPrioridad(persona.id, (persona.prioridad + 1));
		} 

		cola = cola_aux;
	}
}

Process Persona[i=1 to P]{
	int prioridad = 0;

	prioridad = darPrioridad();
	Equipo.solicitar(i, prioridad);
	Equipo.obtener();
	--usar--
}

Process AdministradorEquipo(){
	while true{
		delay(180);
		Equipo.incrementarPrioridad();
	}
}