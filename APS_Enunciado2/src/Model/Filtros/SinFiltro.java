package Model.Filtros;

public class SinFiltro implements Filtro {
	
	@Override
	public String obtenerConsulta() {		
		return "SELECT * FROM Aplicacion_Vacunas;";
	}

}
