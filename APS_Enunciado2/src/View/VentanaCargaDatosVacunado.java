package View;

import Presenter.PresentadorDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCargaDatosVacunado extends VentanaDatosVacunado
{
    JButton botonGuardar;
    PresentadorDatos presentador;

    public VentanaCargaDatosVacunado(PresentadorDatos presentador)
    {
        super("Carga de datos", presentador);
        this.presentador = presentador;
        presentador.establecerVista(this);

        presentador.inicializarVista();

        botonGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                presentador.actuar();
            }
        });
    }

    @Override
    protected void accionesEspecificas()
    {
        JPanel panelInferior = new JPanel(new GridLayout(2, 3, 5, 5));

        botonGuardar = new JButton("Guardar");
        celdasInferiores[0][1].add(botonGuardar);
    }
}
