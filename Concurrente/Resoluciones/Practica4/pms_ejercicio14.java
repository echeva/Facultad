/*14.Suponga que existe un antivirus distribuido en él hay R procesos robots que
continuamente están buscando posibles sitios web infectados; cada vez que encuentran
uno avisan la dirección y continúan buscando. Hay un proceso analizador que se encargue
de hacer todas las pruebas necesarias con cada uno de los sitios encontrados por los robots
para determinar si están o no infectados. */

Process robot[i=1 to R]{
	while(true){
		//busca un posible sitio web infectado
		administrador ! direccion(aviso);
	}
}

Process analizador{
	while(true){
		administrador ! libre();
		administrador ? analizar(aviso);
		//realiza la prueba
	}
}

Process administrador{
	Queue direcciones;
	while(true){
		if true; robot[*] ? direccion(aviso) -> push(direcciones, aviso);
		 ▣ !empty(direcciones); analizador ? libre() -> analizador analizar(pop(direcciones()));
	}
}