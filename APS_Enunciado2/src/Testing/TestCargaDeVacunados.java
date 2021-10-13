package Testing;

import Presenter.CargaModificacionVacunados.PresentadorCargaVacunado;
import Presenter.CargaModificacionVacunados.PresentadorDatos;
import View.CargaModificacionVacunados.VentanaCargaVacunado;
import View.CargaModificacionVacunados.VistaDatosVacunado;

import java.util.Date;

public class TestCargaDeVacunados
{
    public static void main(String[] args)
    {
        //PresentadorDatos presenter = new PresentadorCargaVacunado();
        //VistaDatosVacunado vista = new VentanaCargaVacunado(presenter);

        Date date = new Date();
        date.setYear(2020);
        date.setYear(1954);
        System.out.println(date.getYear());
    }
}
