package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Presenter.VacunasAplicadasPresenter;
import Presenter.VacunasDisponiblesPresenter;
import Presenter.VacunasVencidasPresenter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserView extends JFrame {
	private static final long serialVersionUID = 1L;

	private final String userName;

	private JPanel contentPane;
	private VacunasDisponiblesPresenter vacunasDisponiblesPresenter;
	private VacunasAplicadasPresenter vacunasAplicadasPresenter;
	private VacunasVencidasPresenter vacunasVencidasPresenter;
	private LoginUserAdminView loginUserAdminView;

	public UserView(String userName) {
		super("Usuario");
		this.userName = userName;
		buildGraphicComponents();
	}
	
	private void buildGraphicComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblUserName = new JLabel(userName);
		
		JLabel lblUsuarioComun = new JLabel("Usuario comun");
		lblUsuarioComun.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JButton btnVerVacunasAplicadas = new JButton("Vacunas aplicadas");
		btnVerVacunasAplicadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacunasAplicadasPresenter = new VacunasAplicadasPresenter(false);
				vacunasAplicadasPresenter.renderizarVista();
			}
		});
		
		JButton btnVerVacunasDisponibles = new JButton("Vacunas disponibles");
		btnVerVacunasDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacunasDisponiblesPresenter = new VacunasDisponiblesPresenter();
				vacunasDisponiblesPresenter.renderizarVista();
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginUserAdminView = new LoginUserAdminView();
				loginUserAdminView.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnVacunasVencidas = new JButton("Vacunas Vencidas");
		btnVacunasVencidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacunasVencidasPresenter = new VacunasVencidasPresenter();
				vacunasVencidasPresenter.renderizarVista();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBienvenido, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblUsuarioComun, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(129, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVacunasVencidas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVerVacunasDisponibles, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVerVacunasAplicadas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
					.addGap(103))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(lblBienvenido))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsuarioComun)
					.addGap(97)
					.addComponent(btnVerVacunasAplicadas)
					.addGap(18)
					.addComponent(btnVerVacunasDisponibles)
					.addGap(28)
					.addComponent(btnVacunasVencidas)
					.addGap(52)
					.addComponent(btnSalir)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
