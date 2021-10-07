package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public abstract class VentanaDatosVacunado extends JFrame implements VistaDatosVacunado
{
    JTextField tfNombreVacunado;
    JTextField tfApellido;
    JTextField tfDNIVacunado;
    PanelFecha pnFechaDeNacimiento;
    JTextField tfMailVacunado;
    JComboBox cbVacuna;
    PanelFecha pnFechaPrimeraDosis;
    PanelFecha pnFechaSegundaDosis;
    JTextField tfDosisAplicadas;
    JComboBox cbProvinciaDeVacunacion;
    JComboBox cbRegionSanitaria;

    boolean segundaDosisIngresada;

    JPanel[][] celdasInferiores;
    JButton btnCancelar;

    public VentanaDatosVacunado(String titulo) throws HeadlessException
    {
        super();
        this.setSize(600, 600);
        setLayout(new BorderLayout(10, 10));

        JLabel lbTitulo = new JLabel(" " + titulo);
        add(lbTitulo, BorderLayout.PAGE_START);

        inicializarPanelCampos();
        inicializarPanelInferior();
        accionesEspecificas();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void inicializarPanelCampos()
    {
        JPanel panelCampos = new JPanel(new GridLayout(0, 2, 5, 5));

        JLabel lbNombreVacunado = new JLabel("   Nombre");
        JLabel lbApellido = new JLabel("   Apellido");
        JLabel lbDNIVacunado = new JLabel("   DNI");
        JLabel lbFechaNacimiento = new JLabel("   Fecha de nacimiento");
        JLabel lbMailVacunado = new JLabel("   Correo electrónico");
        JLabel lbVacuna = new JLabel("   Vacuna");
        JLabel lbFechaPrimeraDosis = new JLabel("   Fecha de primera dosis");
        JLabel lbFechaSegundaDosis = new JLabel("   Fecha de segunda dosis");
        JLabel lbDosisAplicadas = new JLabel("   Cantidad de dosis recibidas");
        JLabel lbProvinciaDeVacunacion = new JLabel("   Provincia de vacunación");
        JLabel lbRegionSanitaria = new JLabel("   Región sanitaria");

        tfNombreVacunado = new JTextField();
        tfApellido = new JTextField();
        pnFechaDeNacimiento = new PanelFecha();
        cbVacuna = new JComboBox();
        pnFechaPrimeraDosis = new PanelFecha();
        pnFechaSegundaDosis = new PanelFecha();
        tfDosisAplicadas = new JTextField();
        tfMailVacunado = new JTextField();
        tfDNIVacunado = new JTextField();
        cbProvinciaDeVacunacion = new JComboBox();
        cbRegionSanitaria = new JComboBox();

        tfDosisAplicadas.setEnabled(false);
        
        panelCampos.add(lbNombreVacunado);
        panelCampos.add(tfNombreVacunado);
        panelCampos.add(lbApellido);
        panelCampos.add(tfApellido);
        panelCampos.add(lbDNIVacunado);
        panelCampos.add(tfDNIVacunado);
        panelCampos.add(lbMailVacunado);
        panelCampos.add(tfMailVacunado);
        panelCampos.add(lbFechaNacimiento);
        panelCampos.add(pnFechaDeNacimiento);
        panelCampos.add(lbVacuna);
        panelCampos.add(cbVacuna);
        panelCampos.add(lbFechaPrimeraDosis);
        panelCampos.add(pnFechaPrimeraDosis);
        panelCampos.add(lbFechaSegundaDosis);
        panelCampos.add(pnFechaSegundaDosis);
        panelCampos.add(lbDosisAplicadas);
        panelCampos.add(tfDosisAplicadas);
        panelCampos.add(lbProvinciaDeVacunacion);
        panelCampos.add(cbProvinciaDeVacunacion);
        panelCampos.add(lbRegionSanitaria);
        panelCampos.add(cbRegionSanitaria);

        add(panelCampos, BorderLayout.CENTER);
    }

    private void inicializarPanelInferior()
    {
        JPanel panelInferior = new JPanel(new GridLayout(2, 3, 5, 5));

        celdasInferiores = new JPanel[2][3];
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                celdasInferiores[i][j] = new JPanel();
                panelInferior.add(celdasInferiores[i][j]);
            }
        }

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                dispose();
            }
        });
        celdasInferiores[1][2].add(btnCancelar);

        add(panelInferior, BorderLayout.SOUTH);
    }

    protected abstract void accionesEspecificas();

    @Override
    public String obtenerNombre()
    {
        return tfNombreVacunado.getText();
    }

    @Override
    public void establecerNombre(String nombre)
    {
        tfNombreVacunado.setText(nombre);
    }

    @Override
    public String obtenerApellido()
    {
        return tfApellido.getText();
    }

    @Override
    public void establecerApellido(String apellido)
    {
        tfApellido.setText(apellido);
    }

    @Override
    public Date obtenerFechaDeNacimiento()
    {
        return pnFechaDeNacimiento.obtenerFecha();
    }

    @Override
    public void establecerFecha(Date fecha)
    {
        pnFechaDeNacimiento.establecerFecha(fecha);
    }

    @Override
    public String obtenerVacuna()
    {
        return valorComboBox(cbVacuna);
    }

    @Override
    public void establecerNombreVacuna(String nombre)
    {
        cbVacuna.setSelectedItem(nombre);
    }

    @Override
    public Date obtenerFechaPrimeraDosis()
    {
        return pnFechaPrimeraDosis.obtenerFecha();
    }

    @Override
    public void establecerFechaPrimeraDosis(Date fecha)
    {
        pnFechaPrimeraDosis.establecerFecha(fecha);
    }

    @Override
    public Date obtenerFechaSegundaDosis()
    {
        return pnFechaSegundaDosis.obtenerFecha();
    }

    @Override
    public void establecerFechaSegundaDosis(Date fecha)
    {
        pnFechaSegundaDosis.establecerFecha(fecha);
    }

    @Override
    public int obtenerCantidadDeDosisAplicadas()
    {
        return Integer.valueOf(tfDosisAplicadas.getText());
    }

    @Override
    public void establecerCantidadDeDosisAplicadas(int dosis)
    {
        tfDosisAplicadas.setText(String.valueOf(dosis));
    }

    @Override
    public String obtenerMail()
    {
        return tfMailVacunado.getText();
    }

    @Override
    public void establecerMail(String mail)
    {
        tfMailVacunado.setText(mail);
    }

    @Override
    public String obtenerDNI()
    {
        return tfDNIVacunado.getText();
    }

    @Override
    public void establecerDNI(String dni)
    {
        tfDNIVacunado.setText(dni);
    }

    @Override
    public String obtenerProvincia()
    {
        return valorComboBox(cbProvinciaDeVacunacion);
    }

    @Override
    public void establecerProvincia(String provincia)
    {
        cbProvinciaDeVacunacion.setSelectedItem(provincia);
    }

    @Override
    public String obtenerRegionSanitaria()
    {
        return valorComboBox(cbRegionSanitaria);
    }

    @Override
    public void establecerRegionSanitaria(String region)
    {
        cbRegionSanitaria.setSelectedItem(region);
    }

    @Override
    public void actualizarVacunas(String[] vacunas)
    {
        actualizarComboBox(cbVacuna, vacunas);
    }

    @Override
    public void actualizarProvincias(String[] provincias)
    {
        actualizarComboBox(cbProvinciaDeVacunacion, provincias);
    }

    @Override
    public void actualizarRegionesSanitarias(String[] regiones)
    {
        actualizarComboBox(cbRegionSanitaria, regiones);
    }

    @Override
    public boolean dosisValidas()
    {
        boolean valido = pnFechaPrimeraDosis.seleccionValida() && (pnFechaSegundaDosis.seleccionValida() || pnFechaSegundaDosis.seleccionNula());
        if (valido && pnFechaSegundaDosis.seleccionValida())
        {
            Date fechaPrimera = pnFechaPrimeraDosis.obtenerFecha();
            Date fechaSegunda = pnFechaSegundaDosis.obtenerFecha();
            if(fechaPrimera.compareTo(fechaSegunda) > 0)
                valido = false;
        }
        return valido;
    }

    @Override
    public void mostrarAviso(String aviso)
    {
        JOptionPane.showMessageDialog(this, aviso, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarAlerta(String alerta)
    {
        JOptionPane.showMessageDialog(this, alerta, "Información", JOptionPane.ERROR_MESSAGE);
    }

    private void actualizarComboBox(JComboBox comboBox, String[] opciones)
    {
        comboBox.removeAllItems();
        comboBox.setModel(new DefaultComboBoxModel(opciones));
        comboBox.insertItemAt("", 0);
        comboBox.setSelectedIndex(0);
    }

    private String valorComboBox(JComboBox cb)
    {
        String ret = (String)cb.getSelectedItem();
        if (ret == "")
            ret = null;
        return ret;
    }
}
