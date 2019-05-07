/*
Se tienen N procesos que comparten el uso de una CPU.
Un proceso, cuando necesita usar la CPU, le pide el uso, le proporciona el trabajo
que va a ejecutar y el tiempo que insume ejecutar el trabajo. Luego, el proceso
se queda dormido, hasta que la CPU haya finalizado de ejecutar su trabajo, momento
en que es despertado y prosigue su ejecución normal. La CPU recorre circularmente
la lista de procesos esperando para ser ejecutados. Saca cada proceso de la lista
y lo ejecuta durante 10milisegundos (o un lapso menor, si el tiempo de ejecución
del proceso es menor a 10mls). Una vez que ejecutó el proceso, si a éste le queda
tiempo de ejecución, lo vuelve a poner en la lista. Cuando la lista está vacía, 
la CPU no debe hacer nada, debe esperar a que un proceso entre a la lista de ejecución
de procesos para ponerse a trabajar.
Tenga en cuenta que existe una función delay(x), que retarda un proceso durante
X mls. La ejecución de un proceso puede ser modelizada utilizando esta función. 
No haga suposiciones acerca de los tiempos de ejecución de los procesos.
Modelice utilizando Monitores.
*/

Monitor administrador{
	
	queue cola; 
	cond listaProcesos(1..N);

	Process solicitarCPU(trabajo, tiempo, id){
		cola.push(trabajo, tiempo, id);
		wait(listaProcesos[i]); //el proceso se queda dormido
		//El enunciado dice que la cpu lo pone en la lista de procesos, 
		//pero se hace aca?
	}

	Process ejecutar(){
		if( ! empty(cola) ){
			proceso = cola.pop();
			if (proceso.tiempo() > 10) {
				//le da 10mls de tiempo de ejecucion
				proceso.tiempo(tiempo-10);
				delay(10);
				cola.push(proceso.trabajo, proceso.tiempo, proceso.id);
			}else{
				delay(proceso.tiempo);
				SIGNAL(listaProcesos[proceso.id]);
			}
		}
	}
}

Process CPU{
	while(true){
		administrador.ejecutar();
	}
}

Process Proceso[i= 1 to N]{
	trabajo;
	tiempo;
	administrador.solicitarCPU(trabajo, tiempo, i);
	--ejecutar tareas--
}