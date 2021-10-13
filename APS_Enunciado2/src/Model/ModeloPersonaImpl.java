package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import resources.DBConnection;
import resources.Fecha;

public class ModeloPersonaImpl implements ModeloPersonas {

	@Override
	public Persona obtenerPersona(int dni) {
		DBConnection database = DBConnection.getInstance();
		ResultSet result = database.realizarConsulta("SELECT * FROM Personas WHERE DNI=" + dni + ";");
		Persona persona = null;
		try {
			if (result.next()) {
				String nombre = result.getString("nombre");
				String apellido = result.getString("apellido");
				String mail = result.getString("mail");
				Fecha fechaNacimiento = new Fecha(result.getString("fecha_nacimiento"));
				persona = new Persona(dni, nombre, apellido, mail, fechaNacimiento);	
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return persona;
	}

	@Override
	public void almacenarPersona(Persona persona) {
		String nombre = persona.obtenerNombre();
		String apellido = persona.obtenerApellido();
		String fechaNacimiento = persona.obtenerFechaNacimiento().toString();
		String mail = persona.obtenerMail();
		int dni = persona.obtenerDni();
		String query = "INSERT INTO Personas VALUES ('" + nombre + "', '" + apellido + "', '" + fechaNacimiento + "', '" + mail + "', " + dni + ");";
		
		DBConnection database = DBConnection.getInstance();
		database.realizarStatement(query);
	}


	@Override
	public void actualizarPersona(int dni, Persona persona)
	{
		DBConnection database = DBConnection.getInstance();
		database.realizarStatement("UPDATE Personas SET nombre = '" + persona.obtenerNombre() + "', " +
									"apellido = '" + persona.obtenerApellido() + "', fecha_nacimiento = '" + persona.obtenerFechaNacimiento().toString() + "', " +
									"mail = '" + persona.obtenerMail() + "', dni = " + persona.obtenerDni() + " WHERE dni = " + dni + ";");
	}

}
