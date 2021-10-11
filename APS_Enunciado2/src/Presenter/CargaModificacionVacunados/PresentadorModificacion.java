package Presenter.CargaModificacionVacunados;

import Model.Persona;
import Model.Provincia;
import Model.VacunaAplicada;

public class PresentadorModificacion extends PresentadorVacunadoAbs
{
    VacunaAplicada vacunaAplicada;

    public PresentadorModificacion(VacunaAplicada vacunaAplicada)
    {
        this.vacunaAplicada = vacunaAplicada;
    }

    @Override
    public void inicializarVista()
    {
        super.inicializarVista();
        Persona persona = modeloPersona.obtenerPersona(vacunaAplicada.obtenerDni());
//        Provincia provincia = modeloProvincias.
    }

    @Override
    public void actuar()
    {

    }
}
