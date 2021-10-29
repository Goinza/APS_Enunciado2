package Presenter;

import java.util.Vector;

import Model.UsuariosRegistradosModel;
import View.UsuariosRegistradosView;

public class UsuariosRegistradosPresenter {
	private UsuariosRegistradosModel usuariosRegistradosModel;
	private UsuariosRegistradosView vista;
	
	public UsuariosRegistradosPresenter(UsuariosRegistradosView vista) {
		usuariosRegistradosModel = new UsuariosRegistradosModel();
		this.vista = vista;
	}
	
	public void renderizarVista()
	{
		Vector<String> columnas = usuariosRegistradosModel.getNombreColumnas();
		Vector<Vector<Object>> filas = usuariosRegistradosModel.getFilas();
		
		vista.rellenarTabla(filas, columnas);
		vista.setVisible(true);
	}

}
