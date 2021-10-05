package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {

	private JFrame frame;
	private JFrame loginUserAdminView;
	private JFrame vacunasDisponiblesView;
	private JFrame vacunasAplicadasView;
	
	private JButton btnLogin;
	private JButton btnVacunasAplicadas;
	private JButton btnVacunasDisponibles;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainView() {
		buildGraphicComponents();
	}
	 
	private void buildGraphicComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 740, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBtnLogin();
		setBtnVacunasDisponibles();
		setBtnVacunasAplicadas();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(142)
					.addComponent(btnVacunasAplicadas)
					.addGap(97)
					.addComponent(btnVacunasDisponibles)
					.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
					.addComponent(btnLogin)
					.addGap(138))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(421, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnVacunasAplicadas)
						.addComponent(btnVacunasDisponibles))
					.addGap(135))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void setBtnLogin() {
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginUserAdminView = new LoginUserAdminView();
			}
		});
	}
	
	private void setBtnVacunasDisponibles() {
		btnVacunasDisponibles = new JButton("Vacunas Disponibles");
		btnVacunasDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacunasDisponiblesView = new VacunasAplicadasView();
				vacunasAplicadasView.setVisible(true);
			}
		});
	}
	
	private void setBtnVacunasAplicadas() {
		btnVacunasAplicadas = new JButton("Vacunas Aplicadas");
		btnVacunasAplicadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacunasAplicadasView = new VacunasAplicadasView();
				vacunasAplicadasView.setVisible(true);
			}
		});
	}

}
