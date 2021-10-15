package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class FiltroCantidadDosis extends JPanel
{
	ButtonGroup seleccionDosis = new ButtonGroup();
	JRadioButton dosDosis;
	JRadioButton unaDosis;

	/**
	 * Create the panel.
	 */
	public FiltroCantidadDosis()
	{
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la cantidad de dosis por las que desea filtrar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 37, 397, 33);
		add(lblNewLabel);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(FiltroCantidadDosis.this);
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

		unaDosis = new JRadioButton("1");
		unaDosis.setFont(new Font("Tahoma", Font.BOLD, 14));
		unaDosis.setBounds(118, 117, 46, 21);
		add(unaDosis);
		
		dosDosis = new JRadioButton("2");
		dosDosis.setFont(new Font("Tahoma", Font.BOLD, 14));
		dosDosis.setBounds(246, 117, 46, 21);
		add(dosDosis);
		
		seleccionDosis.add(unaDosis);
		seleccionDosis.add(dosDosis);
		
		this.setPreferredSize(new Dimension(500, 250));
		
	}
}
