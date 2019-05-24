Monitor Fuente{
	cond nietos;
	cond primerNieto;
	cond reponer;
	int cant;
	boolean agarre = false;

	Procedure reponer(n){
		wait(reponer);
		cant = cant + n;
		SIGNAL(primerNieto);
	}

	Procedure agarrarCaramelo(){
		while (!agarre){ 
		//te pueden haber despertado pero te podes haber quedado sin caramelos de nuevo
			if (cant == 0) AND (!soyelprimero) {
				wait(nietos); //me duermo porque solo avisa el primero
			}else{
				if (soyelprimero){
					SIGNAL(reponer); //le aviso a la abuela que reponga
					wait(primerNieto); //me duermo hasta que la abuela reponga
					//agarra caramelo;
					cant --;
					agarre = true;
					SIGNAL_ALL(nietos);			
				}else{
					//agarra caramelo
					cant --;
					agarre = true;
				}
			}
		}
	}

}

Process nieto[i= 1..N]{
	while(true){
		fuente.agarrarCaramelo();
		delay(t);
	}
}

Process abuela(){
	while(true){
		fuente.reponer(n);
	}
}