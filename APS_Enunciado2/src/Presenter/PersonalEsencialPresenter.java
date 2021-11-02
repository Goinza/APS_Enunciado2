package Presenter;

import java.util.Vector;

import Model.ModeloPersonalEsencial;
import Model.VacunasAplicadasModel;
import Model.Filtros.Filtro;
import View.PersonalEsencialView;


public class PersonalEsencialPresenter {
	
	private ModeloPersonalEsencial modelo;
	private PersonalEsencialView vista;
	
	public PersonalEsencialPresenter()
	{
		modelo = new ModeloPersonalEsencial();
		vista = new PersonalEsencialView();
	}
	
	public PersonalEsencialPresenter(Filtro filtro)
	{
		
	}

	public void renderizarVista()
	{
		vista.setPresenter(this);
		vista.setVisible(true);
		/*Vector<String> columnas = modelo.getNombreColumnas();
		Vector<Vector<Object>> filas = modelo.getFilas();
		
		vista.rellenarTabla(filas, columnas);
		;*/
	}
	
	public void setFiltro(Filtro filtro)
	{
		modelo = new ModeloPersonalEsencial(filtro);
		
	}
}