/*6.En  un  banco  hay  C  Clientes  que  van  al  banco  a  pagar,  algunas  de  ellos  
son  clientes  Habituales  y  otros  Esporádicos.  En  el  banco  hay  una  única  caja  
donde  se  atiende  a  los  clientes dando siempre prioridad a los Clientes Habituales, 
y entre los de cada categoría (Habitual  o  Esporádico)  en  el  orden  de  llegada.  
Nota:  suponga  que  existe  una  función  DarCategoría que le indica al cliente de que 
categoría es (Habitual o Esporádico). */


chan clienteHabitual(int)
chan clienteEsporadico(int)

Process caja{
	int cliente;
	while true{
		if (not empty(clienteHabitual)) 
			-> receive clienteHabitual(cliente);
		▣ (not empty(clienteEsporadico) AND empty(clienteHabitual))
			-> receive clienteEsporadico(cliente);
		--atender--
		send atencion[cliente](1);
	}
}

Process cliente[i= 1 to C]{
	categoria = DarCategoría();
	if (categoria == 'Habitual') {
		send clienteHabitual(i);
	}else {
		send clienteEsporadico(i);
	}

	receive atencion[i](int);
	--se va--
}