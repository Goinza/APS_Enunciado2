package Model;

import java.util.Vector;

/**
 * Interfaz para acceder a una tabla de la base de datos.
 * Cada implementacion de esta interfaz accedera a una tabla (o vista) diferente.
 */

public interface ModeloTabla {
	
	public boolean accederAdmin(String nombre, String password);
	
	public boolean accederUsuario(String nombre, String password);

	public Vector<String> obtenerNombresColumnas();
	
	public Vector<Vector<Object>> obtenerTuplas();
	
	public boolean agregarTupla(Vector<Object> tupla);
	
	public boolean modificarTupla(Vector<Object> tupla);


	
}
