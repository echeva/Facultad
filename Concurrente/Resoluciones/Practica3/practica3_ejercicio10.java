/*Resolver el funcionamiento en una empresa de genética. Hay N clientes que
sucesivamente envían secuencias de ADN a la empresa para que sean analizadas y esperan
los resultados para poder envían otra secuencia a analizar. Para resolver estos análisis la
empresa cuenta con 2 servidores que van alternando su uso para no exigirlos de más (en
todo momento uno está trabajando y los otros dos descansando); cada 8 horas cambia en
servidor con el que se trabaja. El servidor que está trabajando, toma un pedido (de a uno
de acuerdo al orden de llegada de los mismos), lo resuelve y devuelve el resultado al
cliente correspondiente; si al terminar ya han pasado las 8 horas despierta al próximo
servidor y él descansa, sino continúa con el siguiente pedido.*/

