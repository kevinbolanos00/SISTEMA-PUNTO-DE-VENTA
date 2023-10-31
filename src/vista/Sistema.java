package vista;

import java.awt.EventQueue;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.sound.sampled.Port;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.formula.functions.Code;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.h2.mvstore.type.ObjectDataType;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.itextpdf.awt.geom.Dimension;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.xml.simpleparser.NewLineHandler;
import com.mysql.cj.result.Row;

import modelo.Cliente;
import modelo.ClienteDao;
import modelo.Config;
import modelo.Detalle;
import modelo.Eventos;
import modelo.Productos;
import modelo.ProductosDao;
import modelo.Proveedor;
import modelo.ProveedorDAO;
import modelo.Venta;
import modelo.VentaDao;
import modelo.ingresos;
import modelo.login;
import reportes.Excel;
import reportes.ExcelVentas;
import reportes.Grafico;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessHandle.Info;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.chrono.MinguoDate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.SystemColor;

public class Sistema extends JFrame {
	Date fechaVenta = new Date();
	//String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
	Cliente cl = new Cliente();
	ClienteDao client = new ClienteDao();
	DefaultTableModel modelo = new DefaultTableModel();
	
	Proveedor pr = new Proveedor();
	ProveedorDAO prDao = new ProveedorDAO();
	Productos pro = new Productos();
	ProductosDao proDao = new ProductosDao();
	Venta v = new Venta();
	VentaDao Vdao = new VentaDao();
	Detalle Dv = new Detalle();
	ingresos ing = new ingresos();
	
