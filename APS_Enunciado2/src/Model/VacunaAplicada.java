package Model;

import java.util.Date;

import resources.Fecha;

public class VacunaAplicada
{
    private Persona persona;
    private Vacuna vacuna;
    private int cantDosis;
    private Fecha primeraDosis;
    private Fecha segundaDosis;
    private Provincia provincia;
    private int region;

    public VacunaAplicada() {}

    public VacunaAplicada(Persona persona, Vacuna vacuna, Fecha primeraDosis, Fecha segundaDosis, Provincia provincia, int region)
    {
        this.persona = persona;
        this.vacuna = vacuna;
        this.primeraDosis = primeraDosis;
        this.segundaDosis = segundaDosis;
        this.provincia = provincia;
        this.region = region;
    }

    public VacunaAplicada(Persona persona, Vacuna vacuna, int cantDosis, Fecha primeraDosis, Fecha segundaDosis, Provincia provincia, int region)
    {
        this(persona, vacuna, primeraDosis, segundaDosis, provincia, region);
        this.cantDosis = cantDosis;
    }

    public Persona obtenerPersona()
    {
        return persona;
    }

    public void establecerPersona(Persona persona)
    {
        this.persona = persona;
    }

    public Vacuna obtenerVacuna()
    {
        return vacuna;
    }

    public void establecerVacuna(Vacuna vacuna)
    {
        this.vacuna = vacuna;
    }

    public Fecha obtenerPrimeraDosis()
    {
        return primeraDosis;
    }

    public void establecerPrimeraDosis(Fecha primeraDosis)
    {
        this.primeraDosis = primeraDosis;
    }

    public Fecha obtenerSegundaDosis()
    {
        return segundaDosis;
    }

    public void establecerSegundaDosis(Fecha segundaDosis)
    {
        this.segundaDosis = segundaDosis;
    }

    public int obtenerCantidadDosis()
    {
        return cantDosis;
    }

    public void establecerCantDosis(int cantDosis)
    {
        this.cantDosis = cantDosis;
    }

    public Provincia obtenerProvincia()
    {
        return provincia;
    }

    public void establecerProvincia(Provincia provincia)
    {
        this.provincia = provincia;
    }

    public int obtenerRegion()
    {
        return region;
    }

    public void establecerRegion(int region)
    {
        this.region = region;
    }
}
