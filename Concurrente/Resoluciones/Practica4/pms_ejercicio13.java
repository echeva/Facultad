/*13.Existen N pacientes que van al consultorio de UN médico el cual los atiende de acuerdo
al orden de llegada. Cada paciente espera a que el médico le haga la receta con los
medicamentos y luego se dirige a la Farmacia a comprarlos, espera a que lo terminen de
atender y se retira. En la Farmacia hay UN empleado que atiende a la gente de acuerdo al
orden de llegada. */

Process paciente[i=1 to N]{
	coordinador ! avisarLlegada(i);
	medico ? receta(receta);
	//cuando tiene la receta se dirige a la farmacia
	coordinadorFarmacia avisarLlegada(i);
	empleado ? retirarse(); 
}

Process coordinador{ //CONSULTAR puede ser uno solo no?
	Queue cola;
	while(true){
		if true; paciente[*] ? avisarLlegada(idPaciente) -> push(cola(idPaciente));
		 ▣ !empty(cola); medico ? libre() -> medico ! atender(pop(cola()));
	}
}

Process medico{
	while(true){
		coordinador ! libre();
		coordinador ? atender(idPaciente);
		//realiza la receta
		paciente[idPaciente] ! receta(receta);
	}
}

Process coordinadorFarmacia{
	Queue cola;
	while(true){
		if true; paciente ? avisarLlegada(idPaciente) -> push(cola, idPaciente);
		 ▣ !empty(cola); empleado ? libre() -> empleado ! atender(pop(cola()));
	}
}

Process empleado{
	while(true){
		coordinadorFarmacia ! libre();
		coordinadorFarmacia ? atender(idPaciente);
		//atiende y le dice al paciente que ya se puede retirar.
		paciente[idPaciente] ! retirarse();
	}
}