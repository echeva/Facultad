/*5) En una clínica existe un médico de guardia que recibe continuamente peticiones de
atención de las E enfermeras que trabajan en su piso y de las P personas que llegan a la
clínica a ser atendidos.
Cuando una persona necesita que la atiendan espera a lo sumo 5 minutos a que el
médico lo haga, si pasado ese tiempo no lo hace, espera 10 minutos y vuelve a requerir
la atención del médico. Si no es atendida tres veces, se enoja y se retira de la clínica.
Cuando una enfermera requiere la atención del médico, si este no lo atiende
inmediatamente le hace una nota y se la deja en el consultorio para que esta resuelva su
pedido en el momento que pueda (el pedido puede ser que el médico le firme algún papel). 
Cuando la petición ha sido recibida por el médico o la nota ha sido dejada en el
escritorio, continúa trabajando y haciendo más peticiones.
El médico atiende los pedidos dándoles prioridad a los pacientes que llegan para ser
atendidos. Cuando atiende un pedido, recibe la solicitud y la procesa durante un cierto
tiempo. Cuando está libre aprovecha a procesar las notas dejadas por las enfermeras.*/


TASK medicoGuardia IS
	ENTRY atencionPaciente();
	ENTRY atencionEnfermera();
	ENTRY dejarNota();
END;

TASK TYPE enfermera;
enfermeras = array(1..E) of enfermera;

TASK TYPE persona;
personas = array(1..P) of persona;

TASK administrador IS
	ENTRY dejarNota(nota: IN String);
	ENTRY solicitarNota(nota: OUT String);
END;


TASK BODY medicoGuardia
var String nota;
BEGIN
	LOOP
		SELECT ACCEPT atencionPaciente();
			   delay(); //procesa la solicitud
		OR WHEN(atencionPaciente`count = 0) ACCEPT atencionEnfermera();
		ELSE administrador.solicitarNota(nota); 
		     if (nota != "") {
		     	//lee la nota
		     	delay();
		     }
		END SELECT;
	END LOOP;
END;

TASK BODY paciente
var boolean ok;
var int cant;
BEGIN
	ok:= false;
	cant:= 0;
	LOOP not ok
		SELECT medicoGuardia.atencionPaciente();
			   ok:= true;
		OR DELAY 5
			cant ++;
			if (cant == 3) {
				//la persona se retira de la clinica
				ok:= true;
			}else{
				Delay(10);
			}
		END SELECT;
	END LOOP;
END;

TASK BODY enfermera
var string nota;
BEGIN
	LOOP 
		SELECT medicoGuardia.atencionEnferma();
		ELSE
			nota = 'Mi nota';
			administrador.dejarNota(nota);
		END SELECT;
		//continua trabajando
	END LOOP;
END;

TASK BODY administrador
cola notas;
BEGIN
	LOOP
		SELECT ACCEPT dejarNota(nota: IN String) IS;
		OR ACCEPT solicitarNota()
	END LOOP;
END;
