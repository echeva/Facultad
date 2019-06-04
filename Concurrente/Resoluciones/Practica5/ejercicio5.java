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
END;

TASK TYPE enfermera;

enfermeras = array() of enfermera;

TASK TYPE persona;

personas = array() of persona;

TASK administrador IS
	ENTRY dejarNota();
END;


TASK BODY medicoGuardia
BEGIN
	LOOP
		SELECT ACCEPT atencionPaciente();
		OR WHEN(atencionPaciente`count = 0) ACCEPT atencionEnferma();
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
				ok:= true;
			}else{
				Delay(10);
			}
		END SELECT;
	END LOOP;
END;

TASK BODY enfermera
BEGIN
	LOOP 
		SELECT medicoGuardia.atencionEnferma();
		OR administrador.dejarNota();
		END SELECT;
		//continua trabajando
	END LOOP;
END;

TASK BODY administrador
cola notas;
BEGIN
	LOOP
		ACCEPT dejarNota();
	END LOOP;
END;