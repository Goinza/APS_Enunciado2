package Model.Filtros;

public class FiltroEdad implements Filtro {

	private int desde;
	private int hasta;	
		
	public FiltroEdad(int desde, int hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}

	@Override
	public String obtenerConsulta() {		
		return "SELECT * FROM Aplicacion_Vacunas" +
				" WHERE YEAR(CURDATE()) - YEAR(`Fecha Nacimiento`)>" + desde +
				" AND YEAR(CURDATE()) - YEAR(`Fecha Nacimiento`)<" + hasta + ";";
	}

}