	Config conf = new Config();
	Eventos event = new Eventos();
	int item;
	double totalPagar = 0.00;
	double totalVentas=0.00;
	DefaultTableModel tmp = new DefaultTableModel();
	
	Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE); // crear un borde inferior personalizado
	Color miColor = new Color(255,87,97);

	

	private JPanel contentPane;
	private JTextField textCodigoVenta;
	private JTextField textDescripcionVenta;
	private JTextField textCantidadVenta;
	private JTextField textPrecioVenta;
	private JTextField textStockDisponible;
	private JTable tableVenta;
	private JTextField textRucVenta;
	private JTextField textNombreClienteventa;
	private JTextField textDniCliente;
	private JTextField textNombreCliente;
	private JTextField textTelefonoCliente;
	private JTextField textDireccionCliente;
	private JTextField texRazonCliente;
	private JTable TableCliente;
	private JTextField textRucProveedor;
	private JTextField textNombreProveedor;
	private JTextField textTelefonoProveedor;
	private JTextField textDireccionProveedor;
	private JTextField textRazonProveedor;
	private JTable TableProveedor;
	private JTextField textCodigoProducto;
	private JTextField textDesPro;
	private JTextField textCantPro;
	private JTextField textPrecioPro;
	private JTable TableProducto;
	private JTable TableVentas;
	private JTextField textTelefonoCV;
	private JTextField textDireccionCV;
	private JTextField textRazonCV;
	private JTextField textIdCliente;
	private JTextField textIdProveedor;
	private JTextField textIdpro;
	private JTextField textIdVenta;
	private JTextField textIdPro;

	private JButton btnProductos;
	private JTextField textBuscarProducto;
	private JTextField textCosto;
	private JTextField textAgregar;
	private JTable tableIngresos;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema frame = new Sistema("", "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Sistema(String user, String rol) {
		//JOptionPane.showMessageDialog(null, user);
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_HORIZ);
		setBounds(100, 100, 1192, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));*/
		
		
        //PANTALLA COMPLETA
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setBounds(0, 0, 1920, 1032);
		//setBounds(0, 0, 1366, 760);
		setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		//deseño JOptionPane
		
		UIManager.put("OptionPane.background", Color.GRAY);
		UIManager.put("Panel.background", Color.GRAY);
		UIManager.put("Button.background", new Color(45, 235, 201));
		UIManager.put("Button.foreground", Color.BLACK);
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
		
		
		
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.setBackground(new Color(60,63,65));
		
		
		
		//proDao.ConsultarProveedor();
		//otiginal
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 222, 996);
		panel.setBackground(SystemColor.menuText);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		//tableVenta.setRowHeight(30);

		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/Img/encabezado5.png")));
		lblNewLabel.setBounds(224, 0, 1680, 143);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(229, 103, 1665, 879);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(31, 73, 102, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descripcion");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(225, 73, 296, 31);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(547, 73, 86, 31);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precio");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(699, 73, 65, 31);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("StoCK Disponible");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_6.setForeground(new Color(255, 255, 224));
		lblNewLabel_6.setBounds(854, 75, 121, 30);
		panel_1.add(lblNewLabel_6);
		
		JLabel LabelTotal = new JLabel("...");
		LabelTotal.setBounds(797, 494, 178, 54);
		panel_1.add(LabelTotal);
		LabelTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelTotal.setBackground(miColor);
		LabelTotal.setForeground(Color.WHITE);
		
		
		
		textCodigoVenta = new JTextField();
		
		textCodigoVenta.setForeground(Color.WHITE);
		//Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE); // crear un borde inferior personalizado
		textCodigoVenta.setBorder(border); 
		textCodigoVenta.setBackground(Color.DARK_GRAY);
		textCodigoVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//textCodigoVenta.setBorder(null);
		textCodigoVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				
				if (e.getKeyCode() ==  KeyEvent.VK_ENTER) {
					if (!"".equals(textCodigoVenta.getText())) {
						String cod = textCodigoVenta.getText();
						pro = proDao.BuscarPro(cod);
						if (pro.getNombre() != null) {
							textDescripcionVenta.setText(""+pro.getNombre());
							textPrecioVenta.setText(""+pro.getPrecio());
							textStockDisponible.setText(""+pro.getStock());
							textCantidadVenta.setText("1");
							textCantidadVenta.selectAll();
							textCantidadVenta.requestFocus();
							
						}else {
							LimpiarVenta();
							textCodigoVenta.requestFocus();
							
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
						textCodigoVenta.requestFocus();
					}
					
				}
				
				
				
				
			
			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		textCodigoVenta.setBounds(31, 101, 159, 28);
		panel_1.add(textCodigoVenta);
		textCodigoVenta.setColumns(10);
		
		textDescripcionVenta = new JTextField();
		textDescripcionVenta.setForeground(Color.WHITE);
		textDescripcionVenta.setBorder(border); 
		textDescripcionVenta.setBackground(Color.DARK_GRAY);
		
		textDescripcionVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textDescripcionVenta.setEditable(false);
		//Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE); // crear un borde inferior personalizado
		
		textDescripcionVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});
		textDescripcionVenta.setBounds(225, 101, 296, 28);
		panel_1.add(textDescripcionVenta);
		textDescripcionVenta.setColumns(10);
		
		textCantidadVenta = new JTextField();
		textCantidadVenta.setForeground(Color.WHITE);
		textCantidadVenta.setBackground(Color.DARK_GRAY);
		textCantidadVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCantidadVenta.setBorder(border); 

		textCantidadVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(textCantidadVenta.getText())) {
						String cod = textCodigoVenta.getText();
						String descripcion = textDescripcionVenta.getText();
						int cant = Integer.parseInt(textCantidadVenta.getText());
						Double precio = Double.parseDouble(textPrecioVenta.getText()) ;
						Double total = cant*precio;
						int stock = Integer.parseInt(textStockDisponible.getText());
						if (stock >= cant) {
							item = item + 1;
						    tmp = (DefaultTableModel) tableVenta.getModel();
						    tableVenta.setRowHeight(30);
							for (int i = 0; i < tableVenta.getRowCount(); i++) {
								if (tableVenta.getValueAt(i, 1).equals(textDescripcionVenta.getText())) {
									JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
									return;
								}
								
							}
							ArrayList lista = new ArrayList();
							lista.add(item);
							lista.add(cod);
							lista.add(descripcion);
							lista.add(cant);
							lista.add(precio);
							lista.add(total);
							Object[] o = new Object [5];
							
							o[0]= lista.get(1);
							o[1]= lista.get(2);
							o[2]= lista.get(3);
							o[3]= lista.get(4);
							o[4]= lista.get(5);
							
							tmp.addRow(o);
							tableVenta.setModel(tmp);
							LabelTotal.setText(String.format("%.2f", TotalPagar()));
							LimpiarVenta();
							textCodigoVenta.requestFocus();
							
							
							
						
						}else {
							JOptionPane.showMessageDialog(null, "Stock no disponible");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Ingrese cantidad");
					}
				}
				
				
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		textCantidadVenta.setBounds(547, 101, 132, 28);
		panel_1.add(textCantidadVenta);
		textCantidadVenta.setColumns(10);
		
		textPrecioVenta = new JTextField();
		textPrecioVenta.setBackground(Color.DARK_GRAY);
		textPrecioVenta.setForeground(new Color(255, 255, 255));
		textPrecioVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPrecioVenta.setBounds(699, 101, 143, 28);
		textPrecioVenta.setBorder(border); 
		textPrecioVenta.setEditable(false);
		panel_1.add(textPrecioVenta);
		textPrecioVenta.setColumns(10);
		
		textStockDisponible = new JTextField();
		textStockDisponible.setBackground(Color.DARK_GRAY);
		textStockDisponible.setForeground(Color.WHITE);
		textStockDisponible.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textStockDisponible.setBounds(854, 103, 121, 26);
		textStockDisponible.setBorder(border); 
		textStockDisponible.setEditable(false);

		panel_1.add(textStockDisponible);
		textStockDisponible.setColumns(10);
		
		JButton btnEliminarVenta = new JButton("");
		btnEliminarVenta.setBackground(SystemColor.controlDkShadow);
		btnEliminarVenta.setBackground(new Color(45, 235, 201));
		btnEliminarVenta.setToolTipText("Eliminar un producto de la venta");
		btnEliminarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo= (DefaultTableModel) tableVenta.getModel();
				modelo.removeRow(tableVenta.getSelectedRow());
				TotalPagar();
				textCodigoVenta.requestFocus();
			
			}
		});
		btnEliminarVenta.setBounds(1001, 90, 57, 39);
		btnEliminarVenta.setBorderPainted(false);
		btnEliminarVenta.setIcon(new ImageIcon(Sistema.class.getResource("/Img/eliminar.png")));
		panel_1.add(btnEliminarVenta);
		
		
		//-----------
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 141, 1056, 327);
		scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
		panel_1.add(scrollPane);
		
		tableVenta = new JTable();
		tableVenta.setBackground(Color.DARK_GRAY);
		tableVenta.setForeground(Color.WHITE);
		tableVenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTableHeader headerV = tableVenta.getTableHeader();
		headerV.setForeground(Color.DARK_GRAY);
		headerV.setBackground(new Color(18, 217, 227));
		headerV.setFont(new Font("Arial", Font.BOLD, 16));
		
		tableVenta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
			}
		));
		tableVenta.getColumnModel().getColumn(0).setPreferredWidth(154);
		tableVenta.getColumnModel().getColumn(1).setPreferredWidth(155);
		tableVenta.getColumnModel().getColumn(2).setPreferredWidth(107);
		tableVenta.getColumnModel().getColumn(3).setPreferredWidth(114);
		tableVenta.getColumnModel().getColumn(4).setPreferredWidth(99);
		scrollPane.setViewportView(tableVenta);
		
		JLabel lblNewLabel_3 = new JLabel("Identificacion");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(31, 482, 159, 54);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(283, 471, 89, 77);
		panel_1.add(lblNewLabel_7);
		
		textRucVenta = new JTextField();
		textRucVenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRucVenta.setForeground(Color.WHITE);
		textRucVenta.setBackground(Color.DARK_GRAY);
		textRucVenta.setBorder(border); 
		textRucVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(textRucVenta.getText())) {
						int dni = Integer.parseInt(textRucVenta.getText());
						cl = client.BuscarCliente(dni);
						if (cl.getNombre() != null) {
							textNombreClienteventa.setText(""+cl.getNombre());
							textTelefonoCV.setText(""+cl.getTelefono());
							textDireccionCV.setText(""+cl.getDirrecion());
							textRazonCV.setText(""+cl.getRazon());
						}else {
							textRucVenta.setText("");
							JOptionPane.showMessageDialog(null, "el cliente no existe");
						}
					}
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		textRucVenta.setBounds(32, 519, 186, 37);
		panel_1.add(textRucVenta);
		textRucVenta.setColumns(10);
		
		textNombreClienteventa = new JTextField();
		textNombreClienteventa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNombreClienteventa.setForeground(Color.WHITE);
		textNombreClienteventa.setBackground(Color.DARK_GRAY);
		textNombreClienteventa.setEditable(false);
		textNombreClienteventa.setBounds(283, 521, 215, 35);
		textNombreClienteventa.setBorder(border); 
		textNombreClienteventa.setEditable(false);


		panel_1.add(textNombreClienteventa);
		textNombreClienteventa.setColumns(10);
		
		
		
		JLabel lblNewLabel_8 = new JLabel("Total a Pagar");
		lblNewLabel_8.setIcon(new ImageIcon(Sistema.class.getResource("/Img/money.png")));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_8.setBounds(607, 485, 203, 62);
		lblNewLabel_8.setBorder(null);
		lblNewLabel_8.setBackground(miColor);
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		panel_1.add(lblNewLabel_8);
		
		
		
		
		
		textTelefonoCV = new JTextField();
		textTelefonoCV.setBounds(472, 499, 6, 20);
		panel_1.add(textTelefonoCV);
		textTelefonoCV.setColumns(10);
		textTelefonoCV.setVisible(false);
		
		textDireccionCV = new JTextField();
		textDireccionCV.setBounds(489, 499, 6, 20);
		panel_1.add(textDireccionCV);
		textDireccionCV.setColumns(10);
		textDireccionCV.setVisible(false);
		
		textRazonCV = new JTextField();
		textRazonCV.setBounds(505, 499, 6, 20);
		panel_1.add(textRazonCV);
		textRazonCV.setColumns(10);
		textRazonCV.setVisible(false);
		
		textIdPro = new JTextField();
		textIdPro.setBounds(10, 145, 11, 20);
		panel_1.add(textIdPro);
		textIdPro.setColumns(10);
		textIdPro.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Identificacion:");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(10, 92, 118, 34);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(10, 146, 118, 22);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Telefono:");
		lblNewLabel_12.setForeground(Color.WHITE);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(10, 193, 118, 31);
		panel_2.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Direccion:");
		lblNewLabel_13.setForeground(Color.WHITE);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(10, 238, 118, 32);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Razon social:");
		lblNewLabel_14.setForeground(Color.WHITE);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14.setBounds(10, 281, 118, 33);
		panel_2.add(lblNewLabel_14);
		
		textDniCliente = new JTextField();
		textDniCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textDniCliente.setForeground(Color.WHITE);
		textDniCliente.setBackground(Color.DARK_GRAY);
		textDniCliente.setBounds(111, 92, 156, 34);
		textDniCliente.setBorder(border);
		panel_2.add(textDniCliente);
		textDniCliente.setColumns(10);
		
		textNombreCliente = new JTextField();
		textNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textNombreCliente.setBackground(Color.DARK_GRAY);
		textNombreCliente.setForeground(Color.WHITE);
		textNombreCliente.setBounds(111, 141, 156, 33);
		textNombreCliente.setBorder(border);
		panel_2.add(textNombreCliente);
		textNombreCliente.setColumns(10);
		
		textTelefonoCliente = new JTextField();
		textTelefonoCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textTelefonoCliente.setForeground(Color.WHITE);
		textTelefonoCliente.setBackground(Color.DARK_GRAY);
		textTelefonoCliente.setBounds(111, 193, 156, 31);
		textTelefonoCliente.setBorder(border);
		panel_2.add(textTelefonoCliente);
		textTelefonoCliente.setColumns(10);
		
		textDireccionCliente = new JTextField();
		textDireccionCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textDireccionCliente.setBackground(Color.DARK_GRAY);
		textDireccionCliente.setForeground(Color.WHITE);
		textDireccionCliente.setBounds(111, 240, 156, 30);
		textDireccionCliente.setBorder(border);
		panel_2.add(textDireccionCliente);
		textDireccionCliente.setColumns(10);
		
		texRazonCliente = new JTextField();
		texRazonCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		texRazonCliente.setForeground(Color.WHITE);
		texRazonCliente.setBackground(Color.DARK_GRAY);
		texRazonCliente.setBounds(111, 281, 156, 33);
		texRazonCliente.setBorder(border);
		panel_2.add(texRazonCliente);
		texRazonCliente.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(277, 72, 815, 483);
		scrollPane_1.getViewport().setBackground(Color.LIGHT_GRAY);
		panel_2.add(scrollPane_1);
		
		TableCliente = new JTable();
		TableCliente.setForeground(Color.WHITE);
		TableCliente.setBackground(Color.DARK_GRAY);
		TableCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTableHeader header = TableCliente.getTableHeader();
		header.setForeground(Color.DARK_GRAY);
		header.setBackground(new Color(18, 217, 227));
		header.setFont(new Font("Arial", Font.BOLD, 16));
		//header.setDefaultRenderer(new HeaderRenderer());
		TableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = TableCliente.rowAtPoint(e.getPoint());
				textIdCliente.setText(TableCliente.getValueAt(fila, 0).toString());
				textDniCliente.setText(TableCliente.getValueAt(fila, 1).toString());
				textNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
				textTelefonoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
				textDireccionCliente.setText(TableCliente.getValueAt(fila, 4).toString());
				textRazonCV.setText(TableCliente.getValueAt(fila, 5).toString());
				
			}
		});
		TableCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ID", "NOMBRE", "TELEFONO", "DIRECCION", "RAZON SOCIAL"
			}
		));
		TableCliente.getColumnModel().getColumn(0).setPreferredWidth(80);
		scrollPane_1.setViewportView(TableCliente);
		
		JButton btnGuardarCliente = new JButton("");
		btnGuardarCliente.setToolTipText("Guardar");
		btnGuardarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(textDniCliente.getText()) || !"".equals(textNombreCliente.getText()) || !"".equals(textTelefonoCliente.getText()) || !"".equals(textDireccionCliente.getText())) {
					cl.setDni(Integer.parseInt(textDniCliente.getText()));
					cl.setNombre(textNombreCliente.getText());
					cl.setTelefono(Integer.parseInt(textTelefonoCliente.getText()));
					cl.setDirrecion(textDireccionCliente.getText());
					cl.setRazon(textRazonCV.getText());
					
					client.RegistrarCliente(cl);
					JOptionPane.showMessageDialog(null, "Cliente Registrado");
					LimpiarTable();
					LimpiarCliente();
					ListarCliente();
					JOptionPane.showMessageDialog(null, "cliente Registrado");
					
				}else {
					JOptionPane.showMessageDialog(null, "Los campos estan vacios");
				}
			}
		});
		btnGuardarCliente.setIcon(new ImageIcon(Sistema.class.getResource("/Img/save2.png")));
		
		btnGuardarCliente.setBounds(22, 380, 59, 47);
		btnGuardarCliente.setBackground(new Color(18,217,227));
		panel_2.add(btnGuardarCliente);
		
		JButton btnEditarCliente = new JButton("");
		btnEditarCliente.setToolTipText("Actualizar");
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(textIdCliente.getText())) {
					JOptionPane.showMessageDialog(null, "seleccione una fila");
				}else {
					
					if (!"".equals(textDniCliente.getText()) || !"".equals(textNombreCliente.getText()) || !"".equals(textTelefonoCliente.getText())) {
						cl.setDni(Integer.parseInt(textDniCliente.getText()));
						cl.setNombre(textNombreCliente.getText());
						cl.setTelefono(Integer.parseInt(textTelefonoCliente.getText()));
						cl.setDirrecion(textDireccionCliente.getText());
						cl.setRazon(textRazonCV.getText());
						cl.setId(Integer.parseInt(textIdCliente.getText()));
						client.ModificarCliente(cl);
						JOptionPane.showMessageDialog(null, "Cliente modificado");
						LimpiarTable();
						LimpiarCliente();
						ListarCliente();
						
					}else {
						
						JOptionPane.showMessageDialog(null, "los campos estan vacios");
					}
				}
			}
		});
		btnEditarCliente.setIcon(new ImageIcon(Sistema.class.getResource("/Img/ACTUALIZAR.png")));
		btnEditarCliente.setBounds(111, 380, 59, 47);
		btnEditarCliente.setBackground(new Color(18,217,227));
		panel_2.add(btnEditarCliente);
		
		JButton btnEliminarCliente = new JButton("");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!"".equals(textIdCliente.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "¿esta seguro de eliminar");
					if (pregunta == 0) {
						int id = Integer.parseInt(textIdCliente.getText());
						client.EliminarCliente(id);
						LimpiarTable();
						LimpiarCliente();
						ListarCliente();
						
						
					}
				}
			}
		});
		btnEliminarCliente.setIcon(new ImageIcon(Sistema.class.getResource("/Img/eliminar.png")));
		btnEliminarCliente.setBounds(21, 450, 59, 47);
		btnEliminarCliente.setBackground(new Color(18,217,227));
		panel_2.add(btnEliminarCliente);
		
		JButton btnNuevoCliente = new JButton("");
		btnNuevoCliente.setToolTipText("Vaciar campos");
		btnNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarCliente();
			}
		});
		btnNuevoCliente.setIcon(new ImageIcon(Sistema.class.getResource("/Img/VACIAR.png")));
		btnNuevoCliente.setBounds(111, 450, 59, 47);
		panel_2.add(btnNuevoCliente);
		
		textIdCliente = new JTextField();
		textIdCliente.setText("");
		textIdCliente.setBounds(189, 28, 15, 20);
		panel_2.add(textIdCliente);
		textIdCliente.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.WHITE);
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_15 = new JLabel("IDENT.");
		lblNewLabel_15.setForeground(Color.WHITE);
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_15.setBounds(26, 94, 142, 29);
		panel_3.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("NOMBRE");
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_16.setBounds(26, 150, 102, 29);
		panel_3.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("TELEFONO");
		lblNewLabel_17.setForeground(Color.WHITE);
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_17.setBounds(26, 203, 102, 29);
		panel_3.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("DIRECCION");
		lblNewLabel_18.setForeground(Color.WHITE);
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_18.setBounds(26, 251, 102, 29);
		panel_3.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("RAZON SOCIAL");
		lblNewLabel_19.setForeground(Color.WHITE);
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_19.setBounds(26, 308, 142, 29);
		panel_3.add(lblNewLabel_19);
		
		
		JButton btnCliente = new JButton("Clientes");
		btnCliente.setBackground(new Color(45, 235, 201));
		btnCliente.setBorderPainted(false);

		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarCliente();
				//JTabbedPane1.setSelectedIndex(1);
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		btnCliente.setIcon(new ImageIcon(Sistema.class.getResource("/Img/Clientes.png")));
		btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCliente.setBounds(28, 248, 169, 70);
		panel.add(btnCliente);
		
		textRucProveedor = new JTextField();
		textRucProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textRucProveedor.setBackground(Color.DARK_GRAY);
		textRucProveedor.setForeground(Color.WHITE);
		textRucProveedor.setBounds(109, 94, 179, 31);
		textRucProveedor.setBorder(border);
		
		panel_3.add(textRucProveedor);
		textRucProveedor.setColumns(10);
		
		textNombreProveedor = new JTextField();
		textNombreProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombreProveedor.setBackground(Color.DARK_GRAY);
		textNombreProveedor.setForeground(Color.WHITE);
		textNombreProveedor.setBounds(109, 150, 179, 31);
		
		textNombreProveedor.setBorder(border);
		panel_3.add(textNombreProveedor);
		textNombreProveedor.setColumns(10);
		
		textTelefonoProveedor = new JTextField();
		textTelefonoProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textTelefonoProveedor.setBackground(Color.DARK_GRAY);
		textTelefonoProveedor.setForeground(Color.WHITE);
		textTelefonoProveedor.setBounds(109, 203, 179, 31);
		textTelefonoProveedor.setBorder(border);
		panel_3.add(textTelefonoProveedor);
		textTelefonoProveedor.setColumns(10);
		
		textDireccionProveedor = new JTextField();
		textDireccionProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textDireccionProveedor.setBackground(Color.DARK_GRAY);
		textDireccionProveedor.setForeground(Color.WHITE);
		textDireccionProveedor.setBounds(109, 251, 179, 31);
		textDireccionProveedor.setBorder(border);
		panel_3.add(textDireccionProveedor);
		textDireccionProveedor.setColumns(10);
		
		textRazonProveedor = new JTextField();
		textRazonProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textRazonProveedor.setBackground(Color.DARK_GRAY);
		textRazonProveedor.setForeground(Color.WHITE);
		textRazonProveedor.setBounds(139, 308, 149, 31);
		textRazonProveedor.setBorder(border);
		panel_3.add(textRazonProveedor);
		textRazonProveedor.setColumns(10);
		//---------------------
		
		
		//--------------------------
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(298, 96, 809, 476);
		scrollPane_2.getViewport().setBackground(Color.LIGHT_GRAY);
		panel_3.add(scrollPane_2);
		
		TableProveedor = new JTable();
		TableProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TableProveedor.setForeground(Color.WHITE);
		TableProveedor.setBackground(Color.DARK_GRAY);
		JTableHeader headerP = TableProveedor.getTableHeader();
		headerP.setForeground(Color.DARK_GRAY);
		headerP.setBackground(new Color(18, 217, 227));
		headerP.setFont(new Font("Arial", Font.BOLD, 16));
		TableProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = TableProveedor.rowAtPoint(e.getPoint());
				textIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
				textRucProveedor.setText(TableProveedor.getValueAt(fila, 1).toString());
				textNombreProveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
				textTelefonoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
				textDireccionProveedor.setText(TableProveedor.getValueAt(fila, 4).toString());
				textRazonProveedor.setText(TableProveedor.getValueAt(fila, 5).toString());
			    
			}
		});
		TableProveedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "RUC", "NOMBRE", "TELEFONO", "DIRECCION", "RAZON SOCIAL"
			}
		));
		TableProveedor.getColumnModel().getColumn(5).setPreferredWidth(99);
		scrollPane_2.setViewportView(TableProveedor);
		
		JButton btnGuardarProveedor = new JButton("");
		btnGuardarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!"".equals(textRucProveedor.getText()) || !"".equals(textNombreProveedor.getText()) || !"".equals(textTelefonoProveedor.getText()) || !"".equals(textDireccionProveedor.getText()) ) {
					pr.setRuc(Integer.parseInt(textRucProveedor.getText()));
					pr.setNombre(textNombreProveedor.getText());
					pr.setTelefono(Integer.parseInt(textTelefonoProveedor.getText()));
					pr.setDireccion(textDireccionProveedor.getText());
					pr.setRazon(textRazonProveedor.getText());
					prDao.RegistrarProveedor(pr);
					JOptionPane.showMessageDialog(null, "Proveedor Registrado");
					LimpiarTable();
					ListarProveedor();
					LimpiarProveedor();
				}else {
					JOptionPane.showMessageDialog(null, "Los campos estan vacios");
				}
				 
			}
		});
		btnGuardarProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/Img/save2.png")));
		btnGuardarProveedor.setBounds(71, 379, 57, 39);
		btnGuardarProveedor.setBackground(new Color(18,217,227));
		panel_3.add(btnGuardarProveedor);
		
		JButton btnEditarProveedor = new JButton("");
		btnEditarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(textIdProveedor.getText())) {
					JOptionPane.showMessageDialog(null, "seleccione una fila");
				}else {
					if (!"".equals(textRucProveedor.getText()) || !"".equals(textNombreProveedor.getText()) || !"".equals(textTelefonoProveedor.getText()) || !"".equals(textDireccionProveedor.getText()) ) {
						pr.setRuc(Integer.parseInt(textRucProveedor.getText()));
						pr.setNombre(textNombreProveedor.getText());
						pr.setTelefono(Integer.parseInt(textTelefonoProveedor.getText()));
						pr.setDireccion(textDireccionProveedor.getText());
						pr.setRazon(textRazonProveedor.getText());
						pr.setId(Integer.parseInt(textIdProveedor.getText()));
						prDao.ModificarProveedor(pr);
						JOptionPane.showMessageDialog(null, "Proveedor modificado");
						LimpiarTable();
						LimpiarProveedor();
						ListarProveedor();
						
						
					}
				}
				
			}
		});
		btnEditarProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/Img/ACTUALIZAR.png")));
		btnEditarProveedor.setBounds(158, 379, 57, 39);
		btnEditarProveedor.setBackground(new Color(18,217,227));
		panel_3.add(btnEditarProveedor);
		
		JButton btnEliminarProveedor = new JButton("");
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!"".equals(textIdProveedor.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "¡esta seguro de eliminar?" );
					if (pregunta==0) {
						int id = Integer.parseInt(textIdProveedor.getText());
						prDao.EliminarProveedor(id);
						LimpiarTable();
						ListarProveedor();
						LimpiarProveedor();
					}
				}else {
					JOptionPane.showMessageDialog(null, "seleccione una fila");
					
				}
			}
		});
		btnEliminarProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/Img/eliminar.png")));
		btnEliminarProveedor.setBounds(71, 438, 57, 39);
		btnEliminarProveedor.setBackground(new Color(18,217,227));
		panel_3.add(btnEliminarProveedor);
		
		JButton btnNuevoProveedor = new JButton("");
		btnNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarProveedor();
			}
		});
		btnNuevoProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/Img/VACIAR.png")));
		btnNuevoProveedor.setBounds(158, 438, 57, 39);
		panel_3.add(btnNuevoProveedor);
		
		textIdProveedor = new JTextField();
		textIdProveedor.setBounds(196, 19, 9, 20);
		panel_3.add(textIdProveedor);
		textIdProveedor.setColumns(10);
		textIdProveedor.setVisible(false);
		
		
		
		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarProductos();
				tabbedPane.setSelectedIndex(3);
				
			}
		});
		btnProductos.setIcon(new ImageIcon(Sistema.class.getResource("/Img/PRODUCTOS.png")));
		btnProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProductos.setBackground(new Color(45, 235, 201));
		btnProductos.setBorderPainted(false);

		btnProductos.setBounds(28, 167, 169, 70);
		
		panel.add(btnProductos);
		
		
		
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_20 = new JLabel("Codigo:");
		lblNewLabel_20.setForeground(Color.WHITE);
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_20.setBounds(10, 116, 95, 46);
		panel_4.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("Descripcion:");
		lblNewLabel_21.setForeground(Color.WHITE);
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_21.setBounds(10, 161, 95, 41);
		panel_4.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("Cantidad:");
		lblNewLabel_22.setForeground(Color.WHITE);
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_22.setBounds(10, 213, 95, 34);
		panel_4.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("Precio:");
		lblNewLabel_23.setForeground(Color.WHITE);
		lblNewLabel_23.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_23.setBounds(10, 253, 95, 29);
		panel_4.add(lblNewLabel_23);
		
		JLabel lblNewLabel_24 = new JLabel("Proveedor:");
		lblNewLabel_24.setForeground(Color.WHITE);
		lblNewLabel_24.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_24.setBounds(10, 354, 95, 28);
		panel_4.add(lblNewLabel_24);
		
		textCodigoProducto = new JTextField();
		textCodigoProducto.setBackground(Color.DARK_GRAY);
		textCodigoProducto.setForeground(Color.WHITE);
		textCodigoProducto.setFont(new Font("Dialog", Font.PLAIN, 17));
		textCodigoProducto.setBounds(114, 119, 157, 41);
		textCodigoProducto.setBorder(border);
		panel_4.add(textCodigoProducto);
		textCodigoProducto.setColumns(10);
		
		textDesPro = new JTextField();
		textDesPro.setBackground(Color.DARK_GRAY);
		textDesPro.setForeground(Color.WHITE);
		textDesPro.setFont(new Font("Dialog", Font.PLAIN, 17));
		textDesPro.setBounds(115, 169, 156, 34);
		textDesPro.setBorder(border);

		panel_4.add(textDesPro);
		textDesPro.setColumns(10);
		
		textCantPro = new JTextField();
		textCantPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		textCantPro.setBackground(Color.DARK_GRAY);
		textCantPro.setForeground(Color.WHITE);
		textCantPro.setFont(new Font("Dialog", Font.PLAIN, 17));
		textCantPro.setBounds(114, 212, 157, 37);
		textCantPro.setBorder(border);

		panel_4.add(textCantPro);
		textCantPro.setColumns(10);
		
		textPrecioPro = new JTextField();
		textPrecioPro.setBackground(Color.DARK_GRAY);
		textPrecioPro.setForeground(Color.WHITE);
		textPrecioPro.setBorder(border);

		textPrecioPro.setFont(new Font("Dialog", Font.PLAIN, 17));
		textPrecioPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberDecimalKeyPress(e, textPrecioPro);
			}
		});
		textPrecioPro.setBounds(114, 252, 157, 34);
		panel_4.add(textPrecioPro);
		textPrecioPro.setColumns(10);
		
		JComboBox cbxProveedorPro = new JComboBox();
		cbxProveedorPro.setBackground(Color.DARK_GRAY);
		cbxProveedorPro.setForeground(Color.WHITE);
		cbxProveedorPro.setFont(new Font("Dialog", Font.BOLD, 14));
		cbxProveedorPro.setEditable(true);
		cbxProveedorPro.setBounds(115, 358, 156, 29);
		
		panel_4.add(cbxProveedorPro);
	
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(281, 104, 822, 375);
		scrollPane_3.getViewport().setBackground(Color.LIGHT_GRAY);
		panel_4.add(scrollPane_3);
		
		TableProducto = new JTable();
		TableProducto.setBackground(Color.DARK_GRAY);
		TableProducto.setForeground(Color.WHITE);
		TableProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTableHeader headerProd = TableProducto.getTableHeader();
		headerProd.setForeground(Color.DARK_GRAY);
		headerProd.setBackground(new Color(18, 217, 227));
		headerProd.setFont(new Font("Arial", Font.BOLD, 16));
		
		TableProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textCantPro.setEditable(false);
				
				int fila = TableProducto.rowAtPoint(e.getPoint());
				textIdPro.setText(TableProducto.getValueAt(fila, 0).toString());
				textCodigoProducto.setText(TableProducto.getValueAt(fila, 1).toString());
				textDesPro.setText(TableProducto.getValueAt(fila, 2).toString());
				cbxProveedorPro.setSelectedItem(TableProducto.getValueAt(fila, 3).toString());
				textCantPro.setText(TableProducto.getValueAt(fila, 4).toString());
				textPrecioPro.setText(TableProducto.getValueAt(fila, 5).toString());
				textCosto.setText(TableProducto.getValueAt(fila, 6).toString());
				
			}
		});
		TableProducto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "CODIGO", "DESCRIPCION", "PROVEEDOR", "STOCK", "PRECIO", "COSTO"
			}
		));
		scrollPane_3.setViewportView(TableProducto);
		
		JButton btnGuardarPro = new JButton("");
		btnGuardarPro.setToolTipText("Guardar");
		btnGuardarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!"".equals(textCodigoProducto.getText()) || !"".equals(textDesPro.getText()) || !"".equals(cbxProveedorPro.getSelectedItem()) || !"".equals(textCantPro.getText()) || !"".equals(textPrecioPro.getText()) || !"".equals(textCosto.getText()) ) {
					
					/*int cant;
					String cantStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad inicial del producto");
					cant = Integer.parseInt(cantStr);*/
					
					pro.setCodigo(textCodigoProducto.getText());
					pro.setNombre(textDesPro.getText());
					pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
					pro.setStock(Integer.parseInt(textCantPro.getText()));
					//pro.setStock(cant);
					pro.setPrecio(Double.parseDouble(textPrecioPro.getText()));
					pro.setCosto(Double.parseDouble(textCosto.getText()));
					proDao.ResgistrarProductos(pro);
					
					
					LimpiarTable();
					LimpiarProductos();
					cbxProveedorPro.setSelectedItem(null);
					ListarProductos();
				
				}else {
					JOptionPane.showMessageDialog(null, "Los campos estan vacios");
				}
				
			}
		});
		btnGuardarPro.setIcon(new ImageIcon(Sistema.class.getResource("/Img/save2.png")));
		btnGuardarPro.setBounds(53, 445, 52, 34);
		btnGuardarPro.setBackground(new Color(18,217,227));
		panel_4.add(btnGuardarPro);
		
		JButton btnEditarPro = new JButton("");
		btnEditarPro.setToolTipText("Actualizar");
		btnEditarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ("".equals(textIdPro.getText())) {
					JOptionPane.showMessageDialog(null, "seleccione una fila");
				}else {
					if (!"".equals(textCodigoProducto.getText()) || !"".equals(textDesPro.getText()) || !"".equals(textCantPro.getText()) || !"".equals(textPrecioPro.getText()) || !"".equals(textCosto.getText()) ) {
						pro.setCodigo(textCodigoProducto.getText());
						pro.setNombre(textDesPro.getText());
						pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
						pro.setStock(Integer.parseInt(textCantPro.getText()));
						pro.setPrecio(Double.parseDouble(textPrecioPro.getText()));
						pro.setCosto(Double.parseDouble(textCosto.getText()));
						pro.setId(Integer.parseInt(textIdPro.getText()));
						proDao.ModificarProductos(pro);
						JOptionPane.showMessageDialog(null, "Producto modificado");
						LimpiarTable();
						LimpiarProductos();
						cbxProveedorPro.setSelectedItem(null);
						ListarProductos();
						
						
					}
				}
			}
		});
		btnEditarPro.setIcon(new ImageIcon(Sistema.class.getResource("/Img/ACTUALIZAR.png")));
		btnEditarPro.setBounds(125, 445, 52, 34);
		btnEditarPro.setBackground(new Color(18,217,227));

		panel_4.add(btnEditarPro);
		
		JButton btnEliminarPro = new JButton("");
		btnEliminarPro.setToolTipText("Eliminar");
		btnEliminarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!"".equals(textIdPro.getText())) {
					String[] opciones = {"Sí", "Cancelar"};

					// Mostrar el cuadro de diálogo con las opciones personalizadas
					int respuesta = JOptionPane.showOptionDialog(null, "¡esta seguro de eliminar?", "Confirmar", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);

					
					//int pregunta = JOptionPane.showConfirmDialog(null, "¡esta seguro de eliminar?" );
					
					if(respuesta == JOptionPane.YES_OPTION){
						int id = Integer.parseInt(textIdPro.getText());
						proDao.EliminarProductos(id);
						LimpiarTable();
						LimpiarProductos();
						cbxProveedorPro.setSelectedItem(null);
						ListarProductos();
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "seleccione una fila");
					
				}
				
			}
		});
		btnEliminarPro.setIcon(new ImageIcon(Sistema.class.getResource("/Img/eliminar.png")));
		btnEliminarPro.setBounds(53, 490, 52, 34);
		btnEliminarPro.setBackground(new Color(18,217,227));

		panel_4.add(btnEliminarPro);
		
		JButton btnNuevoPro = new JButton("");
		btnNuevoPro.setToolTipText("Vaciar campos");
		btnNuevoPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textCantPro.setEditable(true);
				LimpiarProductos();
				cbxProveedorPro.setSelectedItem(null);
			}
		});
		btnNuevoPro.setIcon(new ImageIcon(Sistema.class.getResource("/Img/VACIAR.png")));
		btnNuevoPro.setBounds(125, 490, 52, 34);
		btnNuevoPro.setBackground(new Color(18,217,227));

		panel_4.add(btnNuevoPro);
		
		JButton btnExcelPro = new JButton("");
		btnExcelPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Excel.reporte();
				
			}
		});
		btnExcelPro.setIcon(new ImageIcon(Sistema.class.getResource("/Img/EXCEL2.png")));
		btnExcelPro.setBounds(530, 512, 52, 38);
		btnExcelPro.setBackground(new Color(18,217,227));

		panel_4.add(btnExcelPro);
		
		textIdpro = new JTextField();
		textIdpro.setBounds(676, 44, 8, 20);
		panel_4.add(textIdpro);
		textIdpro.setColumns(10);
		textIdpro.setVisible(false);
		
		
		
		
		JButton btnProveedor = new JButton("Proveedor");
		btnProveedor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarProveedor();
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/Img/proveedor.png")));
		btnProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProveedor.setBounds(28, 329, 169, 70);
		btnProveedor.setBackground(new Color(45, 235, 201));
		btnProveedor.setBorderPainted(false);

		panel.add(btnProveedor);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("New tab", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 99, 1074, 468);
		scrollPane_5.getViewport().setBackground(Color.LIGHT_GRAY);
		panel_6.add(scrollPane_5);
		
		tableIngresos = new JTable();
		tableIngresos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableIngresos.setForeground(Color.WHITE);
		tableIngresos.setBackground(Color.DARK_GRAY);
		JTableHeader headerPing = tableIngresos.getTableHeader();
		headerPing.setForeground(Color.DARK_GRAY);
		headerPing.setBackground(new Color(18, 217, 227));
		headerPing.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		tableIngresos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COD PRODUCTO", "PRODUCTO", "COSTO", "CANTIDAD", "TOTAL", "FECHA", "VENDEDOR"
			}
		));
		scrollPane_5.setViewportView(tableIngresos);
		
		
		
		JDateChooser fechaInicioIng = new JDateChooser();
		fechaInicioIng.setBackground(Color.DARK_GRAY);
		fechaInicioIng.setBounds(778, 61, 150, 27);
		panel_6.add(fechaInicioIng);
		
		JDateChooser fechafinIng = new JDateChooser();
		fechafinIng.setBackground(Color.DARK_GRAY);
		fechafinIng.setBounds(934, 61, 150, 27);
		panel_6.add(fechafinIng);
		
		JButton btnBuscarIngresos = new JButton("");
		btnBuscarIngresos.setIcon(new ImageIcon(Sistema.class.getResource("/Img/BUSCAR2.png")));
		btnBuscarIngresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            LimpiarTable();
				
				Date fechaInicial = fechaInicioIng.getDate();
				Date fechaFinal = fechafinIng.getDate();
				java.sql.Date fechaInicialSql = new java.sql.Date(fechaInicial.getTime());
				java.sql.Date fechaFinalSql = new java.sql.Date(fechaFinal.getTime());
				ListarIngresos(fechaInicialSql, fechaFinalSql);
			}
		});
		btnBuscarIngresos.setBounds(713, 49, 55, 39);
		btnBuscarIngresos.setBackground(new Color(18,217,227));
		panel_6.add(btnBuscarIngresos);
		
		JLabel lblNewLabel_9_1_1 = new JLabel("Fecha Inicial:");
		lblNewLabel_9_1_1.setForeground(Color.WHITE);
		lblNewLabel_9_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9_1_1.setBounds(778, 36, 131, 21);
		panel_6.add(lblNewLabel_9_1_1);
		
		JLabel lblNewLabel_9_2 = new JLabel("Fecha Final:");
		lblNewLabel_9_2.setForeground(Color.WHITE);
		lblNewLabel_9_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9_2.setBounds(934, 40, 113, 21);
		panel_6.add(lblNewLabel_9_2);
		
		JLabel lblNewLabel_26_1 = new JLabel("INGRESOS DE PRODUCTOS");
		lblNewLabel_26_1.setForeground(Color.WHITE);
		lblNewLabel_26_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_26_1.setBounds(10, 40, 548, 63);
		panel_6.add(lblNewLabel_26_1);
		
	
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);
		
		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 81, 1093, 418);
		scrollPane_4.getViewport().setBackground(Color.LIGHT_GRAY);
		panel_5.add(scrollPane_4);
		
		TableVentas = new JTable();
		TableVentas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TableVentas.setForeground(Color.WHITE);
		TableVentas.setBackground(Color.DARK_GRAY);
		JTableHeader headerVe = TableVentas.getTableHeader();
		headerVe.setForeground(Color.DARK_GRAY);
		headerVe.setBackground(new Color(18, 217, 227));
		headerVe.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		TableVentas.addMouseListener(new MouseAdapter() {
			//@Override
			
			    public void mousePressed(MouseEvent e) {
			        if (e.getClickCount() == 2) {
			        	int fila = TableVentas.rowAtPoint(e.getPoint());
			           
			            textIdVenta.setText(TableVentas.getValueAt(fila, 0).toString());
			            TableVentas.clearSelection(); // Desseleccionar la celda
			            


			            int idVenta = Integer.parseInt(textIdVenta.getText().toString());
					    mostrarDetalle(idVenta);
			            
			        }
			        if (e.getClickCount() == 1) {
			        	int fila = TableVentas.rowAtPoint(e.getPoint());
						textIdVenta.setText(TableVentas.getValueAt(fila, 0).toString());
			        }
			        
			    }
			});
			
		TableVentas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "CLIENTE", "VENDEDOR","FECHA", "TOTAL"
			}
		));
		scrollPane_4.setViewportView(TableVentas);
		
		JButton btnPdfVentas = new JButton("");
		btnPdfVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String path2 = System.getProperty("user.dir");
					int id = Integer.parseInt(textIdVenta.getText());
					File file = new File(path2+"/pdfs/venta"+id+".pdf");
					Desktop.getDesktop().open(file);
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPdfVentas.setIcon(new ImageIcon(Sistema.class.getResource("/Img/PDF2.png")));
		btnPdfVentas.setBounds(405, 510, 79, 55);
		btnPdfVentas.setBorderPainted(false);
		btnPdfVentas.setBackground(new Color(18,217,227));

		panel_5.add(btnPdfVentas);
		
		btnPdfVentas.setVisible(false);
		
		textIdVenta = new JTextField();
		textIdVenta.setText("");
		textIdVenta.setBounds(360, 11, 6, 20);
		panel_5.add(textIdVenta);
		textIdVenta.setColumns(10);
		textIdVenta.setVisible(false);
		
		
		JDateChooser Midate2 = new JDateChooser();
		Midate2.setBounds(960, 41, 143, 27);
		panel_5.add(Midate2);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha Final:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(960, 11, 113, 21);
		panel_5.add(lblNewLabel_9);
		
		JDateChooser Midate2_1 = new JDateChooser();
		Midate2_1.setBackground(Color.DARK_GRAY);
		Midate2_1.setBounds(807, 41, 143, 27);
		panel_5.add(Midate2_1);
		
		JLabel lblNewLabel_9_1 = new JLabel("Fecha Inicial:");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_9_1.setBounds(807, 9, 131, 21);
		panel_5.add(lblNewLabel_9_1);
		
		JLabel lblTotalVentas = new JLabel("...");
		lblTotalVentas.setForeground(Color.WHITE);
		lblTotalVentas.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTotalVentas.setBounds(920, 524, 183, 39);
		panel_5.add(lblTotalVentas);
		
		JButton btnBuscarV = new JButton("");
		btnBuscarV.setToolTipText("Buscar por fecha");

		btnBuscarV.setIcon(new ImageIcon(Sistema.class.getResource("/Img/BUSCAR2.png")));
		btnBuscarV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LimpiarTable();
				
				Date fechaInicial = Midate2_1.getDate();
				Date fechaFinal = Midate2.getDate();
				java.sql.Date fechaInicialSql = new java.sql.Date(fechaInicial.getTime());
				java.sql.Date fechaFinalSql = new java.sql.Date(fechaFinal.getTime());
				ListarVentasB(fechaInicialSql, fechaFinalSql);
				
				lblTotalVentas.setText(String.format("%.2f", TotalVentas()));
				
				
				
			}
		});
		btnBuscarV.setBounds(745, 31, 52, 39);
		btnBuscarV.setBackground(new Color(18,217,227));
		panel_5.add(btnBuscarV);
		
		JButton btnExcelVentas = new JButton("");
		btnExcelVentas.setToolTipText("Generar listado en excel");
		btnExcelVentas.setIcon(new ImageIcon(Sistema.class.getResource("/Img/EXCEL2.png")));
		btnExcelVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ExcelVentas.generarExcel(TableVentas);
					for (int i = 0; i < TableVentas.getRowCount(); i++) {
					    
					}
				} catch (IOException e1) {
					System.out.println("Error al generar Excel");
					e1.printStackTrace();
				}
			}
		});
		btnExcelVentas.setBounds(10, 510, 52, 39);
		btnExcelVentas.setBackground(new Color(18,217,227));
		btnExcelVentas.setBorderPainted(false);
		
		panel_5.add(btnExcelVentas);
		
		JLabel lblNewLabel_26_2 = new JLabel("VENTAS");
		lblNewLabel_26_2.setForeground(Color.WHITE);
		lblNewLabel_26_2.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_26_2.setBounds(10, 11, 345, 63);
		panel_5.add(lblNewLabel_26_2);
		
		JButton btnPdfVentas_1 = new JButton("");
		btnPdfVentas_1.setToolTipText("Mostrar detalle de la venta");
		btnPdfVentas_1.setIcon(new ImageIcon(Sistema.class.getResource("/Img/ver.png")));
		btnPdfVentas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ("".equals(textIdVenta.getText())) {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}else {
				
			    int idVenta = Integer.parseInt(textIdVenta.getText().toString());
			    mostrarDetalle(idVenta);


			   
				}
			}
		});
		btnPdfVentas_1.setBackground(new Color(18, 217, 227));
		btnPdfVentas_1.setBounds(72, 510, 52, 39);
		btnPdfVentas_1.setBorderPainted(false);
		panel_5.add(btnPdfVentas_1);
		
		JLabel lblNewLabel_32_1 = new JLabel("Total Ventas: ");
		lblNewLabel_32_1.setForeground(Color.WHITE);
		lblNewLabel_32_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_32_1.setBounds(745, 524, 165, 39);
		panel_5.add(lblNewLabel_32_1);
		
		
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
				LimpiarTable();
				//ListarVentas();
			}
		});
		btnVentas.setIcon(new ImageIcon(Sistema.class.getResource("/Img/VENTAS.png")));
		btnVentas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVentas.setBackground(new Color(45, 235, 201));
		btnVentas.setBounds(28, 410, 169, 70);
		btnVentas.setBorderPainted(false);

		panel.add(btnVentas);
		
		JButton btnNewButton = new JButton("Nueva Venta");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(45, 235, 201));
		btnNewButton.setBorderPainted(false);

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setIcon(new ImageIcon(Sistema.class.getResource("/Img/VENTA.png")));
		//btnNewButton.setBorder(null);
		
		//Border border = BorderFactory.createLineBorder(Color.RED, 5);

		// Establecer el borde personalizado en el botón
		//btnNewButton.setBorder(border);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				textCodigoVenta.requestFocus();
				
			}
		});
		btnNewButton.setBounds(28, 86, 169, 70);
		panel.add(btnNewButton);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.DARK_GRAY);
		panel_7.setBounds(24, 577, 173, 120);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		
		
		
		
		
		
		
		
		
		
		JLabel labelRol = new JLabel("<dynamic>");
		labelRol.setHorizontalAlignment(SwingConstants.CENTER);
		labelRol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelRol.setBounds(10, 84, 153, 33);
		panel_7.add(labelRol);
		labelRol.setForeground(Color.WHITE);
		labelRol.setText(rol);
		
		JLabel labelVendedor = new JLabel("");
		labelVendedor.setHorizontalAlignment(SwingConstants.CENTER);
		labelVendedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelVendedor.setBounds(10, 48, 153, 43);
		panel_7.add(labelVendedor);
		labelVendedor.setForeground(new Color(255, 255, 255));
		labelVendedor.setText(user);
		
		JLabel lblNewLabel_33 = new JLabel("");
		lblNewLabel_33.setIcon(new ImageIcon(Sistema.class.getResource("/Img/USUARIO3.png")));
		lblNewLabel_33.setBounds(63, 11, 48, 44);
		panel_7.add(lblNewLabel_33);
		
		JButton btnIngresos = new JButton("Ingresos");
		btnIngresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
				LimpiarTable();
			}
		});
		btnIngresos.setIcon(new ImageIcon(Sistema.class.getResource("/Img/entrada.png")));
		btnIngresos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIngresos.setBorderPainted(false);
		btnIngresos.setBackground(new Color(45, 235, 201));
		btnIngresos.setBounds(28, 491, 169, 70);
		panel.add(btnIngresos);
		System.out.println(labelRol.getText().toString());
		
		//btnNuevoCliente
		btnNuevoCliente.setBackground(new Color(18,217,227));
		
		JLabel lblNewLabel_26_4 = new JLabel("CLIENTES");
		lblNewLabel_26_4.setForeground(Color.WHITE);
		lblNewLabel_26_4.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_26_4.setBounds(277, 11, 345, 63);
		panel_2.add(lblNewLabel_26_4);
		btnNuevoProveedor.setBackground(new Color(18,217,227));
		
		JLabel lblNewLabel_26_3 = new JLabel("PROVEEDORES");
		lblNewLabel_26_3.setForeground(Color.WHITE);
		lblNewLabel_26_3.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_26_3.setBounds(302, 19, 430, 63);
		panel_3.add(lblNewLabel_26_3);

		
		JButton btnGenerarVenta = new JButton("");
		btnGenerarVenta.setToolTipText("Registrar venta");
		btnGenerarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableVenta.getRowCount() > 0) {
				String cliente = textNombreClienteventa.getText();
				String vendedor = labelVendedor.getText();
				Double monto = TotalPagar();
				
				
				v.setCliente(cliente);
				v.setVendedor(vendedor);
				v.setTotal(monto);
				//v.setFecha(fechaActual);
				Vdao.RegistrarVenta(v);	
				RegistrarDetalle();
				ActualizarStock();
				//pdf();
				//pdfVentasNew();
				
				JOptionPane.showMessageDialog(
			            null,
			            "La venta se ha registrado Exitosamente",
			            "Venta Registrada",
			            JOptionPane.INFORMATION_MESSAGE
			        );
				LimpiarTableVenta();
				LimpiarClienteVenta();
				}else {
					JOptionPane.showMessageDialog(null, "No hay productos en la venta");
				}
				
			}
		});
		btnGenerarVenta.setIcon(new ImageIcon(Sistema.class.getResource("/Img/icons8-cuenta-verificada-48.png")));
		btnGenerarVenta.setBounds(998, 494, 89, 54);
		//btnCliente.setBounds(10, 280, 202, 79);
		
		btnGenerarVenta.setBackground(new Color(255,87,97));
		btnGenerarVenta.setBorderPainted(false);
		panel_1.add(btnGenerarVenta);
		
		
		this.setLocationRelativeTo(null);
		textIdCliente.setVisible(false);
		AutoCompleteDecorator.decorate(cbxProveedorPro);
		proDao.ConsultarProveedor(cbxProveedorPro);
		
		textBuscarProducto = new JTextField();
		textBuscarProducto.setForeground(Color.WHITE);
		textBuscarProducto.setFont(new Font("Dialog", Font.BOLD, 14));
		textBuscarProducto.setColumns(10);
		textBuscarProducto.setBorder(border);
		textBuscarProducto.setBackground(Color.DARK_GRAY);
		textBuscarProducto.setBounds(843, 65, 260, 28);
		
		
		
		panel_4.add(textBuscarProducto);
		
		JLabel lblNewLabel_31 = new JLabel("Ingrese el nombre del producto");
		lblNewLabel_31.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_31.setForeground(Color.WHITE);
		lblNewLabel_31.setBounds(843, 44, 232, 20);
		panel_4.add(lblNewLabel_31);
		
		JButton btnBuscarProducto = new JButton("");
		btnBuscarProducto.setToolTipText("Buscar producto por nombre");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				String consulta= textBuscarProducto.getText();
				ListarProductosBu(consulta);
				
			}
		});
		btnBuscarProducto.setIcon(new ImageIcon(Sistema.class.getResource("/Img/BUSCAR2.png")));
		btnBuscarProducto.setBounds(758, 59, 75, 34);
		btnBuscarProducto.setBackground(new Color(18,217,227));
		panel_4.add(btnBuscarProducto);
		
		textCosto = new JTextField();
		textCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		textCosto.setForeground(Color.WHITE);
		textCosto.setFont(new Font("Dialog", Font.PLAIN, 17));
		textCosto.setColumns(10);
		textCosto.setBorder(border);
		textCosto.setBackground(Color.DARK_GRAY);
		textCosto.setBounds(114, 290, 157, 38);
		panel_4.add(textCosto);
		
		JLabel lblNewLabel_23_1 = new JLabel("Costo:");
		lblNewLabel_23_1.setForeground(Color.WHITE);
		lblNewLabel_23_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_23_1.setBounds(10, 295, 95, 29);
		panel_4.add(lblNewLabel_23_1);
		
		textAgregar = new JTextField();
		textAgregar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		textAgregar.setForeground(Color.WHITE);
		textAgregar.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAgregar.setColumns(10);
		textAgregar.setBorder(border);
		textAgregar.setBackground(Color.DARK_GRAY);
		textAgregar.setBounds(300, 512, 158, 41);
		panel_4.add(textAgregar);
		
		JButton btnAgregar = new JButton("");
		btnAgregar.setToolTipText("Ingresar producto");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int agregar= Integer.parseInt(textAgregar.getText());
				int nuevaCantidad= agregar + (Integer.parseInt(textCantPro.getText()));
				System.out.println("cantidad nueva: " + nuevaCantidad);
				double costoTotal=(Double.parseDouble(textCosto.getText()))*agregar;
				//*****
				if ("".equals(textIdPro.getText())) {
					JOptionPane.showMessageDialog(null, "seleccione una fila");
				}else {
					if ( !"".equals(textCantPro.getText()) ) {
						
						
						// Crear un array de Strings con las opciones del cuadro de diálogo
						String[] opciones = {"Sí", "Cancelar"};

						// Mostrar el cuadro de diálogo con las opciones personalizadas
						int respuesta = JOptionPane.showOptionDialog(null,"¿Esta seguro de ingresar el siguiente producto?\n"
								+"Producto: "+textDesPro.getText()+"\n"
								+"Cantidad: "+agregar+"\n"
								+"Costo unitario: "+textCosto.getText()
								+"\nCosto Total: "+costoTotal, "Confirmar", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
						

						// Comprobar qué opción fue seleccionada por el usuario
						if(respuesta == JOptionPane.YES_OPTION){
						
						pro.setStock(nuevaCantidad);
						
						pro.setId(Integer.parseInt(textIdPro.getText()));
						proDao.ModificarCantPro(pro);
						
						
						//se ingresa a la tabla ingresos
						
						ing.setCod_prod(textCodigoProducto.getText());
						ing.setDescripcion(textDesPro.getText());
						ing.setCosto(Double.parseDouble(textCosto.getText()));
						ing.setCantidad(Integer.parseInt(textAgregar.getText()));
						ing.setVendedor(labelVendedor.getText());
						ing.setTotal(costoTotal);
						
						proDao.RegistrarIngresos(ing);
						JOptionPane.showMessageDialog(null, "Se registro el ingreso");
						
						
						
						
						LimpiarTable();
						LimpiarProductos();
						cbxProveedorPro.setSelectedItem(null);
						ListarProductos();
						
						
					}
					}
				}
				
			}
		});
		btnAgregar.setIcon(new ImageIcon(Sistema.class.getResource("/Img/añadir2.png")));
		btnAgregar.setBounds(468, 512, 52, 41);
		btnAgregar.setBackground(new Color(45, 235, 201));
		btnAgregar.setBorderPainted(false);
		panel_4.add(btnAgregar);
		
		JLabel lblNewLabel_25 = new JLabel("Cantidad a ingresar:");
		lblNewLabel_25.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_25.setForeground(Color.WHITE);
		lblNewLabel_25.setBounds(300, 490, 164, 20);
		panel_4.add(lblNewLabel_25);
		
		JLabel lblNewLabel_26 = new JLabel("PRODUCTOS");
		lblNewLabel_26.setForeground(Color.WHITE);
		lblNewLabel_26.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_26.setBounds(281, 36, 345, 63);
		panel_4.add(lblNewLabel_26);
		
		JLabel lblNewLabel_34_1 = new JLabel("Software desarrollado por Kevin Bolaños");
		lblNewLabel_34_1.setForeground(Color.WHITE);
		lblNewLabel_34_1.setBounds(1551, 55, 253, 33);
		contentPane.add(lblNewLabel_34_1);
		
		
		
		String rol3= labelRol.getText().toString();
		
		/*if (rol3.equals("Administrador")) {
			textCantPro.setEditable(true);
			
		}else {
			
			textCantPro.setEditable(false);
		}*/
		
	
		
		
		ListarConfig();
		
		
	}

	//actualizar la tabla del detalle
	/*public void actualizarTabla(DefaultTableModel modelo) {
	    // Asignar el modelo a la tabla
	    tabla.setModel(modelo);
	}*/
	

	
	

