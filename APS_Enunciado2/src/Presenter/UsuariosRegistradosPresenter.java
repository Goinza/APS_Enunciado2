package Presenter;

import Model.UsuariosRegistradosModel;

public class UsuariosRegistradosPresenter {
	private UsuariosRegistradosModel usuariosRegistradosModel;
	
	public UsuariosRegistradosPresenter() {
		usuariosRegistradosModel = new UsuariosRegistradosModel();
	}
	
	public void obtenerUsuariosRegistrados() {
		usuariosRegistradosModel.obtenerUsuariosRegistrados();
	}

}
