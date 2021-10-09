package Model;

import java.util.List;

public interface ModeloProvincias
{
    List<Provincia> obtenerProvincias();

    List<Integer> obtenerRegiones(Provincia provincia);
}
