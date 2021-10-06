package Presenter;


import Model.ModeloAccesoImpl;

public class LoginUserAdminPresenter {
	private ModeloAccesoImpl modelo;
	
	private MainPresenter mainView;
	
	public LoginUserAdminPresenter() {
		modelo = new ModeloAccesoImpl();
		mainView = new MainPresenter();
	}
	
	public boolean adminLogin(String nombre, String password) {
		return modelo.accederAdmin(nombre, password);
	}
	
	public boolean usuarioLogin(String nombre, String password) {
		return modelo.accederUsuario(nombre, password);
	}
	
	public void success(String userType) {
		mainView.displayView(userType);
	}
	
}
