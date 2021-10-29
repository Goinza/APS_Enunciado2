package View;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Presenter.UsuariosRegistradosPresenter;

public class UsuariosRegistradosView extends JFrame{
	private UsuariosRegistradosPresenter usuariosRegistradosPresenter;
	private JScrollPane scrollUsuarios;
	private JTable usuariosTable;

	public UsuariosRegistradosView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1147, 477);
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
