// Se desea modelar una competencia de atletismo. Para eso existen dos tipos de procesos:
// C corredores y un portero. Los corredores deben esperar que se habilite la entrada a la
// pista, donde deben esperar que lleguen todos los corredores para comenzar. El portero es
// el encargado de habilitar la entrada a la pista.
// 		a) Implementar usando un proceso extra coordinador.
// 		b) Implementar sin usar ningún proceso extra.
// NOTAS: el proceso portero NO puede contabilizar nada, su única función es habilitar la
// entrada a la pista; NO se puede suponer ningún orden en la llegada de los corredores al
// punto de partida.

//a)

Chan avisar(int);
Chan habilitado(boolean);
Chan habilitarPista(boolean);

process Coordinador(){
	int id;
	for i = 1 to C;
		receive avisar(id)
	send habilitarPista(True)
}

process Portero(){
	receive habilitarPista()
	for i = 1 to C;
		send habilitado[i](True)
}


process Corredor[i= 1 to C]{
	send avisar(i)
	receive habilitado[i]()
}

//b)
Chan contador(int);
Chan habilitado(boolean);
Chan estamosTodes(boolean);

process Portero(){
	boolean ok;
	receive estamosTodes(ok)
	for i = 1 to C:
		send habilitado[i](True)
}

process Corredor[i=1 to C]{
	int cant;
	if i == 1{
		send contador(1)
	}else{
		receive contador(cant)
		send contador(cant + 1)
	}

	if (cant + 1) == C {
	    send estamosTodes(true)
	}
	receive habilitado(bool)
	--correr--
}