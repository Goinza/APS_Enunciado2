package Model;

import java.util.Date;

public class Persona
{
    int dni;
    String nombre;
    String apellido;
    String mail;
    Date fechaNacimiento;

    public Persona()
    {
    }

    public Persona(int dni, String nombre, String apellido, String mail, Date fechaNacimiento)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
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

    public Date obtenerFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void establecerFechaNacimiento(Date fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean equals(Persona persona)
    {
        return dni == persona.obtenerDni();
    }
}
