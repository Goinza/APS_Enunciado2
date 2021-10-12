package Testing;

import Presenter.VacunasAplicadasPresenter;

public class TestVacunasAplicadasAdmin {

	public static void main(String[] args) {
		VacunasAplicadasPresenter presenter = new VacunasAplicadasPresenter(true);
		presenter.renderizarVista();
	}
	
}
