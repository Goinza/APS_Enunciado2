package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Model.ModeloNuevoUsuario;
import Presenter.AltaUsuarioPresenter;

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
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtCargo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	
	private AdminView adminView;
	private String nombreAdmin;

	private AltaUsuarioPresenter presenter;
	
	private JTextField txtDNI;
	
	public AltaUsuarioView(String nombreAdmin) {
		this.nombreAdmin = nombreAdmin;
		
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
		
		int widthLabel = 78;
		int widthText = 164;
		int height = 20;
		
		int xLabel = 38;
		int xText = 212;
		int y = 90;
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblNombre);		
		txtNombre = new JTextField();
		txtNombre.setBounds(xText, y, widthText, height);
		contentPane.add(txtNombre);
		
		y += 40;
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblApellido);		
		txtApellido = new JTextField();
		txtApellido.setBounds(xText, y, widthText, height);
		contentPane.add(txtApellido);
		
		y += 40;
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblDNI);		
		txtDNI = new JTextField();
		txtDNI.setBounds(xText, y, widthText, height);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		y += 40;		
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblUsuario);	
		txtUsuario = new JTextField();
		txtUsuario.setBounds(xText, y, widthText, height);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		y += 40;
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblPassword);
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(xText, y, widthText, height);
		contentPane.add(txtPassword);
		
		y += 40;
		
		JLabel lblEmail = new JLabel("Mail");
		lblEmail.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblEmail);
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(xText, y, widthText, height);
		contentPane.add(txtEmail);
		
		y += 40;
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblTelefono);
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(xText, y, widthText, height);
		contentPane.add(txtTelefono);
		
		y += 40;
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(xLabel, y, widthLabel, height);
		contentPane.add(lblCargo);
		txtCargo = new JTextField();
		txtCargo.setColumns(10);
		txtCargo.setBounds(xText, y, widthText, height);
		contentPane.add(txtCargo);

		JButton btnNewButton = new JButton("Dar de alta usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.agregarUsuario(txtNombre.getText(), txtApellido.getText(), txtUsuario.getText(), txtPassword.getText(), txtDNI.getText(), txtEmail.getText(), txtTelefono.getText(), txtCargo.getText());			
			}
		});
		btnNewButton.setBounds(152, 414, 161, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVista();
			}
		});
		btnSalir.setBounds(383, 461, 89, 23);
		contentPane.add(btnSalir);
		
	}
	
	public void setPresenter(AltaUsuarioPresenter altaUsuarioPresenter) {
		presenter = altaUsuarioPresenter;
		presenter.setVista(this);
	}
	
	public void cerrarVista() {
		adminView = new AdminView(nombreAdmin);
		adminView.setVisible(true);
		setVisible(false);
	}
	
	public void errorUsuarioExistente() {
		JOptionPane optionPane = new JOptionPane("Usuario existente", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	public void exitoUsuarioCreado() {
		JOptionPane optionPane = new JOptionPane("Usuario creado con éxito", JOptionPane.PLAIN_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Exito");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	public void errorCampoVacio() {
		JOptionPane optionPane = new JOptionPane("Uno de los campos est� vac�o", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	public void errorNumeros() {
		JOptionPane optionPane = new JOptionPane("Tel�fono y DNI deben ser valores num�ricos", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	public void errorDni() {
		JOptionPane optionPane = new JOptionPane("DNI no puede tener mas de 8 caracteres", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	public void errorLetras() {
		JOptionPane optionPane = new JOptionPane("Los campos Nombre, Apellido, Usuario y Cargo deben comenzar con una letra", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog(this, "Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
}

