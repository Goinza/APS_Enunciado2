package View;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Model.Filtros.FiltroEdad;
import Presenter.VacunasAplicadasPresenter;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelFiltroEdad extends JPanel {
	private JTextField desde;
	private JTextField hasta;
	private VacunasAplicadasPresenter presenter;

	/**
	 * Create the panel.
	 */
	public PanelFiltroEdad(VacunasAplicadasPresenter presenter)
	{	
		this.setLayout(null);
		
		this.presenter = presenter;
		
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
				cerrarFiltro();
			}
		});
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		botonSalir.setBounds(95, 188, 85, 21);
		add(botonSalir);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str_desde = desde.getText();
				String str_hasta = hasta.getText();
				
				if(str_desde.length() != 0 && str_hasta.length() != 0)
				{
					int edad_desde = Integer.valueOf(str_desde);
					int edad_hasta = Integer.valueOf(str_hasta);
					
					boolean letras_en_desde = str_desde.matches("[a-zA-Z]+");
					boolean letras_en_hasta = str_hasta.matches("[a-zA-Z]+");
					boolean desde_negativo = edad_desde <= 0;
					boolean hasta_negativo = edad_hasta <= 0;
					
					if(desde_negativo || hasta_negativo)
					{
						presenter.mostrarAlerta("La Edad no puede ser un número negativo");
					}
					else
					{
						if(letras_en_desde || letras_en_hasta)
						{
							presenter.mostrarAlerta("La Edad solo puede ser un número entero");
						}
						else
						{
							if(edad_hasta < edad_desde)
							{
								presenter.mostrarAlerta("El límite inferior debe ser menor o igual al límite superior para el rango de Edad en este Filtro");
							}
							else
							{
								presenter.setFiltro(new FiltroEdad(edad_desde,edad_hasta));
								cerrarFiltro();
								presenter.renderizarVista();
							}
						}
					}
				}				
				else
				{
					presenter.mostrarAlerta("Debe ingresar ambos límites de Edad para este tipo de Filtro");
				}
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConfirmar.setBounds(225, 188, 102, 21);
		add(btnConfirmar);
		
		this.setPreferredSize(new Dimension(500, 250));
	}
	
	private void cerrarFiltro()
	{
		Window w = SwingUtilities.getWindowAncestor(PanelFiltroEdad.this);
		w.setVisible(false);
	}
}
