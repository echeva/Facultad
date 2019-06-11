/*Suponga que existen N barcos que recorren un canal llevando gente. Su recorrido
es el siguiente: A-B, B-C, C-A.
Los barcos parten desde A, van hasta B y luego van hasta C; y de C nuevamente 
vuelven a A.
Luego de hacer 7 vueltas completas para durante 1hs a descansar y vuelven a salir.
No puede haber más de 3 barcos haciendo el mismo recorrido simultaneamente.
Cuando un barco llega a un punto de parada, los pasajeros que quieren bajar lo hacen. 
Una vez que bajaron todos, el barco se fija si puede partir (teniendo en cuenta que 
no puede haner mas de 3 barcos haciendo el mismo recorrido), si no puede se queda 
esperando sin subir gente hasta poder partir.
Cuando se dan condiciones de poder seguir, permite subir gente durante 5' o hasta
que se llene su capacidad. Suponga que cada barco tiene capacidad para 30 pasajeros.
Existen R pasajeros que conocen la parada en la que suben y en cual bajan. Cuando un 
pasajero llega a su punto de partida trata de subir en el primer barco que parta,
cuando su barco llega a la parada destino se baja. Tenga en cuenta que el barco
no conoce la parada de sus pasajeros.
Suponga que como mínimo cada recorrido del barco tarda 20'. Maximice la concurrencia.
Modelice con PMS o PMA según crea conveniente.*/