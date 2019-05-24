/*7. Se debe modelar la descarga de cereales en una acopladora. Para esto existen N camiones
que deben descargar su carga. Los camiones se descargan de a uno por vez, y de acuerdo
al orden de llegada. Una vez que el camión llegó, espera a lo sumo 2 hs. a que le avisan
que es su turno para descargar, sino se retira sin realizar la descarga. Existe además un
empleado que es el encargado de avisar a cada camión cuando es su turno.
NOTAS: existe una función Descarga que es llamada por el camión que representa el
tiempo que tarda en descargar su contenido el camión*/


Chan colaDescarga(int);
Chan startTimer(int);
Chan estadoCamion[N](string);
Chan avisarCamion[N](int);

Process camion[i=1 to N]{
	string estado;
	//llega el camion
	send startTimer(i);
	send estadoCamion[i]('esperando');
	send colaDescarga(i);
	receive avisarCamion[i](estado);
	if (estado == 'descarga') {
		self.descarga(); //el camion realiza su descarga
	}
}

Process empleado{
	int idCamion;
	string estado;
	
	for (i=1; to N; i++) {
		receive colaDescarga(idCamion);
		receive estadoCamion[idCamion](estado);
		if (estado == 'esperando') {
			send estadoCamion[idCamion]('atendido');
			send avisarCamion[idCamion]('descarga');
		}
	}
}

Process timer[i=1 to N]{
	int idCamion;
	receive startTimer(idCamion);
	delay(2);
	receive estadoCamion[idCamion](estado);
	if (estado == 'esperando') {
		send estadoCamion[idCamion]('retirarse');
		send avisarCamion[idCamion]('retirarse');
	}
}