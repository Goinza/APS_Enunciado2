package Model;

import resources.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ModeloCargosImpl implements ModeloCargos
{
    public ModeloCargosImpl()
    {
    }

    @Override
    public Cargo obtenerCargo(int id)
    {
        DBConnection database = DBConnection.getInstance();
        ResultSet result = database.realizarConsulta("SELECT * FROM Cargos WHERE id_cargo =" + id + ";");
        Cargo cargo = null;
        try {
            if (result.next()) {
                String nombre = result.getString("nombre_cargo");
                boolean esencial = result.getInt("es_esencial") == 1? true : false;
                cargo = new Cargo(id, nombre, esencial);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return cargo;
    }

    @Override
    public List<Cargo> obtenerCargos()
    {
        DBConnection database = DBConnection.getInstance();
        ResultSet result = database.realizarConsulta("SELECT * FROM Cargos;");
        List<Cargo> lista = new LinkedList<>();
        Cargo cargo;
        int id;
        String nombre;
        boolean esencial;
        try {
            while (result.next()) {
                id = result.getInt("id_cargo");
                nombre = result.getString("nombre_cargo");
                esencial = result.getInt("es_esencial") == 1? true : false;
                System.out.println(id + " - " + nombre + " - " + esencial);
                cargo = new Cargo(id, nombre, esencial);
                lista.add(cargo);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
