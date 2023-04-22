package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.postgresql.jdbc2.Jdbc2Array;

import Controladora.transbus;
import de.java2html.Java2Html;
import model.Hoja_de_ruta;
import services.Carro_serv;
import services.Hoja_de_ruta_serv;
import utils.ConnectionManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import com.toedter.calendar.JCalendar;
import javax.swing.JTable;

public class hoja_ruta_interfaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox_carro;
	private JComboBox comboBox_chofer;
	private JPanel panel;
	private JCalendar calendar_fecha;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			hoja_ruta_interfaz dialog = new hoja_ruta_interfaz();
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
	public hoja_ruta_interfaz() throws ClassNotFoundException, SQLException {
		setBounds(100, 100, 546, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 520, 400);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Carro");
		lblNewLabel.setBounds(10, 23, 46, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		comboBox_carro = new JComboBox();
		comboBox_carro.setBounds(78, 25, 189, 20);
		llenar_carro();
		panel.add(comboBox_carro);
		
		Label label = new Label("Chofer");
		label.setBounds(10, 61, 62, 22);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label);
		
		comboBox_chofer = new JComboBox();
		comboBox_chofer.setBounds(78, 63, 189, 20);
		llenar_chofer();
		panel.add(comboBox_chofer);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(25, 111, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					;
					insertar();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		calendar_fecha = new JCalendar();
		calendar_fecha.setBounds(277, 23, 246, 163);
		panel.add(calendar_fecha);
		
		table = new JTable();
		table.setBounds(10, 189, 500, 200);
		panel.add(table);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//eliminar_carro();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(124, 111, 117, 23);
		panel.add(btnNewButton_1);
		Actualizar_Tabla();
	}
	
	public void llenar_carro() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> carro = new DefaultComboBoxModel<String>();
		for(int i =0;i<transbus.gettransbus().getCarros().size();i++) {
			carro.addElement(transbus.gettransbus().getCarros().get(i).getPlaca());
		}
		comboBox_carro.setModel(carro);
	}
	public void llenar_chofer() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> chofer = new DefaultComboBoxModel<String>();
		ArrayList<String>nombre = new ArrayList<String>();
		long marca =0;
		String placa = (String)comboBox_carro.getSelectedItem();
		for(int i =0; i<transbus.gettransbus().getCarros().size();i++) {
			if(transbus.gettransbus().getCarros().get(i).getPlaca().equals(placa)) {
				 marca =transbus.gettransbus().getCarros().get(i).getId_marca();
			}
			nombre = Hoja_de_ruta_serv.choferes(marca);
		}
		for(int j =0;j<nombre.size();j++) {
			chofer.addElement(nombre.get(j));
			
		}
		comboBox_chofer.setModel(chofer);
	}
	
	//insertar
	public void insertar() throws ClassNotFoundException, SQLException {
		String placa = (String)comboBox_carro.getSelectedItem();
		String chofer=(String)comboBox_chofer.getSelectedItem();
		 java.sql.Date fecha = new Date(calendar_fecha.getDate().getDate()) ;
		long carro = buscar_carro(placa);
		String choferes = buscar_chofer(chofer);
		
		 try {
			 Hoja_de_ruta_serv.insertar(carro,fecha,choferes);
				Actualizar_Tabla();
				//limpiar();
				JOptionPane.showConfirmDialog(hoja_ruta_interfaz.this, "Marca insertada", "OK", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(hoja_ruta_interfaz.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/*public void eliminar_carro(){
		if(JOptionPane.showConfirmDialog(hoja_ruta_interfaz.this,"¿Desea eliminar?" ,"Confirmar" , JOptionPane.YES_OPTION)== JOptionPane.YES_OPTION){
			int poss = -1;
			for(int contador=0;contador<table.getSelectedRows().length;contador++){
				try {
					poss = hoja_elegido(contador);
					if(poss!=-1){
						Hoja_de_ruta_serv.eliminar(transbus.gettransbus().getHoja_ruta().get(poss).getIdentificador());;
						actualizarlista();
						Actualizar_Tabla();
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}*/
	
	 public long buscar_carro(String carro) throws ClassNotFoundException, SQLException {
		 long m = 0;
		 boolean encontrado = false;
		 for(int i=0; i< transbus.gettransbus().getCarros().size()&& encontrado == false;i++) {
			 if(transbus.gettransbus().getCarros().get(i).getPlaca().equals(carro)) {
				 m= transbus.gettransbus().getCarros().get(i).getIdentificador();
				 encontrado = true;
			 }
			 
		 }
		 return m;
	 }
	 
	 public String buscar_chofer(String nombre) throws ClassNotFoundException, SQLException {
		 String m = null;
		 boolean encontrado = false;
		 String consulta= "Select nombre,ci from chofer order by id ASC";
			PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
			ResultSet rs = prepararcons.executeQuery();
			while(rs.next()&& encontrado == false) {
				if(rs.getString("nombre").equals(nombre)) {
					m=rs.getString("ci");
					encontrado = true;
				}
			}
		
		 return m;
	 }
	 
	 public void Actualizar_Tabla() throws ClassNotFoundException, SQLException {
		 String [] encabezado = {"placa","fecha","ci"};
		 Calendar c;
		 String placa=null;
		 long id_carro=0;
		 long eventMask=0;
		 Date fecha = new Date(eventMask);
		 String nombre = null;
		 String ci;
		 String consulta= "Select * from hojas_ruta";
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
			 id_carro =  rs.getLong("id_carro");
			 placa =buscar_carros(id_carro);
			 ci = rs.getString("ci");
			 nombre = buscar_choferes(ci);
			 fecha = rs.getDate("fecha");
			
			 
			 }
			 model.addRow( new Object[] {placa,fecha,nombre});
		 }
	 public void actualizarlista() throws ClassNotFoundException, SQLException {
		 transbus.gettransbus().setHoja_ruta(Hoja_de_ruta_serv.cargar_hoja_ruta());
	 }
	 
	 public String buscar_choferes(String ci) throws ClassNotFoundException, SQLException {
		 String m = null;
		 boolean encontrado = false;
		 String consulta= "Select nombre,ci from chofer";
			PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
			ResultSet rs = prepararcons.executeQuery();
			while(rs.next()&& encontrado == false) {
				if(rs.getString("ci").equals(ci)) {
					m=rs.getString("nombre");
					encontrado = true;
				}
			}
		
		 return m;
	 }
	 public String buscar_carros(long carro) throws ClassNotFoundException, SQLException {
		 String m = null;
		 boolean encontrado = false;
		 for(int i=0; i< transbus.gettransbus().getCarros().size()&& encontrado == false;i++) {
			 if(transbus.gettransbus().getCarros().get(i).getIdentificador()==carro) {
				 m= transbus.gettransbus().getCarros().get(i).getPlaca();
				 encontrado = true;
			 }
			 
		 }
		 return m;
	 }
	 
	/* public int  hoja_elegido(int contador) throws ClassNotFoundException, SQLException {
		 return transbus.gettransbus().buscar_pos_ruta((String)table.getValueAt(table.getSelectedRows()[contador], 0));
	 }*/
	 }

