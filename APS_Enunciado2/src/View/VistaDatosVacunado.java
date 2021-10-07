package View;

import java.util.Date;

public interface VistaDatosVacunado
{
    String obtenerNombre();

    void establecerNombre(String nombre);

    String obtenerApellido();
    
    void establecerApellido(String apellido);
    
    Date obtenerFechaDeNacimiento();
    
    void establecerFecha(Date fecha);
    
    String obtenerVacuna();

    void establecerNombreVacuna(String nombre);
    
    Date obtenerFechaPrimeraDosis();
    
    void establecerFechaPrimeraDosis(Date fecha);

    Date obtenerFechaSegundaDosis();

    void establecerFechaSegundaDosis(Date fecha);

    int obtenerCantidadDeDosisAplicadas();

    void establecerCantidadDeDosisAplicadas(int dosis);

    String obtenerMail();

    void establecerMail(String mail);

    String obtenerDNI();

    void establecerDNI(String dni);

    String obtenerProvincia();

    void establecerProvincia(String provincia);

    String obtenerRegionSanitaria();

    void establecerRegionSanitaria(String region);

    void actualizarVacunas(String[] vacunas);

    void actualizarProvincias(String[] provincias);

    void actualizarRegionesSanitarias(String[] regionesSanitarias);

    boolean dosisValidas();

    void mostrarAviso(String aviso);

    void mostrarAlerta(String alerta);
}
