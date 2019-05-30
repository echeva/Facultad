task body tipoa
begin
	tipob.llegue(); //se queda bloqueado hasta que lo atiendan, mientras se ejecuta el cuerpo del accept,
	//el proceso sigue bloqueado, a cada entrycall hay una cola de procesos asociados
end

task body tipob
var cant: int;
begin
	cant:= 0;
	accept llegue(); //como solo es para sincronizar (avisar que llego) ponemos directo ; 
	//va desencolando los procesos en orden
	//hay que hacer en el cuerpo del accept lo minimo para no mantener bloq al proceso que estoy atendiendo
	cant ++;
end

//si en el accept tuviera un parametro de salida:

task body tipoa
  var c: int;
  begin
	tipoob.llegue(c); //estoy solicitando y recibiendo datos en la misma llamada
  end;

task body tipob
begin
	accept llegue(out cantidad:integer) is 
	//si aca me quisiera comunicar con tipoa, lo tengo que hacer por fuera del accept p no entrar en deadlock
	//pues tipoa esta bloqueado
		cantidad:= cant;
	end llegue;
end;

//Dentro del accept puedo saber cuantos procesos estan esperando para ser atendidos,
//lo puedo usar para dar prioridad

//El programa principal se vuelve importante solo cuando se necesita enviar los id de las task

//ejercicio4: tipos de select
//no puedo en un accept hacer un or entryCall, siempre dsps de un OR necesito un ACCEPT
//adentro del accept puedo meter un select entryCall
//en un OR de un accept puedo hacer un or delay, ó un else

task body tipoa
begin
	LOOP
		//intenta escribir
		administrador.quieroEscribir() //-> si hago esto me quedo bloq hasta q escribo X
		SELECT 
			administrador.quieroEscribir(); //me encolo para solicitar permiso para escribir
			Delay
			Aministrador.liberarescribir(); //libero el permiso
		OR Delay 2 //si en dos minutos no logre escribir, me desencolo y continuo
			Delay 5 //espera 5 minutos y vuelve a intentarlo 

end 

task body administrador
begin
	SELECT Accept quieroEscribir();
	OR WHEN (quieroEscribir().count == 0) Accept quieroLeer();
		//puedo meter otro accept de entryCall
		SELECT contador.contame();
	OR Accept liberoEscribir();
end 