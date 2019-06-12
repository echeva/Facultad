/*10.Se debe modelar la atención en una panadería por parte de 3 empleados. 
Hay C clientes que  ingresan  al  negocio  para  ser  atendidos  por  cualquiera  de  
los  empleados,  los  cuales  deben atenderse de acuerdo al orden de llegada.*/
//si la condicion booleana siempre es true, se lo dejamos explicito

process Cliente[i=1 to C]{
	administrador ! atencion(i);
}

process Administrador{
	Queue cola;
	int idCliente;
	int idEmpleado;

	while true{
		if true; cliente[*] ? atencion(idCliente) -> push(cola, idCliente); //tiene algo
		▣ !empty(cola); empleadoLibre[*] ? libre(idEmpleado)
			-> empleadoLibre[idEmpleado] ! pedido(pop(cola));
		end if;
	}
}

process Empleado[i=1 to 3]{
	Pedido pedido; 

	while true{
		administrador ! libre(i);
		administrador ? pedido(pedido);
		 --atender--;
	}
}