package Presenter;

import View.VistaDatosVacunado;

public class PresentadorModificacionVacunadoImpl extends PresentadorVacunadoAbs implements PresentadorModificacionVacunado
{
    private int dniGuardado;

    public PresentadorModificacionVacunadoImpl(int dni)
    {
        dniGuardado = dni;
    }

    @Override
    public void actualizar()
    {
    }
}
