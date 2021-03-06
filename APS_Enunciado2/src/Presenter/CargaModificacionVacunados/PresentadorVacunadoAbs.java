package Presenter.CargaModificacionVacunados;

import Excepciones.*;
import Model.*;
import Presenter.VacunasAplicadasPresenter;
import View.CargaModificacionVacunados.VistaDatosVacunado;
import resources.Fecha;

import java.util.Date;
import java.util.List;

public abstract class PresentadorVacunadoAbs implements PresentadorDatos
{
    protected VistaDatosVacunado vista;
    protected ModeloPersonas modeloPersona;
    protected ModeloProvincias modeloProvincias;
    protected ModeloCargos modeloCargos;
    protected ModeloVacunas modeloVacunas;
    protected VacunasAplicadasModel modeloVacunasAplicadas;
    protected VacunasAplicadasPresenter presentadorVacunasAplicadas;
    
    protected PresentadorVacunadoAbs(VacunasAplicadasPresenter presentadorVacunasAplicadas) {
    	modeloPersona = new ModeloPersonaImpl();
        modeloCargos = new ModeloCargosImpl();
    	modeloProvincias = new ModeloProvinciasImpl();
    	modeloVacunas = new ModeloVacunasImpl();
    	modeloVacunasAplicadas = new VacunasAplicadasModel();
    	this.presentadorVacunasAplicadas = presentadorVacunasAplicadas;
    }

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
        if (vista.obtenerDNI() == -1)
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

    private void validarCargo() throws CargoNoValidoException
    {
        Cargo cargo = vista.obtenerCargo();
        if (cargo == null)
            throw new CargoNoValidoException();
    }

    private void validarFechaNacimiento() throws FechaDeNacimientoNoValidaException
    {
    	Fecha fecha = vista.obtenerFechaDeNacimiento();
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
        if (vista.obtenerRegion() == -1)
            throw new RegionSanitariaNoValidaException();
    }

    protected void validarDatos() throws RegionSanitariaNoValidaException, ProvinciaNoValidException, DosisNoValidasException,
            VacunaNoValidaException, MailNoValidoException, FechaDeNacimientoNoValidaException,
            DNINoValidoException, ApellidoNoValidoException, NombreNoValidoException, CargoNoValidoException
    {
        validarNombre();
        validarApellido();
        validarDNI();
        validarMail();
        validarCargo();
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
        List<Cargo> cargos = modeloCargos.obtenerCargos();
        vista.actualizarCargos(cargos);
    }

    @Override
    public void provinciaSeleccionada()
    {
        Provincia provincia = vista.obtenerProvincia();
        if (provincia!=null) {
            vista.actualizarRegiones(modeloProvincias.obtenerRegiones(provincia));
            vista.actualizarVacunas(modeloVacunas.obtenerVacunasPorProvincia(provincia));	
        }
    }
    
    protected void fin()
    {
        vista.mostrarAviso("La entrada ha sido modificada exitosamente");
        vista.cerrar();
    	presentadorVacunasAplicadas.renderizarVista();
    }
}
