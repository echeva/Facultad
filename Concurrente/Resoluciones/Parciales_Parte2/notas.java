/* CONSIDERACIONES PARA RESOLVER LOS EJERCICIOS DE PMA:
	• Los canales son compartidos por todos los procesos.
	• Cada canal es una cola de mensajes, por lo tanto el primer mensaje encolado es el
	primero en ser atendido.
	• Por ser pasaje de mensajes asincrónico el send no bloquea al emisor.
	• Se puede usar la sentencia empty para saber si hay algún mensaje en el canal, pero
	no se puede consultar por la cantidad de mensajes encolados.
	• Se puede utilizar el if/do no determinístico donde cada opción es una condición
	boolena donde se puede preguntar por variables locales y/o por empty de canales.
	 if (cond 1) -> Acciones 1;
	   (cond 2) -> Acciones 2;
	 ….
	   (cond N) -> Acciones N;
	 end if
	De todas las opciones cuya condición sea Verdadera elige una en forma no
	determinística y ejecuta las acciones correspondientes. Si ninguna es verdadera
	sale del if/do si hacer nada.
	• Se debe tratar de evitar hacer busy waiting (sólo hacerlo si no hay otra opción).
	• En todos los ejercicios el tiempo debe representarse con la función delay.
*/

/* CONSIDERACIONES PARA RESOLVER LOS EJERCICIOS DE PMS:
	• Los canales son punto a punto y no deben declararse.
	• No se puede usar la sentencia empty para saber si hay algún mensaje en un canal.
	• Tanto el envío como la recepción de mensajes es bloqueante.
	• Sintaxis de las sentencias de envío y recepción:
		Envío: nombreProcesoReceptor!port (datos a enviar)
		Recepción: nombreProcesoEmisor?port (datos a recibir)
		El port (o etiqueta) puede no ir. Se utiliza para diferenciar los tipos de mensajes
		que se podrían comunicarse entre dos procesos.
	• En la sentencia de comunicación de recepción se puede usar el comodín * si el origen
		es un proceso dentro de un arreglo de procesos. Ejemplo: Clientes[*]?port(datos).
	• Sintaxis de la Comunicación guardada:
		Guarda: (condición booleana); sentencia de recepción → sentencia a realizar
	 	Si no se especifica la condición booleana se considera verdadera (la condición
		booleana sólo puede hacer referencia a variables locales al proceso).
	 	Cada guarda tiene tres posibles estados:
	 	Elegible: la condición booleana es verdadera y la sentencia de comunicación
		se puede resolver inmediatamente.
	 	No elegible: la condición booleana es falsa.
	 	Bloqueada: la condición booleana es verdadera y la sentencia de
		comunicación no se puede resolver inmediatamente.
	 	Sólo se puede usar dentro de un if o un do guardado:
	 	El IF funciona de la siguiente manera: de todas las guardas elegibles se
		selecciona una en forma no determinística, se realiza la sentencia de
		comunicación correspondiente, y luego las acciones asociadas a esa guarda.
		Si todas las guardas tienen el estado de no elegibles, se sale sin hacer nada.
		Si no hay ninguna guarda elegible, pero algunas están en estado bloqueado,
		se queda esperando en el if hasta que alguna se vuelva elegible.
	 	El DO funciona de la siguiente manera: sigue iterando de la misma manera
		que el if hasta que todas las guardas hasta que todas las guardas sean no
		elegibles
*/

/* CONSIDERACIONES PARA RESOLVER LOS EJERCICIOS:
	1. NO SE PUEDE USAR VARIABLES COMPARTIDAS
	2. Declaración de tareas
		• Especificación de tareas sin ENTRY’s (nadie le puede hacer llamados).
			TASK Nombre;
			TASK TYPE Nombre;
		• Especificación de tareas con ENTRY’s (le puede hacer llamados). Los entry’s
		funcionan de manera semejante los procedimientos: solo pueden recibir o
		enviar información por medio de los parámetros del entry. NO RETORNAN
		VALORES COMO LAS FUNCIONES
			TASK [TYPE] Nombre IS
			ENTRY e1;
			ENTRY e2 (p1: IN integer; p2: OUT char; p3: IN OUT float);
			END Nombre;
		• Cuerpo de las tareas.
			TASK BODY Nombre IS
			Codigo que realiza la Tarea;
			END Nombre;
	3. Sincronización y comunicación entre tareas
		• Entry call para enviar información (o avisar algún evento).
		 	NombreTarea.NombreEntry (parametros);
		• Accept para atender un pedido de entry call sin cuerpo (sólo para recibir el
		aviso de un evento para sincronización).
		 	ACCEPT NombreEntry (p1: IN integer; p3: IN OUT float);
		• Accept para atender un pedido de entry call con cuerpo.
		 	ACCEPT NombreEntry (p1: IN integer; p3: IN OUT float) do
		Cuerpo del accept donde se puede acceder a los parámetros p1 y p3.
		Fuera del entry estos parámetros no se pueden usar.
		 	END NombreEntry;
		• El accept se puede hacer en el cuerpo de la tarea que ha declarado el entry en
		su especificación. Los entry call se pueden hacer en cualquier tarea o en el
		programa principal.
		• Tanto el entry call como el accept son bloqueantes, ambas tareas continúan
		trabajando cuando el cuerpo del accept ha terminado su ejecución.
*/