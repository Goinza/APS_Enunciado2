package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import resources.DBConnection;

public class ModeloNuevoUsuario {
	
	public boolean existeUsuario(String nombre) {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("select * from Usuarios where usuario='"+nombre+"';");
		boolean existe = false;
		try {
			existe = result.next();
		}
		catch (SQLException e) {
			
		}
		return existe;
	}

	public void agregarUsuario(String usuario, String password, String dni, String email, String telefono, String cargo) {
		DBConnection database = DBConnection.getInstance();
		String nombre = "Juan";
		String apellido = "Perez";
		String query = "insert into Usuarios values ('" + usuario + "', '" + password + "', '" + nombre + "', '"
				+ apellido + "', '" + cargo + "', '" + mail + ", " + dni + ", " + telefono + ", " + false;
		database.realizarStatement(query);		
	}
	
	
	
	

}
