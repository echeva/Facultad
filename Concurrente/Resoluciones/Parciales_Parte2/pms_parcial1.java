/* En un banco se tiene un sistema que administra el uso de una sala de reuniones
por parte de N clientes. Los clientes se clasifican en Habitulaes y Temporales.
La sala puede ser usada por un único cliente a la vez, y cuando está libre se debe
determinar a quién permitirle su uso dando prioridad a los clientes Habituales. 
Dentro de cada clase de cliente se debe respetar el orden de llegada. Nota: suponga
que existe una funcion Tipo() que le indica al cliente de qué tipo es.*/

Process Cliente[i= 1 to N]{
	var tipo string;

	tipo = Tipo();
	Administrador ! usarSala(tipo, i);
	Sala ? pasar();
	-- usar sala --
	Sala ! liberarSala();

}

Process Sala{
	var cliente;
	while (true){
		Administrador ! salaLibre();
		Administrador ? solicitarCliente(cliente);
		Cliente[cliente.id] ! pasar();
		Cliente[cliente.id] ? liberarSala();
	}
}

Process Administrador{
	Queue habitual;
	Queue temporal;

	while (true) {
		if Cliente[*] ? usarSala(tipo, id)-> 
				if (tipo = 'Habitual') {
					push(habitual, id);
				}else{
					push(temporal, id);
				}
		/*Si hay clientes esperando, tomo el mensaje de sala libre, sino no.*/		
		▣ !empty(habitual) OR !empty(temporal); Sala ? salaLibre() ->
			if (!empty(habitual)) {
				Sala ! solicitarCliente(pop(habitual));
			}else{
				Sala ! solicitarCliente(pop(temporal))S
			}
	}
}
