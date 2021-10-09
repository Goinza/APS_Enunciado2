package Model;

public interface ModeloAcceso {
	boolean accederAdmin(String nombre, String password);

	boolean accederUsuario(String nombre, String password);
}
