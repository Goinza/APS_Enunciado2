package Model;

import java.util.List;

public interface ModeloVacunas
{
    List<Vacuna> obtenerVacunas();

    Vacuna obtenerVacuna(int id);

    List<Vacuna> obtenerVacunasPorProvincia(Provincia provincia);
}
