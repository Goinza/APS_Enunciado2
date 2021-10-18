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
	
	public void agregarUsuario(String nombre, String apellido, String usuario, String password, String dni, String email, String telefono, String cargo) {
		boolean camposCorrectos = verificarCampos(nombre, apellido, usuario, password, dni, email, telefono, cargo);
		if (camposCorrectos) {
			modelo.agregarUsuario(nombre, apellido, usuario, password, dni, email, telefono, cargo);
		}
	}
	
	private boolean verificarCampos(String nombre, String apellido, String usuario, String password, String dni, String email, String telefono, String cargo) {
		boolean camposLlenos = camposLlenos(nombre, apellido, usuario, password, dni, email, telefono, cargo);
		boolean soloNumeros = soloNumeros(dni, telefono);
		boolean dniMenor = dniMenorNueve(dni);
		boolean comienzaLetra = comienzaLetra(nombre, apellido, usuario, cargo);
		boolean usuarioNuevo = usuarioNuevo(usuario);
		
		return camposLlenos && soloNumeros && dniMenor && comienzaLetra && usuarioNuevo;
	}
	
	private boolean camposLlenos(String nombre, String apellido, String usuario, String password, String dni, String email, String telefono, String cargo) {
		boolean camposLlenos = nombre.length() > 0 && apellido.length() > 0 && usuario.length() > 0 && password.length() > 0 && dni.length() > 0 && email.length() > 0 && telefono.length() > 0 && cargo.length() > 0;
		
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
			vista.errorNumeros();
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
	
	private boolean comienzaLetra(String nombre, String apellido, String usuario, String cargo) {
		boolean comienzaLetra = false;
		try {
			boolean nombreLetra = Character.isLetter(nombre.charAt(0));
			boolean apellidoLetra = Character.isLetter(apellido.charAt(0));
			boolean usuarioLetra = Character.isLetter(usuario.charAt(0));
			boolean cargoLetra = Character.isLetter(usuario.charAt(0));
			comienzaLetra = nombreLetra && apellidoLetra && usuarioLetra && cargoLetra;
			
			if (!comienzaLetra) {
				vista.errorLetras();
			}
		}		
		catch (StringIndexOutOfBoundsException e) {
			vista.errorLetras();
		}
		return comienzaLetra;
	}
	
	private boolean usuarioNuevo(String usuario) {
		boolean usuarioNuevo = !modelo.existeUsuario(usuario); 
		if (!usuarioNuevo) {
			vista.errorUsuarioExistente();
		}
		
		return usuarioNuevo;
	}

}
