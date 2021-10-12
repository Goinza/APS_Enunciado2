package Model;

import java.util.Date;

public class VacunaAplicada
{
    private Date primeraDosis;
    private Date segundaDosis;
    private int cantidadDosis;
    private int dni;
    private int idVacuna;
    private int idProvincia;
    private int idRegion;

    public VacunaAplicada(Date primeraDosis, Date segundaDosis, int cantidadDosis, int dni, int idVacuna, int idProvincia, int idRegion)
    {
        this.primeraDosis = primeraDosis;
        this.segundaDosis = segundaDosis;
        this.cantidadDosis = cantidadDosis;
        this.dni = dni;
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

    public int obtenerDni()
    {
        return dni;
    }

    public void establecerDni(int dni)
    {
        this.dni = dni;
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

    public int obtenerRegion()
    {
        return idRegion;
    }

    public void establecerIdRegion(int idRegion)
    {
        this.idRegion = idRegion;
    }
}
