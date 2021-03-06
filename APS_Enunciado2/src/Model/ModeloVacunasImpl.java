package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import resources.DBConnection;

public class ModeloVacunasImpl implements ModeloVacunas {

	@Override
	public List<Vacuna> obtenerVacunas() {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("SELECT * FROM Vacunas;");
		List<Vacuna> lista = new LinkedList<Vacuna>();
		Vacuna vacuna;
		int id;
		String nombre;
		try {
			while (result.next()) {
				id = result.getInt("id_vacuna");
				nombre = result.getString("nombre_vacuna");
				vacuna = new Vacuna(id, nombre);
				lista.add(vacuna);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Vacuna obtenerVacuna(int id)
	{
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("SELECT * FROM Vacunas WHERE id_vacuna=" + id + ";");
		Vacuna vac = null;
		try
		{
			result.next();
			vac.establecerId(result.getInt("id_vacuna"));
			vac.establecerNombre(result.getString("nombre_vacuna"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return vac;
	}

	@Override
	public List<Vacuna> obtenerVacunasPorProvincia(Provincia provincia) {
		String query = "SELECT DISTINCT id_vacuna, nombre_vacuna FROM " +
				"Vacunas_entregadas NATURAL JOIN Vacunas" + 
				" WHERE id_provincia=" + provincia.obtenerId() + ";";
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta(query);
		List<Vacuna> lista = new LinkedList<Vacuna>();
		Vacuna vacuna;
		int id;
		String nombre;
		try {
			while (result.next()) {
				id = result.getInt("id_vacuna");
				nombre = result.getString("nombre_vacuna");
				vacuna = new Vacuna(id, nombre);
				lista.add(vacuna);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;		
	}

}