public void ListarCliente() {

List<Cliente> ListarCl = client.listarCliente();
modelo= (DefaultTableModel) TableCliente.getModel();
TableCliente.setRowHeight(30);

Object[] ob = new Object[6];
for(int i = 0 ; i < ListarCl.size(); i++) {
	ob[0]= ListarCl.get(i).getId();
	ob[1]= ListarCl.get(i).getDni();
	ob[2]= ListarCl.get(i).getNombre();
	ob[3]= ListarCl.get(i).getTelefono();
	ob[4]= ListarCl.get(i).getDirrecion();
	ob[5]= ListarCl.get(i).getRazon();
	modelo.addRow(ob);
	
	
}
TableCliente.setModel(modelo);

}


public void ListarProveedor() {
List<Proveedor> ListarPr = prDao.ListarProveedor();
modelo= (DefaultTableModel) TableProveedor.getModel();
TableProveedor.setRowHeight(30);
Object[] ob = new Object[6];
for(int i = 0 ; i < ListarPr.size(); i++) {
	ob[0]= ListarPr.get(i).getId();
	ob[1]= ListarPr.get(i).getRuc();
	ob[2]= ListarPr.get(i).getNombre();
	ob[3]= ListarPr.get(i).getTelefono();
	ob[4]= ListarPr.get(i).getDireccion();
	ob[5]= ListarPr.get(i).getRazon();
	modelo.addRow(ob);
	
	
}
TableProveedor.setModel(modelo);

}

