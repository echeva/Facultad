/* 9.En  una  estación  de  comunicaciones  se  cuenta  con  10  radares  y  una  unidad  de  
 procesamiento que se encarga de procesar la información enviada por los radares. 
 Cada radar repetidamente detecta señales de radio durante 15 segundos y le envía esos 
 datos a la  unidad  de  procesamiento  para  que  los  analice.  Los  radares  no  
 deben  esperar  a  ser  atendidos para continuar trabajando.*/

 Radar[*] //Recibo un mensaje de cualquier proceso RADAR
 //Como el send es bloqeuante, se necesita un proceso intermedio que reciba antes
 //de que su muestra pueda ser procesada por la unidad

/*PMS Hasta que el otro proceso no reciba un msj, no puedo continuar.
 tanto el send como el receive son bloqueantes y no tengo cola de mensajes
Radares: 
 up! -> el send es con !
 Radar[*] -> esto quiere decir que recibo msj de cualquier proceso Radar
 Si ponemos un process admin que encola todas las señales que recibe de radar, */

process admin
	while true: 
		// ambas sentencias tienen = prioridad. Espera a que alguna se pueda ejecutar
		if radar[*] ? (señal)-> cola.push(señal)
		▣ not empty(cola) cpu?() -> cpu!(cola.pop())

process cpu
	while true:
		Admin ! Solicitar()
		Admin ? (señal) #procesa

