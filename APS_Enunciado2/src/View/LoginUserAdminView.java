package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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

import Presenter.LoginUserAdminPresenter;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;

public class LoginUserAdminView extends JFrame{
	private static final long serialVersionUID = 1L;

	private LoginUserAdminPresenter presenter;

	private JTextField txtUsuario;
	private JTextField txtPassword;
	private ButtonGroup rbGroup;
	private JRadioButton rdbtnAdministrador;
	private JRadioButton rdbtnUsuario;
	private JButton btnLogin;

	
	public LoginUserAdminView() {
		presenter = new LoginUserAdminPresenter();
		buildGraphicComponents();
		setRdButtons();
		
		btnLogin.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLogin();
			} 
		} );
		
	}

	private void buildGraphicComponents() {
		this.setBounds(100, 100, 486, 340);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setText("");
		
		JLabel lblPassword = new JLabel("Contraseña");
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setText("");
		
		rdbtnAdministrador = new JRadioButton("Administrador");
		
		rdbtnUsuario = new JRadioButton("Usuario");
		
		JLabel lblBienvenidx = new JLabel("Soy: ");
		
		btnLogin = new JButton("Iniciar sesion");
		
	
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
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
							.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
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
		this.getContentPane().setLayout(groupLayout);
	}
	
	private void setRdButtons() {	
		rbGroup = new ButtonGroup();
		rbGroup.add(rdbtnAdministrador);
		rbGroup.add(rdbtnUsuario);
	}
		
	private void btnLogin() {
		checkUsuario();  
		checkPassword();
		checkUsuarioSeleccionado();
		
		login();
		
		flushView();
	}
	
	private boolean checkUsuario() {
		if((txtUsuario.getText().equals(""))) {
			JOptionPane optionPane = new JOptionPane("Campo usuario requerido", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog(this, "Advertencia");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			
			return false;
		}
		return true;
	}
	
	private boolean checkPassword() {
		if((txtPassword.getText().equals(""))) {
			JOptionPane optionPane = new JOptionPane("Campo contraseña requerido", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog(this, "Advertencia");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			
			return false;
		}
		
		return true;
	}
	
	private boolean checkUsuarioSeleccionado() {	
		ButtonModel modeloSeleccionado = rbGroup.getSelection();
		
		if(modeloSeleccionado == null) {
			JOptionPane optionPane = new JOptionPane("Debe seleccionar un tipo de usuario", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog(this, "Advertencia");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			
			return false;
		}
		
		return true;
	}
	
	private void login() {
		String selected = getSelectedButtonText(rbGroup);
		if(selected != null) {
			if(selected.equals("Administrador")) {
				adminLoginInput();
			}else if(selected.equals("Usuario")){
				usuarioLoginInput();		
			}
		}
		
		presenter.success(txtUsuario.getText());
	}
	
	private void adminLoginInput() {
		if (presenter.adminLogin(txtUsuario.getText(), txtPassword.getText())) {
			presenter.success("Administrador");	
			setVisible(false);
		}		
		else {
			JOptionPane optionPane = new JOptionPane("El usuario no existe o la constraseña es incorrecta", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog(this, "Error");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}
	
	private void usuarioLoginInput() {
		if (presenter.usuarioLogin(txtUsuario.getText(), txtPassword.getText())) {
			presenter.success("Usuario");	
			setVisible(false);
		}	
		else {
			JOptionPane optionPane = new JOptionPane("El usuario no existe o la constraseña es incorrecta", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog(this, "Error");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}
	
	public String getUserName() {
		return txtUsuario.getText();
	}
	
	private void flushView() {
		txtUsuario.setText("");
		txtPassword.setText("");
		
		rbGroup.clearSelection();
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
