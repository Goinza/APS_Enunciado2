package View.CargaModificacionVacunados;

import Model.Provincia;
import Model.Vacuna;
import resources.Fecha;

import java.util.Date;
import java.util.List;

public interface VistaDatosVacunado
{
    String obtenerNombre();

    void establecerNombre(String nombre);

    String obtenerApellido();
    
    void establecerApellido(String apellido);
    
    Fecha obtenerFechaDeNacimiento();
    
    void establecerFecha(Fecha fecha);
    
    Vacuna obtenerVacuna();

    void establecerVacuna(String nombre);
    
    Fecha obtenerFechaPrimeraDosis();
    
    void establecerFechaPrimeraDosis(Fecha fecha);

    Fecha obtenerFechaSegundaDosis();

    void establecerFechaSegundaDosis(Fecha fecha);

    int obtenerCantidadDeDosisAplicadas();

    void establecerCantidadDeDosisAplicadas(int dosis);

    String obtenerMail();

    void establecerMail(String mail);

    int obtenerDNI();

    void establecerDNI(int dni);

    Provincia obtenerProvincia();

    void establecerProvincia(String provincia);

    int obtenerRegion();

    void establecerRegion(int region);

    void actualizarVacunas(List<Vacuna> vacunas);

    void actualizarProvincias(List<Provincia> provincias);

    void actualizarRegiones(List<Integer> regionesSanitarias);

    boolean dosisValidas();

    void mostrarAviso(String aviso);

    void mostrarAlerta(String alerta);

    void cerrar();
}