public void ListarProductos() {
List<Productos> ListarPro = proDao.ListarProductos();
modelo= (DefaultTableModel) TableProducto.getModel();
TableProducto.setRowHeight(30);
Object[] ob = new Object[7];
for(int i = 0 ; i < ListarPro.size(); i++) {
	ob[0]= ListarPro.get(i).getId();
	ob[1]= ListarPro.get(i).getCodigo();
	ob[2]= ListarPro.get(i).getNombre();
	ob[3]= ListarPro.get(i).getProveedor();
	ob[4]= ListarPro.get(i).getStock();
	ob[5]= ListarPro.get(i).getPrecio();
	ob[6]= ListarPro.get(i).getCosto();
	modelo.addRow(ob);
	
	
	
}
TableProducto.setModel(modelo);

}

public void mostrarDetalle(int idVenta) {
	 JFrame ventanaDetalles = new JFrame("Detalles de la Venta # " + idVenta);

	    // Establecer el icono de la ventana
	    //ventanaDetalles.setIconImage(icono.getImage());
	    //ventanaDetalles.setIconImage(null);
	    ventanaDetalles.getContentPane().setBackground(Color.BLACK);
	    // Crear el modelo de la tabla y obtener los datos de la venta
	    DefaultTableModel modeloTabla = proDao.ListarDetalleVenta(idVenta);

	    // Crear la tabla con el modelo y establecer su tamaño preferido
	    JTable tablaDetalles = new JTable(modeloTabla);
	    
	    Font font = new Font("Arial", Font.PLAIN, 18);
	    tablaDetalles.setFont(font);

	    // Establecer la altura de cada fila de la tabla
	    tablaDetalles.setRowHeight(30);
	  

	    // Establecer el color de fondo y de fuente de la tabla
	    tablaDetalles.setBackground(Color.LIGHT_GRAY);
	    tablaDetalles.setForeground(Color.BLACK);

	    // Establecer el color de fondo y de fuente del encabezado de la tabla
	    JTableHeader header = tablaDetalles.getTableHeader();
	    header.setBackground(new Color(18, 217, 227));
	    //header.setBackground(Color.BLUE);
	    header.setForeground(Color.WHITE);
	    header.setFont(new Font("Arial", Font.BOLD, 20));
	    //header.setPreferredSize(new Dimension(header.getWidth(), 30))
	    

	    // Quitar el logo de Java de la ventana
	    ventanaDetalles.setIconImage(null);

	    // Agregar la tabla al panel de scroll y este al panel principal de la ventana
	    JScrollPane scrollPaneD = new JScrollPane(tablaDetalles);
	    scrollPaneD.getViewport().setBackground(Color.DARK_GRAY);
	    scrollPaneD.getVerticalScrollBar().setBackground(Color.BLACK);
	    ventanaDetalles.getContentPane().add(scrollPaneD, BorderLayout.CENTER);
	    
	    
	    // Establecer las propiedades de la ventana y hacerla visible
	    ventanaDetalles.setSize(800, 600);
	    ventanaDetalles.setLocationRelativeTo(null);
	    
	    
	    ventanaDetalles.setVisible(true);
}

