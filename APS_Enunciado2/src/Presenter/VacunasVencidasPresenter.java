package Presenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Model.*;
import Model.Filtros.Filtro;
import Model.Filtros.SinFiltro;
import Presenter.CargaModificacionVacunados.PresentadorModificacion;
import View.CargaModificacionVacunados.VentanaModificacionVacunado;
import resources.Fecha;
import View.VacunasAplicadasView;
import View.VacunasVencidasView;

public class VacunasVencidasPresenter {
		private VacunasVencidasModel modelo;
		private VacunasVencidasView vista;
		private int cant_vencidas;
		
		public VacunasVencidasPresenter()
		{
			modelo = new VacunasVencidasModel();
			vista = new VacunasVencidasView();
			cant_vencidas = 0;
		}
		
		/*public VacunasVencidasPresenter(boolean admin_logueado, Filtro filtro) {
			modelo = new VacunasVencidasModel();
			vista = new VacunasVencidasView(admin_logueado);
		}*/
			
		public void renderizarVista()
		{
			vista.setPresenter(this);
			Vector<String> columnas = modelo.getNombreColumnas();
			Vector<Vector<Object>> filas = modelo.getFilas();
			
			for(Vector<Object> fila : filas)
			{
				if(fila.get(11).equals("Si"))
				{
					cant_vencidas++;
				}
				
			}
			
			vista.rellenarTabla(filas, columnas,cant_vencidas);
			vista.setVisible(true);
		}
		
		
		public void setFiltro(Filtro filtro)
		{
			modelo = new VacunasVencidasModel();
			
		}
		
		public void mostrarAlerta(String msg)
		{
			vista.mostrarAlerta(msg);
		}
		

}
