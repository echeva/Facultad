/*10.Se debe modelar la atención en una panadería por parte de 3 empleados. 
Hay C clientes que  ingresan  al  negocio  para  ser  atendidos  por  cualquiera  de  
los  empleados,  los  cuales  deben atenderse de acuerdo al orden de llegada.*/

Queue cola;

process Cliente[i=1 to C]{
	administrador ! atencion(i);
}

process Administrador{
	while true{
		if condicionBooleana; atencion[*] ? -> encolar; //tiene algo
		▣ condicionBooleana; empleadoLibre[*] ? 
			-> 	desencolar;									
				empleadoLibre[id] ! pedido(pedido);
		fi
	}
}

process Empleado[i=1 to 3]{
	while true{
		administrador ! empleadoLibre[i] -> no sé que onda;
		administrador ? pedido(pedido) -> --atender--;
	}
}