package Testing;

import java.sql.Date;

import Model.Filtros.*;
import Presenter.VacunasAplicadasPresenter;

public class TestFiltros {

	public static void main(String[] args) {
		Filtro filtro = new FiltroEdad(50, 70);
		VacunasAplicadasPresenter presenter = new VacunasAplicadasPresenter(false, filtro); 
		presenter.renderizarVista();
	}

}
