package View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

import Presenter.VacunasAplicadasPresenter;

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

	public VacunasAplicadasView(boolean admin) {
		presenter = new VacunasAplicadasPresenter();
		buildGraphicComponents();
		setVisible(true);
		esAdmin = admin;
	}
	
	private void buildGraphicComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1147, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("VACUNAS APLICADAS");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(10, 10, 187, 38);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 397, 85, 21);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		if(esAdmin)
		{
			JButton btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnModificar.setBounds(483, 397, 85, 21);
			contentPane.add(btnModificar);
			
			JButton btnCargar = new JButton("Cargar");
			btnCargar.setBounds(580, 397, 85, 21);
			contentPane.add(btnCargar);
		}
		
		String servidor = "localhost:3306";
		String baseDatos = "vacunas";
		String url = "jdbc:mysql://" + servidor + "/" +baseDatos+ "?serverTimezone=America/Argentina/Buenos_Aires";
		try 
		{
																	
			Connection cnx = java.sql.DriverManager.getConnection(url, "administrador", "admin");
			Statement s = cnx.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM Aplicacion_Vacunas;");
	        
	        ResultSetMetaData md = rs.getMetaData();
	        int cantColumnas = md.getColumnCount();
	        
	        Vector<String> nombreColumnas = new Vector<String> ();
	        Vector<Vector<Object>> datos = new Vector<Vector<Object>>(); 
	        Vector<Object> aux;
	        
	        //Agrego las columnas
	        for (int i=1; i<=cantColumnas; i++)
	        {
	            nombreColumnas.add(md.getColumnLabel(i));
	        }         
	        
	      //Agrego las tuplas
	        while (rs.next())
	        {
	        	aux = new Vector<Object>();
	        	
	        	for (int i=1; i<=cantColumnas; i++)
		        {
		        	aux.add(rs.getString(i));
		        }   

	        	datos.add(aux);
	        }
	        
	        vacunasAplicadasTable = new JTable(datos, nombreColumnas);
	        vacunasAplicadasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        vacunasAplicadasTable.setBounds(33, 45, 655, 327);
			
	        scrollAplicadas = new JScrollPane(vacunasAplicadasTable);
	        scrollAplicadas.setBounds(10, 45, 1113, 327);
            contentPane.add(scrollAplicadas);
            repaint();
	        
	        this.repaint();
		}
		catch (SQLException ex)	
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
