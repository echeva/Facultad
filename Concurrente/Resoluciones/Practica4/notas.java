//PMA el send es no bloqueante y los mensajes se encolan en orden.

/*Se usa el proceso administrador cuando mas de un proceso puede sacar del mismo canal
ya que al preguntar por not empty del canal, no es atomico con respecto al receive. 
Y siempre y cuando los procesos que iguales tengan que preguntar si esta vacío, no pasa nada
si pueden esperar eternamente a que haya un mensaje*/

/*PMS tanto el send como el receive son bloqueantes.*/
//el do recorre cuantas veces sea necesario, hasta que todas las sentencias sean falsas

