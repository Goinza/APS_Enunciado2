package Testing;

import Presenter.PresentadorCargaVacunado;
import Presenter.PresentadorCargaVacunadoImpl;
import View.VentanaCargaDatosVacunado;
import View.VistaDatosVacunado;

public class TestCargaDeVacunados
{
    public static void main(String[] args)
    {
        PresentadorCargaVacunado presenter = new PresentadorCargaVacunadoImpl();
        VistaDatosVacunado vista = new VentanaCargaDatosVacunado(presenter);
    }
}
