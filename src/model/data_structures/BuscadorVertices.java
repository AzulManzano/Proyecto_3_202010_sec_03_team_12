package model.data_structures;

public class BuscadorVertices 
{

	private Haversine distanciador;
	private Graph<Integer, Bogota_Vertice> bogotaSectores0;
	private Graph<Integer, Bogota_Vertice> bogotaSectores1;
	private Graph<Integer, Bogota_Vertice> bogotaSectores2;
	private Graph<Integer, Bogota_Vertice> bogotaSectores3;
	private Graph<Integer, Bogota_Vertice> bogotaSectores4;
	private Graph<Integer, Bogota_Vertice> bogotaSectores5;
	private Graph<Integer, Bogota_Vertice> bogotaSectores6;
	private Graph<Integer, Bogota_Vertice> bogotaSectores7;
	private Graph<Integer, Bogota_Vertice> bogotaSectores8;
	private Graph<Integer, Bogota_Vertice> bogotaSectores9;
	private Graph<Integer, Bogota_Vertice> bogotaSectores10;
	private Graph<Integer, Bogota_Vertice> bogotaSectores11;
	private Graph<Integer, Bogota_Vertice> bogotaSectores12;
	private Graph<Integer, Bogota_Vertice> bogotaSectores13;
	private Graph<Integer, Bogota_Vertice> bogotaSectores14;
	private Graph<Integer, Bogota_Vertice> bogotaSectores15;
	private Graph<Integer, Bogota_Vertice> bogotaSectores16;
	private Graph<Integer, Bogota_Vertice> bogotaSectores17;
	private Graph<Integer, Bogota_Vertice> bogotaSectores18;
	private Graph<Integer, Bogota_Vertice> bogotaSectores19;

	public BuscadorVertices()
	{
		distanciador =new Haversine(); 
		
		bogotaSectores0	= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores1 = new Graph<Integer, Bogota_Vertice>();
		bogotaSectores2 = new Graph<Integer, Bogota_Vertice>();
		bogotaSectores3 = new Graph<Integer, Bogota_Vertice>();
		bogotaSectores4= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores5= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores6= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores7= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores8= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores9= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores10= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores11= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores12= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores13= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores14= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores15= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores16= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores17= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores18= new Graph<Integer, Bogota_Vertice>();
		bogotaSectores19= new Graph<Integer, Bogota_Vertice>();
	}

	public Integer buscarVertice( double latitud, double longitud)
	{
		Graph<Integer, Bogota_Vertice> grafo = new Graph<Integer, Bogota_Vertice>();
		if(longitud >= -74.227752 && longitud <= -74.16715575)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				grafo = bogotaSectores4;
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				grafo = bogotaSectores3;
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				grafo = bogotaSectores2;
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				grafo = bogotaSectores1;
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				grafo = bogotaSectores0;
			}

		}
		
		else if(longitud >= -74.16715575 && longitud <= -74.1065595)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				grafo = bogotaSectores9;
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				grafo = bogotaSectores8;
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				grafo = bogotaSectores7;
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				grafo = bogotaSectores6;
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				grafo = bogotaSectores5;
			}
		}
		
		else if(longitud >= -74.1065595 && longitud <= -74.04596325)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				grafo = bogotaSectores14;
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				grafo = bogotaSectores13;
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				grafo = bogotaSectores12;
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				grafo = bogotaSectores11;
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				grafo = bogotaSectores10;
			}
		}
		
		else if(longitud >= -74.04596325 && longitud <= -73.985367)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				grafo = bogotaSectores19;
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				grafo = bogotaSectores18;
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				grafo = bogotaSectores17;
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				grafo = bogotaSectores16;
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				grafo = bogotaSectores15;
			}
		}
		
		Queue<Integer> llaves = grafo.vectores();
		int tam = llaves.getSize();
		Integer respuesta = 0;
		Integer llaveMomento = -1;
		double momentanio = 1000000;
		double distancia = 1000000;
		boolean termine = false;

		for(int i = 0; i<tam && termine == false; i++)
		{
			llaveMomento = llaves.dequeue();
			Bogota_Vertice vertice = grafo.getInfoVertex(llaveMomento);
			momentanio = distanciador.distance(latitud, longitud, vertice.darLatitud(), vertice.darLongitud());

			if(momentanio<=distancia)
			{
				respuesta = llaveMomento;
				distancia = momentanio;
			}
			if(momentanio ==0)
				termine = true;
		}

		return respuesta;
	}

	public void cargar(Integer id,double latitud, double longitud, Bogota_Vertice elemento)
	{
		if(longitud >= -74.227752 && longitud <= -74.16715575)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				bogotaSectores4.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				bogotaSectores3.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				bogotaSectores2.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				bogotaSectores1.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				bogotaSectores0.addVertex(id, elemento);
			}

		}
		
		else if(longitud >= -74.16715575 && longitud <= -74.1065595)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				bogotaSectores9.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				bogotaSectores8.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				bogotaSectores7.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				bogotaSectores6.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				bogotaSectores5.addVertex(id, elemento);
			}
		}
		
		else if(longitud >= -74.1065595 && longitud <= -74.04596325)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				bogotaSectores14.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				bogotaSectores13.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				bogotaSectores12.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				bogotaSectores11.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				bogotaSectores10.addVertex(id, elemento);
			}
		}
		
		else if(longitud >= -74.04596325 && longitud <= -73.985367)
		{
			if(latitud >= 4.455951 && latitud <= 4.584159333)
			{
				bogotaSectores19.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.584159333 && latitud <= 4.626895444)
			{
				bogotaSectores18.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.626895444 && latitud <= 4.669631556)
			{
				bogotaSectores17.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.669631556 && latitud <= 4.712367667)
			{
				bogotaSectores16.addVertex(id, elemento);
			}
			
			else if(latitud >= 4.712367667 && latitud <= 4.840576)
			{
				bogotaSectores15.addVertex(id, elemento);
			}
		}
	}
}
