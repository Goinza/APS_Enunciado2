package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;

public class LoginUserAdminView {

	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JRadioButton rdbtnAdministrador;
	private JRadioButton rdbtnUsuario;
	private ButtonGroup group;
	private JButton btnLogin;
	private ButtonModel model;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUserAdminView window = new LoginUserAdminView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginUserAdminView() {
		buildComponents();
		setRdButtons();
		
		btnLogin.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLogin();
			} 
		} );
	}

	private void buildComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setText("");
		
		JLabel lblPassword = new JLabel("Contraseña");
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setText("");
		
		rdbtnAdministrador = new JRadioButton("Administrador");
		
		rdbtnUsuario = new JRadioButton("Usuario");
		
		JLabel lblBienvenidx = new JLabel("Soy: ");
		
		btnLogin = new JButton("Iniciar sesion");
		
	
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBienvenidx, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnUsuario, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnAdministrador, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(22))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLogin, Alignment.TRAILING))
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addGap(75))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblBienvenidx)
					.addGap(44)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPassword)
							.addGap(11)
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnAdministrador)
							.addGap(18)
							.addComponent(rdbtnUsuario)))
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void setRdButtons() {	
		group = new ButtonGroup();
		group.add(rdbtnAdministrador);
		group.add(rdbtnUsuario);
	}
		
	private void btnLogin() {
		checkUsuario();
		checkPassword();
		checkSelectedUser();

	}
	
	private void checkUsuario() {
		if((txtUsuario.getText().equals(""))) {
			JOptionPane optionPane = new JOptionPane("Campo usuario requerido", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Advertencia");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}
	
	private void checkPassword() {
		if((txtPassword.getText().equals(""))) {
			JOptionPane optionPane = new JOptionPane("Campo contraseña requerido", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Advertencia");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		
		}
	}
	
	private void checkSelectedUser() {
		JOptionPane optionPane = new JOptionPane("Tipo de usuario requerido", JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog("Advertencia");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	private void login() {
		String selected = getSelectedButtonText(group);
		if(selected.equals("Administrador")) {
			adminLogin();
		}else if(selected.equals("Usuario")){
			usuarioLogin();
		} else {
			JOptionPane optionPane = new JOptionPane("Debe seleccionar un tipo de usuario", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Advertencia");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}
	
	private void adminLogin() {
		//clash with database
	}
	
	private void usuarioLogin() {
		//clash with database 
	}
	
	 public String getSelectedButtonText(ButtonGroup buttonGroup) {
	        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	                return button.getText();
	            }
	        }

	        return null;
	    }
	
}
