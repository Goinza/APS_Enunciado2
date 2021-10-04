package Model;

import java.util.Vector;

/**
 * Implementación mockup - siempre retorna los mismos valores.
 *
 */
public class TestTabla implements ModeloTabla {

	@Override
	public Vector<String> obtenerNombresColumnas() {
		Vector<String> columna = new Vector<String>();
		columna.add("Nombre");
		columna.add("Apellido");
		columna.add("DNI");
		return columna;
	}

	@Override
	public Vector<Vector<Object>> obtenerTuplas() {
		Vector<Vector<Object>> tuplas = new Vector<Vector<Object>>();
		Vector<Object> t;
		
		t = new Vector<Object>();
		t.add("Roberto");
		t.add("Perez");
		t.add(32122758);
		tuplas.add(t);
		
		t = new Vector<Object>();
		t.add("Luis");
		t.add("Miguel");
		t.add(26412517);
		tuplas.add(t);
				
		return tuplas;
	}

	@Override
	public boolean agregarTupla(Vector<Object> tupla) {
		return true;
	}

	@Override
	public boolean modificarTupla(Vector<Object> tupla) {
		return true;
	}

	@Override
	public boolean accederAdmin(String nombre, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean accederUsuario(String nombre, String password) {
		// TODO Auto-generated method stub
		return true;
	}

}
