package View;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

//import quick.dbtable.DBTable;

public class VacunasAplicadasView extends JFrame {

	private JPanel contentPane;
	private JTable vacunasAplicadasTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacunasAplicadasView frame = new VacunasAplicadasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VacunasAplicadasView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 477);
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
		contentPane.add(btnVolver);
		
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
		
		vacunasAplicadasTable = new JTable();
		vacunasAplicadasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vacunasAplicadasTable.setBounds(10, 46, 655, 327);
		contentPane.add(vacunasAplicadasTable);
	}
}
