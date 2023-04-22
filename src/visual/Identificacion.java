package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Transbus;
import services.DBServices;
import utils.ConnectionManager;
import utils.Encription;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Identificacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jTextFieldUsuario;
	private JButton btn_conectar;
	private JButton btn_cancelar;
	private JPasswordField JPassword_contrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionManager.GetInstance();
					Identificacion frame = new Identificacion();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Identificacion() {
		setTitle("Ingresar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlabel_nombre_usuario = new JLabel(" Nombre de Usuario");
		jlabel_nombre_usuario.setBounds(70, 27, 118, 26);
		contentPane.add(jlabel_nombre_usuario);
		
		jTextFieldUsuario = new JTextField();
		jTextFieldUsuario.setBounds(198, 30, 148, 20);
		contentPane.add(jTextFieldUsuario);
		jTextFieldUsuario.setColumns(10);
		
		JLabel lbl_contraseña = new JLabel("Contrase\u00F1a");
		lbl_contraseña.setBounds(70, 71, 100, 26);
		contentPane.add(lbl_contraseña);
		
		btn_conectar = new JButton("Conectar");
		btn_conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String usuario=jTextFieldUsuario.getText();
					if(DBServices.Login(usuario, Encription.getMd5(new String(JPassword_contrasena.getPassword())))) 
					{
						Transbus.getTransbus().usuario_rol=DBServices.Rol(usuario);
						Transbus.getTransbus().usuario_nombre=usuario;
						Interfaz_principal inter = new Interfaz_principal();
						dispose();
						inter.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(Identificacion.this,"Usuario y contraseña incorrectos","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(Identificacion.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btn_conectar.setBounds(70, 123, 89, 23);
		contentPane.add(btn_conectar);
		
		btn_cancelar = new JButton("Finalizar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_cancelar.setBounds(257, 123, 89, 23);
		contentPane.add(btn_cancelar);
		
		JPassword_contrasena = new JPasswordField();
		JPassword_contrasena.setBounds(198, 74, 148, 20);
		contentPane.add(JPassword_contrasena);
	}
	
	public Identificacion returnThis(){
		return this;
	}

}
