package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import javax.swing.JOptionPane;

public class Conexion {
	
	Connection con;
	
	public Connection getConnection(){
		
		try {
			
			// Obtener la zona horaria del sistema
			TimeZone timezone = TimeZone.getDefault();
			String systemTimezone = timezone.getID();

			// Establecer la zona horaria en la conexión de MySQL
			//String myBD = "jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=" + systemTimezone;
			String myBD = "jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=" + systemTimezone;
			con = DriverManager.getConnection(myBD, "root", "");
			//con = DriverManager.getConnection(myBD, "SISTEMAVENTAS", "Kevin1004731286");
			
			return con;
			
		} catch (SQLException e){
			System.out.println(e.toString());
			JOptionPane.showMessageDialog(null, "Error en conexion DB");
		}
			return null;
			
		
	}

}
