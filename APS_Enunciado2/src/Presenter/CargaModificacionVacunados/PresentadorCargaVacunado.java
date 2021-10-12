package Presenter.CargaModificacionVacunados;

import Excepciones.*;
import Model.Persona;
import Model.Provincia;
import Model.Vacuna;
import Model.VacunaAplicada;
import Presenter.VacunasAplicadasPresenter;

import java.util.Date;

public class PresentadorCargaVacunado extends PresentadorVacunadoAbs
{
	
	public PresentadorCargaVacunado(VacunasAplicadasPresenter presenterVacunasAplicadas) {
		super(presenterVacunasAplicadas);
	}
	
    @Override
    public void actuar()
    {
        try
        {
            validarDatos();

            int dni = Integer.valueOf(vista.obtenerDNI());
            if(modeloPersona.obtenerPersona(dni) != null)
                throw new PersonaYaAlmacenadaException();
            else
            {
                Persona persona = new Persona(dni, vista.obtenerNombre(), vista.obtenerApellido(), vista.obtenerMail(), vista.obtenerFechaDeNacimiento());

                modeloPersona.almacenarPersona(persona);

                Vacuna vacuna = vista.obtenerVacuna();
                Provincia provincia = vista.obtenerProvincia();
                int region = vista.obtenerRegion();
                Date primeraDosis = vista.obtenerFechaPrimeraDosis();
                Date segundaDosis = vista.obtenerFechaSegundaDosis();

                VacunaAplicada vacunaAplicada = new VacunaAplicada(primeraDosis, segundaDosis, segundaDosis == null? 1 : 2, persona.obtenerDni(), vacuna.obtenerId(), provincia.obtenerId(), region);
                
                modeloVacunasAplicadas.agregarVacunaAplicada(vacunaAplicada);
                
                vista.mostrarAviso("La nueva entrada ha sido almacenada exitosamente");
                vista.cerrar();
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
            vista.mostrarAlerta("Imposible guardar: ya se registra una instancia de vacunación para el ciudadano ingresado.");
        }
    }
}