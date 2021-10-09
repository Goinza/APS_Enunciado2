package Model;

public class FiltroCantidadDosis implements Filtro {

	private int cantidad;
	
	public FiltroCantidadDosis(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String obtenerConsulta() {
		return "SELECT * FROM Aplicacion_Vacunas WHERE `Cantidad Dosis`=" + cantidad + ";";
	}

}
