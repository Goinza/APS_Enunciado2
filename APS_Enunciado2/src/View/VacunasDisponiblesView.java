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

public class VacunasDisponiblesView extends JFrame {

	private JPanel contentPane;
	private JTable vacunasDisponiblesTable;

	public VacunasDisponiblesView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("VACUNAS DISPONIBLES");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(10, 10, 196, 38);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 397, 85, 21);
		contentPane.add(btnVolver);
		
		vacunasDisponiblesTable = new JTable();
		vacunasDisponiblesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vacunasDisponiblesTable.setBounds(10, 46, 655, 327);
		contentPane.add(vacunasDisponiblesTable);
	}

}
