package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import resources.DBConnection;

public class ModeloAccesoImpl implements ModeloAcceso {
	
	@Override
	public boolean accederAdmin(String nombre, String password) {
		DBConnection database = new DBConnection();
		ResultSet result = database.realizarConsulta("select * from Usuarios where usuario="+nombre+" && contrasena="+password+" && esAdmin = 0");
		boolean acceso = false;
		try {
			acceso = result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acceso;
	}
	
	@Override
	public boolean accederUsuario(String nombre, String password) {
		DBConnection database = new DBConnection();
		ResultSet result = database.realizarConsulta("select * from Usuarios where usuario="+nombre+" && contrasena="+password+" && esAdmin = 1");
		boolean acceso = false;
		try {
			acceso = result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acceso;
	}

}
