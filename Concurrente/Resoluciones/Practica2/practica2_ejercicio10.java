/*10.Resolver el funcionamiento en una empresa de genética. Hay N clientes que
sucesivamente envían secuencias de ADN a la empresa para que sean analizadas y esperan
los resultados para poder envían otra secuencia a analizar. Para resolver estos análisis la
empresa cuenta con 3 servidores que van alternando su uso para no exigirlos de más (en
todo momento uno está trabajando y los otros dos descansando); cada 8 horas cambia en
servidor con el que se trabaja. El servidor que está trabajando, toma un pedido (de a uno
de acuerdo al orden de llegada de los mismos), lo resuelve y devuelve el resultado al
cliente correspondiente; si al terminar ya han pasado las 8 horas despierta al próximo
servidor y él descansa, sino continúa con el siguiente pedido. */

Sem clientes[n] = ([n] 0);
int horarios[3] = ([3] 0);
Sem servidores[3] = {1, 0, 0};
Queue cola;
Sem accesoCola = 1;
int proximo;

Procedure cliente[i= 1 to n]{
	while(true){
		//envia secuencia a analizar
		P(accesoCola);
		cola.push(i);
		V(accesoCola);
		P(clientes[i]); //espera su resultado para poder enviar otra secuencia a analizar
	}
}

Procedure servidor[i= 1 to 3]{ //el tiempo transcurrido se actualiza solo?
	while(true){
		P(servidores[i]);
		//Actualiza su horario
		horarios[i] = Cronometro new;
		while(horarios[i] <= 8){
			if (cola is not empty) {
				P(accesoCola);
				cliente = cola.pop();
				V(accesoCola);
				//resuelve el pedido
				V(clientes[cliente]);
			}
		}
		proximo = (i mod 3) + 1;
		V(servidores[proximo]);
		P(servidores[i]);
	}
}
