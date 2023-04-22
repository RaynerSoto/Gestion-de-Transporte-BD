package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import services.ChoferServices;
import services.DistritoSerices;
import services.Marca_serv;
import services.ProvinciaServices;
import utils.ConnectionManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Interfaces_creadas.JTextFieldCarnet;
import model.Transbus;

public class Chofer_interfaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_nombre;
	private JTextField textField_direccion;
	private JPanel panel2;
	private JCheckBox fijoornot;
	private JTable table;
	private JComboBox comboBox_Provincia;
	private JComboBox comboBox_Marca;
	private JTextFieldCarnet textFieldCarnet_Telefono;
	private JTextFieldCarnet textFieldCarnet;
	private JComboBox comboBox_Auto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Chofer_interfaz dialog = new Chofer_interfaz();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Chofer_interfaz() throws ClassNotFoundException, SQLException {
		setTitle("Chofer");
		setBounds(100, 100, 652, 725);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 414, 325);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Nombre");
				lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel.setBounds(10, 11, 78, 34);
				panel.add(lblNewLabel);
			}
			{
				textField_nombre = new JTextField();
				textField_nombre.setFont(new Font("Calibri", Font.PLAIN, 16));
				textField_nombre.setBounds(98, 11, 308, 34);
				panel.add(textField_nombre);
				textField_nombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("CI");
				lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_1.setBounds(10, 56, 78, 34);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Direcci\u00F3n");
				lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(10, 101, 78, 34);
				panel.add(lblNewLabel_2);
			}
			{
				textField_direccion = new JTextField();
				textField_direccion.setBounds(98, 101, 308, 34);
				panel.add(textField_direccion);
				textField_direccion.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Tel\u00E9fono");
				lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_3.setBounds(10, 146, 78, 34);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Provincia");
				lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_4.setBounds(10, 191, 78, 34);
				panel.add(lblNewLabel_4);
			}
			{
				comboBox_Provincia = new JComboBox();
				comboBox_Provincia.setBounds(98, 191, 308, 34);
				panel.add(comboBox_Provincia);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Marca");
				lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_5.setBounds(10, 236, 78, 34);
				panel.add(lblNewLabel_5);
			}
			{
				comboBox_Marca = new JComboBox();
				comboBox_Marca.setBounds(98, 236, 308, 34);
				panel.add(comboBox_Marca);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Fijo");
				lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_6.setBounds(10, 281, 78, 33);
				panel.add(lblNewLabel_6);
			}
			
			fijoornot = new JCheckBox("Aceptar");
			fijoornot.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					actualizar_chekbox();
				}
			});
			fijoornot.setBounds(98, 286, 97, 23);
			panel.add(fijoornot);
			
			textFieldCarnet = new JTextFieldCarnet();
			textFieldCarnet.setBounds(98, 56, 308, 34);
			panel.add(textFieldCarnet);
			
			textFieldCarnet_Telefono = new JTextFieldCarnet();
			textFieldCarnet_Telefono.setBounds(98, 146, 308, 34);
			panel.add(textFieldCarnet_Telefono);
		}
		{
			panel2 = new JPanel();
			panel2.setBounds(434, 11, 181, 91);
			contentPanel.add(panel2);
			panel2.setLayout(null);
			{
				JLabel lblNewLabel_7 = new JLabel("Seleccionar el auto");
				lblNewLabel_7.setBounds(8, 5, 132, 20);
				lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel2.add(lblNewLabel_7);
			}
			{
				comboBox_Auto = new JComboBox();
				comboBox_Auto.setBounds(8, 47, 163, 34);
				panel2.add(comboBox_Auto);
			}
		}
		{
			JButton btnNewButton = new JButton("A\u00F1adir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(validar() == true) {
						if(validar_carnet() == true) {
							String distrito = (String) comboBox_Provincia.getSelectedItem();
							String marca = (String) comboBox_Marca.getSelectedItem();
							long id_distrito = -1;
							long id_marca = -1;
							try {
								id_distrito = DistritoSerices.buscar_distrito_id(distrito);
								id_marca = Marca_serv.buscar_marca_ID(marca);
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(validar_telefono() == true) {
								if(fijoornot.isSelected() == false) {
									try {
										ChoferServices.añadir_chofer_cubre_franco(textFieldCarnet.getText(),textField_nombre.getText(),textField_direccion.getText(),textFieldCarnet_Telefono.getText(),id_distrito,id_marca);
										JOptionPane.showMessageDialog(Chofer_interfaz.this,"Chofer cubre-franco insertado con exito","Conseguido",JOptionPane.INFORMATION_MESSAGE);
									} catch (ClassNotFoundException | SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							}
							else {
								JOptionPane.showMessageDialog(Chofer_interfaz.this,"El número de caracteres del teléfono es menor que 8","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(Chofer_interfaz.this,"El carnet no tiene 11 números","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(Chofer_interfaz.this,"Datos obligatorios vacíos","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnNewButton.setBounds(434, 113, 181, 34);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Modificar");
			btnNewButton_1.setBounds(434, 158, 181, 34);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_2 = new JButton("Eliminar");
			btnNewButton_2.setBounds(434, 203, 181, 34);
			contentPanel.add(btnNewButton_2);
		}
		{
			table = new JTable();
			table.setFont(new Font("Calibri", Font.PLAIN, 16));
			table.setBounds(20, 347, 606, 328);
			contentPanel.add(table);
			llenar_combobox_provincias();
			llenar_combox_marcas();
			llenar_combox_autos();
		}
	}
	public void actualizar_chekbox() {
		if(fijoornot.isSelected() == false) {
			panel2.setVisible(false);
		}
		else
			panel2.setVisible(true);
	}
	
	 public void llenar_combobox_provincias() throws ClassNotFoundException, SQLException {
		 DefaultComboBoxModel<String> comb = new DefaultComboBoxModel<String>();
		 ArrayList<String>listado = ProvinciaServices.cargar_provincias();
		 for(int contador = 0; contador<listado.size();contador++) {
			 comb.addElement(listado.get(contador));
		 }
		 comboBox_Provincia.setModel(comb);
	}
	 
	 public void llenar_combox_marcas() throws ClassNotFoundException, SQLException {
		 DefaultComboBoxModel<String> comb = new DefaultComboBoxModel<String>();
		 ArrayList<String>listado = Marca_serv.cargar_marcar_nombre();
		 for(int contador = 0; contador<listado.size();contador++) {
			 comb.addElement(listado.get(contador));
		 }
		 comboBox_Marca.setModel(comb);

	 }
	 
	 public void llenar_combox_autos() throws ClassNotFoundException, SQLException {
		 DefaultComboBoxModel<String> comb = new DefaultComboBoxModel<String>();
		 for(int contador = 0; contador<Controladora.transbus.gettransbus().getCarros().size();contador++) {
			 comb.addElement(Controladora.transbus.gettransbus().getCarros().get(contador).getPlaca());
		 }
		 comboBox_Auto.setModel(comb);
	 }
	 
	 public boolean validar() {
		 boolean verdad = true;
		 if(textField_nombre.getText().isEmpty() == true || textField_direccion.getText().isEmpty() == true || textFieldCarnet_Telefono.getText().isEmpty() == true || textFieldCarnet.getText().isEmpty() == true) 
		 {
			 verdad = false;
		 }
		 return verdad;
	 }
	 
	 public boolean validar_carnet() {
		 boolean verdad = true;
		 if(textFieldCarnet.getText().length() != 11) {
			 verdad = false;
		 }
		 return verdad;
	 }
	 
	 public boolean validar_telefono() {
		 boolean verdad = true;
		 if(textFieldCarnet_Telefono.getText().length()<8) {
			 verdad = false;
		 }
		 return verdad;
	 }
	 
}
