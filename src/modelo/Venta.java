package modelo;

import java.util.Date;

public class Venta {
	private int id;
	private String cliente;
	private String vendedor;
	private double total;
	private Date fecha;
	
	public Venta(){
		
	}

	public Venta(int id, String cliente, String vendedor, double total, Date fecha) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.total = total;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/*public Date getFecha() {
		return fecha;
	}*/
	
	public Date getFecha() {
	    if (fecha == null) {
	        fecha = new Date();
	    }
	    return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
