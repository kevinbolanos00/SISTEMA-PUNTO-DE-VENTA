package modelo;

//import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
//import java.util.TimeZone;
import java.sql.Timestamp;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class VentaDao {
	//Timestamp timestamp = new Timestamp(date.getTime());

	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;
	int r;
	
	public int IdVenta() {
		int id = 0;
		String sql = "SELECT MAX(id) FROM ventas";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {
				id= rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return id;
	}
	
	public int RegistrarVenta(Venta v) {
		java.util.Date fecha = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(fecha.getTime());
		TimeZone timeZone = TimeZone.getDefault(); // Obtener la zona horaria local del sistema
		LocalDateTime localDateTime = timestamp.toLocalDateTime(); // Convertir el objeto Timestamp a LocalDateTime
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, timeZone.toZoneId()); // Establecer la zona horaria deseada
		timestamp = java.sql.Timestamp.valueOf(zonedDateTime.toLocalDateTime()); // Convertir el objeto ZonedDateTime a Timestamp
		

		
		//String sql = "INSERT INTO ventas (cliente, vendedor, total) VALUES (?, ?, ?)";
		
		String sql = "INSERT INTO ventas (cliente, vendedor, total, fecha) VALUES (?, ?, ?, ?)";
		
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, v.getCliente());
			ps.setString(2, v.getVendedor());
			ps.setDouble(3, v.getTotal());
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
			//ps.setDate(4, (java.sql.Date) v.getFecha());
			//ps.setDate(4, new java.sql.Date(v.getFecha().getTime()));
			//ps.setDate(4, new java.sql.Date(v.getFecha().getTime()));
			//ps.setTimestamp(4, new java.sql.Date(v.getFecha().getTime()));
			
			//ps.setTimestamp(4, new Timestamp(v.getFecha().getTime()));
			ps.setTimestamp(4, timestamp,cal);
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		finally{
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2.toString());
			}
		}
		return r;
	}
	public int RegistrarDetalle(Detalle Dv) {
		String sql = "INSERT INTO detalle (cod_pro, cantidad, precio, id_venta) VALUES (?, ?, ?, ?)";
		try {
			con = cn.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, Dv.getCod_pro());
			ps.setInt(2, Dv.getCantidad());
			ps.setDouble(3, Dv.getPrecio());
			ps.setInt(4, Dv.getId());
			ps.execute();
			
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2.toString());
			}
		}
		return r;
	}
	
	public boolean ActualizarStock (int cant, String cod) {
		String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
		try {
			con= cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cant);
			ps.setString(2, cod);
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public List ListarVentas() {
		List<Venta> ListaVenta = new ArrayList();
		String sql = "SELECT * FROM ventas";
		try {
			con= cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Venta vent = new Venta();
				vent.setId(rs.getInt("id"));
				vent.setCliente(rs.getString("cliente"));
				vent.setVendedor(rs.getString("vendedor"));
				//vent.setFecha(rs.getDate("fecha"));
				
				vent.setFecha(rs.getTimestamp("fecha"));
				vent.setTotal(rs.getDouble("total"));
				
				
				ListaVenta.add(vent);
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return ListaVenta;
	}
	
public List ListarVentasFecha(Date ini, Date fin) {
		List<Venta> ListaVenta = new ArrayList();
		String sql = "SELECT * FROM ventas WHERE fecha BETWEEN ? AND ?";
		//String sql = "SELECT * FROM ventas WHERE STR_TO_DATE(fecha, '%d-%m-%Y') BETWEEN ? AND ?";
		
		try {
			Calendar calIni = Calendar.getInstance();
	        calIni.setTime(ini);
	        calIni.set(Calendar.HOUR_OF_DAY, 0);
	        calIni.set(Calendar.MINUTE, 0);
	        calIni.set(Calendar.SECOND, 0);
	        calIni.set(Calendar.MILLISECOND, 0);
	        Timestamp iniTimestamp = new Timestamp(calIni.getTimeInMillis());

	        Calendar calFin = Calendar.getInstance();
	        calFin.setTime(fin);
	        calFin.set(Calendar.HOUR_OF_DAY, 23);
	        calFin.set(Calendar.MINUTE, 59);
	        calFin.set(Calendar.SECOND, 59);
	        calFin.set(Calendar.MILLISECOND, 999);
	        Timestamp finTimestamp = new Timestamp(calFin.getTimeInMillis());

			
			con= cn.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setDate(1, (java.sql.Date) Ini);
			//ps.setDate(2, (java.sql.Date) fin);
			ps.setTimestamp(1, iniTimestamp);
	        ps.setTimestamp(2, finTimestamp);
			rs = ps.executeQuery();
			while (rs.next()) {
				Venta vent = new Venta();
				vent.setId(rs.getInt("id"));
				vent.setCliente(rs.getString("cliente"));
				vent.setVendedor(rs.getString("vendedor"));
				vent.setFecha(rs.getTimestamp("fecha"));
				//vent.setFecha(rs.getString("fecha"));
				vent.setTotal(rs.getDouble("total"));
				
			
				
				ListaVenta.add(vent);
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return ListaVenta;
	}

}
