package Presenter;

import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;

import Model.*;
import Model.Filtros.Filtro;
import Model.Filtros.SinFiltro;
import Presenter.CargaModificacionVacunados.PresentadorModificacion;
import View.CargaModificacionVacunados.VentanaModificacionVacunado;
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
		Date fecha = (Date) vacunasAplicadasTable.getValueAt(selectedRow, 2); // Fecha de nacimiento?
		String nombreVacuna = vacunasAplicadasTable.getValueAt(selectedRow, 3).toString(); // Nombre de vacuna
		Date primeraDosis = (Date) vacunasAplicadasTable.getValueAt(selectedRow, 4);
		Date segundaDosis = (Date) vacunasAplicadasTable.getValueAt(selectedRow, 5);
		int cantidadDosis = (int) vacunasAplicadasTable.getValueAt(selectedRow, 6);
		String mail = vacunasAplicadasTable.getValueAt(selectedRow, 7).toString();
		int dni = (int)vacunasAplicadasTable.getValueAt(selectedRow, 8);
		String provincia = vacunasAplicadasTable.getValueAt(selectedRow, 9).toString();
		int region = (int) vacunasAplicadasTable.getValueAt(selectedRow, 10);

		Persona persona = new Persona(dni, nombre, apellido, mail, fecha);
		VacunaAplicada vacunaAplicada = new VacunaAplicada(persona, new Vacuna(nombreVacuna), primeraDosis, segundaDosis, new Provincia(provincia), region);

		PresentadorModificacion presentador = new PresentadorModificacion(vacunaAplicada, this);
		VentanaModificacionVacunado vista = new VentanaModificacionVacunado(presentador);
	}

}