public void ListarProductosBu(String busqueda) {
List<Productos> ListarPro = proDao.ListarProductosBusqueda(busqueda);
modelo= (DefaultTableModel) TableProducto.getModel();
TableProducto.setRowHeight(30);

Object[] ob = new Object[7];
for(int i = 0 ; i < ListarPro.size(); i++) {
	ob[0]= ListarPro.get(i).getId();
	ob[1]= ListarPro.get(i).getCodigo();
	ob[2]= ListarPro.get(i).getNombre();
	ob[3]= ListarPro.get(i).getProveedor();
	ob[4]= ListarPro.get(i).getStock();
	ob[5]= ListarPro.get(i).getPrecio();
	ob[6]= ListarPro.get(i).getCosto();
	modelo.addRow(ob);
	
	
}
TableProducto.setModel(modelo);

}



public void ListarConfig() {
	conf= proDao.BuscarDatos();

}

public void ListarVentas() {
List<Venta> ListarVenta = Vdao.ListarVentas();
modelo= (DefaultTableModel) TableVentas.getModel();
TableVentas.setRowHeight(30);

Object[] ob = new Object[5];
for(int i = 0 ; i < ListarVenta.size(); i++) {
	ob[0]= ListarVenta.get(i).getId();
	ob[1]= ListarVenta.get(i).getCliente();
	ob[2]= ListarVenta.get(i).getVendedor();
	ob[3]= ListarVenta.get(i).getFecha();
	ob[4]= ListarVenta.get(i).getTotal();
	modelo.addRow(ob);
	
	
}
TableVentas.setModel(modelo);

}
//tengo que hacer la funcion para pasarle a la consulta
public void ListarVentasB(Date I, Date F) {

List<Venta> ListarVenta = Vdao. ListarVentasFecha(I,F);
modelo= (DefaultTableModel) TableVentas.getModel();
TableVentas.setRowHeight(30);

Object[] ob = new Object[5];
for(int i = 0 ; i < ListarVenta.size(); i++) {
	ob[0]= ListarVenta.get(i).getId();
	ob[1]= ListarVenta.get(i).getCliente();
	ob[2]= ListarVenta.get(i).getVendedor();
	ob[3]= ListarVenta.get(i).getFecha();
	ob[4]= ListarVenta.get(i).getTotal();
	
	modelo.addRow(ob);
	
	
}

TableVentas.setModel(modelo);


}

