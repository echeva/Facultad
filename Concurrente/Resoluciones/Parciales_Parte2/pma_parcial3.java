/*Se desea modelar el funcionamiento de un banco el cual se encarga unicamente
de cobrar el servicio de seguro de sus clientes, en el cual existen 5 cajas
donde se cobra.
Existen P personas que desean pagar su seguro en el banco. Para esto cada persona
selecciona la caja donde hay menos personas esperando, una vez seleccionada espera
a ser atendido según el orden de llegada. Cuando lo atienden, si espero más de 
20 minutos entonces el banco le regala el cobro del servicio, en caso contrario, 
debe abonar una cierta cantidad dependiendo de la categoría de cliente (en caso 
de no pagar justo, el empleado debe darle el vuelto).
Si la persona esperó más de 20 minutos, puede optar por levantar un queja. En ese
caso, una vez que se retiró de la caja (sin pagar), se dirige al departamento de 
quejas donde un supervisor toma los datos de la persona (DNI de la persona, monto)
y el número de caja que lo atendió. Cuando se han levantado más de 15 quejas para 
una misma caja el supervisor cierra la caja dejando pasar gratis a todas las personas
que estaban esperando en ella.
Implemente utilizando pasaje de mensajes sincrónico o asincrónico según crea 
conveniente. 
Aclaraciones:
	* Existe una función Costo que retorna la cantidad que debe pagar quien la invoca.
	* Supone que nunca se necesitará cerrar todas las cajas.
	* Existe una función que dado el DNI de la persona devuelve si la misma quiere o
	no realizar la queja.
	* MAXIMICE LA CONCURRENCIA.*/

//para ir llevando la cuenta de ka cantidad de gente en cada caja, lo hacemos de forma centralizada, 
//podemos usar un coordinador dsps para sacar el max o el min, usamos una funcion magica

Chan cajas[5](int id);
Chan llegue(int id);
Chan asignarCaja[P](int caja);

Process Persona[i=1 to P]{
	int caja = 0;

	send llegue(i);
	receive asignarCaja(caja);
	send cajas[caja](i);
	send Timer[i].start(now);
	//Espera a que le atiendan.
	receive atender();
}

Process Caja[i= 1 to 5]{
	while true{

	}
}

Process Coordinador{
	int personaId;
	while true{
		receive llegue(personaId);
		send asignarCaja[personaId](asignarCajaMagica());
	}
}

Process Timer[i=1 to P]{
	date hora;

	receive start(hora);
	delay(20`);

}