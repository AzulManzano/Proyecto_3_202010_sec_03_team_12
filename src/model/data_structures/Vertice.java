package model.data_structures;

public class Vertice<K,V>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	private K llave;
	private V informacion;
	private boolean marca;
	private int color;
	private Arco<K,V> arcoLlegada;
	
	private Lista<Arco<K,V>> adyacentes;
	
	private EstacionPolicia estacion;
	private Lista<Comparendo> listaComparendos;
	
	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public Vertice(K pLlave, V pInformacion)
	{
		llave = pLlave;
		informacion = pInformacion;
		
		marca = false;
		color = -1;
		arcoLlegada = null;
		adyacentes = new Lista<Arco<K,V>>();
		
		estacion = null;
		listaComparendos = new Lista<Comparendo>();
	}
	
	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	public K darLlave()
	{
		return llave;
	}
	
	public V darInformacion()
	{
		return informacion;
	}
	
	public Lista<Arco<K,V>> darAdyacentes()
	{
		return adyacentes;
	}
	
	public boolean darMarca()
	{
		return marca;
	}
	
	public int darColor()
	{
		return color;
	}
	
	public EstacionPolicia darEstacion()
	{
		return estacion;
	}
	
	public Lista<Comparendo> darListaComparenos()
	{
		return listaComparendos;
	}
	
	public void agregarEstacion(EstacionPolicia pEstacion)
	{
		estacion = pEstacion;
	}
	
	public void agregarComparendo(Comparendo pComparendo)
	{
		listaComparendos.add(pComparendo);
	}
	
	public void cambiarInformacion(V pInformacion)
	{
		informacion = pInformacion;
	}
	
	public void agregarArco(Arco<K,V> pArco)
	{
		adyacentes.add(pArco);
	}
	
	public void marcar(int pColor,Arco<K,V> pArcoLlegada )
	{
		color = pColor;
		arcoLlegada = pArcoLlegada;
		marca = true;
	}
	
	public void desmarcar()
	{
		color = -1;
		arcoLlegada = null;
		marca = false;
	}
	
	
	
}
