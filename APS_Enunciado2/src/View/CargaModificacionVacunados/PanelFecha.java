package View.CargaModificacionVacunados;

import javax.swing.*;

import resources.Fecha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PanelFecha extends JPanel
{
    private JComboBox cbDia;
    private JComboBox cbMes;
    private JComboBox cbAgno;

    public PanelFecha()
    {
        super();
        this.setLayout(new GridLayout(1, 3));

        cbDia = new JComboBox();
        cbMes = new JComboBox();
        cbAgno = new JComboBox();

        initCBDia(31);
        initCBMes();
        initCBAgno();

        this.add(cbDia);
        this.add(cbMes);
        this.add(cbAgno);

        desactivar();

        asignarListeners();
    }

    private void initCBDia(int cantDias)
    {
        String[] dias = new String[cantDias + 1];
        llenarArregloHasta(dias, cantDias, 0);
        cbDia.setModel(new DefaultComboBoxModel(dias));
    }

    private void initCBMes()
    {
        String[] meses = new String[13];
        llenarArregloHasta(meses, 12, 0);
        cbMes.setModel(new DefaultComboBoxModel(meses));
    }

    private void initCBAgno()
    {
        String[] agnos = new String[121];
        llenarArregloHasta(agnos, 120, 1900);
        cbAgno.setModel(new DefaultComboBoxModel(agnos));
    }

    private void llenarArregloHasta(String[] arr, int n, int extra)
    {
        arr[0] = "";
        for(int i = 1; i <= n; i++)
        {
            String representacionString = String.valueOf(i + extra);
            if (i + extra < 10)
                representacionString = "0" + representacionString;
            arr[i] = representacionString;
        }
    }

    private void asignarListeners()
    {
        cbMes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (seleccionValida(cbMes))
                    ajustarCBDia();
            }
        });

        cbAgno.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (seleccionValida(cbMes))
                    ajustarCBDia();
            }
        });
    }

    private void ajustarCBDia()
    {
        String diaSeleccionado = null;

        if (seleccionValida(cbDia))
            diaSeleccionado = (String) cbDia.getSelectedItem();

        int cantDias = cantDias(cbMes.getSelectedIndex());
        initCBDia(cantDias);

        if (diaSeleccionado != null)
        {
            if (Integer.valueOf(diaSeleccionado) > cantDias)
                cbDia.setSelectedItem(String.valueOf(cantDias));
            else
                cbDia.setSelectedItem(diaSeleccionado);
        }
    }

    public Fecha obtenerFecha()
    {
        if (seleccionValida())
            return new Fecha(obtenerAgno(), obtenerMes(), obtenerDia());
        else
            return null;
    }

    public void establecerFecha(Fecha fecha)
    {
        cbDia.setSelectedItem(String.valueOf(fecha.getDay()));
        cbMes.setSelectedItem(String.valueOf(fecha.getMonth()));
        cbAgno.setSelectedItem(String.valueOf(fecha.getYear()));
    }

    public int obtenerAgno()
    {
        if(seleccionValida(cbAgno))
            return Integer.valueOf((String)cbAgno.getSelectedItem());
        else
            return -1;
    }

    public int obtenerMes()
    {
        if(seleccionValida(cbMes))
            return Integer.valueOf((String)cbMes.getSelectedItem());
        else
            return -1;
    }

    public int obtenerDia()
    {
        if(seleccionValida(cbDia))
            return Integer.valueOf((String)cbDia.getSelectedItem());
        else
            return -1;
    }

    public boolean seleccionValida()
    {
        return seleccionValida(cbDia) && seleccionValida(cbMes) && seleccionValida(cbAgno);
    }

    public boolean seleccionNula()
    {
        return cbDia.getSelectedIndex() == 0 && cbMes.getSelectedIndex() == 0 && cbAgno.getSelectedIndex() == 0;
    }

    public void activar()
    {
        activar(cbDia);
        activar(cbMes);
        activar(cbAgno);
    }

    public void desactivar()
    {
        desactivar(cbDia);
        desactivar(cbMes);
        desactivar(cbAgno);
    }

    private void desactivar(JComboBox cb)
    {
        cb.setSelectedIndex(0);
        cb.setEnabled(false);
    }

    private void activar(JComboBox cb)
    {
        cb.setEnabled(true);
    }

    private boolean seleccionValida(JComboBox cb)
    {
        return cb.getSelectedIndex() != 0;
    }

    private int cantDias(int m)
    {
        if(m == 4 || m == 6 || m == 9 || m == 11)
            return 30;
        else if (m == 1 || m == 3 || m == 5 || m == 7 || m== 8 || m == 10 || m == 12)
            return 31;
        else if (seleccionValida(cbAgno))
        {
            int agno = Integer.parseInt((String) cbAgno.getSelectedItem());
            if (agno % 4 == 0)
                return 29;
            else
                return 28;
        }
        else
            return 28;
    }
}
