package Presenter;

import java.util.Vector;

import Model.Filtro;
import Model.SinFiltro;
import Model.VacunasAplicadasModel;
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
		Vector<String> columnas = modelo.getNombreColumnas();
		Vector<Vector<Object>> filas = modelo.getFilas();
		
		vista.rellenarTabla(filas, columnas);
		vista.setVisible(true);
	}

}
