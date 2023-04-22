package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Transbus;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.SystemColor;

public class Interfaz_principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnboton_marcas;
	private JMenuItem mnboton_usuario;
	private JMenuItem mnboton_marca;
	private JMenu mnboton_carros;
	private JMenuItem mnboton_carro;
	private JMenuItem mnboton_hoja_ruta;
	private JMenuItem mncombustible;
	private JMenuItem mnservicios;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_3;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_principal frame = new Interfaz_principal();
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
	public Interfaz_principal() {
		setTitle("Transbus");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 457);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Gestionar");
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setFont(new Font("Arial Black", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		
		mnboton_usuario = new JMenuItem("Usuario");
		mnboton_usuario.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnboton_usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario_interfaz user;
				try {
					user = new Usuario_interfaz();
					user.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mnboton_usuario);
		
		mncombustible = new JMenuItem("Combustible");
		mncombustible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Combustible_interfaz combus;
				try {
					combus = new Combustible_interfaz();
					combus.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mncombustible.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnNewMenu.add(mncombustible);
		
		mnboton_marcas = new JMenu("Marcas");
		mnboton_marcas.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnNewMenu.add(mnboton_marcas);
		
		mnboton_marca = new JMenuItem("Marca");
		mnboton_marca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Marca_Interfaz marca = new Marca_Interfaz();
					marca.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnboton_marca.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnboton_marcas.add(mnboton_marca);
		
		mnboton_carros = new JMenu("Carros");
		mnboton_carros.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnboton_marcas.add(mnboton_carros);
		
		JMenuItem mnboton_flotilla = new JMenuItem("Flotilla");
		mnboton_flotilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Flotilla_Visual flo = new Flotilla_Visual();
					flo.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnboton_carros.add(mnboton_flotilla);
		mnboton_flotilla.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		mnboton_carro = new JMenuItem("Carro");
		mnboton_carro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Carro_interfaz carro = new Carro_interfaz();
					carro.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnboton_carro.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnboton_carros.add(mnboton_carro);
		
		mnboton_hoja_ruta = new JMenuItem("Hoja de ruta");
		mnboton_hoja_ruta.setFont(new Font("Calibri", Font.PLAIN, 12));
		mnboton_carros.add(mnboton_hoja_ruta);
		
		mnNewMenu_3 = new JMenu("Chofer");
		mnNewMenu.add(mnNewMenu_3);
		
		mntmNewMenuItem_3 = new JMenuItem("Chofer");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Chofer_interfaz chofer;
				try {
					chofer = new Chofer_interfaz();
					chofer.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_2 = new JMenuItem("Hoja de ruta");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Hoja_ruta_interfaz hoja;
				try {
					hoja = new Hoja_ruta_interfaz();
					hoja.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mnservicios = new JMenuItem("Servicios");
		mnNewMenu.add(mnservicios);
		
		mnNewMenu_1 = new JMenu("Opciones");
		mnNewMenu_1.setForeground(SystemColor.desktop);
		mnNewMenu_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_1);
		
		mntmNewMenuItem = new JMenuItem("Ayuda");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Salir");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Identificacion iden = new Identificacion();
				iden.setVisible(true);
				dispose();
			}
		});
		
		mnNewMenu_2 = new JMenu("Eliminaci\u00F3n avanzado");
		mnNewMenu_1.add(mnNewMenu_2);
		mnNewMenu_1.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		actualizar();
	}
	
	public void actualizar() {
		if(Transbus.getTransbus().usuario_rol == 1) {
			mncombustible.setVisible(false);
			mnservicios.setVisible(false);
			mnboton_marcas.setVisible(false);
		}
		else {
			mnboton_usuario.setVisible(false);
		}
	}
	
	public void actualizar_botones() {
		
	}

}
