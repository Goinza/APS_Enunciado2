package Testing;

import java.util.List;

import Model.ModeloPersonaImpl;
import Model.ModeloPersonas;
import Model.ModeloProvincias;
import Model.ModeloProvinciasImpl;
import Model.ModeloVacunas;
import Model.ModeloVacunasImpl;
import Model.Persona;
import Model.Provincia;
import Model.Vacuna;

public class TestingModificacionVacunados {

	public static void main(String[] args) {
		//testPersona();
		testRegiones();
	}
	
	private static void testPersona(){
		ModeloPersonas mp = new ModeloPersonaImpl();
		Persona p = mp.obtenerPersona(11111118);
		System.out.println(p.getFechaNacimiento().toString());
	}
	
	private static void testRegiones() {
		ModeloProvincias mp = new ModeloProvinciasImpl();
		List<Provincia> lista = mp.obtenerProvincias();
		for (Provincia p: lista) {
			System.out.println(p.obtenerNombre());	
		}
		
		Provincia p = lista.get(0);
		List<Integer> regiones = mp.obtenerRegiones(p);
		for (Integer i : regiones) {
			System.out.println(i);
		}
		
		ModeloVacunas mv = new ModeloVacunasImpl();
		List<Vacuna> vacunasProvincia = mv.obtenerVacunasPorProvincia(p);
		
		for (Vacuna v : vacunasProvincia) {
			System.out.println(v.obtenerNombre());
		}
		
	}

}
