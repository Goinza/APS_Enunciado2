package View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Model.Filtros.*;
import Presenter.VacunasAplicadasPresenter;
import Presenter.VacunasVencidasPresenter;
import Presenter.CargaModificacionVacunados.PresentadorCargaVacunado;
import Presenter.CargaModificacionVacunados.PresentadorDatos;
import View.CargaModificacionVacunados.VentanaCargaVacunado;
import View.CargaModificacionVacunados.VistaDatosVacunado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class VacunasVencidasView extends JFrame
{
	private JPanel contentPane;
	private JTable vacunasAplicadasTable;
	private JScrollPane scrollAplicadas;
	private boolean esAdmin;
	private VacunasVencidasPresenter presenter;
    //private JComboBox dropDownFiltros;
    private Filtro filtroActual;

	public VacunasVencidasView()
	{
		//esAdmin = admin;
		buildGraphicComponents();
	}
	
	private void buildGraphicComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1147, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("VACUNAS VENCIDAS");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(10, 10, 187, 38);
		contentPane.add(lblTitulo);

		/*dropDownFiltros = new JComboBox();
		String[] opciones_filtros = {"Sin Filtros","Edad","Fecha de Aplicación","Cantidad de Dosis Aplicadas"};
		dropDownFiltros.setModel(new DefaultComboBoxModel(opciones_filtros));
		dropDownFiltros.setBounds(10, 40, 187, 20);
		contentPane.add(dropDownFiltros);
		dropDownFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mostrarFiltro();
			}
		});*/

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 397, 100, 21);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		JButton btnTotalDePersonas = new JButton("Total de Personas con 1\u00B0 dosis Vencida");
		btnTotalDePersonas.setBounds(863, 397, 225, 21);
		contentPane.add(btnTotalDePersonas);
		
		filtroActual = new SinFiltro();
	}
	
	public void setPresenter(VacunasVencidasPresenter presenter) {
		this.presenter = presenter;
	}
	
	public void rellenarTabla(Vector<Vector<Object>> datos, Vector<String> nombreColumnas)
	{
		if (scrollAplicadas != null) {
			contentPane.remove(scrollAplicadas);
		}
		vacunasAplicadasTable = new JTable(datos, nombreColumnas);
		vacunasAplicadasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vacunasAplicadasTable.setBounds(33, 65, 655, 327);
			
		scrollAplicadas = new JScrollPane(vacunasAplicadasTable);
		scrollAplicadas.setBounds(10, 65, 1113, 327);
		contentPane.add(scrollAplicadas);
        contentPane.repaint();	        
	}
	/*
	public void mostrarFiltro()
    {
		switch(dropDownFiltros.getSelectedIndex())
		{
			case 0:
			{
				presenter.setFiltro(new SinFiltro());
				presenter.renderizarVista();
				break;
			}
			case 1:
			{
				JOptionPane.showOptionDialog(this,filtroEdad(), "Por favor, seleccione el Filtro que desea aplicar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
				break;
			}
			case 2:
			{
				JOptionPane.showOptionDialog(this, filtroFechaAplicacion(), "Por favor, seleccione el Filtro que desea aplicar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
				break;
			}
			case 3:
			{
				JOptionPane.showOptionDialog(this, filtroCantidadDosis(), "Por favor, seleccione el Filtro que desea aplicar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
				break;
			}
		}
    }
	
	private JPanel filtroEdad()
	{
		return new PanelFiltroEdad(presenter);
	}
	
	private JPanel filtroFechaAplicacion()
	{
		return new PanelFiltroFechaAplicacion(presenter);
	}
	
	private JPanel filtroCantidadDosis()
	{
		return new PanelFiltroCantidadDosis(presenter);
	}
	*/
	
	public void mostrarAlerta(String msg)
	{
		JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.ERROR_MESSAGE);
	}

}
