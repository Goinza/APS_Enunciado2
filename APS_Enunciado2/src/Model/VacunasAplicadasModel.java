package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import Model.Filtros.Filtro;
import Model.Filtros.SinFiltro;
import resources.DBConnection;
import resources.Fecha;

public class VacunasAplicadasModel
{
	private ResultSet query_vista_aplicadas;
	private ResultSetMetaData md;
	private int cantColumnas;
	private Filtro filtro;
	
	public VacunasAplicadasModel()
	{
		this.filtro = new SinFiltro();
		updateQuery();
	}
	
	public VacunasAplicadasModel(Filtro filtro)
	{
		this.filtro = filtro;
		updateQuery();
	}
	
	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	
	public void updateQuery() {
		DBConnection database = DBConnection.getInstance();
		query_vista_aplicadas = database.realizarConsulta(filtro.obtenerConsulta());
		try
		{
			md = query_vista_aplicadas.getMetaData();
			cantColumnas = md.getColumnCount();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Vector<String> getNombreColumnas()
	{		
		Vector<String> nombreColumnas = new Vector<String>();
		try
		{
			//Agrego las columnas
	        for (int i=1; i<=cantColumnas; i++)
	        {
	            nombreColumnas.add(md.getColumnLabel(i));
	        }     
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}    
		
		return nombreColumnas;
	}
	
	public Vector<Vector<Object>> getFilas()
	{
		updateQuery();
		Vector<Vector<Object>> datos = new Vector<Vector<Object>>(); 
        Vector<Object> aux;
        
        //Agrego las tuplas
        try
        {
			while (query_vista_aplicadas.next())
			{
				aux = new Vector<Object>();
				
				for (int i=1; i<=cantColumnas; i++)
			    {
			    	aux.add(query_vista_aplicadas.getString(i));
			    }   

				datos.add(aux);
			}
		}
        catch (SQLException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return datos;
	}
	
	public void agregarVacunaAplicada(VacunaAplicada vacunaAplicada) {		
		DBConnection database = DBConnection.getInstance();
		String primeraDosis = getFechaFromDate(vacunaAplicada.obtenerPrimeraDosis());
		String segundaDosis = getFechaFromDate(vacunaAplicada.obtenerSegundaDosis());
		int cantidadDosis = vacunaAplicada.obtenerCantidadDosis();
		int dni = vacunaAplicada.obtenerPersona().obtenerDni();
		int idVacuna = vacunaAplicada.obtenerVacuna().obtenerId();
		int idProvincia = vacunaAplicada.obtenerProvincia().obtenerId();
		int idRegion = vacunaAplicada.obtenerRegion();
		String query;
		if (segundaDosis == null) {
			query = "INSERT INTO Vacunas_Aplicadas VALUES ('" + primeraDosis + "', NULL, " + cantidadDosis + ", " + dni + ", " + idVacuna + ", " + idProvincia + ", " + idRegion + ");";
		}
		else {
			query = "INSERT INTO Vacunas_Aplicadas VALUES ('" + primeraDosis + "', '" + segundaDosis + "', " + cantidadDosis + ", " + dni + ", " + idVacuna + ", " + idProvincia + ", " + idRegion + ");";
		}
		database.realizarStatement(query);
	}
	
	private String getFechaFromDate(Fecha dateFecha) {
		if (dateFecha == null) return null;
		
		int year = dateFecha.getYear();
		int month = dateFecha.getMonth();
		int day = dateFecha.getDay();
		
		return year + "-" + month + "-" + day;
	}
	
	public void actualizarVacunaAplicada(VacunaAplicada nuevaVacunaAplicada, int dni)
	{
		DBConnection database = DBConnection.getInstance();
		
		/*Fecha segunda_dosis = null;
		
		if(nuevaVacunaAplicada.obtenerSegundaDosis() != null)
		{
			segunda_dosis = nuevaVacunaAplicada.obtenerSegundaDosis();
		}*/
				
		
		database.realizarStatement("UPDATE Vacunas_Aplicadas SET primera_dosis = '" + nuevaVacunaAplicada.obtenerPrimeraDosis() + "', " +
									"segunda_dosis = " + (nuevaVacunaAplicada.obtenerSegundaDosis() != null? "'"+nuevaVacunaAplicada.obtenerSegundaDosis()+"'" : "NULL")+ ", cantidad_dosis = " + nuevaVacunaAplicada.obtenerCantidadDosis() + ", " +
									"dni = " + dni + ", id_vacuna = " + nuevaVacunaAplicada.obtenerVacuna().obtenerId()+ ", id_provincia = " +nuevaVacunaAplicada.obtenerProvincia().obtenerId()+ ", id_region = " +nuevaVacunaAplicada.obtenerRegion() + " WHERE dni = " + dni + ";");
	}
	
}
