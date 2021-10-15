package View;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FiltroEdad extends JPanel {
	private JTextField desde;
	private JTextField hasta;

	/**
	 * Create the panel.
	 */
	public FiltroEdad()
	{	
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese rango de edad (en a\u00F1os)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(95, 32, 238, 33);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Desde: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(95, 106, 54, 23);
		add(lblNewLabel_1);
		
		desde = new JTextField();
		desde.setBounds(144, 103, 54, 33);
		add(desde);
		desde.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hasta: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(225, 106, 54, 23);
		add(lblNewLabel_1_1);
		
		hasta = new JTextField();
		hasta.setColumns(10);
		hasta.setBounds(279, 103, 54, 33);
		add(hasta);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(FiltroEdad.this);
				w.setVisible(false);
			}
		});
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		botonSalir.setBounds(95, 188, 85, 21);
		add(botonSalir);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConfirmar.setBounds(225, 188, 102, 21);
		add(btnConfirmar);
		
		this.setPreferredSize(new Dimension(500, 250));
	}
}
