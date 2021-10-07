package Presenter;

import Excepciones.*;
import View.VistaDatosVacunado;

public class PresentadorCargaVacunadoImpl extends PresentadorVacunadoAbs implements PresentadorCargaVacunado
{
    @Override
    public void guardar()
    {
        try
        {
            validarDatos();

            // TODO: 5/10/2021 Verificar que el usuario no prexista.

            // TODO: 5/10/2021 Almacenar la nueva entrada en la base de datos.

            vista.mostrarAviso("La nueva entrada ha sido almacenada exitosamente");
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
        }
    }
}
