package model.data_structures;

public class EstacionPolicia 
{	
	private int objectId;
	private String direccion;
	private String nobre;

	private String descripcion;
	private String servicio; 
	private String horario;
	private String telefono;
	private String local;

	private double latitud;
	private double longitud;

	private int idVertice;

	public EstacionPolicia(int pObjet, String pDireccion, String pNombre, double pLatitud, double pLongitud, String EPODESCRIP, String EPOSERVICIO, String EPOHORARIO,String EPOTELEFON,String EPOIULOCAL)
	{
		objectId = pObjet;
		direccion = pDireccion;
		nobre = pNombre;
		latitud = pLatitud;
		longitud = pLongitud;

		descripcion = EPODESCRIP;
		servicio = EPOSERVICIO; 
		horario = EPOHORARIO;
		telefono = EPOTELEFON;
		local = EPOIULOCAL;

		idVertice =-1;
	}

	public int darObjet()
	{
		return objectId;
	}

	public String darDireccion()
	{
		return direccion;
	}

	public String darNombre()
	{
		return nobre;
	}

	public double darLatitud()
	{
		return latitud;
	}

	public double darLongitud()
	{
		return longitud;
	}

	public String darDescripcion()
	{
		return descripcion;
	}

	public String darServicio()
	{
		return servicio;
	}

	public String darHorario()
	{
		return horario;
	}

	public String darTelefono()
	{
		return telefono;
	}

	public String darLocal()
	{
		return local;
	}

	//	OBJECTID, EPODESCRIP, EPODIR_SITIO,EPOLATITUD, EPOLONGITU, EPOSERVICIO, EPOHORARIO, EPOTELEFON, EPOIULOCAL
	public String darInformacion()
	{
		return "[OBJECTID: " + objectId+ ", EPODESCRIP: " + descripcion+", EPODIR_SITIO: "+ direccion+ ", EPOLATITUD: " + latitud + ", EPOLONGITU: "+longitud+", EPOSERVICIO: "+servicio+", EPOHORARIO: "+horario+", EPOTELEFON: "+telefono+", EPOIULOCAL: "+local+"]";
	}

	public int darIdVertice()
	{
		return idVertice;
	}

	public void cambiarIdVertice(int nuevoId)
	{
		idVertice = nuevoId;
	}
}
