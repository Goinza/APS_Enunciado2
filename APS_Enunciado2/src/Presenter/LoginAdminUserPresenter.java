package Presenter;

import Model.ModeloTabla;
import Model.TestTabla;

public class LoginAdminUserPresenter {
	private ModeloTabla modelo;
	
	public LoginAdminUserPresenter() {
		modelo = new TestTabla();
	}
	
	/**
	 * Retorna verdadero, falso en caso contrario. 
	 */
	public boolean adminLogin(String nombre, String password) {
		return modelo.accederAdmin(nombre, password);
	}
	
	/**
	 * Retorna verdadero, falso en caso contrario. 
	 */
	public boolean usuarioLogin(String nombre, String password) {
		return modelo.accederUsuario(nombre, password);
	}
	
}
