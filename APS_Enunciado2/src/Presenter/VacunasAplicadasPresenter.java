package Presenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JTable;

import Model.*;
import Model.Filtros.Filtro;
import Model.Filtros.SinFiltro;
import Presenter.CargaModificacionVacunados.PresentadorModificacion;
import View.CargaModificacionVacunados.VentanaModificacionVacunado;
import resources.Fecha;
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

		int selectedRow = vacunasAplicadasTable.getSelectedRow();

		String nombre = vacunasAplicadasTable.getValueAt(selectedRow, 0).toString(); // Nombre
		String apellido = vacunasAplicadasTable.getValueAt(selectedRow, 1).toString(); // Apellido
		Fecha fecha = new Fecha(vacunasAplicadasTable.getValueAt(selectedRow, 2).toString()); // Fecha de nacimiento?
		String nombreVacuna = vacunasAplicadasTable.getValueAt(selectedRow, 3).toString(); // Nombre de vacuna
		Fecha primeraDosis = new Fecha(vacunasAplicadasTable.getValueAt(selectedRow, 4).toString());
		Fecha segundaDosis = new Fecha(vacunasAplicadasTable.getValueAt(selectedRow, 5).toString());
		System.out.println(vacunasAplicadasTable.getValueAt(selectedRow, 6).toString());
		int cantidadDosis = Integer.valueOf(vacunasAplicadasTable.getValueAt(selectedRow, 6).toString()) ;
		String mail = vacunasAplicadasTable.getValueAt(selectedRow, 7).toString();
		int dni = Integer.valueOf(vacunasAplicadasTable.getValueAt(selectedRow, 8).toString());
		String provincia = vacunasAplicadasTable.getValueAt(selectedRow, 9).toString();
		int region = Integer.valueOf(vacunasAplicadasTable.getValueAt(selectedRow, 10).toString());

		Persona persona = new Persona(dni, nombre, apellido, mail, fecha);
		VacunaAplicada vacunaAplicada = new VacunaAplicada(persona, new Vacuna(nombreVacuna), primeraDosis, segundaDosis, new Provincia(provincia), region);

		PresentadorModificacion presentador = new PresentadorModificacion(vacunaAplicada, this);
		VentanaModificacionVacunado vista = new VentanaModificacionVacunado(presentador);
	}

}
