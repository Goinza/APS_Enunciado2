package Presenter.CargaModificacionVacunados;

import Excepciones.*;
import Model.Persona;
import Model.Provincia;
import Model.Vacuna;
import Model.VacunaAplicada;
import Presenter.VacunasAplicadasPresenter;
import resources.Fecha;

public class PresentadorModificacion extends PresentadorVacunadoAbs
{
    VacunaAplicada vacunaAplicada;

    public PresentadorModificacion(VacunaAplicada vacunaAplicada, VacunasAplicadasPresenter presenterVacunasAplicadas)
    {
    	super(presenterVacunasAplicadas);
        this.vacunaAplicada = vacunaAplicada;
    }

    @Override
    public void inicializarVista()
    {
        super.inicializarVista();
        Persona persona = vacunaAplicada.obtenerPersona();

        int region = vacunaAplicada.obtenerRegion();
        Fecha primeraDosis = vacunaAplicada.obtenerPrimeraDosis();
        Fecha segundaDosis = vacunaAplicada.obtenerSegundaDosis();

        vista.establecerNombre(persona.obtenerNombre());
        vista.establecerApellido(persona.obtenerApellido());
        vista.establecerDNI(persona.obtenerDni());
        vista.establecerMail(vacunaAplicada.obtenerPersona().obtenerMail());
        vista.establecerCargo(vacunaAplicada.obtenerPersona().obtenerCargo().obtenerNombreCargo());
        vista.establecerFecha(persona.obtenerFechaNacimiento());
        vista.establecerProvincia(vacunaAplicada.obtenerProvincia().obtenerNombre());
        provinciaSeleccionada();
        vista.establecerRegion(region);
        vista.establecerVacuna(vacunaAplicada.obtenerVacuna().obtenerNombre());
        vista.establecerFechaPrimeraDosis(primeraDosis);
        vista.deshabilitarCampoDNI();
        if(segundaDosis != null)
            vista.establecerFechaSegundaDosis(segundaDosis);
    }

    @Override
    public void actuar()
    {
        try
        {
            validarDatos();

            Persona persona = new Persona(vista.obtenerDNI(), vista.obtenerNombre(), vista.obtenerApellido(), vista.obtenerMail(), vista.obtenerCargo(), vista.obtenerFechaDeNacimiento());

            modeloPersona.actualizarPersona(vacunaAplicada.obtenerPersona().obtenerDni(), persona);

            Vacuna vacuna = vista.obtenerVacuna();
            Provincia provincia = vista.obtenerProvincia();
            int region = vista.obtenerRegion();
            Fecha primeraDosis = vista.obtenerFechaPrimeraDosis();
            Fecha segundaDosis = vista.obtenerFechaSegundaDosis();

            VacunaAplicada nuevaVacunaAplicada = new VacunaAplicada(persona, vacuna,(segundaDosis == null? 1 : 2), primeraDosis, segundaDosis, provincia, region);

            modeloVacunasAplicadas.actualizarVacunaAplicada(nuevaVacunaAplicada,vacunaAplicada.obtenerPersona().obtenerDni());
            fin();
        } catch (NombreNoValidoException e) {
            vista.mostrarAlerta("El campo Nombre se encuentra vac??o o presenta un formato no v??lido.");
        } catch (MailNoValidoException e) {
            vista.mostrarAlerta("El campo Mail se encuentra vac??o o presenta un formato no v??lido.");
        } catch (FechaDeNacimientoNoValidaException e) {
            vista.mostrarAlerta("No se ha seleccionado una Fecha de Nacimiento, o se lo ha hecho parcialmente.");
        } catch (ApellidoNoValidoException e) {
            vista.mostrarAlerta("El campo Apellido se encuentra vac??o o presenta un formato no v??lido.");
        } catch (RegionSanitariaNoValidaException e) {
            vista.mostrarAlerta("Seleccione una regi??n sanitaria.");
        } catch (DosisNoValidasException e) {
            vista.mostrarAlerta("Debe seleccionar una fecha de primera dosis. De seleccionar una para la segunda, aseg??rese de haber ingresado todos los campos y que la fecha resultante sea posterior a la primera.");
        } catch (VacunaNoValidaException e) {
            vista.mostrarAlerta("Seleccione una Vacuna.");
        } catch (ProvinciaNoValidException e) {
            vista.mostrarAlerta("Seleccione una Provincia.");
        } catch (DNINoValidoException e) {
            vista.mostrarAlerta("El campo DNI se encuentra vac??o o presenta un formato no v??lido.");
        } catch (CargoNoValidoException e) {
            vista.mostrarAlerta("Ingrese un cargo.");
        }
    }
}

