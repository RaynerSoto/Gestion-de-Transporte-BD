package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladora.transbus;
import model.Combustible;
import model.Marca;
import services.Combustible_serv;
import services.Marca_serv;
import utils.ConnectionManager;
import utils.Encription;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Marca_Interfaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_nombre;
	private JTable table;
	private JSpinner spinner_consumo;
	private JSpinner spinner_asientos;
	private JComboBox<String> combustible_combo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Marca_Interfaz dialog = new Marca_Interfaz();
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
	public Marca_Interfaz() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Marca");
		setBounds(100, 100, 456, 582);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 419, 179);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 135, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(233, 11, 176, 34);
		panel.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de asientos");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 56, 142, 34);
		panel.add(lblNewLabel_1);
		
		spinner_asientos = new JSpinner();
		spinner_asientos.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spinner_asientos.setBounds(233, 58, 176, 31);
		panel.add(spinner_asientos);
		
		JLabel lblNewLabel_2 = new JLabel("Combustible");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 101, 135, 34);
		panel.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_3 = new JLabel("Consumo por kilometro recorrido");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 146, 217, 31);
		panel.add(lblNewLabel_3);
		
		spinner_consumo = new JSpinner();
		spinner_consumo.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		spinner_consumo.setBounds(233, 140, 176, 31);
		panel.add(spinner_consumo);
		
		combustible_combo = new JComboBox();
		llenar_combobox();
		combustible_combo.setBounds(233, 100, 176, 28);
		panel.add(combustible_combo);
		
		JButton btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					insertar_marca();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 201, 150, 43);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedColumn()==1) {
				try {
					modificar();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(Marca_Interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(Marca_Interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(Marca_Interfaz.this,"Usted no ha seleccionado en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(170, 201, 150, 41);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar_marca();
			}
		});
		btnNewButton_2.setBounds(330, 201, 99, 43);
		contentPanel.add(btnNewButton_2);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Seleccionar_fila();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		table.setBounds(10, 267, 420, 289);
		contentPanel.add(table);
		
		Actualizar_Tabla();
	}
	//insertar
	public void insertar_marca() throws ClassNotFoundException, SQLException {
	 String nombre =textField_nombre.getText();
	 String combustible = (String)combustible_combo.getSelectedItem();
	 int cant_asientos= (int) spinner_asientos.getValue();
	 long id_combustible = buscar_id(combustible);
	 double consumo= (double)spinner_consumo.getValue();
	 
	 if(validar() == false) {
	 try {
		
		  	Marca_serv.añadir_marcas(nombre, cant_asientos, id_combustible, consumo);
			Actualizar_Tabla();
			limpiar();
			JOptionPane.showMessageDialog(Marca_Interfaz.this, "Marca insertada", "OK", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(Marca_Interfaz.this,e.getException(),"Error",JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(Marca_Interfaz.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	 }else
			JOptionPane.showConfirmDialog(Marca_Interfaz.this, "Campos vacios", "OK", JOptionPane.INFORMATION_MESSAGE);

	}
	
	// eliminar
	public void eliminar_marca(){
		if(JOptionPane.showConfirmDialog(Marca_Interfaz.this,"¿Desea eliminar?" ,"Confirmar" , JOptionPane.YES_OPTION)== JOptionPane.YES_OPTION){
			int poss = -1;
			for(int contador=0;contador<table.getSelectedRows().length;contador++){
				try {
					poss = marca_elegida(contador);
					if(poss!=-1){
						Marca_serv.eliminar_marcas(transbus.gettransbus().getMarcas().get(poss).getIdentificador());
						actualizarlista();
						Actualizar_Tabla();
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(Marca_Interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
	//Modificar
	public void modificar() throws ClassNotFoundException, SQLException {
		int poss = table.getSelectedRow();
		String nombre = (String) table.getValueAt(poss, 0);
		String combustible = (String)combustible_combo.getSelectedItem();
		 int cant_asientos= (int) spinner_asientos.getValue();
		 long id_combustible = buscar_id(combustible);
		 double consumo= (double) spinner_consumo.getValue();
		 
			if(nombre.isEmpty()==false) {
				if(validar() == true) {
			Marca_serv.modificar(textField_nombre.getText(),Marca_serv.buscar_marca_ID(nombre),id_combustible,consumo,cant_asientos);
			actualizarlista();
			Actualizar_Tabla();
			limpiar();
				}
			}
	}
	

		
	public void limpiar() {
		textField_nombre.setText("");
		spinner_asientos.setValue(0);
		combustible_combo.setSelectedItem(0);
		spinner_consumo.setValue(0);
		
	}
	
	 public void Actualizar_Tabla() throws ClassNotFoundException, SQLException {
		 String [] encabezado = {"nombre","cantidad_asientos","combustible","consumo"};
		 String nombre=null;
		 int cant_asientos= 0;
		 long id_combustible;
		 double consumo=0;
		 String combustible = null;
		 String consulta= "Select * from marcas order by id ASC";
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
			 cant_asientos= rs.getInt("cantidad_asientos");
			 id_combustible = rs.getLong("id_combustible");
			 consumo = rs.getDouble("consumo");
			 for(int i=0;i<transbus.gettransbus().getCombustible().size();i++) {
				 if(transbus.gettransbus().getCombustible().get(i).getId()==id_combustible) {
					 combustible = transbus.gettransbus().getCombustible().get(i).getNombre();
				 }
			 }
			 model.addRow( new Object[] {nombre,cant_asientos,combustible,consumo});
		 }
		
	 }
	 
	 public void actualizarlista() throws ClassNotFoundException, SQLException {
		 transbus.gettransbus().setMarcas(Marca_serv.cargar_marcar());
	 }
	 public void llenarCampo(Marca m) throws ClassNotFoundException, SQLException {
		    long id_comb = m.getId_combustible();
		    String combustible = buscar_combustible(id_comb);
			textField_nombre.setText(m.getNombre());
			spinner_asientos.setValue(m.getCant_asientos());
			combustible_combo.setSelectedItem(combustible);
			spinner_consumo.setValue(m.getCombustible_por_km());
	 }
	 
	 public void Seleccionar_fila() throws ClassNotFoundException, SQLException {
		 if(table.getSelectedRowCount()==1) {
			 String marca = (String)table.getValueAt(table.getSelectedRows()[0], 0);
			Marca m = transbus.gettransbus().buscar(marca);
			 if( m != null) {
				 llenarCampo(m);
			 }
		 }
	 }
	 
	 public void llenar_combobox() throws ClassNotFoundException, SQLException {
		 DefaultComboBoxModel<String> comb = new DefaultComboBoxModel<String>();
		 for(int i = 0; i < transbus.gettransbus().getCombustible().size();i++) {
			 String valor =(String) transbus.gettransbus().getCombustible().get(i).getNombre();
			 comb.addElement(valor); 
		 }
		 combustible_combo.setModel(comb);
	 }
	 
	 public long buscar_id(String nombre) throws ClassNotFoundException, SQLException {
		 long id = 0;
		 boolean encontrado = false;
		 for(int i=0; i< transbus.gettransbus().getCombustible().size()&& encontrado == false;i++) {
			 if(transbus.gettransbus().getCombustible().get(i).getNombre().equals(nombre)) {
				 id= transbus.gettransbus().getCombustible().get(i).getId();
				 encontrado = true;
			 }
			 
		 }
		 return id;
	 }
	 
	 public int  marca_elegida(int contador) throws ClassNotFoundException, SQLException {
		 return transbus.gettransbus().buscar_pos((String)table.getValueAt(table.getSelectedRows()[contador], 0));
	 }
	 
	 public String buscar_combustible(long combustible) throws ClassNotFoundException, SQLException {
		 String m = null;
		 boolean encontrado = false;
		 for(int i=0; i< transbus.gettransbus().getCombustible().size()&& encontrado == false;i++) {
			 if(transbus.gettransbus().getCombustible().get(i).getId() == combustible) {
				 m= transbus.gettransbus().getCombustible().get(i).getNombre();
				 encontrado = true;
			 }
			 
		 }
		 return m;
	 }
	 public boolean validar() {
		 boolean verdad = true;
		 if(textField_nombre.getText().isEmpty() == true && combustible_combo.getSelectedItem()==null && (int)spinner_asientos.getValue() == 0 && (double)spinner_consumo.getValue() == 0) {
			 verdad = false;
		 }
		 return verdad;
	 }
}
