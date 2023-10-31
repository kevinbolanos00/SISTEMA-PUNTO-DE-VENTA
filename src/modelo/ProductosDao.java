package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class ProductosDao {
	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;
	int r;

	public boolean ResgistrarProductos(Productos pro) {
		
		String sql = "INSERT INTO productos(codigo, nombre, proveedor, stock, precio, costo) VALUES (?, ?, ?, ?, ?,?)";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, pro.getCodigo());
			ps.setString(2, pro.getNombre());
			ps.setString(3, pro.getProveedor());
			ps.setInt(4, pro.getStock());
			ps.setDouble(5, pro.getPrecio());
			ps.setDouble(6, pro.getCosto());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Producto registrado con exito ");
			return true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
	         
	         JOptionPane.showMessageDialog(null, "Un producto con ese codigo ya esta registrado. Verifique!");
	         return false;
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	         return false;
	      }
		
	}

	
	public void ConsultarProveedor(JComboBox proveedor) {
		
		String sql = "SELECT nombre FROM proveedor";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				proveedor.addItem(rs.getString("nombre"));
				
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		
		}
	}

	
	public List ListarProductos() {
		List<Productos> ListaPro = new ArrayList();
		String sql = "SELECT * FROM productos";
		try {
			con= cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Productos pro = new Productos();
				pro.setId(rs.getInt("id"));
				pro.setCodigo(rs.getString("codigo"));
				pro.setNombre(rs.getString("nombre"));
				pro.setProveedor(rs.getString("proveedor"));
				pro.setStock(rs.getInt("stock"));
				pro.setPrecio(rs.getDouble("precio"));
				pro.setCosto(rs.getDouble("costo"));
				
				ListaPro.add(pro);
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return ListaPro;
	}
	
	
	
	public List ListarProductosBusqueda(String busqueda) {
		List<Productos> ListaPro = new ArrayList();
		String sql ="SELECT * FROM productos WHERE nombre LIKE '%" + busqueda + "%'";
		try {
			con= cn.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setString(1, busqueda);
			rs = ps.executeQuery();
			while (rs.next()) {
				Productos pro = new Productos();
				pro.setId(rs.getInt("id"));
				pro.setCodigo(rs.getString("codigo"));
				pro.setNombre(rs.getString("nombre"));
				pro.setProveedor(rs.getString("proveedor"));
				pro.setStock(rs.getInt("stock"));
				pro.setPrecio(rs.getDouble("precio"));
				pro.setCosto(rs.getDouble("costo"));
				
				ListaPro.add(pro);
			}
			
		} catch (SQLException e) {
			System.out.println("error en listar productos");
			System.out.println(e.toString());
		}
		return ListaPro;
	}
	
	public DefaultTableModel ListarDetalleVenta(int id_venta) {
	    DefaultTableModel modelo = new DefaultTableModel();

	    // Agregar las columnas al modelo
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Cantidad");
	    modelo.addColumn("Precio");
	    modelo.addColumn("Total");

	    String sql = "SELECT productos.nombre , detalle.cantidad, detalle.precio FROM detalle INNER JOIN productos ON productos.codigo = detalle.cod_pro WHERE detalle.id_venta = ?";

	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id_venta);
	        rs = ps.executeQuery();

	        // Agregar las filas al modelo de la tabla
	        while (rs.next()) {
	            String nombreProducto = rs.getString("nombre");
	            int cantidad = rs.getInt("cantidad");
	            double precio = rs.getDouble("precio");
	            double total = cantidad * precio;
	            Object[] fila = { nombreProducto, cantidad, precio, total };
	            modelo.addRow(fila);
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error en listar productos");
	        System.out.println(e.toString());
	    }

	    return modelo;
	}

	
	
	//------------------
	
	public boolean EliminarProductos(int id) {
		String sql = "DELETE FROM productos WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		
	}
	
	
	public boolean ModificarProductos(Productos pro) {
		String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?, precio=?, costo=? WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pro.getCodigo());
			ps.setString(2, pro.getNombre());
			ps.setString(3, pro.getProveedor());
			ps.setInt(4, pro.getStock());
			ps.setDouble(5, pro.getPrecio());
			
			ps.setDouble(6, pro.getCosto());
			
			System.out.println("costo en funcion: "+  pro.getCosto());
			ps.setInt(7, pro.getId());
			ps.execute();
	        return true;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2.toString());
				
			}
			
		}
	}
	
	//******************
	public boolean ModificarCantPro(Productos pro) {
		String sql = "UPDATE productos SET stock=? WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, pro.getStock());
			ps.setInt(2, pro.getId());
			ps.execute();
	        return true;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2.toString());
				
			}
			
		}
	}
	//******************

	
	public Productos BuscarPro(String codigo) {
		
		Productos producto = new Productos();
		String sql = "SELECT *FROM productos WHERE codigo=?";
		try {
			con =cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);
			rs= ps.executeQuery();
			
			if (rs.next()) {
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock"));
				
			}
		} catch (SQLException e) {
			System.out.println(toString());
		}
		return producto;
	}
	
	//************************
	

	
	//***************
	
	
	public Config BuscarDatos() {
		
		Config conf = new Config();
		String sql = "SELECT *FROM config";
		try {
			con =cn.getConnection();
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			
			if (rs.next()) {
				conf.setId(rs.getInt("id"));
				conf.setRuc(rs.getString("ruc"));
				conf.setNombre(rs.getString("nombre"));
				conf.setTelefono(rs.getInt("telefono"));
				conf.setDirecion(rs.getString("direccion"));
				conf.setRazon(rs.getString("razon"));

				
			}
		} catch (SQLException e) {
			System.out.println(toString());
		}
		return conf;
	}
	
	
	public boolean ModificarDatos(Config conf) {
		String sql = "UPDATE config SET  nombre=?, ruc=?, telefono=?, direccion=?, razon=? WHERE id=?";
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, conf.getNombre());
			ps.setString(2, conf.getRuc());
			ps.setInt(3, conf.getTelefono());
			ps.setString(4, conf.getDirecion());
			ps.setInt(5, Integer.parseInt(conf.getRazon()));
			ps.setInt(6, conf.getId());
			ps.execute();
	        return true;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2.toString());
				
			}
			
		}
	}

	public int RegistrarIngresos(ingresos ing) {
		java.util.Date fecha = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(fecha.getTime());
		TimeZone timeZone = TimeZone.getDefault(); // Obtener la zona horaria local del sistema
		LocalDateTime localDateTime = timestamp.toLocalDateTime(); // Convertir el objeto Timestamp a LocalDateTime
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, timeZone.toZoneId()); // Establecer la zona horaria deseada
		timestamp = java.sql.Timestamp.valueOf(zonedDateTime.toLocalDateTime()); // Convertir el objeto ZonedDateTime a Timestamp
		
		String sql = "INSERT INTO ingresos (cod_prod, descripcion, costo, cantidad, total, vendedor, fecha) VALUES (?, ?, ?, ?, ?, ?,?)";
		try {
			con = cn.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, ing.getCod_prod());
			ps.setString(2, ing.getDescripcion());
			ps.setDouble(3, ing.getCosto());
			ps.setInt(4, ing.getCantidad());
			ps.setDouble(5, ing.getTotal());
			ps.setString(6, ing.getVendedor());
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
			ps.setTimestamp(7, timestamp,cal);
			
			ps.execute();
			
			
		} catch (SQLException e) {
			System.out.println(e.toString()+ "Error en consulta");
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2.toString());
			}
		}
		return r;
	}
	
	public List ListarIngresosFecha(Date ini, Date fin) {
		List<ingresos> ListaIngresos = new ArrayList();
		String sql = "SELECT * FROM ingresos WHERE fecha BETWEEN ? AND ?";
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
				ingresos ingre = new ingresos();
				ingre.setCod_prod(rs.getString("cod_prod"));
				ingre.setDescripcion(rs.getString("descripcion"));
				ingre.setCosto(rs.getDouble("costo"));
				ingre.setCantidad(rs.getInt("cantidad"));
				ingre.setTotal(rs.getDouble("total"));
				ingre.setFecha(rs.getTimestamp("fecha"));
				ingre.setVendedor(rs.getString("vendedor"));
				
				
			
				
				
				ListaIngresos.add(ingre);
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString()); 
		}
		return ListaIngresos;
	}
	
}
