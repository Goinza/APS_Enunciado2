package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Presenter.AltaUsuarioPresenter;
import Presenter.UsuariosRegistradosPresenter;
import Presenter.VacunasAplicadasPresenter;
import Presenter.VacunasDisponiblesPresenter;
import Presenter.VacunasVencidasPresenter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AdminView extends JFrame {
	
	private String adminName;

	private JPanel contentPane;
	private VacunasDisponiblesPresenter vacunasDisponiblesPresenter;
	private VacunasAplicadasPresenter vacunasAplicadasPresenter;
	private VacunasVencidasPresenter vacunasVencidasPresenter;
	private UsuariosRegistradosPresenter usuariosRegistradosPresenter;
	private UsuariosRegistradosView usuariosRegistradosView;
	private AltaUsuarioView altaUsuarioView;
	private LoginUserAdminView loginUserAdminView;
	

	public AdminView(String adminName) {
		super("Administrador");
		this.adminName = adminName;
		buildGraphicComponents();
	}
	
	private void buildGraphicComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNombreUsuario = new JLabel(adminName);
		
		JLabel lblUsuarioAdministrador = new JLabel("Usuario Administrador");
		lblUsuarioAdministrador.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JButton btnVerVacunasAplicadas = new JButton("Vacunas aplicadas");
		btnVerVacunasAplicadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacunasAplicadasPresenter = new VacunasAplicadasPresenter(true);
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
		
		JButton btnRegistrarNuevoUsuario = new JButton("Registrar nuevo usuario");
		btnRegistrarNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuarioView = new AltaUsuarioView(adminName);
				altaUsuarioView.setPresenter(new AltaUsuarioPresenter());
				altaUsuarioView.setVisible(true);
				setVisible(false);
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
		
		JButton btnVerUsuariosRegistrados = new JButton("Ver usuarios registrados");
		btnVerUsuariosRegistrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuariosRegistradosView = new UsuariosRegistradosView(adminName);
				usuariosRegistradosView.setPresenter(new UsuariosRegistradosPresenter(usuariosRegistradosView));
				usuariosRegistradosView.setVisible(true);
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalir)
						.addComponent(lblUsuarioAdministrador, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBienvenido, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnRegistrarNuevoUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnVerVacunasDisponibles, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnVerVacunasAplicadas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnVerUsuariosRegistrados, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
									.addComponent(btnVacunasVencidas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblNombreUsuario, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBienvenido)
						.addComponent(lblNombreUsuario))
					.addGap(18)
					.addComponent(lblUsuarioAdministrador)
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(btnVerVacunasAplicadas)
					.addGap(18)
					.addComponent(btnVerVacunasDisponibles)
					.addGap(13)
					.addComponent(btnVacunasVencidas)
					.addGap(18)
					.addComponent(btnRegistrarNuevoUsuario)
					.addGap(18)
					.addComponent(btnVerUsuariosRegistrados)
					.addGap(47)
					.addComponent(btnSalir)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
