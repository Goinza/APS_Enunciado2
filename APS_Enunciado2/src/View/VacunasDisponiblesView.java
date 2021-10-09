package View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	}
	
	public void rellenarTabla(Vector<Vector<Object>> datos, Vector<String> nombreColumnas)
	{
	        vacunasDisponiblesTable = new JTable(datos, nombreColumnas);
			vacunasDisponiblesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			vacunasDisponiblesTable.setBounds(33, 45, 655, 327);
			
			scrollDisponibles = new JScrollPane(vacunasDisponiblesTable);
			scrollDisponibles.setBounds(33, 45, 655, 327);
            contentPane.add(scrollDisponibles);
            System.out.println("AAAAAAAAAAA");
            repaint();
	        
	        this.repaint();
	 }

}
