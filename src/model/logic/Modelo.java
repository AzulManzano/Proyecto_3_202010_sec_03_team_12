package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.RedBlackBST;
import model.data_structures.RedBlackBST2;
import model.data_structures.Arco;
import model.data_structures.Bogota_Vertice;
import model.data_structures.BuscadorVertices;
import model.data_structures.Comparendo;
import model.data_structures.Escritor;
import model.data_structures.EstacionPolicia;
import model.data_structures.Graph;
import model.data_structures.Haversine;
import model.data_structures.LLaves2A;
import model.data_structures.LLaves2B;
import model.data_structures.LinearProbing;
import model.data_structures.Lista;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.SeparateChaining;
import model.data_structures.SequentialSearch;
import model.data_structures.Vertice;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{

	public static String PATH = "./data/Comparendos_DEI_2018_Bogotá_D.C_50000_.geojson";
	public static String ESTACION = "./data/estacionpolicia.geojson.json";

	private EstacionPolicia mayorEstacionPolicia;
	private Bogota_Vertice mayorVerice;
	private Comparendo mayorComparendo;

	private Haversine distanciador;
	private Escritor escritor;
	private BuscadorVertices buscarVertice;

	private MaxHeapCP<Comparendo> listaComparenodos;
	private RedBlackBST2<Integer,Comparendo>  comparendos;
	private RedBlackBST2<Integer,EstacionPolicia> estcionesPolicia;

	private Graph<Integer, Bogota_Vertice> grafo;

	public Modelo()
	{
		mayorEstacionPolicia = null;
		mayorVerice = null;
		mayorComparendo = null;
		buscarVertice = new BuscadorVertices(); 

		listaComparenodos = new MaxHeapCP<Comparendo>();

		grafo = new Graph<Integer, Bogota_Vertice>();


		distanciador = new Haversine();
		escritor = new Escritor();


		estcionesPolicia = new RedBlackBST2<Integer,EstacionPolicia>(); 
		comparendos = new RedBlackBST2<Integer,Comparendo>();

	}


	// Retorno de estructuras -----------------------

	public RedBlackBST2<Integer,Comparendo> darArbolComparendos()
	{
		return comparendos;
	}

	public MaxHeapCP<Comparendo> darListaDeComparendos()
	{
		return listaComparenodos;
	}

	public RedBlackBST2<Integer,EstacionPolicia> darArbolEstacionesPolicia()
	{
		return estcionesPolicia;
	}

	public Graph<Integer, Bogota_Vertice> darGrafo()
	{
		return grafo;	
	}

	public Escritor escribir()
	{
		return escritor;
	}

	public EstacionPolicia darMayorEstacion()
	{
		return mayorEstacionPolicia;
	}

	public Bogota_Vertice darMayorVertice()
	{
		return mayorVerice;
	}

	public Comparendo darMayorComparendo()
	{
		return mayorComparendo;
	}

	//---------------------------------------------


	//Tamaño ----------------------------------------------

	public int darTotalComparendos()
	{
		return listaComparenodos.darNumElementos();
	}

	public int darNumeroVertices()
	{
		return grafo.V();
	}

	public int darNumeroArcos()
	{
		return grafo.E();
	}


	//---------------------------------------------------

	public void cargarDatos() 
	{
		int mayor = 0;
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();


				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

				comparendos.put(OBJECTID, c);
				listaComparenodos.agregar(c);

				if(OBJECTID>mayor)
				{
					mayor=OBJECTID;
					mayorComparendo = c;
				}
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void cargarMallaVial() 
	{
		int mayor = 0;
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader("data/mallaVial.json"));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("Vertices").getAsJsonArray();


			for(JsonElement e: e2) {

				double longitud = e.getAsJsonObject().get("Longitud").getAsDouble();
				double latitud = e.getAsJsonObject().get("Latitud").getAsDouble();
				int OBJECTID = e.getAsJsonObject().get("Id").getAsInt();

				Bogota_Vertice nuevo = new Bogota_Vertice(OBJECTID,latitud,longitud );
				grafo.addVertex(OBJECTID, nuevo);
				buscarVertice.cargar(OBJECTID, latitud, longitud, nuevo);
				if(OBJECTID>mayor)
				{
					mayor=OBJECTID;
					mayorVerice = nuevo;
				}

			}

			JsonArray e3 = elem.getAsJsonObject().get("Arcos").getAsJsonArray();

			for(JsonElement e: e3) {

				double costo = e.getAsJsonObject().get("Costo").getAsDouble();
				int destino = e.getAsJsonObject().get("IdDestino").getAsInt();
				int origen = e.getAsJsonObject().get("IdOrigen").getAsInt();

				grafo.addEdge(origen, destino, costo);

			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void cargarEstaciones() 
	{
		int mayor = 0;
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(ESTACION));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			for(JsonElement e: e2) {

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String EPOIULOCAL = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOIULOCAL").getAsString();
				String EPOTELEFON = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOTELEFON").getAsString();
				String EPOHORARIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOHORARIO").getAsString();
				String EPOSERVICIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOSERVICIO").getAsString();
				String EPODESCRIP = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPODESCRIP").getAsString();
				String DIRECCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPODIR_SITIO").getAsString();
				String NOMBRE = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPONOMBRE").getAsString();


				EstacionPolicia n = new EstacionPolicia( OBJECTID, DIRECCION,  NOMBRE,  latitud,  longitud,EPODESCRIP, EPOSERVICIO, EPOHORARIO,EPOTELEFON,EPOIULOCAL);


				estcionesPolicia.put(OBJECTID, n);

				if(OBJECTID>mayor)
				{
					mayor=OBJECTID;
					mayorEstacionPolicia = n;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}


	public MaxHeapCP<Comparendo> mayorGravedad()
	{
		MaxHeapCP<Comparendo> elementos = clonar();
		int num = elementos.darNumElementos();
		MaxHeapCP<Comparendo> elementosFinales = new MaxHeapCP<Comparendo>();

		for(int i = 0; i<num;i++)
		{
			Comparendo este = elementos.sacarMax();
			este.cambiarComparacion(1);


			elementosFinales.agregar(este);
		}
		return elementosFinales;
	}

	public MaxHeapCP<Comparendo> clonar()
	{
		MaxHeapCP<Comparendo> nueva =  new MaxHeapCP<Comparendo>();
		MaxHeapCP<Comparendo> vieja =  new MaxHeapCP<Comparendo>();
		int cor = listaComparenodos.darNumElementos();

		for(int i = 0; i<cor;i++)
		{
			Comparendo agre = listaComparenodos.sacarMax();
			nueva.agregar(agre);
			vieja.agregar(agre);
		}

		listaComparenodos = vieja;

		return nueva;			
	}

	public void cargarVertices( String pArchivo, Graph<Integer, Bogota_Vertice> grafo, Graph<Integer, Bogota_Vertice> grafo1) throws Exception
	{
		String texto, valores[];

		int id;
		double  latitud, longitud;


		try
		{
			File datos = new File( pArchivo );
			FileReader fr = new FileReader( datos );
			BufferedReader lector = new BufferedReader( fr );

			texto = lector.readLine( );
			while(texto != null)
			{
				valores = texto.split( "," );

				id = Integer.parseInt(valores[ 0 ]);
				longitud = Double.parseDouble(valores[ 1 ]);
				latitud = Double.parseDouble(valores[ 2 ]);

				Bogota_Vertice nuevo = new Bogota_Vertice(id,latitud,longitud );
				grafo.addVertex(id, nuevo);

				if((longitud< -74.062707 && longitud > -74.094723) && (latitud <  4.621360 && latitud > 4.597714 ))
					grafo1.addVertex(id, nuevo);

				// Siguiente línea
				texto = lector.readLine( );
			}
			lector.close( );
		}
		catch( IOException e )
		{
			throw new Exception( "Error al cargar los datos almacenados de vehículos." );
		}
		catch( NumberFormatException e )
		{
			throw new Exception( "El archivo no tiene el formato esperado." );
		}
	}

	public void cargarArcos( String pArchivo, Graph<Integer, Bogota_Vertice> grafo, Graph<Integer, Bogota_Vertice> grafo1) throws Exception
	{
		String texto, valores[];

		Integer idOrigen;
		Integer idDestino;


		try
		{
			File datos = new File( pArchivo );
			FileReader fr = new FileReader( datos );
			BufferedReader lector = new BufferedReader( fr );

			texto = lector.readLine( );
			texto = lector.readLine( );
			texto = lector.readLine( );
			texto = lector.readLine( );
			while(texto != null)
			{
				valores = texto.split( " " );
				int tam = valores.length;

				idOrigen = Integer.parseInt(valores[ 0 ]);
				Bogota_Vertice origen = grafo.getInfoVertex(idOrigen);

				for(int i = 1;i<tam;i++)
				{
					idDestino = Integer.parseInt(valores[ i ]);
					Bogota_Vertice destino = grafo.getInfoVertex(idDestino);

					if(origen != null && destino != null)
					{
						double distancia = distanciador.distance(origen.darLatitud(), origen.darLongitud(), destino.darLatitud(), destino.darLongitud());
						grafo.addEdge(idOrigen, idDestino, distancia);grafo1.addEdge(idOrigen, idDestino, distancia);
					}
				}




				// Siguiente línea
				texto = lector.readLine( );
			}
			lector.close( );
		}
		catch( IOException e )
		{
			throw new Exception( "Error al cargar los datos almacenados de vehículos." );
		}
		catch( NumberFormatException e )
		{
			throw new Exception( "El archivo no tiene el formato esperado." );
		}
	}

	public void crearJson()
	{

		JSONObject obj = new JSONObject();
		obj.put("Num_vertices", darNumeroVertices());
		obj.put("Num_arcos", darNumeroArcos());


		JSONArray list = new JSONArray();

		Queue<Integer> llaves = darGrafo().vectores();
		for(int i=0; i<darNumeroVertices(); i++)
		{
			Bogota_Vertice elemento = darGrafo().getInfoVertex(llaves.dequeue());
			JSONObject propiedades = new JSONObject();

			propiedades.put("Longitud", elemento.darLongitud());
			propiedades.put("Latitud", elemento.darLatitud());
			propiedades.put("Id",elemento.darId());
			list.add(propiedades);

		}
		obj.put("Vertices", list);


		JSONArray list2 = new JSONArray();

		Queue<Integer> llaves1 = darGrafo().vectores();
		for(int i=0; i<darNumeroVertices(); i++)
		{
			Integer origen = llaves1.dequeue();
			Queue<Integer> adya = grafo.adj(origen);

			if(adya.isEmpty() == false)
			{
				while(adya.isEmpty() == false)
				{
					Integer destino = adya.dequeue();
					if(origen<destino)
					{
						JSONObject propiedades = new JSONObject();

						propiedades.put("IdOrigen", origen);
						propiedades.put("IdDestino", destino);
						Double costo = grafo.getCostArc(origen, destino);
						propiedades.put("Costo",costo);
						list2.add(propiedades);
					}
				}
			}
		}
		obj.put("Arcos", list2);



		try {

			FileWriter file = new FileWriter("data/mallaVial.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			//manejar error
		}
	}

	public void acomplar()
	{
		JSONObject obj = new JSONObject();

		JSONArray list = new JSONArray();





		Queue<Integer> compa = comparendos.keys();
		while(compa.isEmpty()==false)
		{
			JSONObject propiedades = new JSONObject();
			Comparendo moment = comparendos.get(compa.dequeue());
			int idMoment =  buscarVertice.buscarVertice( moment.darLatitud(), moment.darLongitud());
			grafo.getVertex(idMoment).agregarComparendo(moment);
			moment.cambiarIdVertice(idMoment);
			propiedades.put("IdVertice", idMoment);
			propiedades.put("IdComparendo", moment.darObjec());
			list.add(propiedades);
		}


		obj.put("Acoplamientos", list);


		try {

			FileWriter file = new FileWriter("data/acoplamiento.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			//manejar error
		}
	}



	public void acomplarEstaciones()
	{
		int tamEstaciones = estcionesPolicia.size();
		Queue<Integer> estaci =  estcionesPolicia.keys();
		for(int i = 0; i<tamEstaciones; i++)
		{
			EstacionPolicia moment = estcionesPolicia.get(estaci.dequeue());
			int idMoment =  buscarVertice.buscarVertice( moment.darLatitud(), moment.darLongitud());
			grafo.getVertex(idMoment).agregarEstacion(moment);
			moment.cambiarIdVertice(idMoment);
		}
	}

	public void cargarAcoplamiento()
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader("data/acoplamiento.json"));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("Acoplamientos").getAsJsonArray();


			for(JsonElement e: e2) {

				int IdVertice = e.getAsJsonObject().get("IdVertice").getAsInt();
				int IdComparendo = e.getAsJsonObject().get("IdComparendo").getAsInt();


				Comparendo moment = comparendos.get(IdComparendo);
				grafo.getVertex(IdVertice).agregarComparendo(moment);
				moment.cambiarIdVertice(IdVertice);

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actualizarArcos()
	{
		Queue<Integer> vertices = grafo.vectores();

		while(vertices.isEmpty() == false)
		{
			Integer id = vertices.dequeue();
			Queue<Arco<Integer, Bogota_Vertice>> arcos = grafo.getVertex(id).darAdyacentes().darArcos();
			while(arcos.isEmpty() == false)
			{
				arcos.dequeue().modificarNumComparendos();
			}
		}
	}

	public Graph<Integer, Bogota_Vertice> requerimiento1A(double latOrigen, double longOrigen,double latDestino, double longDestino) throws IOException
	{
		Integer idDestino = buscarVertice.buscarVertice(latDestino, longDestino);
		Integer idOrigen = buscarVertice.buscarVertice(latOrigen, longOrigen);

		Graph<Integer, Bogota_Vertice> momentanio = grafo.dijkstra(idOrigen, idDestino, 0);

		escritor.mapaRequerimiento1A(momentanio);

		return momentanio;
	}

	public Graph<Integer, Bogota_Vertice> requerimiento2A(int pNumero) throws IOException
	{
		Graph<Integer, Bogota_Vertice> ultimo = new Graph<Integer, Bogota_Vertice>();
		Comparendo elemento1 = listaComparenodos.sacarMax();
		for(int i =0; i<pNumero-1;i++)
		{
			Comparendo elemento2 = listaComparenodos.sacarMax();
			Graph<Integer, Bogota_Vertice> momentanio = grafo.dijkstra(elemento1.darIdVertice(),elemento2.darIdVertice() , 0);
			elemento1 = elemento2;
			unirGrafos(ultimo,momentanio);
		}
		escritor.mapaRequerimiento2A(ultimo);
		return ultimo;
	}

	public Graph<Integer, Bogota_Vertice> requerimiento1B(double latOrigen, double longOrigen,double latDestino, double longDestino) throws IOException
	{
		Integer idDestino = buscarVertice.buscarVertice(latDestino, longDestino);
		Integer idOrigen = buscarVertice.buscarVertice(latOrigen, longOrigen);

		Graph<Integer, Bogota_Vertice> momentanio = grafo.dijkstra(idOrigen, idDestino, 1);

		escritor.mapaRequerimiento1B(momentanio);

		return momentanio;
	}

	public Graph<Integer, Bogota_Vertice> requerimiento2B(int pNumero) throws IOException
	{
		Queue<Integer> comparendos = new Queue<Integer>();
		int mayor = 0;
		int mayorsito = 0;
		for(int i =0; i<pNumero;i++)
		{
			Queue<Integer> llaves = grafo.vectores();
			while(llaves.isEmpty() == false)
			{
				Vertice<Integer, Bogota_Vertice> vertice = grafo.getVertex(llaves.dequeue());
				if(vertice.darListaComparenos().size() >= mayor)
				{
					if(mayorsito != 0)
					{
						if(mayorsito > vertice.darListaComparenos().size())
						{
							mayor = vertice.darListaComparenos().size();

						}
					}
					else
					{
						mayor = vertice.darListaComparenos().size();
					}

				}
			}
			mayorsito = mayor;
			mayor = 0;
		}

		Graph<Integer, Bogota_Vertice> ultimo = new Graph<Integer, Bogota_Vertice>();
		Integer elemento1 = comparendos.dequeue();
		while(comparendos.isEmpty() == false)
		{
			Integer elemento2 = comparendos.dequeue();
			Graph<Integer, Bogota_Vertice> momentanio = grafo.dijkstra(elemento1,elemento2 , 0);
			elemento1 = elemento2;
			unirGrafos(ultimo,momentanio);
		}
		escritor.mapaRequerimiento2B(ultimo);
		return ultimo;
	}

	public Graph<Integer, Bogota_Vertice> requerimiento1C(int pNumero) throws IOException
	{
		Graph<Integer, Bogota_Vertice> ultimo = new Graph<Integer, Bogota_Vertice>();
		for(int i =0; i<pNumero;i++)
		{
			Comparendo elemento1 = listaComparenodos.sacarMax();
			EstacionPolicia elemento2 = null;
			double distanciaInicial =100000;
			Queue<Integer> llaves = estcionesPolicia.keys();

			while(llaves.isEmpty() == false)
			{
				Integer llave = llaves.dequeue();
				if(distanciador.distance(estcionesPolicia.get(llave).darLatitud(), estcionesPolicia.get(llave).darLongitud(), elemento1.darLatitud(), elemento1.darLongitud())<distanciaInicial);
				{	
					elemento2 = estcionesPolicia.get(llave);
					distanciaInicial = distanciador.distance(estcionesPolicia.get(llave).darLatitud(), estcionesPolicia.get(llave).darLongitud(), elemento1.darLatitud(), elemento1.darLongitud());
				}
			}

			Graph<Integer, Bogota_Vertice> momentanio = grafo.dijkstra(elemento1.darIdVertice(),elemento2.darIdVertice(), 0);
			unirGrafos(ultimo,momentanio);
		}
		escritor.mapaRequerimiento1C(ultimo);
		return ultimo;
	}

	public Graph<Integer, Bogota_Vertice> mapaRequerimiento2C() throws IOException
	{
		Graph<Integer, Bogota_Vertice> ultimo = new Graph<Integer, Bogota_Vertice>();
		for(int i =0; i<grafo.V();i++)
		{
			Comparendo elemento1 = listaComparenodos.sacarMax();
			EstacionPolicia elemento2 = null;
			double distanciaInicial =100000;
			Queue<Integer> llaves = estcionesPolicia.keys();

			while(llaves.isEmpty() == false)
			{
				Integer llave = llaves.dequeue();
				if(distanciador.distance(estcionesPolicia.get(llave).darLatitud(), estcionesPolicia.get(llave).darLongitud(), elemento1.darLatitud(), elemento1.darLongitud())<distanciaInicial);
				{	
					elemento2 = estcionesPolicia.get(llave);
					distanciaInicial = distanciador.distance(estcionesPolicia.get(llave).darLatitud(), estcionesPolicia.get(llave).darLongitud(), elemento1.darLatitud(), elemento1.darLongitud());
				}
			}

			Graph<Integer, Bogota_Vertice> momentanio = grafo.dijkstra(elemento1.darIdVertice(),elemento2.darIdVertice(), 0);
			unirGrafos(ultimo,momentanio);
		}
		ultimo.cc();
		escritor.mapaRequerimiento2C(ultimo);
		return ultimo;
	}

	public Graph<Integer, Bogota_Vertice> unirGrafos(Graph<Integer, Bogota_Vertice> origen, Graph<Integer, Bogota_Vertice> nuevos)
	{
		Queue<Integer> llaves = nuevos.vectores();
		while(llaves.isEmpty() == false)
		{
			Integer llave = llaves.dequeue();
			if(origen.getInfoVertex(llave) != null)
			{
				Vertice<Integer, Bogota_Vertice> elmento = nuevos.getVertex(llave);
				Queue<Arco<Integer, Bogota_Vertice>> arcos = elmento.darAdyacentes().darArcos();
				origen.addVertex(elmento.darLlave(), elmento.darInformacion());
				while(arcos.isEmpty() == false)
				{
					Arco<Integer, Bogota_Vertice> arco = arcos.dequeue();
					origen.addEdge(arco.darOrigen().darLlave(), arco.darDestino().darLlave(), arco.darCosto());
				}
			}
		}
		return origen;
	}
}


