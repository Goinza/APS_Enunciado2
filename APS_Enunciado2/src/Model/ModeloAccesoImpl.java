package Model;

public class ModeloAccesoImpl implements ModeloAcceso {
	@Override
	public boolean accederAdmin(String nombre, String password) {
		//select * from Usuarios where usuario=nombre && contrasena=password && esAdmin = 0;
		return true;
	}
	
	@Override
	public boolean accederUsuario(String nombre, String password) {
		//select * from Usuarios where usuario=nombre && contrasena=password && esAdmin = 1;
		return true;
	}

}
