package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.login;
import modelo.loginDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Registro extends JFrame {
	login lg = new login();
	loginDAO login =  new loginDAO();
	

	private JPanel contentPane;
	private JTextField textcorreo;
	private JPasswordField txtpas;
	private JTextField textNombre;
	
	public void validar() {
		
		JComboBox cbxRol2 = new JComboBox();
		cbxRol2.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Cajero"}));
		cbxRol2.setBounds(70, 262, 219, 22);
		contentPane.add(cbxRol2);
		
		String correo = textcorreo.getText();
		String pass = String.valueOf(txtpas.getPassword());
		String nom = textNombre.getText();
		String rol = cbxRol2.getSelectedItem().toString();
		if(!"".equals(correo) || !"".equals(pass) || !"".equals(nom)) {
			
			lg.setNombre(nom);
			lg.setCorreo(correo);
			lg.setPass(pass);
			lg.setRol(rol);
			login.Registrar(lg);
			
			
				
			Login iniciar = new Login(); 
			iniciar.setVisible(true);
			dispose();
			
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(356, 11, 324, 346);
		lblNewLabel.setIcon(new ImageIcon(Registro.class.getResource("/Img/login.jpg")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Correo Electronico");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBackground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(70, 36, 219, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setBackground(new Color(0, 0, 255));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2.setBounds(70, 107, 219, 14);
		contentPane.add(lblNewLabel_2);
		
		textcorreo = new JTextField();
		textcorreo.setBounds(70, 61, 219, 20);
		contentPane.add(textcorreo);
		textcorreo.setColumns(10);
		
		txtpas = new JPasswordField();
		txtpas.setBounds(70, 132, 219, 20);
		contentPane.add(txtpas);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(125, 311, 115, 46);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_3.setBounds(70, 176, 219, 14);
		contentPane.add(lblNewLabel_3);
		
		textNombre = new JTextField();
		textNombre.setBounds(70, 192, 219, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Rol:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(0, 0, 255));
		lblNewLabel_4.setBounds(70, 244, 219, 14);
		contentPane.add(lblNewLabel_4);
		
		JComboBox cbxRol2 = new JComboBox();
		cbxRol2.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Cajero"}));
		cbxRol2.setBounds(70, 266, 219, 22);
		contentPane.add(cbxRol2);
		
		
	}
}
