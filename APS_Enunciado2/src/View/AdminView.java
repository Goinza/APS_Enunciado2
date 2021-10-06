package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminView extends JFrame {

	private JPanel contentPane;

	public AdminView(String adminName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		
		JLabel lblNombreUsuario = new JLabel(adminName);
		
		JLabel lblUsuarioAdministrador = new JLabel("Usuario Administrador");
		
		JButton btnVerVacunasAplicadas = new JButton("Vacunas aplicadas");
		btnVerVacunasAplicadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnVerVacunasDisponibles = new JButton("Vacunas disponibles");
		btnVerVacunasDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnRegistrarNuevoUsuario = new JButton("Registrar nuevo usuario");
		btnRegistrarNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsuarioAdministrador, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnVerVacunasAplicadas)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblBienvenido, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNombreUsuario, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnVerVacunasDisponibles)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(btnRegistrarNuevoUsuario))))
					.addContainerGap(494, Short.MAX_VALUE))
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
					.addGap(85)
					.addComponent(btnVerVacunasAplicadas)
					.addGap(18)
					.addComponent(btnVerVacunasDisponibles)
					.addGap(18)
					.addComponent(btnRegistrarNuevoUsuario)
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
