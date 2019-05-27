/*12.En un laboratorio de genética veterinaria hay 3 empleados. El primero de ellos se encarga
de preparar las muestras de ADN lo más rápido posible; el segundo toma cada muestra de
ADN preparada y arma el set de análisis que se deben realizar con ella y espera el resultado
para archivarlo y continuar trabajando; el tercer empleado se encarga de realizar el análisis
y devolverle el resultado al segundo empleado.*/

Process empleado1{
	while(true){
		//prepara muestra de adn
		admin ! muestra(muestra);
	}
}

Process empleado2{
	while(true){
		admin ! libre();
		admin ? muestra(muestra); //toma la muestra de adn preparada
		//arma el set de analisis
		empleado3 ! set(analisis);
		empleado3 ? resultado(resuAnalisis);
	}
}

Process empleado3{
	while(true){
		empleado2 ? set(analisis);
		//realiza el analisis
		empleado2 ! resultado(resuAnalisis);
	}
}

Process admin{
	Queue muestras;
	while(true){
		if true; empleado1 ? muestra(muestra) -> push(muestras, muestra);
	 	 ▣ !empty(muestras); empleado2 ? libre() -> empleado2 analisis(pop(muestras)); 
	}
}