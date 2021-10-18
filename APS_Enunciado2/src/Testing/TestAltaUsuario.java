package Testing;

import Presenter.AltaUsuarioPresenter;
import View.AltaUsuarioView;

public class TestAltaUsuario {
	
	public static void main(String[] args) {
		
		AltaUsuarioView altaUsuarioView = new AltaUsuarioView("admin");
		altaUsuarioView.setPresenter(new AltaUsuarioPresenter());
		altaUsuarioView.setVisible(true);
	}

}
