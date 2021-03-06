package Model.Filtros;

import java.sql.Date;

import resources.Fecha;

public class FiltroFechaAplicacion implements Filtro {
	
	private Fecha desde;
	private Fecha hasta;
	
	public FiltroFechaAplicacion(Fecha desde, Fecha hasta) {
		this.desde = desde;
		this.hasta = hasta;		
	}

	@Override
	public String obtenerConsulta() {
		String fecha_desde = desde.getYear() + "-" + desde.getMonth() + "-" + desde.getDay();
		String fecha_hasta = hasta.getYear() + "-" + hasta.getMonth() + "-" + hasta.getDay();
		return "SELECT * FROM Aplicacion_Vacunas WHERE"
				+ "`Primera Dosis`>'" + fecha_desde + "' AND `Primera Dosis`<'" + fecha_hasta
				+ "' OR `Segunda Dosis`>'" + fecha_desde + "' AND `Segunda Dosis`<'" + fecha_hasta + "';";
	}

}
