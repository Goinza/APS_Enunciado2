package View.CargaModificacionVacunados;

import Model.Persona;
import Model.Provincia;
import Model.Vacuna;
import Presenter.CargaModificacionVacunados.PresentadorDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class VentanaModificacionVacunado extends VentanaDatosVacunado
{
    private JButton botonModificar;

    public VentanaModificacionVacunado(PresentadorDatos presentador)
    {
        super("Modificación", presentador);
        this.presentador = presentador;
        presentador.establecerVista(this);

        botonModificar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                presentador.actuar();
            }
        });

        presentador.inicializarVista();
    }

    @Override
    protected void accionesEspecificas()
    {
        JPanel panelInferior = new JPanel(new GridLayout(2, 3, 5, 5));

        botonModificar = new JButton("Modificar");
        celdasInferiores[0][1].add(botonModificar);
    }
}
