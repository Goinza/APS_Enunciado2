package Presenter.CargaModificacionVacunados;

import Excepciones.*;
import Model.Persona;
import Model.Provincia;
import Model.Vacuna;
import Model.VacunaAplicada;
import Presenter.VacunasAplicadasPresenter;

import java.util.Date;

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
        Date primeraDosis = vacunaAplicada.obtenerPrimeraDosis();
        Date segundaDosis = vacunaAplicada.obtenerSegundaDosis();

        vista.establecerProvincia(vacunaAplicada.obtenerProvincia().obtenerNombre());
        vista.establecerNombre(persona.obtenerNombre());
        vista.establecerApellido(persona.obtenerApellido());
        vista.establecerDNI(persona.obtenerDni());
        vista.establecerFecha(persona.obtenerFechaNacimiento());
        vista.establecerMail(vacunaAplicada.obtenerProvincia().obtenerNombre());
        vista.establecerRegion(region);
        vista.establecerVacuna(vacunaAplicada.obtenerVacuna().obtenerNombre());
        vista.establecerFechaPrimeraDosis(primeraDosis);
        if(segundaDosis != null)
            vista.establecerFechaSegundaDosis(segundaDosis);
    }

    @Override
    public void actuar()
    {
        try
        {
            validarDatos();
            int dni = Integer.valueOf(vista.obtenerDNI());
            if (dni != vacunaAplicada.obtenerPersona().obtenerDni() && modeloPersona.obtenerPersona(dni) != null)
                throw new PersonaYaAlmacenadaException();
            else
            {
                Persona persona = new Persona(dni, vista.obtenerNombre(), vista.obtenerApellido(), vista.obtenerMail(), vista.obtenerFechaDeNacimiento());


                modeloPersona.actualizarPersona(vacunaAplicada.obtenerPersona().obtenerDni(), persona);

                Vacuna vacuna = vista.obtenerVacuna();
                Provincia provincia = vista.obtenerProvincia();
                int region = vista.obtenerRegion();
                Date primeraDosis = vista.obtenerFechaPrimeraDosis();
                Date segundaDosis = vista.obtenerFechaSegundaDosis();

                VacunaAplicada vacunaAplicada = new VacunaAplicada(persona, vacuna, primeraDosis, segundaDosis, provincia, region);

//                modeloVacunasAplicadas.actualizarVacunaAplicada(vacunaAplicada);
                fin();
            }
        } catch (NombreNoValidoException e) {
            vista.mostrarAlerta("El campo Nombre se encuentra vacío o presenta un formato no válido.");
        } catch (MailNoValidoException e) {
            vista.mostrarAlerta("El campo Mail se encuentra vacío o presenta un formato no válido.");
        } catch (FechaDeNacimientoNoValidaException e) {
            vista.mostrarAlerta("No se ha seleccionado una Fecha de Nacimiento, o se lo ha hecho parcialmente.");
        } catch (ApellidoNoValidoException e) {
            vista.mostrarAlerta("El campo Apellido se encuentra vacío o presenta un formato no válido.");
        } catch (RegionSanitariaNoValidaException e) {
            vista.mostrarAlerta("Seleccione una región sanitaria.");
        } catch (DosisNoValidasException e) {
            vista.mostrarAlerta("Debe seleccionar una fecha de primera dosis. De seleccionar una para la segunda, asegúrese de haber ingresado todos los campos y que la fecha resultante sea posterior a la primera.");
        } catch (VacunaNoValidaException e) {
            vista.mostrarAlerta("Seleccione una Vacuna.");
        } catch (ProvinciaNoValidException e) {
            vista.mostrarAlerta("Seleccione una Provincia.");
        } catch (DNINoValidoException e) {
            vista.mostrarAlerta("El campo DNI se encuentra vacío o presenta un formato no válido.");
        } catch (PersonaYaAlmacenadaException e) {
            vista.mostrarAlerta("Imposible guardar: ya se registra una instancia de vacunación para el documento ingresado.");
        }
    }
}

