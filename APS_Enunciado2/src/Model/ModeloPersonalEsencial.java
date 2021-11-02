package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import Model.Filtros.Filtro;
import Model.Filtros.SinFiltro;
import resources.DBConnection;
import resources.Fecha;

public class ModeloPersonalEsencial {
	
	private ResultSet query_vista_aplicadas;
	private ResultSetMetaData md;
	private int cantColumnas;
	private Filtro filtro;
	
	public ModeloPersonalEsencial()
	{
		this.filtro = new SinFiltro();
		updateQuery();
	}
	
	public ModeloPersonalEsencial(Filtro filtro)
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
	
	private String getFechaFromDate(Fecha dateFecha) {
		if (dateFecha == null) return null;
		
		int year = dateFecha.getYear();
		int month = dateFecha.getMonth();
		int day = dateFecha.getDay();
		
		return year + "-" + month + "-" + day;
	}

}
