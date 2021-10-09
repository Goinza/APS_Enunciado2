package View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class VacunasAplicadasView extends JFrame {

	private JPanel contentPane;
	private JTable vacunasAplicadasTable;
	private JScrollPane scrollAplicadas;
	private boolean esAdmin;

	public VacunasAplicadasView(boolean admin)
	{
		buildGraphicComponents();
		esAdmin = admin;
	}
	
	private void buildGraphicComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1147, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("VACUNAS APLICADAS");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(10, 10, 187, 38);
		contentPane.add(lblTitulo);
		
		//Boton Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 397, 85, 21);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		//Botones Modificar y Cargar, SOLO si el usuario logueado es Administrador
		if(esAdmin)
		{
			JButton btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//MODIFICO DATOS VACUNA
				}
			});
			btnModificar.setBounds(483, 397, 85, 21);
			contentPane.add(btnModificar);
			
			JButton btnCargar = new JButton("Cargar");
			btnCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//CARGA NUEVA VACUNA
				}
			});
			btnCargar.setBounds(580, 397, 85, 21);
			contentPane.add(btnCargar);
		}
	}
	
	public void rellenarTabla(Vector<Vector<Object>> datos, Vector<String> nombreColumnas)
	{
		vacunasAplicadasTable = new JTable(datos, nombreColumnas);
		vacunasAplicadasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vacunasAplicadasTable.setBounds(33, 45, 655, 327);
			
		scrollAplicadas = new JScrollPane(vacunasAplicadasTable);
		scrollAplicadas.setBounds(10, 45, 1113, 327);
		contentPane.add(scrollAplicadas);
        repaint();
	        
	    this.repaint();
	}
}
