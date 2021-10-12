package Presenter;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JTable;

import Model.VacunaAplicada;
import Model.VacunasAplicadasModel;
import Model.Filtros.Filtro;
import Model.Filtros.SinFiltro;
import View.VacunasAplicadasView;

public class VacunasAplicadasPresenter
{
	private VacunasAplicadasModel modelo;
	private VacunasAplicadasView vista;
	
	public VacunasAplicadasPresenter(boolean admin_logueado)
	{
		modelo = new VacunasAplicadasModel(new SinFiltro());
		vista = new VacunasAplicadasView(admin_logueado);
	}
	
	public VacunasAplicadasPresenter(boolean admin_logueado, Filtro filtro) {
		modelo = new VacunasAplicadasModel(filtro);
		vista = new VacunasAplicadasView(admin_logueado);
	}
		
	public void renderizarVista()
	{
		vista.setPresenter(this);
		Vector<String> columnas = modelo.getNombreColumnas();
		Vector<Vector<Object>> filas = modelo.getFilas();
		
		vista.rellenarTabla(filas, columnas);
		vista.setVisible(true);
	}
	
	public void crearVistaModificacionVacunas(JTable vacunasAplicadasTable) {
		/*int selectedRow = vacunasAplicadasTable.getSelectedRow();
		int columnCount = vacunasAplicadasTable.getColumnCount();

		String nombre, apellido;
		
		try {
		}
		catch (ParseException e) {
			e.printStackTrace();
		}		*/
	}

}
