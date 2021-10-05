package Presenter;


import Model.ModeloAccesoImpl;

public class LoginAdminUserPresenter {
	private ModeloAccesoImpl modelo;
	
	public LoginAdminUserPresenter() {
		modelo = new ModeloAccesoImpl();
	}
	
	public boolean adminLogin(String nombre, String password) {
		return modelo.accederAdmin(nombre, password);
	}
	
	public boolean usuarioLogin(String nombre, String password) {
		return modelo.accederUsuario(nombre, password);
	}
	
}
