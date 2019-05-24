/*8. En un entrenamiento de futbol hay 20 jugadores que forman 4 equipos (cada jugador
conoce el equipo al cual pertenece llamando a la función DarEquipo()). Cuando un
equipo está listo (han llegado los 5 jugadores que lo componen), debe enfrentarse a otro
equipo que también esté listo (los dos primeros equipos en juntarse juegan en la cancha
1, y los otros dos equipos juegan en la cancha 2). Una vez que el equipo conoce la
cancha en la que juega, sus jugadores se dirigen a ella. Cuando los 10 jugadores del
partido llegaron a la cancha comienza el partido, juegan durante 50 minutos, y al
terminar todos los jugadores del partido se retiran (no es necesario que se esperen para
salir).*/

Monitor Equipo[i=1 to 4]{
	Cond esperar;
	int cantJugador = 0;

	Procedure llegar(){
		cantJugador ++;
		if (cantJugador == 5){
			SIGNAL_ALL(esperar);
		}else{
			wait(esperar);
		}
	}	 
}

Monitor AdministradorPartido(int equipo){
	int cancha = 0;
	Cond esperar;
	Queue cola;

	Procedure enfrentarse(){
		if (cola.empty){
			cola.push(equipo);
			wait(esperar);
		}else{
			cancha ++;
			equipo = cola.pop();
			SIGNAL(esperar);
		}

		return cancha;
	}
}

Monitor Cancha[i=1 to 2]{
	int cantJugadores = 0;
	cond (esperar);

	Procedure entrar(){
		cantJugadores ++;
		if (cantJugadores < 10){
			wait(esperar);
		}else{
			SIGNAL_ALL(esperar);
		}
	}
}

Process jugador[i=1 to 20]{
	int equipo = 0;
	int cancha = 0;

	equipo = DarEquipo();
	Equipo[equipo].llegar();
	cancha = AdministradorPartido.enfrentarse(equipo);
	Cancha[cancha].entrar(i);
	delay(50); //jugar. No se puede hacer dentro de la cancha porque jugaría una persona a la vez.
 	-- irse --
}