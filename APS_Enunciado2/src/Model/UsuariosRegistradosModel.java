package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import resources.DBConnection;

public class UsuariosRegistradosModel {
	
	private ResultSet query_vista_aplicadas;
	private ResultSetMetaData md;
	private int cantColumnas;

	public Vector<String> getNombreColumnas()
	{		
		updateQuery();
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
			e.printStackTrace();
		}
        
        return datos;
	}

	private void updateQuery() {
		DBConnection database = DBConnection.getInstance();
		query_vista_aplicadas = database.realizarConsulta("SELECT * FROM Usuarios;");
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
}
