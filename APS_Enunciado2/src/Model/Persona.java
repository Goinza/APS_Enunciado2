package Model;

import java.util.Date;

import resources.Fecha;

public class Persona
{
    private int dni;
    private String nombre;
    private String apellido;
    private String mail;
    private Cargo cargo;
    private Fecha fechaNacimiento;

    public Persona(int dni, String nombre, String apellido, String mail, Cargo cargo, Fecha fechaNacimiento)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.cargo = cargo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int obtenerDni()
    {
        return dni;
    }

    public void establecerDni(int dni)
    {
        this.dni = dni;
    }

    public String obtenerNombre()
    {
        return nombre;
    }

    public void establecerNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String obtenerApellido()
    {
        return apellido;
    }

    public void establecerApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String obtenerMail()
    {
        return mail;
    }

    public void establecerMail(String mail)
    {
        this.mail = mail;
    }

    public Cargo obtenerCargo()
    {
        return cargo;
    }

    public void establecerCargo(Cargo cargo)
    {
        this.cargo = cargo;
    }

    public Fecha obtenerFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void establecerFechaNacimiento(Fecha fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean equals(Persona persona)
    {
        return dni == persona.obtenerDni();
    }
}
