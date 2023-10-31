package reportes;

import java.io.IOException;
import java.util.Date;

import javax.swing.JTable;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelVentas {
	public static void generarExcel(JTable table) throws IOException {
		
	    
	    
	 // Crear el archivo Excel
        XSSFWorkbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Ventas");
        
        // Crear la fila de encabezados
        Row filaEncabezados = hoja.createRow(0);
        for (int j = 0; j < table.getColumnCount(); j++) {
            Cell celda = filaEncabezados.createCell(j);
            celda.setCellValue(table.getColumnName(j));
        }
        
        // Crear las filas y celdas del archivo Excel a partir de la tabla
        for (int i = 0; i < table.getRowCount(); i++) {
            Row fila = hoja.createRow(i + 1);
            for (int j = 0; j < table.getColumnCount(); j++) {
                Cell celda = fila.createCell(j);
                Object valor = table.getValueAt(i, j);
                if (valor != null) {
                    celda.setCellValue(valor.toString());
                }
            }
        }
        
        // Abrir el archivo Excel
        try {
            File archivo = File.createTempFile("ventas", ".xlsx");
            FileOutputStream archivoSalida = new FileOutputStream(archivo);
            libro.write(archivoSalida);
            archivoSalida.close();
            Desktop.getDesktop().open(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
	  }
}
