package Model;

public interface ModeloAcceso {
	boolean accederAdmin(Provincia nombre, Provincia password);

	boolean accederUsuario(Provincia nombre, Provincia password);
}