public void ListarIngresos(Date In, Date Fin) {

List<ingresos> ListarIngresos = proDao.ListarIngresosFecha(In, Fin);
modelo= (DefaultTableModel) tableIngresos.getModel();
tableIngresos.setRowHeight(30);

Object[] ob = new Object[7];
for(int i = 0 ; i < ListarIngresos.size(); i++) {
	ob[0]= ListarIngresos.get(i).getCod_prod();
	ob[1]= ListarIngresos.get(i).getDescripcion();
	ob[2]= ListarIngresos.get(i).getCosto();
	ob[3]= ListarIngresos.get(i).getCantidad();
	ob[4]= ListarIngresos.get(i).getTotal();
	ob[5]= ListarIngresos.get(i).getFecha();
	ob[6]= ListarIngresos.get(i).getVendedor();
	
	
	modelo.addRow(ob);
	
	
}
tableIngresos.setModel(modelo);

}

public void LimpiarTable() {
	
	for (int i = 0; i < modelo.getRowCount(); i++) {
		modelo.removeRow(i);
		i = i-1;
	}
}
private void LimpiarCliente() {
	textIdCliente.setText("");
	textDniCliente.setText("");
	textNombreCliente.setText("");
	textTelefonoCliente.setText("");
	textDireccionCliente.setText("");
	textRazonCV.setText("");
	
}

