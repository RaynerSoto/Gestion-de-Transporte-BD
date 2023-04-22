package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;
import com.sun.prism.impl.Disposer.Record;

import model.Usuario;
import services.DBServices;
import services.UserServices;
import utils.ConnectionManager;
import utils.Encription;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;

public class Usuario_interfaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JPasswordField password;
	private JTextField textField_nombre;
	private JComboBox rol_combo;
	private JButton btnNewButton_Delete;
	private JButton btnNewButton_Modificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuario_interfaz dialog = new Usuario_interfaz();
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
	public Usuario_interfaz() throws ClassNotFoundException, SQLException {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		Usuario user;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Usuario");
		setBounds(100, 100, 452, 545);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(81, 11, 276, 159);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(10, 11, 75, 37);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 59, 75, 37);
		panel.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(114, 59, 152, 37);
		panel.add(password);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(114, 11, 152, 37);
		panel.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 107, 75, 41);
		panel.add(lblNewLabel_2);
		
		rol_combo = new JComboBox();
		rol_combo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Gestor"}));
		rol_combo.setFont(new Font("Calibri", Font.PLAIN, 16));
		rol_combo.setBounds(114, 107, 152, 41);
		panel.add(rol_combo);
		
		JButton btnNewButton_ADD = new JButton("A\u00F1adir");
		btnNewButton_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validacion() == true) {
					String usuario = textField_nombre.getText();
					try {
						int valor = rol_combo.getSelectedIndex();
						insertar_usuario(usuario, Encription.getMd5(new String(password.getPassword())),valor+1);
						JOptionPane.showMessageDialog(Usuario_interfaz.this,"Usuario ingresado","Conseguido",JOptionPane.INFORMATION_MESSAGE);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(Usuario_interfaz.this,e.getException(),"Error",JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(Usuario_interfaz.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(Usuario_interfaz.this,"Datos obligatorios vacios","Error",JOptionPane.ERROR_MESSAGE);
				}
				}
				
		});
		btnNewButton_ADD.setBounds(10, 220, 128, 33);
		contentPanel.add(btnNewButton_ADD);
		
		btnNewButton_Modificar = new JButton("Modificar");
/*		btnNewButton_Modificar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				try {
					Usuario user = actualizar_campos();
					if(validacion() == true) {
						long ID = UserServices.buscar_usuario_ID(user.getNombre());
						String usuario = textField_nombre.getText();
						int valor = rol_combo.getSelectedIndex();
						UserServices.modificar_usuario(ID, usuario,Encription.getMd5(new String(password.getPassword())),valor);
						JOptionPane.showMessageDialog(Usuario_interfaz.this,"Datos modificados","Conseguido",JOptionPane.INFORMATION_MESSAGE);
						actualizarTabla();
					}
					else {
						JOptionPane.showMessageDialog(Usuario_interfaz.this,"Datos obligatorios vacios","Error",JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
		btnNewButton_Modificar.setEnabled(false);
		btnNewButton_Modificar.setBounds(148, 220, 128, 33);
		contentPanel.add(btnNewButton_Modificar);
		
		btnNewButton_Delete = new JButton("Eliminar");
		btnNewButton_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Usuario user = actualizar_campos();
					long ID = UserServices.buscar_usuario_ID(user.getNombre());
					UserServices.eliminar(ID);
					actualizarTabla();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_Delete.setEnabled(false);
		btnNewButton_Delete.setBounds(298, 220, 128, 33);
		contentPanel.add(btnNewButton_Delete);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				actualizar_botones();
				try {
					actualizar_campos();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		table.setBounds(10, 264, 416, 231);
		contentPanel.add(table);
		actualizarTabla();
		actualizar_botones();
	}
	public void insertar_usuario(String usuario, String contrasenna, int valor) throws ClassNotFoundException, SQLException {
		UserServices.insertar_usuario(usuario, contrasenna, valor);
		actualizarTabla();
	}
	
	private void actualizarTabla() throws ClassNotFoundException, SQLException {
		ArrayList<Usuario>usuarios = UserServices.listado_usuarios();
		String[] encabezado = {"Nombre", "Rol"};		
		Object [] [] tabla = new Object[usuarios.size()][encabezado.length];		

		for(int i=0; i<usuarios.size(); i++){
			tabla [i] [0] = usuarios.get(i).getNombre();
			tabla [i] [1] = usuarios.get(i).getId_rol();
		}		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(tabla, encabezado){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		table.setModel(defaultTableModel);
	}
	public void actualizar_botones() {
		if(table.getSelectedRowCount() == 1) {
			btnNewButton_Delete.setEnabled(true);
			btnNewButton_Modificar.setEnabled(true);
		}
		else {
			btnNewButton_Delete.setEnabled(false);
			btnNewButton_Modificar.setEnabled(false);
		}
	}
	public Usuario actualizar_campos() throws ClassNotFoundException, SQLException {
		Usuario user = null;
		String nombre;
		if(table.getSelectedRowCount() == 1) {
			int pos = table.getSelectedRow();
			nombre = (String)table.getValueAt(pos, 0);
			user = UserServices.buscar_usuario(nombre);
			textField_nombre.setText(user.getNombre());
			if(user.getId_rol().equals("Administrador")) {
				rol_combo.setSelectedIndex(0);
			}
			else {
				rol_combo.setSelectedIndex(1);
			}
		}
		return user;
	}
	
	public boolean validacion() {
		boolean verdad = true;
		if(textField_nombre.getText().isEmpty() == true || password.getText().isEmpty() == true) {
			verdad = false;
		}
		return verdad;
	}
}

