package Model;

import java.util.Date;

public class VacunaAplicada
{
    private Date primeraDosis;
    private Date segundaDosis;
    private int cantidadDosis;
    private Persona persona;
    private int idVacuna;
    private int idProvincia;
    private int idRegion;

    public VacunaAplicada(Date primeraDosis, Date segundaDosis, Persona persona, int idVacuna, int idProvincia, int idRegion)
    {
        this.primeraDosis = primeraDosis;
        this.segundaDosis = segundaDosis;
        this.persona = persona;
        this.idVacuna = idVacuna;
        this.idProvincia = idProvincia;
        this.idRegion = idRegion;
    }

    public Date obtenerPrimeraDosis()
    {
        return primeraDosis;
    }

    public void establecerPrimeraDosis(Date primeraDosis)
    {
        this.primeraDosis = primeraDosis;
    }

    public Date obtenerSegundaDosis()
    {
        return segundaDosis;
    }

    public void establecerSegundaDosis(Date segundaDosis)
    {
        this.segundaDosis = segundaDosis;
    }

    public int obtenerCantidadDosis()
    {
        return cantidadDosis;
    }

    public void establecerCantidadDosis(int cantidadDosis)
    {
        this.cantidadDosis = cantidadDosis;
    }

    public Persona obtenerPersona()
    {
        return persona;
    }

    public void establecerPersona(Persona persona)
    {
        this.persona = persona;
    }

    public int obtenerIdVacuna()
    {
        return idVacuna;
    }

    public void establecerIdVacuna(int idVacuna)
    {
        this.idVacuna = idVacuna;
    }

    public int obtenerIdProvincia()
    {
        return idProvincia;
    }

    public void establecerIdProvincia(int idProvincia)
    {
        this.idProvincia = idProvincia;
    }

    public int obtenerIdRegion()
    {
        return idRegion;
    }

    public void establecerIdRegion(int idRegion)
    {
        this.idRegion = idRegion;
    }
}
