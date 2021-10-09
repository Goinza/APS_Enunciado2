package Model;

import java.util.List;

public interface ModeloVacunas
{
    List<Vacuna> obtenerVacunas();

    List<Vacuna> obtenerVacunasPorProvincia(Provincia provincia);

    void decrementarVacuna(int idVacuna);
}
