/*Resolver con PMS el siguiente problema: se debe administrar el acceso para usar
un determinado Servidor donde no se permiten más de 10 usuarios trabajando al mismo
tiempo, por cuestiones de rendimiento. Existen N usuarios que solicitan acceder al 
Servidor, esperan hasta que se les de acceso para trabajar en él y luego salen del
mismo. Nota: suponga que existe una función TrabajarEnServidor() que llaman los 
usuarios para representar que están trabajando en el Servidor.*/

Process Usuario[i: 1 to N]{
	Servidor ! acceder();
	TrabajarEnServidor();
	Servido ! liberar();
}

Process Servidor{
	int cant = 0;

	if true; Usuario[*] ? liberar() -> cant --;
	▣ cant < 10; Usuario[*] ? acceder() -> cant ++;
}