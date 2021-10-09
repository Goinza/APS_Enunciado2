package Presenter;

import Excepciones.*;
import Model.*;
import Model.Provincia;
import View.VistaDatosVacunado;

import java.util.Date;
import java.util.List;

public abstract class PresentadorVacunadoAbs implements PresentadorDatos
{
    protected VistaDatosVacunado vista;
    protected ModeloPersonas modeloPersona;
    protected ModeloProvincias modeloProvincias;
    protected ModeloVacunas modeloVacunas;
    protected ModeloRegiones modeloRegiones;

    private void validarNombre() throws NombreNoValidoException
    {
        String nombre = vista.obtenerNombre();
        boolean valido = !nombre.isBlank() && Character.isAlphabetic(nombre.charAt(0));
        if (!valido)
            throw new NombreNoValidoException();
    }

    private void validarApellido() throws ApellidoNoValidoException
    {
        String apellido = vista.obtenerApellido();
        boolean valido = !apellido.isBlank() && Character.isAlphabetic(apellido.charAt(0));
        if (!valido)
            throw new ApellidoNoValidoException();
    }

    private void validarDNI() throws DNINoValidoException
    {
        String dni = vista.obtenerDNI();
        boolean valido = !dni.isBlank();
        for (int i = 0; i < dni.length() && valido; i++)
            valido = valido && Character.isDigit(dni.charAt(i));
        if (!valido)
            throw new DNINoValidoException();
    }

    private void validarMail() throws MailNoValidoException
    {
        String mail = vista.obtenerMail();
        if (mail.isBlank())
            throw new MailNoValidoException();
        int atIndex = mail.indexOf('@');
        if (atIndex > 0)
        {
            int dotIndex = mail.indexOf('.', atIndex);
            if (dotIndex < atIndex)
                throw new MailNoValidoException();
        }
        else
            throw new MailNoValidoException();
    }

    private void validarFechaNacimiento() throws FechaDeNacimientoNoValidaException
    {
        Date fecha = vista.obtenerFechaDeNacimiento();
        if (fecha == null)
            throw new FechaDeNacimientoNoValidaException();
    }

    private void validarVacuna() throws VacunaNoValidaException
    {
        if (vista.obtenerVacuna() == null)
            throw new VacunaNoValidaException();
    }

    private void validarDosis() throws DosisNoValidasException
    {
        if (!vista.dosisValidas())
            throw new DosisNoValidasException();
    }

    private void validarProvincia() throws ProvinciaNoValidException
    {
        if (vista.obtenerProvincia() == null)
            throw new ProvinciaNoValidException();
    }

    private void validarRegion() throws RegionSanitariaNoValidaException
    {
        if (vista.obtenerRegion() == null)
            throw new RegionSanitariaNoValidaException();
    }

    protected void validarDatos() throws RegionSanitariaNoValidaException, ProvinciaNoValidException, DosisNoValidasException,
            VacunaNoValidaException, MailNoValidoException, FechaDeNacimientoNoValidaException,
                        DNINoValidoException, ApellidoNoValidoException, NombreNoValidoException
    {
        validarNombre();
        validarApellido();
        validarDNI();
        validarMail();
        validarFechaNacimiento();
        validarVacuna();
        validarDosis();
        validarProvincia();
        validarRegion();
    }

    @Override
    public void establecerVista(VistaDatosVacunado vista)
    {
        this.vista = vista;
    }

    @Override
    public void inicializarVista()
    {
        List<Provincia> provincias = modeloProvincias.obtenerProvincias();
        vista.actualizarProvincias(provincias);
    }

    @Override
    public void provinciaSeleccionada()
    {
        Provincia provincia = vista.obtenerProvincia();
        vista.actualizarRegiones(modeloRegiones.obtenerRegiones(provincia));
        vista.actualizarVacunas(modeloVacunas.obtenerVacunasPorProvincia(provincia));
    }
}
