package View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;



public class VacunasDisponiblesView extends JFrame {

	private JPanel contentPane;
	private JTable vacunasDisponiblesTable;
	private JScrollPane scrollDisponibles;

	/**
	 * Create the frame.
	 */
	public VacunasDisponiblesView()
	{ 	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 735, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("VACUNAS DISPONIBLES");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(33, 10, 340, 38);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//AL HOME
			}
		});
		btnVolver.setBounds(33, 395, 85, 21);
		contentPane.add(btnVolver);
		
		//---------------------------------Primero obtengo vacunas aplicadas por provincia y tipo de Vacuna---------------------------------
		 	
		/*public void rellenarTabla(datos, nombreColumnas)
		{
			 vacunasDisponiblesTable = new JTable(datos, nombreColumnas);
		}

		*
		*/
		
			String servidor = "localhost:3306";
			String baseDatos = "vacunas";
			String url = "jdbc:mysql://" + servidor + "/" +baseDatos+ "?serverTimezone=America/Argentina/Buenos_Aires";
			try 
			{
				Connection cnx = java.sql.DriverManager.getConnection(url, "administrador", "admin");
				Statement s = cnx.createStatement();
		        ResultSet rs = s.executeQuery("SELECT * FROM Vacunas_Disponibles;");
		        
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
		        
		        vacunasDisponiblesTable = new JTable(datos, nombreColumnas);
				vacunasDisponiblesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				vacunasDisponiblesTable.setBounds(33, 45, 655, 327);
				
				scrollDisponibles = new JScrollPane(vacunasDisponiblesTable);
				scrollDisponibles.setBounds(33, 45, 655, 327);
	            contentPane.add(scrollDisponibles);
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
