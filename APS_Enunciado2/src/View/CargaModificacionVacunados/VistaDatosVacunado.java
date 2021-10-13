package View.CargaModificacionVacunados;

import Model.Provincia;
import Model.Vacuna;

import java.util.Date;
import java.util.List;

public interface VistaDatosVacunado
{
    String obtenerNombre();

    void establecerNombre(String nombre);

    String obtenerApellido();
    
    void establecerApellido(String apellido);
    
    Date obtenerFechaDeNacimiento();
    
    void establecerFecha(Date fecha);
    
    Vacuna obtenerVacuna();

    void establecerVacuna(String nombre);
    
    Date obtenerFechaPrimeraDosis();
    
    void establecerFechaPrimeraDosis(Date fecha);

    Date obtenerFechaSegundaDosis();

    void establecerFechaSegundaDosis(Date fecha);

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
