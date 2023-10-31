package modelo;

import java.util.Date;

public class ingresos {
	private int id;
	private String cod_prod;
	private String descripcion;
	private double costo;
	private int cantidad;
	private double total;
	private String vendedor;
	private Date fecha;
	
	public ingresos() {
			
	}



	public ingresos(int id, String cod_prod, String descripcion, double costo, int cantidad, double total,
			String vendedor, Date fecha) {
		super();
		this.id = id;
		this.cod_prod = cod_prod;
		this.descripcion = descripcion;
		this.costo = costo;
		this.cantidad = cantidad;
		this.total = total;
		this.vendedor = vendedor;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCod_prod() {
		return cod_prod;
	}

	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public void setCod_prod(String cod_prod) {
		this.cod_prod = cod_prod;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
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
