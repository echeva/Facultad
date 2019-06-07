/*
Suponga un juego donde hay 30 competidores. Cuando los jugadores llegan, avisan
al encargado, una vez que están los 30, el encargado les entrega un numero del 1 al 15, 
de tal manera que dos jugadores tendrán el mismo número. (Suponga que existe un función
darNumero() que otorga un numero aleatorio entre 1 y 15, el encargado no guarda el 
número que les asigna a los competidores). Una vez que ya se entregaron los 30 números
los competidores buscarán concurrentemente al compañero que tenga el mismo número.
Cuando los competidores se encuentran, permanecen en una sala durante 15 minutos y 
dejan de jugar. Luego cada uno de los competidores avisa al encargado que terminó
de jugar y espera a que su compañero (el que tenia el mismo número) también avise que
finalizó para irse ambos. El encargado cuando llega el segundo competidor les devuelve
a ambos el resultado que obtuvieron, que el orden en que se van. (Los primeros en 
retirarse tendrán el número 1, los últimos 15). Para modelizar el tiempo, utilice 
la función Delay(x) que produce un retardo de x minutos. Modalice con Semáforos.   
*/

Sem grupos[15] = [15]0;
int cantJugadores = 0;
Sem esperaLlegada[30] = [30]0;
int finGrupo[15] = [15]0;
int miGrupo[30] = 0;
Queue cola;
Sem accesoCola = 1;
Sem arribo = 0;
int puesto[15] = [15]0;
int puesto = 0;
Sem fin = 0;
Sem accesoFinGrupo;
Sem esperaCompañero[15] = [15]0;


Procedure competidores[i= 1 to 30]{
	-- llegada --
	V(arribo);
	P(esperaLlegada[i]);
	grupo = miGrupo[i];
	P(accesoFinGrupo);
	finGrupo[grupo] ++;
	if(finGrupo[grupo] == 2){
		V(accesoFinGrupo);
		V(esperaCompañero[grupo]);
	}else{
		V(accesoFinGrupo);
		P(esperaCompañero[grupo]);
	}
	delay(15min);
	P(accesoCola);
	cola.push(grupo);
	V(accesoCola);
	V(fin);
	P(grupos[i]);
	write('Mi puesto fue: ', puesto[grupo]);
}

Procedure encargado{
	for (j=1; j=30; j++) {
		P(arribo);
	}
	for (j=1; j=30; j++) {
		darNumero(miGrupo[j]);
	}
	for (j=1; j=30; j++) {
		V(esperaLlegada[j]);
	}

	for (j=1; j=30; j++) {
		P(fin);
		P(cola);
		jugador = cola.pop();
		V(cola);
		P(accesoFinGrupo);
		finGrupo[jugador] --;
		if(finGrupo[jugador] == 0){
			puesto++;
			puesto[jugagor] = puesto;
			V(grupos[jugador]);
			V(grupos[jugador]);
		}
	}
}