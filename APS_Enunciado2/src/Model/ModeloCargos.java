package Model;

import java.util.List;

public interface ModeloCargos
{
    Cargo obtenerCargo(int id);

    List<Cargo> obtenerCargos();
}
