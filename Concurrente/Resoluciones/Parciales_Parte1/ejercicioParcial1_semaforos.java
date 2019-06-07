/*En una carpintería se realizan muebles emsamblando 3 partes, existen N1 carpinteros para realizar la parte 1, N2
para realizar la parte2, N3 para realizar la parte3. Mas N carpinteros que se encargan de emsamblar las partes del 
mueble.
Los encargados de embsamblar deben tomar una pieza de cada clase, juntar las partes y una vez que la emsamblaron
los carpinteros que le dieron la pieza pueden seguir trabajando (no antes). En la carpintería solo se armarán 30 
muebles y no se podrán producir pierzas de más.
El armado debe realizarse en forma concurrente, solo pueden esperarse entre los carpinteros de un mismo tipo, cuando
terminan una pieza hasta que los toma un emsamblador (es decir, puede pensar que los emsambladores toman las piezas
de a uno).
Todos los procesos deben terminar correctamente, no pueden quedar procesos colgados. Modalice con Semáforos.*/


int cantPiezas1 = 0;
Sem accesoPiezas1 = 1;
int cantPiezas2 = 0;
Sem accesoPiezas2 = 1;
int cantPiezas3 = 0;
Sem accesoPiezas3 = 1;
Sem esperarEmsamble1[N1] = 0;
Sem esperarEmsamble2[N2] = 0;
Sem esperarEmsamble3[N3] = 0;
int cantPiezasEmsambladas = 0;
Sem accesoEmsambladas = 1;
Queue colaPiezas1;
Queue colaPiezas2;
Queue colaPiezas3;
Sem accesoColaPiezas1 = 1;
Sem accesoColaPiezas2 = 1;
Sem accesoColaPiezas3 = 1;
Sem esperarPieza1 = 0;
Sem esperarPieza2 = 0;
Sem esperarPieza3 = 0;


Process carpitereN1[i=1 to N1]{
	P(accesoPiezas1);
	while (cantPiezas1 < 30) {
		cantPiezas1 ++;
		V(accesoPiezas1);
		pieza = hacerPieza();
		P(accesoColaPiezas1);
		colaPiezas1.push(i, pieza);
		V(accesoColaPiezas1);
		V(esperarPieza1);   --darsela al emsamblador--
		P(esperarEmsamble1[i]);
		P(accesoPiezas1);
	}
	V(accesoPiezas1);
}

Process carpitereN2[i=1 to N2]{
	P(accesoPiezas2);
	while (cantPiezas2 < 30) {
		cantPiezas2 ++;
		V(accesoPiezas2);
		pieza = hacerPieza();
		P(accesoColaPiezas2);
		colaPiezas2.push(i, pieza);
		V(accesoColaPiezas2);
		V(esperarPieza2);   --darsela al emsamblador--
		P(esperarEmsamble2[i]);
		P(accesoPiezas2);
	}
	V(accesoPiezas2);
}

Process carpitereN3[i=1 to N3]{
	P(accesoPiezas3);
	while (cantPiezas3 < 30) {
		cantPiezas3 ++;
		V(accesoPiezas3);
		pieza = hacerPieza();
		P(accesoColaPiezas3);
		colaPiezas3.push(i, pieza);
		V(accesoColaPiezas3);
		V(esperarPieza3);   --darsela al emsamblador--
		P(esperarEmsamble3[i]);
		P(accesoPiezas3);
	}
	V(accesoPiezas3);
}

Process emsamblador[i= 1 to N]{
	P(accesoEmsambladas);
	while (cantPiezasEmsambladas < 30) {
		cantPiezasEmsambladas ++;
		V(accesoEmsambladas);
		P(esperarPieza1);
		P(esperarPieza2);
		P(esperarPieza3);
		
		P(accesoColaPiezas1).
		pieza1 = colaPiezas1.pop();
		V(accesoColaPiezas1);
		P(accesoColaPiezas2).
		pieza2 = colaPiezas2.pop();
		V(accesoColaPiezas2);
		P(accesoColaPiezas3).
		pieza3 = colaPiezas3.pop();
		V(accesoColaPiezas3);
		
		emsamblar(pieza1.pieza, pieza2.pieza, pieza3.pieza);

		V(esperandoEmbamble1[pieza1.id]);
		V(esperandoEmbamble2[pieza2.id]);
		V(esperandoEmbamble3[pieza3.id]);
		P(accesoEmsambladas);
	}
	V(accesoEmsambladas);
}