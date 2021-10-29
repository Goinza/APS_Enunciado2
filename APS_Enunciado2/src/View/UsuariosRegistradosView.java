package View;

import java.awt.Container;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Presenter.UsuariosRegistradosPresenter;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuariosRegistradosView extends JFrame{
	private UsuariosRegistradosPresenter usuariosRegistradosPresenter;
	private JScrollPane scrollUsuarios;
	private JTable usuariosTable;
	private AdminView adminView;
	private String adminName;

	public UsuariosRegistradosView(String adminName) {
		this.adminName = adminName;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1150, 527);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Usuarios registrados");
		lblTitulo.setBounds(10, 11, 276, 47);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		getContentPane().add(lblTitulo);
		
		JButton btnSalir = new JButton("Volver");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVista();
			}
		});
		btnSalir.setBounds(320, 434, 89, 23);
		getContentPane().add(btnSalir);
		
		JButton btnModificarUsuario = new JButton("Modificar usuario");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lanzar venta emergente y pasarle la seleccion de la tabla?
			}
		});
		btnModificarUsuario.setBounds(572, 434, 205, 23);
		getContentPane().add(btnModificarUsuario);
	}
	
	public void cerrarVista() {
		adminView = new AdminView(adminName);
		adminView.setVisible(true);
		setVisible(false);
	}

	public void setPresenter(UsuariosRegistradosPresenter presenter) {
		usuariosRegistradosPresenter = presenter;
		usuariosRegistradosPresenter.renderizarVista();
	}

	public void rellenarTabla(Vector<Vector<Object>> datos, Vector<String> nombreColumnas)
	{
		Container contentPane = this.getContentPane();
		if (scrollUsuarios != null) {
			contentPane.remove(scrollUsuarios);
		}
		usuariosTable = new JTable(datos, nombreColumnas);
		usuariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usuariosTable.setBounds(33, 65, 655, 327);
			
		scrollUsuarios = new JScrollPane(usuariosTable);
		scrollUsuarios.setBounds(10, 65, 1113, 327);
		contentPane.add(scrollUsuarios);
        contentPane.repaint();	        
		this.repaint();
	}
}
