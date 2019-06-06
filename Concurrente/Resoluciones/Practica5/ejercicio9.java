/*9) Una empresa de limpieza se encarga de recolectar residuos en una ciudad. Hay P
personas que continuamente hacen reclamos para que pasen por su casa. La empresa
tiene 2 camiones, que cuando alguno de ellos está libre lo envía a recolectar los residuos
de la persona que más reclamos ha hecho.*/


PROCEDURE ejercicio9;

TASK Administrador IS
	ENTRY enviarReclamo(id: IN Integer);
	ENTRY solicitarReclamo(id: IN Integer);
END

TASK Empresa IS
	ENTRY solicitarReclamo(id: IN Integer);
END

TASK TYPE Camion;
camiones = array(1..2) OF Camion;

TASK TYPE Persona;
personas = array(1..P) OF persona;


TASK BODY Persona
var String direccion;
var Integer id;
BEGIN
	direccion:= "Mi direccion";
	LOOP 
		Administrador.enviarReclamo(id);
	END LOOP;
END

TASK BODY Administrador
VAR Integer[1..P] reclamos; 
BEGIN
	LOOP
		SELECT ACCEPT enviarReclamo(id: IN Integer) IS
						reclamos[id]:= reclamos[id] + 1;
				      END enviarReclamo;
		OR ACCEPT solicitarReclamo(id: OUT Integer) IS
					//consulta cual persona tiene mayor cant de reclamos
			        id:= max(reclamos);
			      END solicitarReclamo;
		END SELECT;
	END LOOP;
END;

TASK BODY Empresa 
BEGIN
	LOOP
		ACCEPT solicitarReclamo(id: OUT Integer) IS
			Administrador.solicitarReclamo(id);
		END solicitarReclamo; 
	END LOOP;
END;

TASK BODY Camion 
var Integer id;
BEGIN
	LOOP
		Empresa.solicitarReclamo(id);
		//ir a la casa de la persona correspondiente
	END LOOP;
END;

BEGIN
	FOR i:=1 to P
	BEGIN
		personas[i]= asignarID(i);
	END;
END

END.