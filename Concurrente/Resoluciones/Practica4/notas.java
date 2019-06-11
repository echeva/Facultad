//PMA el send es no bloqueante y los mensajes se encolan en orden.

/*Se usa el proceso administrador cuando más de un proceso puede sacar del mismo canal
ya que al preguntar por not empty del canal, no es atomico con respecto al receive. 
Y los procesos tienen otra tarea para hacer u otro canal en donde puede hacer un receive*/

/*PMS tanto el send como el receive son bloqueantes.*/
//el do recorre cuantas veces sea necesario, hasta que todas las sentencias sean falsas

 Radar[*] //Recibo un mensaje de cualquier proceso RADAR
 //Como el send es bloqeuante, se necesita un proceso intermedio que reciba antes
 //de que su muestra pueda ser procesada por la unidad

/*PMS Hasta que el otro proceso no reciba un msj, no puedo continuar.
 tanto el send como el receive son bloqueantes y no tengo cola de mensajes
Radares: 
 cpu! -> el send es con !
 Radar[*] -> esto quiere decir que recibo msj de cualquier proceso Radar
 Si ponemos un process admin que encola todas las señales que recibe de radar, */