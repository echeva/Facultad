/*Se  debe  modelar  una  casa  de  Comida  Rápida,  en  el  cual  trabajan  2  cocineros  
y 3 vendedores. Además hay C clientes que dejan un pedido y quedan esperando a que se 
lo alcancen. Los pedidos que hacen los clientes son tomados por cualquiera de los 
vendedores y se lo pasan a los cocineros para que realicen el plato. Cuando no hay pedidos 
para atender, los vendedores aprovechan para reponer un pack de bebidas de la heladera 
(tardan entre 1 y 3 minutos para hacer esto). Repetidamente  cada  cocinero  toma  un  
pedido  pendiente  dejado  por  los  vendedores,  lo  cocina y se lo entrega directamente 
al cliente correspondiente.*/

chan pedido(int idCliente, char pedido);
chan cocinar(int idCliente, char pedido);
chan entregarPedido[1 to C](char pedido);
chan hayPedido(int vendedor);
chan pedidoVendedor[1 to 3](int idCliente, char pedido);

Process cliente[i=1 to C]{
	char pedido = miPedido();
	send pedido(i, pedido);
	receive entregarPedido[i](pedido); 
	//Consulta el cliente se queda esperando a recibir algo??
}

Process vendedor[i=1 to 3]{
	int idCliente;
	char pedido;

	while(true){
		send hayPedido(i); //le pregunta al coordinador si hay un pedido para atender
		receive pedidoVendedor[i](idCliente, pedido); //espera a que el coordinador le mande un pedido
		if (pedido == 'null') {
			delay(1,3);	//si no hay pedido, repone un pack de bebidas		
		}else{
			send cocinar(idCliente, pedido); //le manda el pedido a les cocineres
		}
	}
}

Process cocinero[i=1 to 2]{
	int idCliente;
	char pedido;

	while(true){
		receive cocinar(idCliente, pedido); //espera a recibir un pedido, ya que no realiza ninguna otra tarea
		//cocina
		send entregarPedido[idCliente](idCliente, pedido);
	}
}

Process coordinador{
	int idCliente;
	char pedido;
	int vendedor;

	receive hayPedido(vendedor);
	if (not_empty(pedido)) {
		receive pedido(idCliente, pedido);
		send pedidoVendedor[vendedor](idCliente, pedido);
	}else{
		send pedidoVendedor[vendedor](idCliente, 'null');
	}
}