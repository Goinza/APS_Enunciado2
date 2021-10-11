package Testing;

import Presenter.CargaModificacionVacunados.PresentadorCargaVacunadoImpl;
import Presenter.CargaModificacionVacunados.PresentadorDatos;
import View.CargaModificacionVacunados.VentanaCargaDatosVacunado;
import View.CargaModificacionVacunados.VistaDatosVacunado;

public class TestCargaDeVacunados
{
    public static void main(String[] args)
    {
        PresentadorDatos presenter = new PresentadorCargaVacunadoImpl();
        VistaDatosVacunado vista = new VentanaCargaDatosVacunado(presenter);
    }
}
