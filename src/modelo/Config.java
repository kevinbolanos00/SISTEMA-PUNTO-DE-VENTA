package modelo;

public class Config {
	private int id;
	private String ruc; 
	private String nombre;
	private int telefono;
	private String direcion;
	private String Razon;
	
	
	public Config(){
		
	}


	public Config(int id, String ruc, String nombre, int telefono, String direcion, String razon) {
		super();
		this.id = id;
		this.ruc = ruc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direcion = direcion;
		Razon = razon;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String i) {
		this.ruc = i;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public String getDirecion() {
		return direcion;
	}


	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}


	public String getRazon() {
		return Razon;
	}


	public void setRazon(String razon) {
		Razon = razon;
	}
	
	

}
