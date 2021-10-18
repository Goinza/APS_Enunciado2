package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import resources.DBConnection;

public class ModeloNuevoUsuario {
	
	public boolean existeUsuario(String usuario) {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("select * from Usuarios where usuario='"+usuario+"';");
		boolean existe = false;
		try {
			existe = result.next();
		}
		catch (SQLException e) {
			
		}
		return existe;
	}

	public void agregarUsuario(String nombre, String apellido, String usuario, String password, String dni, String email, String telefono, String cargo) {
		DBConnection database = DBConnection.getInstance();
		String query = "insert into Usuarios values ('" + usuario + "', '" + password + "', '" + nombre + "', '"
				+ apellido + "', '" + cargo + "', '" + email + "', " + dni + ", " + telefono + "," + 0 + ");";
		System.out.println(query);
		database.realizarStatement(query);		
	}
	
	
	
	

}
