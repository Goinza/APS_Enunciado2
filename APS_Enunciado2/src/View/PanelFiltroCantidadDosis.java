package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import Model.Filtros.FiltroCantidadDosis;
import Model.Filtros.FiltroEdad;
import Presenter.VacunasAplicadasPresenter;

public class PanelFiltroCantidadDosis extends JPanel
{
	private ButtonGroup seleccionDosis = new ButtonGroup();
	private JRadioButton dosDosis;
	private JRadioButton unaDosis;
	private VacunasAplicadasPresenter presenter;

	/**
	 * Create the panel.
	 */
	public PanelFiltroCantidadDosis(VacunasAplicadasPresenter presenter)
	{
		this.setLayout(null);
		this.presenter = presenter;
		
		JLabel lblNewLabel = new JLabel("Seleccione la cantidad de dosis por las que desea filtrar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 37, 397, 33);
		add(lblNewLabel);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(PanelFiltroCantidadDosis.this);
				w.setVisible(false);
			}
		});
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		botonSalir.setBounds(95, 188, 85, 21);
		add(botonSalir);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(unaDosis.isSelected() || dosDosis.isSelected())
				{
					if(unaDosis.isSelected())
					{
						presenter.setFiltro(new FiltroCantidadDosis(1));
					}
					else
					{
						presenter.setFiltro(new FiltroCantidadDosis(2));
					}
					
					cerrarFiltro();
					presenter.renderizarVista();
				}
				else
				{
					presenter.mostrarAlerta("Debe seleccionar la Cantidad de Dosis para este tipo de Filtro");
				}
				
			}
		});
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
	
	private void cerrarFiltro()
	{
		Window w = SwingUtilities.getWindowAncestor(PanelFiltroCantidadDosis.this);
		w.setVisible(false);
	}
}
