/*8. Resolver la administración de las impresoras de una oficina. Hay 3 impresoras, N usuarios
y 1 director. Los usuarios y el director están continuamente trabajando y cada tanto envían
documentos a imprimir. Cada impresora, cuando está libre, toma un documento y lo
imprime, de acuerdo al orden de llegada, pero siempre dando prioridad a los pedidos del
director. Nota: los usuarios y el director no deben esperar a que se imprima el documento.*/
/* Con el administrador evitamos que uno de los process impresora se quede trabado en un receive */

Chan colaUsuarios(String);
Chan colaDirector(String);
Chan solicitarDocumento(String);
Chan colaImpresora[i](String);

Process usuario[i=1 to N]{
	String documento;
	while(true){
		//trabaja
		send colaUsuarios(documento);
	}
}

Process director{
	String documento;
	while(true){
		//trabaja
		send colaDirector(documento);
	}
}

Process administrador{
	int impresora;
	String documento;
	while(true){
		receive solicitarDocumento(impresora); //espera que la impresora le solicite uno
		if not empty(colaDirector) 
			-> receive colaDirector(documento); send colaImpresora[impresora](documento);
		▣ empty(colaDirector) and not empty(colaUsuarios) -> receive colaUsuarios(documento); send colaImpresora[impresora](documento);
	}
}

Process impresora[i=1 to 3]{
	String documento;
	while(true){
		send solicitarDocumento(i); //La impresora esta libre
		receive colaImpresora[i](documento); //espera que haya un documento para imprimir
		if (documento != null) {
			//imprime el documento
		}
	}
}