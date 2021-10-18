package Presenter;

import Model.ModeloNuevoUsuario;
import View.AltaUsuarioView;

public class AltaUsuarioPresenter {
	
	private AltaUsuarioView vista;
	private ModeloNuevoUsuario modelo;
	
	public AltaUsuarioPresenter() {
		modelo = new ModeloNuevoUsuario();
	}
	
	public void setVista(AltaUsuarioView vista) {
		this.vista = vista;
	}
	
	public void agregarUsuario(String nombre, String password, String dni, String email, String telefono, String cargo) {
		boolean camposCorrectos = verificarCampos(nombre, password, dni, email, telefono, cargo);
		if (camposCorrectos && !modelo.existeUsuario(nombre)) {
			modelo.agregarUsuario(nombre, password, dni, email, telefono, cargo);
		}
		else {
			
		}
	}
	
	private boolean verificarCampos(String nombre, String password, String dni, String email, String telefono, String cargo) {
		boolean campoVacio = camposLlenos(nombre, password, dni, email, telefono, cargo);
		
		return true;
	}
	
	private boolean camposLlenos(String nombre, String password, String dni, String email, String telefono, String cargo) {
		boolean camposLlenos = nombre.length() > 0 && password.length() > 0 && dni.length() > 0 && email.length() > 0 && telefono.length() > 0 && cargo.length() > 0;
		
		if (!camposLlenos) {
			vista.errorCampoVacio();
		}
		
		return camposLlenos; 
	}
	
	private boolean soloNumeros(String dni, String telefono) {
		boolean soloNumeros = true;
		try {			
			Integer.valueOf(dni);
			Integer.valueOf(telefono);
		}
		catch (NumberFormatException e) {
			soloNumeros = false;
		}
		return soloNumeros;
	}
	
	private boolean dniMenorNueve(String dni) {
		if (dni.length() < 9) {
			return true;
		}
		else {
			vista.errorDni();
			return false;
		}
	}

}