private void LimpiarProveedor() {
	textIdProveedor.setText("");
	textRucProveedor.setText("");
	textNombreProveedor.setText("");
	textTelefonoProveedor.setText("");
	textDireccionProveedor.setText("");
	textRazonProveedor.setText("");
	
}

private void LimpiarProductos() {
	textIdPro.setText("");
	textCodigoProducto.setText("");
	textDesPro.setText("");
	//cbxProveedorPro.setSelectedItem(null);
	textCantPro.setText("");
	textPrecioPro.setText("");
	textCosto.setText("");
	textAgregar.setText("");

}

private Double TotalPagar() {
	totalPagar= 0.00;
	int numFila = tableVenta.getRowCount();
	for (int i = 0; i < numFila; i++) {
		double cal = Double.parseDouble(String.valueOf(tableVenta.getModel().getValueAt(i, 4)));
		totalPagar = totalPagar+cal;
	}
	return totalPagar;
}

private Double TotalVentas() {
	totalVentas= 0.00;
	int numFila = TableVentas.getRowCount();
	for (int i = 0; i < numFila; i++) {
		double cal2 = Double.parseDouble(String.valueOf(TableVentas.getModel().getValueAt(i, 4)));
		totalVentas = totalVentas+cal2;
	}
	return totalVentas;
}

private void LimpiarVenta() {
	
	textCodigoVenta.setText("");
	textDescripcionVenta.setText("");
	textCantidadVenta.setText("");
	textStockDisponible.setText("");
	textPrecioVenta.setText("");
	textIdVenta.setText("");
}




private void RegistrarDetalle() {
	int id = Vdao.IdVenta();
	for (int i = 0; i < tableVenta.getRowCount(); i++) {
		String cod = tableVenta.getValueAt(i, 0).toString();
		int cant = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
		double precio = Double.parseDouble(tableVenta.getValueAt(i, 3).toString());
		
		Dv.setCod_pro(cod);
		Dv.setCantidad(cant);
		Dv.setPrecio(precio);
		Dv.setId(id);
		Vdao.RegistrarDetalle(Dv);
		
	}
}


private void ActualizarStock() {
	for (int i = 0; i < tableVenta.getRowCount(); i++) {
		String cod = tableVenta.getValueAt(i, 0).toString();
		int cant = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
		pro = proDao.BuscarPro(cod);
		int StockActual = pro.getStock() - cant;
		Vdao.ActualizarStock(StockActual, cod);
		
		
	}
}


private void LimpiarTableVenta() {
	tmp = (DefaultTableModel) tableVenta.getModel();
	int fila = tableVenta.getRowCount();
	for (int i = 0; i < fila; i++) {
		tmp.removeRow(0);
		
	}
}

private void LimpiarClienteVenta() {
	textRucVenta.setText("");
	textNombreClienteventa.setText("");
	textTelefonoCV.setText("");
	textDireccionCV.setText("");
	textRazonCV.setText("");
}

//JOPTIONPANEL DESCRIPSION VENTa

private void Descrip() {
	try {
		int id = Integer.parseInt(textIdVenta.getText());
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}




//----------------------------******-------------------**

private void pdf() {
	
	try {
		int id = Vdao.IdVenta();
		FileOutputStream archivo;
		
		//-------------------------------
		String path = System.getProperty("user.dir");
		
		File pdfsDir = new File(path + File.separator + "pdfs");

		if (!pdfsDir.exists()) {
		    pdfsDir.mkdir();
		}
		File file = new File(path+"/pdfs/venta"+id+".pdf");
		
		
		//File file = new File("src/pdf/venta"+id+".pdf");
		
		
		
		
		//***********************************************
		archivo = new FileOutputStream(file);
		Document doc = new Document();
		PdfWriter.getInstance(doc, archivo);
		doc.open();
		//Image img =Image.getInstance("src/Img/log.jpg");
		Image img =Image.getInstance(path+"/img/log.jpg");
		Paragraph fecha = new Paragraph();
		FontFactory.getFont("arial",
				Font.BOLD,
				BaseColor.BLUE);
		fecha.add(Chunk.NEWLINE);
		/*Date date = new Date();
		fecha.add("Factura: "+id+ "\n"+"Fecha "+ new SimpleDateFormat("dd-mm-yyyy").format(date)+ "\n \n");*/
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault()); // Establece la zona horaria local
		fecha.add("Factura: "+id+ "\n"+"Fecha "+ new SimpleDateFormat("dd-MM-yyyy HH:mm").format(calendar.getTime())+ "\n \n");
		
		PdfPTable Encabezado = new PdfPTable(4);
		Encabezado.setWidthPercentage(100);
		Encabezado.getDefaultCell().setBorder(0);
		float[] ColumnaEncabezado = new float[] {20f, 30f , 70f, 40f};
		Encabezado.setWidths(ColumnaEncabezado);
		Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Encabezado.addCell(img);
		
		/*String ruc = textRucConfig.getText();
		String nombre = textNombreConfig.getText();
		int tel = Integer.parseInt(textTelefonoConfig.getText());
		String dir = textDireccionConfig.getText();
		String ra = textRazonConfig.getText();*/
		
		//String ruc = textRucConfig.getText();
		String nombre = "TIENDA LA AMISTAD";
		String tel = "3222814394";
		String dir = "CLL 89 #26B1 - 21";
		
		
		Encabezado.addCell("");
		Encabezado.addCell("\nNombre: "+nombre+"\nTelefono: "+tel+"\nDireccion: "+dir);
		Encabezado.addCell(fecha);
		doc.add(Encabezado);
		
		Paragraph cli= new Paragraph();
		cli.add(Chunk.NEWLINE);
		cli.add("Datos del cliente "+"\n\n");
		doc.add(cli);
		
		
		PdfPTable tablacli = new PdfPTable(4);
		tablacli.setWidthPercentage(100);
		tablacli.getDefaultCell().setBorder(0);
		float[] Columnacli = new float[] {20f, 50f , 30f, 40f};
		tablacli.setWidths(Columnacli);
		tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cl1 = new PdfPCell(new Phrase("Identificacion"));
		PdfPCell cl2 = new PdfPCell(new Phrase("Nombre"));
		PdfPCell cl3 = new PdfPCell(new Phrase("Telefono"));
		PdfPCell cl4 = new PdfPCell(new Phrase("Direccion"));
		cl1.setBorder(0);
		cl2.setBorder(0);
		cl3.setBorder(0);
		cl4.setBorder(0);
		
		tablacli.addCell(cl1);
		tablacli.addCell(cl2);
		tablacli.addCell(cl3);
		tablacli.addCell(cl4);
		tablacli.addCell(textRucVenta.getText());
		tablacli.addCell(textNombreClienteventa.getText());
		tablacli.addCell(textTelefonoCV.getText());
		tablacli.addCell(textDireccionCV.getText());
		
		doc.add(tablacli);
		
		//Productos
		PdfPTable tablapro = new PdfPTable(4);
		tablapro.setWidthPercentage(100);
		tablapro.getDefaultCell().setBorder(0);
		float[] Columnapro = new float[] {10f, 50f , 15f, 20f};
		tablapro.setWidths(Columnapro);
		tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad"));
		PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion"));
		PdfPCell pro3 = new PdfPCell(new Phrase("Precio U."));
		PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total"));
		pro1.setBorder(0);
		pro2.setBorder(0);
		pro3.setBorder(0);
		pro4.setBorder(0);
		pro1.setBackgroundColor(BaseColor.DARK_GRAY);
		pro2.setBackgroundColor(BaseColor.DARK_GRAY);
		pro3.setBackgroundColor(BaseColor.DARK_GRAY);
		pro4.setBackgroundColor(BaseColor.DARK_GRAY);
		
		tablapro.addCell(pro1);
		tablapro.addCell(pro2);
		tablapro.addCell(pro3);
		tablapro.addCell(pro4);
		
		for (int i = 0; i < tableVenta.getRowCount(); i++) {
			String producto = tableVenta.getValueAt(i, 1).toString();
			String cantidad = tableVenta.getValueAt(i, 2).toString();
			String precio = tableVenta.getValueAt(i, 3).toString();
			String total = tableVenta.getValueAt(i, 4).toString();

			tablapro.addCell(cantidad);
			tablapro.addCell(producto);
			tablapro.addCell(precio);
			tablapro.addCell(total);

		}
		
		doc.add(tablapro);
		
		Paragraph info = new Paragraph();
		info.add(Chunk.NEWLINE);
		info.add("Total a Pagar: "+totalPagar);
		info.setAlignment(Element.ALIGN_RIGHT);
		doc.add(info);
		
		Paragraph firma = new Paragraph();
		firma.add(Chunk.NEWLINE);
		firma.add("Cancelacion y Firma\n\n");
		firma.add("------------------------");
		firma.setAlignment(Element.ALIGN_CENTER);
		doc.add(firma);
		
		Paragraph mensaje = new Paragraph();
		mensaje.add(Chunk.NEWLINE);
		mensaje.add("Gracias por su compra\n\n");
		mensaje.setAlignment(Element.ALIGN_CENTER);
		doc.add(mensaje);
		
		
		doc.close();
		archivo.close();
		Desktop.getDesktop().open(file);
	} catch (DocumentException | IOException e) {
		System.out.println(e.toString());
	}
}


