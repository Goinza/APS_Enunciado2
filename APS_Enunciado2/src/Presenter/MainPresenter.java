package Presenter;

import java.awt.EventQueue;

import View.LoginUserAdminView;
import View.AdminView;
import View.UserView;

public class MainPresenter {
	
	private static LoginUserAdminView loginUserAdminView;
	private static AdminView adminView;
	private static UserView userView;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUserAdminView = new LoginUserAdminView();
					loginUserAdminView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void displayView(String userType) {
		String name = loginUserAdminView.getUserName();
		
		if(userType == "Administrador") {
			adminView = new AdminView(name);
			adminView.setVisible(true);
			loginUserAdminView.setVisible(false);
		}else if(userType == "Usuario") {
			userView = new UserView(name);
			userView.setVisible(true);
			loginUserAdminView.setVisible(false);
		}
	}


}
