/*5) Suponga que N personas llegan a la cola de un banco. Una vez que la persona se agrega
en la cola no espera más de 15 minutos para su atención, si pasado ese tiempo no fue
atendida se retira. Para atender a las personas existen 2 empleados que van atendiendo
de a una y por orden de llegada a las personas.*/

/*NECESITA TENER UN PROCESO TIMER QUE ES EL QUE VA A HACER EL DELAY DE 15 MIN
Y UN MONITOR QUE ES EL QUE MANTIENE EL ESTADO */

Monitor colaBanco{
	queue cola;
	cond despertar;

	Procedure llegar(id){
		cola.push(id);
		SIGNAL_ALL(despertar);
	}

	Procedure proximo(){
		if (!cola.empty){
			return cola.pop();
		}else{
			wait(despertar); //Si no hay nadie para atender, todes les empleades se van a tomar mate;
		}
	}
}

Monitor estado[i=1 to N]{
	cond esperar;
	estado = 'Llegué';

	Procedure cambiarEstado(){ 
		switch estado{
			case 'Llegué':
				estado = 'Esperando';
				timer(i);
				wait(esperar);
			case 'Esperando':
				SIGNAL(esperar);
				estado = 'Se fue';
		}
	}	

}

Process timer[i=1 to N](persona){
	delay(15);
	estado[persona].cambiarEstado();
}

Process empleado[i=1 to 2]{
	while true{
		persona = cola.proximo();
		estado[persona].cambiarEstado(); //Si esta en 'Se fue', no hace nada. Si esta en esperando, hace un SIGNAL
										// y lo pone en 'Se fue'.		
	}
}

Process Persona[i= 1 to N]{
	colaBanco.llegar(i);
	estado[i].cambiarEstado(); // Pasa su estado a espando. Saldrá una vez que fue atendide o que el timer le de timeOut
	-- irse --;
}