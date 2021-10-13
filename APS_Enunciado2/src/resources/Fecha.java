package resources;

public class Fecha implements Comparable<Fecha>{
	
	private int dia;
	private int mes;
	private int anio;
	
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	
	public Fecha(String fecha) {
		String string = "2312-23-12";

        String[] sep = fecha.split("-");
        this.anio = Integer.valueOf(sep[0]);
        this.mes = Integer.valueOf(sep[1]);
        this.dia = Integer.valueOf(sep[2]);
	}
	
	public int getDay() {
		return dia;
	}

	public int getMonth() {
		return mes;
	}

	public int getYear() {
		return anio;
	}

	public void setDay(int dia) {
		this.dia = dia;
	}

	public void setMonth(int mes) {
		this.mes = mes;
	}

	public void setYear(int anio) {
		this.anio = anio;
	}
	
	public String toString() {
		return anio + " - " +  mes + " - " + dia;
	}

	public int compareTo(Fecha fechaSegunda) {
		int segundoAnio = fechaSegunda.getYear();
		int segundoMes = fechaSegunda.getMonth();
		int segundoDia = fechaSegunda.getDay();
		
		if (anio != segundoAnio)
            return anio - segundoAnio;
        else
        {
            if(mes != segundoMes)
                return mes - segundoMes;
            else
                return dia - segundoDia;
        }
	}
	

}


