package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import resources.DBConnection;

public class ModeloProvinciasImpl implements ModeloProvincias {

	@Override
	public List<Provincia> obtenerProvincias() {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("SELECT * FROM Provincias;");
		Provincia provincia = null;
		List<Provincia> lista = new LinkedList<Provincia>();
		int id;
		String nombre;
		try {
			while (result.next()) {
				id = result.getInt("id_provincia");
				nombre = result.getString("nombre_provincia");
				provincia = new Provincia(id, nombre);
				lista.add(provincia);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public List<Integer> obtenerRegiones(Provincia provincia) {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("SELECT * FROM Regiones_sanitarias WHERE id_provincia=" + provincia.obtenerId() + ";");
		List<Integer> lista = new LinkedList<Integer>();
		int id;
		try {
			while (result.next()) {
				id = result.getInt("id_region");
				lista.add(id);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public Provincia obtenerProvincia(int id) {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("SELECT * FROM Provincias WHERE id_provincia=" + id + ";");
		Provincia provincia = null;
		String nombre;
		try {
			if (result.next()) {
				nombre = result.getString("nombre_provincia");
				provincia = new Provincia(id, nombre);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return provincia;
	}

}
