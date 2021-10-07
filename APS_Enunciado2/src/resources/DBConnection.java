package resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{
	private String servidor = "localhost:3306";
	private String baseDatos = "vacunas";
	private String url = "jdbc:mysql://" + servidor + "/" +baseDatos+ "?serverTimezone=America/Argentina/Buenos_Aires";
	private Connection cnx;
	
	public DBConnection()
	{
		try 
		{
			cnx = java.sql.DriverManager.getConnection(url, "administrador", "admin");
		}
		catch (SQLException ex)	
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public ResultSet realizarConsulta(String query)
	{
		ResultSet rs = null;
		
		try 
		{
			Statement stm = cnx.createStatement();
										
			rs = stm.executeQuery(query);
			
		}
		catch (SQLException ex)	
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}
	
	public void cerrarConexion() throws SQLException
	{
		try 
		{
			cnx.close();
			
		}
		catch (SQLException ex)	
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}
