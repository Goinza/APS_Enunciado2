package Model;

public class Provincia
{
    private int id;
    private java.lang.String nombre;

    public Provincia(String nombre)
    {
        this.nombre = nombre;
    }

    public Provincia(int id, java.lang.String nombre)
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

    public java.lang.String obtenerNombre()
    {
        return nombre;
    }

    public void establecerNombre(java.lang.String nombre)
    {
        this.nombre = nombre;
    }
}
