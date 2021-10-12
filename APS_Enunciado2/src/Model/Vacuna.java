package Model;

public class Vacuna
{
    private int id;
    private String nombre;

    public Vacuna()
    {
        id = 0;
    }

    public Vacuna(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public int obtenerId()
    {
        return id;
    }

    public void establecerId(int id)
    {
        this.id = id;
    }

    public String obtenerNombre()
    {
        return nombre;
    }

    public void establecerNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
