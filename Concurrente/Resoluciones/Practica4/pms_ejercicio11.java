/*11.Existe una casa de comida rápida que es atendida por 1 empleado. Cuando una persona
llega se pone en la cola y espera a lo sumo 10 minutos a que el empleado lo atienda. Pasado
ese tiempo se retira sin realizar la compra.*/


Process persona[i=1 to N]{
	timer[i]! avisarLlegada(i);
	coordinador! avisarLlegada(i);
	if Empleado?irse() -> skip();
     Timer?irse() -> skip();
}

Process empleado{
	int persona
	string estado

	while (true)
		Coordinador!libre()
		Coordinador?atender(persona)
		Estado[persona]!empleadoConsulta()
		Estado[persona]?check(estado)
		if (estado == 'esperando')
		  atender(persona)
		  Persona[persona]!irse()
	end
}

Process coordinador{
	queue cola
	int id

	do Persona[*]?avisarLlegada(id) -> cola.encolar(id)
	 !empty(cola); Empleado?libre() -> Empelado!atender(cola.desencolar)
	end
}

Process estado{
	string estado = 'esperando'
	int id
  
  	do Empleado?empleadoConsulta() -> Empleado!check(estado)
                                    if (estado == 'esperando')
                                      estado = 'atendido'
    Timer[*]?timerConsulta(id) -> Timer[id]!check(estado)
                                   if (estado == 'esperando')
                                      estado = 'yendo'
  	end
}

Process timer[i=1 to N]{
	Persona[i]? avisarLlegada();
  	delay(10);
  	Estado[i]! timerConsulta(i);
  	Estado[i]? check(estado);
  	if (estado == 'esperando')
    	Persona[i]!irse()

}