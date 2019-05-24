/*9. Resolver con SEMÁFOROS el funcionamiento en una fábrica de ventanas con 7
empleados (4 carpinteros, 1 vidriero y 2 armadores) que trabajan de la siguiente manera:
• Los carpinteros continuamente hacen marcos (cada marco es armando por un único
carpintero) y los deja en un depósito con capacidad de almacenar 30 marcos.
• El vidriero continuamente hace vidrios y los deja en otro depósito con capacidad para
50 vidrios.
• Los armador continuamente toman un marco y un vidrio de los depósitos
correspondientes y arman la ventana (cada ventana es armada por un único armador).*/

int bufferMarcos[30];
int bufferVidrios[50];
sem accesoMarcosLibre = 1;
int libreMarco = 0; 
int libreVidrio = 0;
int ocupadoMarco = 0;
int ocupadoVidrio = 0;
sem llenoMarcos = 0;
sem llenoVidrios = 0;
sem vacioMarcos = 30;
sem vacioVidrios = 50;
sem accesoMarcosOcupado = 1;
sem accesoVidriosOcupado = 1;

Procedure carpintero[i= 1 to 4]{
	while(true){
		//produce el marco
		P(vacioMarcos); //se fija si tiene lugar en el deposito
		P(accesoMarcosLibre);
		bufferMarcos[libreMarco] = marco;
		libreMarco = (libreMarco + 1) mod 30;
		V(accesoMarcosLibre);
		V(llenoMarcos); //avisa que depositó un marco! 
	}
}

Procedure vidriero{
	while(true){
		//produce un vidrio
		P(vacioVidrios);
		bufferVidrios[libreVidrio] = vidrio;
		libreVidrio = (libreVidrio + 1) mod 50;
		V(llenoVidrios);
	}
}

Procedure armador[i= 1 to 2]{

	while(true){
		P(llenoVidrios);
		P(llenoMarcos);
		P(accesoVidriosOcupado);
		vidrio = bufferVidrios[ocupadoVidrio];
		ocupadoVidrio = (ocupadoVidrio + 1) mod 50;
		V(accesoVidriosOcupado);
		P(accesoMarcosOcupado);
		marco = bufferMarcos[ocupadoMarco];
		ocupadoMarco = (ocupadoMarco + 1) mod 30;
		V(accesoMarcosOcupado);
		V(vacioVidrios);
		V(vacioVidrios);
		//consumir vidrio, consumir marco;
	}
	
}