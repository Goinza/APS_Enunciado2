package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonalEsencialView extends JFrame {

	private JPanel contentPane;

	public PersonalEsencialView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Personal Esencial Vacunado");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(22, 22, 232, 35);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Window w = SwingUtilities.getWindowAncestor(PersonalEsencialView.this);
					w.setVisible(false);
				
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolver.setBounds(20, 211, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnProvincia = new JButton("Provincia");
		btnProvincia.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnProvincia.setBounds(66, 107, 106, 42);
		contentPane.add(btnProvincia);
		
		JButton btnPais = new JButton("Pa\u00EDs");
		btnPais.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPais.setBounds(250, 108, 99, 41);
		contentPane.add(btnPais);
	}
}
