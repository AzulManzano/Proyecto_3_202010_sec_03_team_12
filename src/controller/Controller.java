package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.Arco;
import model.data_structures.Bogota_Vertice;
import model.data_structures.Comparendo;
import model.data_structures.Graph;
import model.data_structures.Haversine;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.SeparateChaining;
import model.data_structures.Vertice;
import model.logic.Modelo;
import view.View;

public class Controller 
{
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;
	private Haversine distanciador;
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
		distanciador = new Haversine();
	}

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;


		view.bienvenida();

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			if(option == 1)
			{
				modelo.cargarDatos();
				modelo.cargarMallaVial();
				modelo.cargarEstaciones();
			}
			switch(option)
			{
			case 1:			

				view.printMessage("");
				view.printMessage("1. TOTAL DE COMPARENDOS EN EL ARCHIVO");
				view.printMessage("    -"+modelo.darArbolComparendos().size());
				view.printMessage("   La informacion del comparendo con el mayor OBJECTID es:");
				view.printMessage("    -"+modelo.darMayorComparendo().darInformacionDeCarga());
				view.printMessage("");
				view.printMessage("2. TOTAL DE ESTACIONES DE POLICÍA EN EL ARCHIVO");
				view.printMessage("    -"+modelo.darArbolEstacionesPolicia().size());
				view.printMessage("   La informacion de  la estación de policía con  el mayor OBJECTID es:");
				view.printMessage("    -"+modelo.darMayorEstacion().darInformacion());
				view.printMessage("");
				view.printMessage("3. TOTAL DE VÉRTICES EN EL GRAFO DE LA MALLA VIAL DE BOGOTÁ");
				view.printMessage("    -"+modelo.darNumeroVertices());
				view.printMessage("   La información del vértice con el mayor id es:");
				view.printMessage("    -"+modelo.darMayorVertice().darInformacion());
				view.printMessage("");
				view.printMessage("4. TOTAL DE ARCOS EN EL GRAFO DE LA MALLA VIAL DE BOGOTÁ");
				view.printMessage("    -"+ modelo.darNumeroArcos());
				view.printMessage("   La información de los arcos con el mayor ID son:");
				Queue<Arco<Integer, Bogota_Vertice>> arcos = modelo.darGrafo().getVertex(modelo.darMayorVertice().darId()).darAdyacentes().darArcos();
				while(arcos.isEmpty()== false)
				{
					view.printMessage("    -"+arcos.dequeue().darInformacion());
				}
				view.printMessage("");
				view.printMessage("");
				break;
			case 2:			
				view.printMessage("");
				modelo.acomplarEstaciones();
				modelo.cargarAcoplamiento();
				modelo.actualizarArcos();
				view.printMessage("SE HAN ACOPLADO AL GRAFO LOS SIGUIENTS DATOS:");
				view.printMessage("    +"+modelo.darArbolEstacionesPolicia().size() +" esataciones de policia.");
				view.printMessage("    +"+modelo.darArbolComparendos().size() +" comparendos.");
				view.printMessage("    + # de comparendo por cada arco.");
				view.printMessage("");
				view.printMessage("");
				break;

			case 3:			
				view.printMessage("");
				view.printMessage("");
				view.printMessage("OBTENER EL CAMINO DE COSTO MÍNIMO ENTRE DOS UBICACIONES GEOGRÁFICAS POR DISTANCIA.");
				view.printMessage("Ingrese la latitud 1:");
				int rec1A1 = lector.nextInt();
				view.printMessage("Ingrese la longitud 1:");
				int rec1A2 = lector.nextInt();
				view.printMessage("Ingrese la latitud 2:");
				int rec1A3 = lector.nextInt();
				view.printMessage("Ingrese la longitud 2:");
				int rec1A4 = lector.nextInt();
				try {
					Graph<Integer, Bogota_Vertice> grafo1A = modelo.requerimiento1A(rec1A1, rec1A2, rec1A3, rec1A4);
					view.printMessage("El total de vértices: "+grafo1A.V());
					Queue<Integer> laves = grafo1A.vectores();
					while(laves.isEmpty()== false)
					{
						Integer llave = laves.dequeue();
						view.printMessage("Id: "+grafo1A.getInfoVertex(llave).darId()+ ", latitud: "+grafo1A.getInfoVertex(llave).darLatitud() +", longitud: "+grafo1A.getInfoVertex(llave).darLongitud());
					}
					view.printMessage("El costo mínimo: "+distanciador.distance(rec1A1, rec1A2, rec1A3, rec1A4));
					int ditanciaEstimada1A = 0;
					Queue<Integer> laves1 = grafo1A.vectores();
					while(laves1.isEmpty()== false)
					{
						
					}
					view.printMessage("La distancia estimada: "+ ditanciaEstimada1A);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 4:			
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 5:			
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 6:			
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 7:			
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 8:			
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 0:			
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
