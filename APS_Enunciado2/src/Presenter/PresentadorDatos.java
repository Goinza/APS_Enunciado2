package Presenter;

import View.VistaDatosVacunado;

public interface PresentadorDatos
{
    void establecerVista(VistaDatosVacunado vista);

    void inicializarVista();

    void provinciaSeleccionada();

    void actuar();
}
