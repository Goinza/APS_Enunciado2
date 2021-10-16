package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Model.ModeloNuevoUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaUsuarioView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtCargo;
	
	private AdminView adminView;

	private ModeloNuevoUsuario modeloNuevoUsuario;
	private JTextField txtDNI;
	
	public AltaUsuarioView(String nombreAdmin) {
		
		modeloNuevoUsuario = new ModeloNuevoUsuario();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoUsuario = new JLabel("Nuevo usuario");
		lblNuevoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNuevoUsuario.setBounds(169, 44, 133, 20);
		contentPane.add(lblNuevoUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(38, 130, 65, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(38, 177, 78, 14);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(38, 260, 49, 14);
		contentPane.add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(38, 298, 49, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(38, 339, 49, 14);
		contentPane.add(lblCargo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(212, 127, 161, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(212, 174, 161, 20);
		contentPane.add(txtPassword);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(212, 257, 161, 20);
		contentPane.add(txtEmail);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(212, 295, 161, 20);
		contentPane.add(txtTelefono);
		
		txtCargo = new JTextField();
		txtCargo.setColumns(10);
		txtCargo.setBounds(212, 336, 161, 20);
		contentPane.add(txtCargo);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(38, 221, 49, 14);
		contentPane.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(212, 218, 161, 20);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Dar de alta usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modeloNuevoUsuario.existeUsuario(txtNombre.getText())) {
					modeloNuevoUsuario.agregarUsuario(txtNombre.getText(), 
							txtPassword.getText(),
							txtDNI.getText(),
							txtEmail.getText(),
							txtTelefono.getText(),
							txtCargo.getText());
					
					adminView = new AdminView(nombreAdmin);
					adminView.setVisible(true);
					setVisible(false);
					
				}else {
					errorUsuarioExistente();
				}
			}
		});
		btnNewButton.setBounds(152, 414, 161, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminView = new AdminView(nombreAdmin);
				adminView.setVisible(true);
				setVisible(false);
			}
		});
		btnSalir.setBounds(383, 461, 89, 23);
		contentPane.add(btnSalir);
		

		
	}
	
	private void errorUsuarioExistente() {
		JOptionPane optionPane = new JOptionPane("Usuario existente", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
}

