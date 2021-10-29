package Testing;

import Presenter.UsuariosRegistradosPresenter;
import View.UsuariosRegistradosView;

public class TestTablaUsuarios {
    
    public static void main(String[] args) {
        UsuariosRegistradosView usuariosRegistradosView = new UsuariosRegistradosView("AdminTest");
        UsuariosRegistradosPresenter presenter = new UsuariosRegistradosPresenter(usuariosRegistradosView);
		usuariosRegistradosView.setPresenter(presenter);
        usuariosRegistradosView.repaint();
	}

}
