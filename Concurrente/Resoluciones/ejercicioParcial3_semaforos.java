/*En una casa de comida rápida trabajan N despachadores. A la misma llegan M 
clientes, donde cada uno espera a ser atendido por cualquiera de los despachadores.
Una vez que el cliente es atendido entrega su pedido al despachador, el cual se 
dirige al cocinero, le da el pedido y espera la comida realizada. Luego el 
despachador le da al cliente un ticket que contiene el monto a pagar. El cliente 
se dirige a la caja, paga su ticket, recibe la factura y con ella se dirige al 
despachador que lo atendió, le entrega la factura y obtiene el pedido. Luego de 
tomar el pedido el cliente  se retira y el despachador puede seguir atendiendo.
Nota: ĺos clientes deben ser atendidos en orden de llegada, y si un cliente pasa 
mas de 25 minutos esperando que lo atiendan se retira de la casa de comidas. */


Sem esperarFactura[M] = 0;
Queue cola;
Sem serAtendida = 0;

Process Despachadora[i=1 to N]{

}

Process Cliente[i=1 to M]{

}

Process Cocinera{

}
