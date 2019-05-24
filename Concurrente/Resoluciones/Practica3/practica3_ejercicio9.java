/*9. Suponga una comisión con 50 alumnos. Cuando los alumnos llegan forman una fila, una
vez que están los 50 en la fila el jefe de trabajos prácticos les entrega el número de grupo
(número aleatorio del 1 al 25) de tal manera que dos alumnos tendrán el mismo número
de grupo (suponga que el jefe posee una función DarNumero() que devuelve en forma
aleatoria un número del 1 al 25, el jefe de trabajos prácticos no guarda el número que le
asigna a cada alumno). Cuando un alumno ha recibido su número de grupo comienza a
realizar la práctica. Al terminar de trabajar, el alumno le avisa al jefe de trabajos
prácticos y espera la nota. El jefe de trabajos prácticos, cuando han llegado los dos
alumnos de un grupo les devuelve a ambos la nota del GRUPO (el primer grupo en
terminar tendrá como nota 25, el segundo 24, y así sucesivamente hasta el último que
tendrá nota 1).*/

Monitor comision{

	cond estudiantes[50];
	cond grupos[25];
	int cantEstudiantes[25] = ([25] 0);
	cond jtp;
	int grupo[50] = 0; 
	int llegaron = 0;
	Queue fila;
	int notas[25] = ([25] 0);
	int nota = 25;

	Procedure llegar(i){
		llegaron++;
		fila.push(i);
		if (llegaron == 50) {
			SIGNAL(jtp);
		}
		wait(estudiantes[i]);
	}

	Procedure esperarNota(grupo){
		fila.push(grupo);
		SIGNAL(jtp);
		wait(grupos[grupo]); 
	}

	Procedure asignarGrupos(){
		wait(jtp);
		for (i=1 to 50) {
			estudiante = fila.pop();
			DarNumero(estudiante);
			SIGNAL(estudiantes[estudiante]);
		}
	}

	Procedure darNotas(){
		wait(jtp);
		grupo = fila.pop();
		cantEstudiantes[grupo]++;
		if (cantEstudiantes[grupo] == 2) {
			notas[grupo] = nota;
			SIGNAL_ALL(grupos[grupo]);
			nota --;
		}
	}

}

Procedure estudiante[i=1 to 50]{
	comision.llegar(i);
	//se pone a hacer su tarea
	comision.esperarNota(grupo[i]);
	write("Mi nota es: ", notas[i]);
}

Procedure profesor{

}