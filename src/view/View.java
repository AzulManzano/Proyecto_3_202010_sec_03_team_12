package view;

import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	public void bienvenida()
	{
		System.out.println(".---.  _                                _    .-. \n"     
				+": .; ::_;                              :_;   : :      \n"
				+":   .'.-. .--. ,-.,-..-..-. .--. ,-.,-..-. .-' : .--. \n"
				+": .; :: :' '_.': ,. :: `; :' '_.': ,. :: :' .; :' .; :\n"
				+":___.':_;`.__.':_;:_;`.__.'`.__.':_;:_;:_;`.__.'`.__.'\n");
		System.out.println("");
	}

	public void printMenu()
	{
		System.out.println("MENU DEL USUARIO");
		System.out.println("1. Cargar datos");
		System.out.println("2.  (P) ACOPLAR");
		System.out.println("3.  (1A) OBTENER EL CAMINO DE COSTO MÍNIMO ENTRE DOS UBICACIONES GEOGRÁFICAS POR DISTANCIA.");
		System.out.println("4.  (2A) DETERMINAR LA RED DE CÁMARAS EN LOS PUNTOS DONDE SE PRESENTAN LOS COMPARENDOS DE MAYOR GRAVEDAD.");
		System.out.println("5.  (1B) OBTENER EL CAMINO DE COSTO MÍNIMO ENTRE DOS UBICACIONES GEOGRÁFICAS POR NÚMERO DE COMPARENDOS");
		System.out.println("6.  (2B) DETERMINAR LA RED DE CÁMARAS EN LOS PUNTOS DONDE SE PRESENTA EL MAYOR NÚMERO DE COMPARENDOS EN LA CIUDAD.");
		System.out.println("7.  (1C) OBTENER LOS CAMINOS MÁS CORTOS PARA QUE LOS POLICÍAS PUEDAN ATENDER LOS M COMPARENDOS MÁS GRAVES");
		System.out.println("8.  (2C) IDENTIFICAR LAS ZONAS DE IMPACTO DE LAS ESTACIONES DE POLICÍA.");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}		

	public void printModelo(Modelo modelo)
	{
		// TODO implementar
	}
	
	public void printMenuTipoVeiculo()
	{
		System.out.println("INDIQUE QUE TIPO DE VEICULO QUIERE CONSULTAR:");
		System.out.println(" 1.Automovil");
		System.out.println(" 2.Bicicleta");
		System.out.println(" 3.Bus");
		System.out.println(" 4.Buseta");
		System.out.println(" 5.Camioneta");
		System.out.println(" 6.Campero");
		System.out.println(" 7.Motocicleta");
	}
	
	public void printMenuMedioDeteccion()
	{
		System.out.println("INDIQUE QUE MEDIO DE DETECCION QUIERE CONSULTAR:");
		System.out.println(" 1.Lapiz");
		System.out.println(" 2.DEAP");
	}
	
	public void printMenuTipoServicio()
	{
		System.out.println("INDIQUE QUE TIPO DE SERVICIO QUIERE CONSULTAR:");
		System.out.println(" 1.Público");
		System.out.println(" 2.Oficial");
		System.out.println(" 3.Particular");
	}
	
	public void printMenuLocalidad()
	{
		System.out.println("INDIQUE QUE LOCALIDAD QUIERE CONSULTAR:");
		System.out.println(" 1.PUENTE ARANDA");
		System.out.println(" 2.FONTIBON");
		System.out.println(" 3.ENGATIVA");
		System.out.println(" 4.SUBA");
		System.out.println(" 5.USME");
		System.out.println(" 6.CIUDAD BOLIVAR");
		System.out.println(" 7.USAQUEN");
		System.out.println(" 8.BOSA");
		System.out.println(" 9.SAN CRISTOBAL");
		System.out.println(" 10.KENNEDY");
		System.out.println(" 11.CHAPINERO");
		System.out.println(" 12.MARTIRES");
		System.out.println(" 13.BARRIOS UNIDOS");
		System.out.println(" 14.TUNJUELITO");
		System.out.println(" 15.SANTA FE");
		System.out.println(" 16.ANTONIO NARIÑO");
	}
}
