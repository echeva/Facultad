// Suponga que N personas llegan a la cola de un banco. Una vez que la persona se agrega
// en la cola no espera más de 15 minutos para su atención, si pasado ese tiempo no fue
// atendida se retira. Para atender a las personas existen 2 empleados que van atendiendo de
// a una y por orden de llegada a las personas. 

chan startTimer[1..N]()
chan cola(int id)
chan estadoPersona[1..N](string estado)
chan avisarPersona[1..N]()

process persona[i=1 to N]
  send startTimer[i]()
  send cola(i) 
  send estadoPersona[i]('esperando')
  receive avisarPersona[i]()
end

process empleado[i=2 to 2]
  int id
  string estado
  
  while (true)
    receive cola(id)
    receive estadoPersona[id](estado)//ver cual muere o si reciven los dos o no 
    if (estado == 'esperando')
      send estadoPersona[id]('atendido')
      "empleado atiende a la persona"
      send avisarPersona[id]()
    end
  end
end

process timer[i=2 to N]
  string estado

  receive startTimer[i]()
  delay(15)
  receive estadoPersona[i](estado)//ver cual muere o si reciven los dos o no 
  if (estado == 'esperando')
    send estadoPersona[i]('yendo')
    send avisarPersona[i]()
  end
end


