package reportes;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Font;

import modelo.Conexion;

public class Grafico {
	
	public static void Graficar(String fecha) {
		
		Connection con;
		Conexion cn = new Conexion();
		PreparedStatement ps;
		ResultSet rs;
		try {
			String sql= "SELECT total FROM ventas WHERE fecha = ?";
			con=cn.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, fecha);
			rs=ps.executeQuery();
			DefaultPieDataset dataset = new DefaultPieDataset();
			while (rs.next()) {
				dataset.setValue(rs.getString("total"), rs.getDouble("total"));
				
			}
			JFreeChart jf = ChartFactory.createPieChart("Reporte de venta ", dataset);
			ChartFrame f = new ChartFrame("Total de ventas por dia", jf);
			f.setSize(1000,500);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void GraficarTabla(int id) {
	
	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;
	try {
	    String sql= "SELECT *FROM detalle WHERE id_venta = ?";
	    con=cn.getConnection();
	    ps= con.prepareStatement(sql);
	    ps.setInt(1, id);
	    rs=ps.executeQuery();

	    // Crear la estructura de datos de la tabla
	    String[] columnas = {"Codigo", "Cantidad", "Precio", "num venta", "Total"};
	    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
	    float total;
	    float totalV = 0;

	    // Llenar la tabla con los datos de la consulta
	    while (rs.next()) {
	        //String total = rs.getString("total");
	    	//hacer el total de cada venta
	        int codigo =rs.getInt("cod_pro");
	        int cantidad = rs.getInt("cantidad");
	        Float precio = rs.getFloat("precio");
	        int venta = rs.getInt("id_venta");
	        total=precio*cantidad;
	        totalV=totalV+total;
	        
	        Object[] fila = {codigo, cantidad, precio, venta, total};
	        modelo.addRow(fila);
	    }

	    // Crear la tabla y mostrarla en un JFrame
	    JTable tabla = new JTable(modelo);
	    JFrame f = new JFrame("Reporte de venta");
	    f.getContentPane().add(new JScrollPane(tabla));
	    f.setSize(1000, 500);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	    
	    //-----------------
	    //JPanel panel = new JPanel(new BorderLayout());
	    //panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

	    // Crear los JLabel y agregarlos al panel en la parte derecha
	    //JLabel label1 = new JLabel();
	    JLabel label2 = new JLabel();
	    label2.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));
	    //label2.setFont(new Font("Tahoma", Font.BOLD, 12));
	    //f.add(label1, BorderLayout.SOUTH);
	    f.getContentPane().add(label2, BorderLayout.SOUTH);

	    // Establecer el texto de los JLabel
	    
	   // label1.setText("Total Venta");
	    label2.setText("                                                                                    Total Venta: "+String.valueOf(totalV));

	    // Agregar el panel al JFrame
	    //f.add(panel);
	    
	    
	    //-----

	} catch (SQLException e) {
	    System.out.println(e.toString());
	}
	}

}
