package Testing;

import java.sql.Date;

import Model.Filtro;
import Model.FiltroCantidadDosis;
import Model.FiltroFechaAplicacion;
import Model.SinFiltro;
import Presenter.VacunasAplicadasPresenter;

public class TestFiltros {

	public static void main(String[] args) {
		Filtro filtro = new FiltroFechaAplicacion(new Date(2021, 7, 2), new Date(2021, 9, 1));
		VacunasAplicadasPresenter presenter = new VacunasAplicadasPresenter(false, filtro); 
		presenter.renderizarVista();

	}

}
