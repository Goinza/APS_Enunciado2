package Model;

public class Cargo
{
    private int id;
    private String cargo;
    private boolean esencial;

    public Cargo(int id, String cargo, boolean esencial)
    {
        this.id = id;
        this.cargo = cargo;
        this.esencial = esencial;
    }

    public int obtenerId()
    {
        return id;
    }

    public void establecerId(int id)
    {
        this.id = id;
    }

    public String obtenerNombreCargo()
    {
        return cargo;
    }

    public void establecerCargo(String cargo)
    {
        this.cargo = cargo;
    }

    public boolean esEscencial()
    {
        return esencial;
    }

    public void tornarEscencial(boolean escencial)
    {
        this.esencial = escencial;
    }
}
