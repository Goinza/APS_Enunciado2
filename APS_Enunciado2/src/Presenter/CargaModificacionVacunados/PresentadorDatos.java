package Presenter.CargaModificacionVacunados;

import View.CargaModificacionVacunados.VistaDatosVacunado;

public interface PresentadorDatos
{
    void establecerVista(VistaDatosVacunado vista);

    void inicializarVista();

    void provinciaSeleccionada();

    void actuar();
}
