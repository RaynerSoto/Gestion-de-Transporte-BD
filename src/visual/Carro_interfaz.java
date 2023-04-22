package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladora.transbus;
import model.Carro;
import model.Marca;
import services.Carro_serv;
import services.FlotillaServices;
import services.Marca_serv;
import utils.ConnectionManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Carro_interfaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_placa;
	private JTable table;
	private JComboBox comboBox_marca;
	private JComboBox comboBox_flotilla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Carro_interfaz dialog = new Carro_interfaz();
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
	public Carro_interfaz() throws ClassNotFoundException, SQLException {
		setTitle("Carro");
		setBounds(100, 100, 447, 644);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 414, 175);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Placa");
				lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel.setBounds(10, 11, 90, 43);
				panel.add(lblNewLabel);
			}
			{
				textField_placa = new JTextField();
				textField_placa.setBounds(131, 11, 273, 43);
				panel.add(textField_placa);
				textField_placa.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Marca");
				lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_1.setBounds(10, 65, 90, 43);
				panel.add(lblNewLabel_1);
			}
			{
				comboBox_marca = new JComboBox();
				llenar_combobox();
				comboBox_marca.setBounds(131, 65, 273, 43);
				panel.add(comboBox_marca);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Flotilla");
				lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(10, 119, 90, 45);
				panel.add(lblNewLabel_2);
			}
			{
				comboBox_flotilla = new JComboBox();
				llenar_combobox_flotilla();
				comboBox_flotilla.setBounds(131, 119, 273, 45);
				panel.add(comboBox_flotilla);
			}
		}
		{
			JButton btnNewButton = new JButton("Insertar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						insertar_carro();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(Carro_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton.setBounds(10, 197, 144, 52);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Modificar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getSelectedColumn() == 1) {
					if(JOptionPane.showConfirmDialog(Carro_interfaz.this,"¿Desea modificar?" ,"Confirmar" , JOptionPane.YES_OPTION)== JOptionPane.YES_OPTION) {
						try {
							modificar();
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(Carro_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else
					JOptionPane.showMessageDialog(Carro_interfaz.this,"Usted no ha seleccionado en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
				}
			});
			btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton_1.setBounds(164, 197, 144, 52);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_2 = new JButton("Eliminar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eliminar_carro();
				}
			});
			btnNewButton_2.setBounds(318, 197, 106, 52);
			contentPanel.add(btnNewButton_2);
		}
		{
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						Seleccionar_fila();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(Carro_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			table.setBounds(10, 260, 414, 334);
			contentPanel.add(table);
		}
		Actualizar_Tabla();
	}
	
	// insertar 
	public void insertar_carro() throws ClassNotFoundException, SQLException {
		 String placa =textField_placa.getText();
		 String marca = (String)comboBox_marca.getSelectedItem();
		 String flotilla= (String)comboBox_flotilla.getSelectedItem();
		 long id_marca = buscar_id_marca(marca);
		 long id_flotilla= buscar_id_flotilla(flotilla);
		 
		 
		 try {
			  	Carro_serv.añadir_carros(placa, id_marca, id_flotilla);
				Actualizar_Tabla();
				limpiar();
				JOptionPane.showMessageDialog(Carro_interfaz.this, "Marca insertada", "OK", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(Carro_interfaz.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	// eliminar
	public void eliminar_carro(){
		if(JOptionPane.showConfirmDialog(Carro_interfaz.this,"¿Desea eliminar?" ,"Confirmar" , JOptionPane.YES_OPTION)== JOptionPane.YES_OPTION){
			int poss = -1;
			for(int contador=0;contador<table.getSelectedRows().length;contador++){
				try {
					poss = carro_elegido(contador);
					if(poss!=-1){
						Carro_serv.eliminar_carros(transbus.gettransbus().getCarros().get(poss).getIdentificador());
						actualizarlista();
						Actualizar_Tabla();
						limpiar();
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(Carro_interfaz.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	//Modificar
	public void modificar() throws ClassNotFoundException, SQLException {
		int poss = table.getSelectedRow();
		String nombre = (String)comboBox_marca.getSelectedItem();
		String flotilla = (String)comboBox_flotilla.getSelectedItem();
		String placa = (String) table.getValueAt(poss, 0);

		if(nombre.isEmpty()==false) {
			if(validar() == true) {
			Carro_serv.modificar(placa,Marca_serv.buscar_marca_ID(nombre), FlotillaServices.buscar_flotilla_ID(flotilla),Carro_serv.buscar_carro_ID(placa));
			actualizarlista();
			Actualizar_Tabla();
			limpiar();
			}
		}
	}
		
	
	public void Actualizar_Tabla() throws ClassNotFoundException, SQLException {
		 String [] encabezado = {"placa","marca","flotilla"};
		 String placa=null;
		 long id_marca;
		 String marca = null;
		 long id_flotilla;
		 String flotilla = null;
		 String consulta= "Select * from carros order by id ASC";
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
			 placa = rs.getString("placa");
			 id_marca = rs.getLong("id_marca");
			 id_flotilla = rs.getLong("id_flotilla");
			 flotilla = buscar_flotilla(id_flotilla);
			 for(int i=0; i<transbus.gettransbus().getMarcas().size();i++) {
				 if(transbus.gettransbus().getMarcas().get(i).getIdentificador() == id_marca) {
					 marca= transbus.gettransbus().getMarcas().get(i).getNombre();
				 }
			 }
			 model.addRow( new Object[] {placa,marca,flotilla});
		 }
		
	 }
	 
	 public void actualizarlista() throws ClassNotFoundException, SQLException {
		 transbus.gettransbus().setCarros(Carro_serv.cargar_carro());
	 }
	public void llenarCampo(Carro c) throws ClassNotFoundException, SQLException {
		    String marca = buscar_marca( c.getId_marca());
		    String flotilla = buscar_flotilla(c.getId_numero_de_flotilla());
		    
			textField_placa.setText(c.getPlaca());
			comboBox_marca.setSelectedItem(marca);
			comboBox_flotilla.setSelectedItem(flotilla);	
			
	 }
	 
	 public void Seleccionar_fila() throws ClassNotFoundException, SQLException {
		 if(table.getSelectedRowCount()==1) {
			 String placa = (String)table.getValueAt(table.getSelectedRows()[0], 0);
			Carro c = transbus.gettransbus().buscar_carro(placa);
			 if( c != null) {
				 llenarCampo(c);
			 }
		 }
	 }
	 
	 public String buscar_marca(long marca) throws ClassNotFoundException, SQLException {
		 String m = null;
		 boolean encontrado = false;
		 for(int i=0; i< transbus.gettransbus().getMarcas().size()&& encontrado == false;i++) {
			 if(transbus.gettransbus().getMarcas().get(i).getIdentificador() == marca) {
				 m= transbus.gettransbus().getMarcas().get(i).getNombre();
				 encontrado = true;
			 }
			 
		 }
		 return m;
	 }
	 public String buscar_flotilla(long flotilla) throws ClassNotFoundException, SQLException {
		 String m = null;
		 boolean encontrado = false;
		 String consulta= "Select * from flotillas order by id ASC";
			PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
			ResultSet rs = prepararcons.executeQuery();
			while(rs.next()&& encontrado == false) {
				if(rs.getLong("id")== flotilla) {
					m=rs.getString("nombre");
					encontrado = true;
				}
			}
		
		 return m;
	 }
	 
	 public void llenar_combobox() throws ClassNotFoundException, SQLException {
		 DefaultComboBoxModel<String> comb = new DefaultComboBoxModel<String>();
		 for(int i = 0; i < transbus.gettransbus().getMarcas().size();i++) {
			 String valor =(String) transbus.gettransbus().getMarcas().get(i).getNombre();
			 comb.addElement(valor); 
		 }
		 comboBox_marca.setModel(comb);
	 }
	 
	 public void llenar_combobox_flotilla() throws ClassNotFoundException, SQLException {
		 DefaultComboBoxModel<String> comb = new DefaultComboBoxModel<String>();
		String consulta= "Select nombre from flotillas order by id ASC";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			
			String nombre = rs.getString("nombre");
			comb.addElement(nombre);
		}
		comboBox_flotilla.setModel(comb);
		
	}
			 
	 public int  carro_elegido(int contador) throws ClassNotFoundException, SQLException {
		 return transbus.gettransbus().buscar_pos_carro((String)table.getValueAt(table.getSelectedRows()[contador], 0));
	 }
	 
	 public long buscar_id_marca(String marca) throws ClassNotFoundException, SQLException {
		 long id=0;
		 boolean encontrada = false;
		 for(int i=0; i<transbus.gettransbus().getMarcas().size() && encontrada == false;i++) {
			 if(transbus.gettransbus().getMarcas().get(i).getNombre().equals(marca)) {
				 id = transbus.gettransbus().getMarcas().get(i).getIdentificador();
				 encontrada = true;
			 }
			 
		 }
		 return id;
	 }
	 
	 public long buscar_id_flotilla(String flotilla) throws ClassNotFoundException, SQLException {
		 long id=0;
		 String nombre;
		 boolean encontrado = false;
		 String consulta= "Select * from flotillas order by id ASC";
			PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
			ResultSet rs = prepararcons.executeQuery();
			while(rs.next()&& encontrado == false) {
				nombre = rs.getString("nombre");
				if(nombre.equals(flotilla)) {
					id = rs.getLong("id");
					encontrado = true;
				}
			}
		
			return id;
	 }
	 public boolean validar() {
		 boolean verdad = true;
		 if(textField_placa.getText().isEmpty() == true) {
			 verdad = false;
		 }
		 return verdad;
	 }
	 
	 public void limpiar() {
		    textField_placa.setText("");
			comboBox_marca.setSelectedItem(0);
			comboBox_flotilla.setSelectedItem(0);
			
		}
	 
}
