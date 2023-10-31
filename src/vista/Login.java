package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import modelo.login;
import modelo.loginDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	login lg = new login();
	loginDAO login =  new loginDAO();
	
	Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE); // crear un borde inferior personalizado
	Color miColor = new Color(255,87,97);
	

	private JPanel contentPane;
	private JTextField textcorreo;
	private JPasswordField txtpas;
	
	public void validar() {
		
		String correo = textcorreo.getText();
		String pass = String.valueOf(txtpas.getPassword());
		if(!"".equals(correo) || !"".equals(pass)) {
			
			
			lg = login.log(correo, pass);
			
			if(lg.getCorreo() != null && lg.getPass() != null) {
				String nombre=lg.getNombre();
				String rol= lg.getRol();
				Sistema sis = new Sistema(nombre, rol); 
				sis.setVisible(true);
				dispose();
			}else {
				
				JOptionPane.showMessageDialog(null, "correo o la contraseña incorrectos");
			}
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		//deseño joptionPane
		UIManager.put("OptionPane.background", Color.GRAY);
		UIManager.put("Panel.background", Color.GRAY);
		UIManager.put("Button.background", new Color(45, 235, 201));
		UIManager.put("Button.foreground", Color.BLACK);
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
		//
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 648);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11, 773, 306);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Img/TIENDA4.jpg")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(362, 328, 81, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(340, 414, 112, 32);
		contentPane.add(lblNewLabel_2);
		
		textcorreo = new JTextField();
		textcorreo.setForeground(Color.WHITE);
		textcorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textcorreo.setBackground(Color.DARK_GRAY);
		textcorreo.setBounds(291, 372, 219, 32);
		textcorreo.setBorder(border); 
		contentPane.add(textcorreo);
		textcorreo.setColumns(10);
		
		txtpas = new JPasswordField();
		txtpas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpas.setBackground(Color.DARK_GRAY);
		txtpas.setForeground(Color.WHITE);
		txtpas.setBounds(291, 457, 219, 32);
		txtpas.setBorder(border); 
		contentPane.add(txtpas);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(340, 532, 128, 61);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(45, 235, 201));
		
		JLabel lblNewLabel_3 = new JLabel("Desarrollado por\n Kevin Bolaños");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(610, 576, 181, 33);
		contentPane.add(lblNewLabel_3);
	}
}
