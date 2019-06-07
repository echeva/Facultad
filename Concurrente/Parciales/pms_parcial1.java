/*En un banco se tiene un sistema que administra el uso de una sala de reuniones
por parte de N clientes. Los clientes se clasifican en Habitulaes y Temporales.
La sala puede ser usada por un único cliente a la vez, y cuando está libre se debe
determinar a quién permitirle su uso dando prioridad a los clientes Habituales. 
Dentro de cada clase de cliente se debe respetar el orden de llegada. Nota: suponga
que existe una funcion Tipo() que le indica al cliente de qué tipo es.*/

Process cliente[i= 1 to N]{
	var tipo string;

	tipo = Tipo();
	administrador ! usarSala(tipo, i);
	sala ? pasar();
	-- usar sala --

}

Process sala{
	var cliente;
	while (true){
		administrador ! salaLibre();
		administrador ? solicitarCliente(cliente);
		cliente.id ! pasar();
	}
}

Process administrador{
	Queue habitual;
	Queue temporal;
	Boolean salaLibre;

	while (true) {
		if cliente[*] ? usarSala(tipo, id)-> 
				if (tipo = 'Habitual') {
					push(habitual, id);
				}else{
					push(temporal, id);
				}
		▣ sala ? salaLibre() -> salaLibre = true;
		▣ not empty(habitual) OR not empty(temporal); sala? solicitarCliente(cliente) ->  
				if (not empty(habitual)) {
					sala!solicitarCliente(habitual.pop());
				}else{
					sala!solicitarCliente(temporal.pop());
				}
	}
}

//No se que hacer cuando la sala avisa que esta libre y tiene que esperar a que
//le pasen un cliente. No entiendo si es un solo evento o que.