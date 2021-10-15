package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Presenter.VacunasAplicadasPresenter;
import View.CargaModificacionVacunados.PanelFecha;

public class PanelFiltroFechaAplicacion extends JPanel {
	private PanelFecha fechaDesde;
	private PanelFecha fechaHasta;
	private VacunasAplicadasPresenter presenter;

	/**
	 * Create the panel.
	 */
	public PanelFiltroFechaAplicacion(VacunasAplicadasPresenter presenter)
	{
		this.setLayout(null);
		
		this.presenter = presenter;
		
		JLabel lblNewLabel = new JLabel("Ingrese rango de edad (en años)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(95, 32, 238, 33);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Desde: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(95, 92, 54, 23);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hasta: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(95, 142, 54, 23);
		add(lblNewLabel_1_1);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(PanelFiltroFechaAplicacion.this);
				w.setVisible(false);
			}
		});
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		botonSalir.setBounds(82, 207, 85, 21);
		add(botonSalir);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConfirmar.setBounds(255, 207, 102, 21);
		add(btnConfirmar);
		
		fechaDesde = new PanelFecha();
		fechaDesde.activar();
		fechaDesde.establecerFechaHoy();
		fechaDesde.setBounds(148, 92, 185, 23);
		add(fechaDesde);
		
		fechaHasta = new PanelFecha();
		fechaHasta.activar();
		fechaHasta.establecerFechaHoy();
		fechaHasta.setBounds(148, 142, 185, 23);
		add(fechaHasta);
		
		this.setPreferredSize(new Dimension(500, 250));
		
	}

}
