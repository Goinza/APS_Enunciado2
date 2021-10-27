package View;

import javax.swing.JFrame;

import Presenter.UsuariosRegistradosPresenter;

public class UsuariosRegistradosView extends JFrame{
	private UsuariosRegistradosPresenter usuariosRegistradosPresenter;

	public void setPresenter(UsuariosRegistradosPresenter presenter) {
		usuariosRegistradosPresenter = presenter;
	}
	
}
