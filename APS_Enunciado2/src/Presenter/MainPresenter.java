package Presenter;

import javax.swing.JFrame;

public class MainPresenter {

	private JFrame frame;
	private JFrame loginUserAdminView;
	private JFrame vacunasDisponiblesView;
	private JFrame vacunasAplicadasView;
		
	public void displayView(String userType) {
		if(userType == "Administrador") {
			//ir a pantalla admin
		}else if(userType == "Usuario") {
			//ir a pantalla usuario 
		}
	}


}
