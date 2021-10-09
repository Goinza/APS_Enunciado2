package Model;

public interface ModeloPersonas
{
    // Retorna null si la persona no existe
    Persona obtenerPersona(int dni);

    void almacenarPersona(Persona persona);
}
