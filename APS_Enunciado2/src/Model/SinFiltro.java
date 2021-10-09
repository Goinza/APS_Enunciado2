package Model;

public class SinFiltro implements Filtro {
	
	@Override
	public String obtenerConsulta() {		
		return "SELECT * FROM Aplicacion_Vacunas;";
	}

}
