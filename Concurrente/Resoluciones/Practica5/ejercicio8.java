/*8) En una empresa hay 5 controladores de temperatura y una Central. Cada controlador
toma la temperatura del ambiente cada 10 minutos y se la envía a una central para que
analice el dato y le indique que hacer. Cuando la central recibe una temperatura que es
mayor de 40 grados, detiene a ese controlador durante 1 hora.*/

TASK TYPE controlador;

controladores = array(1..5) of controlador;

TASK central IS 
	ENTRY analizarTemperatura(temp IN Integer, accion OUT Boolean)
END;

TASK BODY controlador;
	var temp Integer;
	var accion Boolean;
BEGIN
	LOOP 
		temp = TomarTemperatura();
		central.analizarTemperatura(temp, accion);
		if (!accion) {
			delay(60);
		}else{
			delay(10);
		}
	END LOOP
END  

TASK BODY central
BEGIN
	LOOP
		ACCEPT analizarTemperatura(temp IN Integer, accion OUT Boolean)IS
			if (temp > 40) {
				accion = False;
			} else{
				accion = True;
			}
		END analizarTemperatura;
	END LOOP
END