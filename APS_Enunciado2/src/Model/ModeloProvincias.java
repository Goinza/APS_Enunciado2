package Model;

import java.util.List;

public interface ModeloProvincias
{
    Provincia obtenerProvincia(int id);

    List<Provincia> obtenerProvincias();

    List<Integer> obtenerRegiones(Provincia provincia);
}
