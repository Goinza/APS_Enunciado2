package View;

import Presenter.PresentadorCargaVacunado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCargaDatosVacunado extends VentanaDatosVacunado
{
    JButton botonGuardar;
    PresentadorCargaVacunado presentador;

    public VentanaCargaDatosVacunado(PresentadorCargaVacunado presentador)
    {
        super("Carga de datos");
        this.presentador = presentador;
        presentador.establecerVista(this);

        botonGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                presentador.guardar();
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
