package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import resources.DBConnection;

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
				Date fechaNacimiento = getFechaNacimientoFromString(result.getString("fecha_nacimiento"));
				persona = new Persona(dni, nombre, apellido, mail, fechaNacimiento);	
			}
		}
		catch (SQLException | ParseException e) {
			e.printStackTrace();
		}		
		
		return persona;
	}

	@Override
	public void almacenarPersona(Persona persona) {
		String nombre = persona.obtenerNombre();
		String apellido = persona.obtenerApellido();
		String fechaNacimiento = getFechaNacimientoFromDate(persona.obtenerFechaNacimiento());
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
		database.realizarStatement("UPDATE Personas SET nombre = '" + persona.obtenerNombre() + "' " +
									"apellido = '" + persona.obtenerApellido() + "' fecha_nacimiento = '" + getFechaNacimientoFromDate(persona.obtenerFechaNacimiento()) + "' " +
									"mal = '" + persona.obtenerMail() + "' dni = " + persona.obtenerDni() + " WHERE dni = " + dni + ";");
	}

	private Date getFechaNacimientoFromString(String stringFecha) throws ParseException {
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		 return format.parse(stringFecha);
	}
	
	private String getFechaNacimientoFromDate(Date dateFecha) {
		int year = dateFecha.getYear();
		int month = dateFecha.getMonth();
		int day = dateFecha.getDay();
		
		return year + "-" + month + "-" + day;
	}

}
