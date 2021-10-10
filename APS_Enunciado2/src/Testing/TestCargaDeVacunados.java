package Testing;

import Presenter.PresentadorCargaVacunadoImpl;
import Presenter.PresentadorDatos;
import View.VentanaCargaDatosVacunado;
import View.VistaDatosVacunado;

public class TestCargaDeVacunados
{
    public static void main(String[] args)
    {
        PresentadorDatos presenter = new PresentadorCargaVacunadoImpl();
        VistaDatosVacunado vista = new VentanaCargaDatosVacunado(presenter);
    }
}
