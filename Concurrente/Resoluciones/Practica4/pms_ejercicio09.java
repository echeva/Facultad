/* 9.En  una  estación  de  comunicaciones  se  cuenta  con  10  radares  y  una  unidad  de  
 procesamiento que se encarga de procesar la información enviada por los radares. 
 Cada radar repetidamente detecta señales de radio durante 15 segundos y le envía esos 
 datos a la  unidad  de  procesamiento  para  que  los  analice.  Los  radares  no  
 deben  esperar  a  ser  atendidos para continuar trabajando.*/

 Radar[*] //Recibo un mensaje de cualquier proceso RADAR
 //Como el send es bloqeuante, se necesita un proceso intermedio que reciba antes
 //de que su muestra pueda ser procesada por la unidad

 ▣