private void pdfVentasNew() {
	
	try {
		int id = Vdao.IdVenta();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Document doc = new Document();
		PdfWriter.getInstance(doc, baos);
		doc.open();
		//contenido del pdf
		
		
		
		//-------------------------------
		String path = System.getProperty("user.dir");
		
		Image img =Image.getInstance(path+"/img/log.jpg");
		Paragraph fecha = new Paragraph();
		FontFactory.getFont("arial",
				Font.BOLD,
				BaseColor.BLUE);
		fecha.add(Chunk.NEWLINE);
		/*Date date = new Date();
		fecha.add("Factura: "+id+ "\n"+"Fecha "+ new SimpleDateFormat("dd-mm-yyyy").format(date)+ "\n \n");*/
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault()); // Establece la zona horaria local
		fecha.add("Factura: "+id+ "\n"+"Fecha "+ new SimpleDateFormat("dd-MM-yyyy HH:mm").format(calendar.getTime())+ "\n \n");
		
		PdfPTable Encabezado = new PdfPTable(4);
		Encabezado.setWidthPercentage(100);
		Encabezado.getDefaultCell().setBorder(0);
		float[] ColumnaEncabezado = new float[] {20f, 30f , 70f, 40f};
		Encabezado.setWidths(ColumnaEncabezado);
		Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Encabezado.addCell(img);
		
		/*String ruc = textRucConfig.getText();
		String nombre = textNombreConfig.getText();
		int tel = Integer.parseInt(textTelefonoConfig.getText());
		String dir = textDireccionConfig.getText();
		String ra = textRazonConfig.getText();*/
		
		//String ruc = textRucConfig.getText();
		String nombre = "TIENDA LA AMISTAD";
		String tel = "3222814394";
		String dir = "CLL 89 #26B1 - 21";
		
		
		Encabezado.addCell("");
		Encabezado.addCell("\nNombre: "+nombre+"\nTelefono: "+tel+"\nDireccion: "+dir);
		Encabezado.addCell(fecha);
		doc.add(Encabezado);
		
		Paragraph cli= new Paragraph();
		cli.add(Chunk.NEWLINE);
		cli.add("Datos del cliente "+"\n\n");
		doc.add(cli);
		
		
		PdfPTable tablacli = new PdfPTable(4);
		tablacli.setWidthPercentage(100);
		tablacli.getDefaultCell().setBorder(0);
		float[] Columnacli = new float[] {20f, 50f , 30f, 40f};
		tablacli.setWidths(Columnacli);
		tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cl1 = new PdfPCell(new Phrase("Identificacion"));
		PdfPCell cl2 = new PdfPCell(new Phrase("Nombre"));
		PdfPCell cl3 = new PdfPCell(new Phrase("Telefono"));
		PdfPCell cl4 = new PdfPCell(new Phrase("Direccion"));
		cl1.setBorder(0);
		
		cl2.setBorder(0);
		cl3.setBorder(0);
		cl4.setBorder(0);
		
		tablacli.addCell(cl1);
		tablacli.addCell(cl2);
		tablacli.addCell(cl3);
		tablacli.addCell(cl4);
		tablacli.addCell(textRucVenta.getText());
		tablacli.addCell(textNombreClienteventa.getText());
		tablacli.addCell(textTelefonoCV.getText());
		tablacli.addCell(textDireccionCV.getText());
		
		doc.add(tablacli);
		
		//Productos
		PdfPTable tablapro = new PdfPTable(4);
		tablapro.setWidthPercentage(100);
		tablapro.getDefaultCell().setBorder(0);
		float[] Columnapro = new float[] {10f, 50f , 15f, 20f};
		tablapro.setWidths(Columnapro);
		tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad"));
		PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion"));
		PdfPCell pro3 = new PdfPCell(new Phrase("Precio U."));
		PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total"));
		pro1.setBorder(0);
		pro2.setBorder(0);
		pro3.setBorder(0);
		pro4.setBorder(0);
		pro1.setBackgroundColor(BaseColor.DARK_GRAY);
		pro2.setBackgroundColor(BaseColor.DARK_GRAY);
		pro3.setBackgroundColor(BaseColor.DARK_GRAY);
		pro4.setBackgroundColor(BaseColor.DARK_GRAY);
		
		tablapro.addCell(pro1);
		tablapro.addCell(pro2);
		tablapro.addCell(pro3);
		tablapro.addCell(pro4);
		
		for (int i = 0; i < tableVenta.getRowCount(); i++) {
			String producto = tableVenta.getValueAt(i, 1).toString();
			String cantidad = tableVenta.getValueAt(i, 2).toString();
			String precio = tableVenta.getValueAt(i, 3).toString();
			String total = tableVenta.getValueAt(i, 4).toString();

			tablapro.addCell(cantidad);
			tablapro.addCell(producto);
			tablapro.addCell(precio);
			tablapro.addCell(total);

		}
		
		doc.add(tablapro);
		
		Paragraph info = new Paragraph();
		info.add(Chunk.NEWLINE);
		info.add("Total a Pagar: "+totalPagar);
		info.setAlignment(Element.ALIGN_RIGHT);
		doc.add(info);
		
		Paragraph firma = new Paragraph();
		firma.add(Chunk.NEWLINE);
		firma.add("Cancelacion y Firma\n\n");
		firma.add("------------------------");
		firma.setAlignment(Element.ALIGN_CENTER);
		doc.add(firma);
		
		Paragraph mensaje = new Paragraph();
		mensaje.add(Chunk.NEWLINE);
		mensaje.add("Gracias por su compra\n\n");
		mensaje.setAlignment(Element.ALIGN_CENTER);
		doc.add(mensaje);
		
		doc.close();
		
		//abrir el pdf
		byte[] pdfBytes = baos.toByteArray();
		Path tempFile = Files.createTempFile("venta" + id, ".pdf");
		Files.write(tempFile, pdfBytes);
		Desktop.getDesktop().open(tempFile.toFile());
	} catch (DocumentException | IOException e) {
		JOptionPane.showMessageDialog(null, "no se encontro el documento");
		System.out.println(e.toString());
		
	}
}








private void pdfVentas() {
	
	try {
		int id = Vdao.IdVenta();
		FileOutputStream archivo;
		
		//-------------------------------
		String path = System.getProperty("user.dir");
		
		File pdfsDir = new File(path + File.separator + "pdfs");

		if (!pdfsDir.exists()) {
		    pdfsDir.mkdir();
		}
		File file = new File(path+"/pdfs/venta"+id+".pdf");
		
		
		//File file = new File("src/pdf/venta"+id+".pdf");
		
		
		
		
	
		archivo = new FileOutputStream(file);
		Document doc = new Document();
		PdfWriter.getInstance(doc, archivo);
		doc.open();
		//Image img =Image.getInstance("src/Img/log.jpg");
		Image img =Image.getInstance(path+"/img/log.jpg");
		Paragraph fecha = new Paragraph();
		FontFactory.getFont("arial",
				Font.BOLD,
				BaseColor.BLUE);
		fecha.add(Chunk.NEWLINE);
		/*Date date = new Date();
		fecha.add("Factura: "+id+ "\n"+"Fecha "+ new SimpleDateFormat("dd-mm-yyyy").format(date)+ "\n \n");*/
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault()); // Establece la zona horaria local
		fecha.add("Factura: "+id+ "\n"+"Fecha "+ new SimpleDateFormat("dd-MM-yyyy HH:mm").format(calendar.getTime())+ "\n \n");
		
		PdfPTable Encabezado = new PdfPTable(4);
		Encabezado.setWidthPercentage(100);
		Encabezado.getDefaultCell().setBorder(0);
		float[] ColumnaEncabezado = new float[] {20f, 30f , 70f, 40f};
		Encabezado.setWidths(ColumnaEncabezado);
		Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Encabezado.addCell(img);
		
		/*String ruc = textRucConfig.getText();
		String nombre = textNombreConfig.getText();
		int tel = Integer.parseInt(textTelefonoConfig.getText());
		String dir = textDireccionConfig.getText();
		String ra = textRazonConfig.getText();*/
		
		//String ruc = textRucConfig.getText();
		String nombre = "TIENDA LA AMISTAD";
		String tel = "3222814394";
		String dir = "CLL 89 #26B1 - 21";
		
		
		Encabezado.addCell("");
		Encabezado.addCell("\nNombre: "+nombre+"\nTelefono: "+tel+"\nDireccion: "+dir);
		Encabezado.addCell(fecha);
		doc.add(Encabezado);
		
		Paragraph cli= new Paragraph();
		cli.add(Chunk.NEWLINE);
		cli.add("Datos del cliente "+"\n\n");
		doc.add(cli);
		
		
		PdfPTable tablacli = new PdfPTable(4);
		tablacli.setWidthPercentage(100);
		tablacli.getDefaultCell().setBorder(0);
		float[] Columnacli = new float[] {20f, 50f , 30f, 40f};
		tablacli.setWidths(Columnacli);
		tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cl1 = new PdfPCell(new Phrase("Identificacion"));
		PdfPCell cl2 = new PdfPCell(new Phrase("Nombre"));
		PdfPCell cl3 = new PdfPCell(new Phrase("Telefono"));
		PdfPCell cl4 = new PdfPCell(new Phrase("Direccion"));
		cl1.setBorder(0);
		cl2.setBorder(0);
		cl3.setBorder(0);
		cl4.setBorder(0);
		
		tablacli.addCell(cl1);
		tablacli.addCell(cl2);
		tablacli.addCell(cl3);
		tablacli.addCell(cl4);
		tablacli.addCell(textRucVenta.getText());
		tablacli.addCell(textNombreClienteventa.getText());
		tablacli.addCell(textTelefonoCV.getText());
		tablacli.addCell(textDireccionCV.getText());
		
		doc.add(tablacli);
		
		//Productos
		PdfPTable tablapro = new PdfPTable(4);
		tablapro.setWidthPercentage(100);
		tablapro.getDefaultCell().setBorder(0);
		float[] Columnapro = new float[] {10f, 50f , 15f, 20f};
		tablapro.setWidths(Columnapro);
		tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad"));
		PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion"));
		PdfPCell pro3 = new PdfPCell(new Phrase("Precio U."));
		PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total"));
		pro1.setBorder(0);
		pro2.setBorder(0);
		pro3.setBorder(0);
		pro4.setBorder(0);
		pro1.setBackgroundColor(BaseColor.DARK_GRAY);
		pro2.setBackgroundColor(BaseColor.DARK_GRAY);
		pro3.setBackgroundColor(BaseColor.DARK_GRAY);
		pro4.setBackgroundColor(BaseColor.DARK_GRAY);
		
		tablapro.addCell(pro1);
		tablapro.addCell(pro2);
		tablapro.addCell(pro3);
		tablapro.addCell(pro4);
		
		for (int i = 0; i < tableVenta.getRowCount(); i++) {
			String producto = tableVenta.getValueAt(i, 1).toString();
			String cantidad = tableVenta.getValueAt(i, 2).toString();
			String precio = tableVenta.getValueAt(i, 3).toString();
			String total = tableVenta.getValueAt(i, 4).toString();

			tablapro.addCell(cantidad);
			tablapro.addCell(producto);
			tablapro.addCell(precio);
			tablapro.addCell(total);

		}
		
		doc.add(tablapro);
		
		Paragraph info = new Paragraph();
		info.add(Chunk.NEWLINE);
		info.add("Total a Pagar: "+totalPagar);
		info.setAlignment(Element.ALIGN_RIGHT);
		doc.add(info);
		
		Paragraph firma = new Paragraph();
		firma.add(Chunk.NEWLINE);
		firma.add("Cancelacion y Firma\n\n");
		firma.add("------------------------");
		firma.setAlignment(Element.ALIGN_CENTER);
		doc.add(firma);
		
		Paragraph mensaje = new Paragraph();
		mensaje.add(Chunk.NEWLINE);
		mensaje.add("Gracias por su compra\n\n");
		mensaje.setAlignment(Element.ALIGN_CENTER);
		doc.add(mensaje);
		
		
		doc.close();
		archivo.close();
		Desktop.getDesktop().open(file);
	} catch (DocumentException | IOException e) {
		System.out.println(e.toString());
	}
}
}

/* funcion que muestra el pdf pero no lo guarda
  private void pdfVentas() {
	
	try {
		int id = Vdao.IdVenta();
		
		//-------------------------------
		String path = System.getProperty("user.dir");
		
		File pdfsDir = new File(path + File.separator + "pdfs");

		if (!pdfsDir.exists()) {
		    pdfsDir.mkdir();
		}
		File file = new File(path+"/pdfs/venta"+id+".pdf");
		
		
		}catch (DocumentException | IOException e) {
		System.out.println(e.toString());
	}
}

		 * */
