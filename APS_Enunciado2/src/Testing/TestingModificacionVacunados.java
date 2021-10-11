package Testing;

import Model.ModeloPersonaImpl;
import Model.ModeloPersonas;
import Model.Persona;

public class TestingModificacionVacunados {

	public static void main(String[] args) {
		testPersona();
	}
	
	private static void testPersona(){
		ModeloPersonas mp = new ModeloPersonaImpl();
		Persona p = mp.obtenerPersona(11111118);
		System.out.println(p.getFechaNacimiento().toString());
	}

}
