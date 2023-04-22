package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import Controladora.transbus;

import model.Combustible;
import services.Combustible_serv;
import utils.ConnectionManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Combustible_interfaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_nombre;
	private JTable table;
	private JButton eliminar;
	private JButton modificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Combustible_interfaz dialog = new Combustible_interfaz();
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
	public Combustible_interfaz() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Combustible");
		setBounds(100, 100, 406, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JPanel panel = new JPanel();
			panel.setBounds(25, 11, 314, 58);
			contentPanel.add(panel);
			panel.setLayout(null);
			
				JLabel lblNewLabel = new JLabel("Nombre");
				lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel.setBounds(10, 11, 97, 40);
				panel.add(lblNewLabel);
			
				textField_nombre = new JTextField();
				textField_nombre.setFont(new Font("Calibri", Font.PLAIN, 16));
				textField_nombre.setBounds(117, 11, 187, 40);
				panel.add(textField_nombre);
				textField_nombre.setColumns(10);
		
			JButton Insertar = new JButton("Insertar");
			Insertar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String nombre = textField_nombre.getText();
					
					try {
						insertar_combustible(nombre);
						Actualizar_Tabla();
						limpiar();
						JOptionPane.showConfirmDialog(Combustible_interfaz.this, "Combustible insertado", "OK", JOptionPane.INFORMATION_MESSAGE);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(Combustible_interfaz.this,e.getException(),"Error",JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(Combustible_interfaz.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			Insertar.setBounds(10, 80, 117, 38);
			contentPanel.add(Insertar);
		
			modificar = new JButton("Modificar");
			modificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
				      if(table.getSelectedColumn()==1) {
						try {
							Modificar();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(Combustible_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(Combustible_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					
				} else
					JOptionPane.showMessageDialog(Combustible_interfaz.this,"Usted no ha seleccionado en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
				}
				      
			});
			modificar.setBounds(137, 80, 117, 38);
			contentPanel.add(modificar);
		
			eliminar = new JButton("Eliminar");
			eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(JOptionPane.showConfirmDialog(Combustible_interfaz.this,"¿Desea eliminar?" ,"Confirmar" , JOptionPane.YES_OPTION)== JOptionPane.YES_OPTION){
						int poss = -1;
						for(int contador=0;contador<table.getSelectedRows().length;contador++){
							try {
								poss = combustible_elegido(contador);
								if(poss!=-1){
									eliminar(poss);
									actualizarlista();
									Actualizar_Tabla();
									limpiar();
								}
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(Combustible_interfaz.this,"Este combustible esta siendo usado por un vehículo. Elimine el vehiculo para continuar","Error",JOptionPane.ERROR_MESSAGE);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(Combustible_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} 
				
				
			});
			eliminar.setBounds(264, 80, 108, 38);
			contentPanel.add(eliminar);
			
			
			table = new JTable();
			table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					try {
						Seleccionar_fila();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			table.setBounds(10, 126, 370, 277);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						Seleccionar_fila();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			contentPanel.add(table);
	        Actualizar_Tabla();
	}
	 

	 public void insertar_combustible(String nombre) throws ClassNotFoundException, SQLException {
		Combustible_serv.añadir_combustible(nombre);
		 
	 }
	 
	public void eliminar(int poss) throws ClassNotFoundException, SQLException {
		Combustible_serv.eliminar_combustible(transbus.gettransbus().getCombustible().get(poss).getId());
		 
	 }
	public void Modificar() throws ClassNotFoundException, SQLException {
		
		int poss = table.getSelectedRow();
		String nombre = (String) table.getValueAt(poss, 0);
		
			if(nombre.isEmpty() == false) {
				if(validar() == true) {
					Combustible_serv.modificar(textField_nombre.getText(),Combustible_serv.id_buscar_nombre(nombre));
					actualizarlista();
					Actualizar_Tabla();
					limpiar();
				}
				else {
					JOptionPane.showMessageDialog(Combustible_interfaz.this, "El campo no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		
		
	}

	public void llenarCampo(String combustible) {
		textField_nombre.setText(combustible);
		
	}
	public int combustible_elegido(int contador) throws ClassNotFoundException, SQLException {
		return transbus.gettransbus().buscar_pos_combustible((String)table.getValueAt(table.getSelectedRows()[contador], 0));
	}
	public void limpiar() {
		textField_nombre.setText("");
	}
	 
	 //Tabla
	 public void Actualizar_Tabla() throws ClassNotFoundException, SQLException {
		 String [] encabezado = {"nombre"};
		 String nombre=null;
		 String consulta= "Select * from combustibles order by id ASC";
		 PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		 ResultSet rs = prepararcons.executeQuery();
		 DefaultTableModel model = new DefaultTableModel() {
			 /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int col){
					return false;
				}
			};
		 
		 model.setColumnIdentifiers(encabezado);
		 table.setModel(model);
		 while(rs.next()) {
			 nombre = rs.getString("nombre");
			 model.addRow( new Object[] {nombre});
		 }
		
	 }
	 
	
	 public void actualizarlista() throws ClassNotFoundException, SQLException {
		 transbus.gettransbus().setCombustible(Combustible_serv.Cargar_combustible());
	 }
	 
	 public void Seleccionar_fila() throws ClassNotFoundException, SQLException {
		 if(table.getSelectedRowCount()==1) {
			 int pos = table.getSelectedRow();
			 String combustible = (String)table.getValueAt(pos, 0);
			 if(combustible != null) {
				 llenarCampo(combustible);
			 }
		 }
	 }
	 
	 public boolean validar() {
		 boolean verdad = true;
		 if(textField_nombre.getText().isEmpty() == true) {
			 verdad = false;
		 }
		 return verdad;
	 }
}
