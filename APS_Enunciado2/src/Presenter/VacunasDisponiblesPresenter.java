package Presenter;


import java.util.Vector;

import Model.VacunasDisponiblesModel;
import View.VacunasDisponiblesView;

public class VacunasDisponiblesPresenter 
{
	private VacunasDisponiblesModel modelo;
	private VacunasDisponiblesView vista;
	
	public VacunasDisponiblesPresenter()
	{
		modelo = new VacunasDisponiblesModel();
		vista = new VacunasDisponiblesView();
	}
		
	public void renderizarVista()
	{
		Vector<String> columnas = modelo.getNombreColumnas();
		Vector<Vector<Object>> filas = modelo.getFilas();
		
		vista.rellenarTabla(filas, columnas);
		vista.setVisible(true);
	}

}
