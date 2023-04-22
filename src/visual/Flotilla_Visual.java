package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Usuario;
import services.FlotillaServices;
import services.UserServices;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Flotilla_Visual extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton_Modificar;
	private JButton btnNewButton_Eliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Flotilla_Visual dialog = new Flotilla_Visual();
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
	public Flotilla_Visual() throws ClassNotFoundException, SQLException {
		setTitle("Flotilla");
		setBounds(100, 100, 471, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 434, 77);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Nombre");
				lblNewLabel.setBounds(10, 11, 159, 42);
				panel.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
			{
				textField = new JTextField();
				textField.setBounds(179, 13, 245, 42);
				panel.add(textField);
				textField.setColumns(10);
			}
		}
		{
			JButton btnNewButton_Insertar = new JButton("Insertar");
			btnNewButton_Insertar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(validar() == true) {
						try {
							FlotillaServices.insertar_flotilla(textField.getText());
							limpiar();
							JOptionPane.showMessageDialog(Flotilla_Visual.this,"Flotilla ingresada","Conseguido",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			btnNewButton_Insertar.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton_Insertar.setBounds(10, 99, 120, 45);
			contentPanel.add(btnNewButton_Insertar);
		}
		{
			btnNewButton_Modificar = new JButton("Modificar");
			btnNewButton_Modificar.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton_Modificar.setBounds(159, 99, 120, 45);
			contentPanel.add(btnNewButton_Modificar);
		}
		{
			btnNewButton_Eliminar = new JButton("Eliminar");
			btnNewButton_Eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						String nombre = actualizar_campos();
						long id = FlotillaServices.buscar_flotilla_ID(nombre);
						FlotillaServices.eliminar_flotilla(id);
						limpiar();
						actualizarTabla();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnNewButton_Eliminar.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton_Eliminar.setBounds(324, 99, 120, 45);
			contentPanel.add(btnNewButton_Eliminar);
		}
		{
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						actualizar_campos();
						actualizar_botones();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			table.setBounds(10, 155, 435, 270);
			contentPanel.add(table);
			actualizar_botones();
			actualizarTabla();
		}
	}
	public boolean validar() {
		boolean verdad = true;
		if(textField.getText().isEmpty() == true) {
			verdad = false;
		}
		return verdad;
	}
	
	private void actualizarTabla() throws ClassNotFoundException, SQLException {
		ArrayList<String>flotilla = FlotillaServices.listado_flotilla();
		String[] encabezado = {"Nombre"};		
		Object [] [] tabla = new Object[flotilla.size()][encabezado.length];		

		for(int i=0; i<flotilla.size(); i++){
			tabla [i] [0] = flotilla.get(i);
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
			btnNewButton_Eliminar.setEnabled(true);
			btnNewButton_Modificar.setEnabled(true);
		}
		else {
			btnNewButton_Eliminar.setEnabled(false);
			btnNewButton_Modificar.setEnabled(false);
		}
	}
	
	public String actualizar_campos() throws ClassNotFoundException, SQLException {
		String nombre = "";
		if(table.getSelectedRowCount() == 1) {
			int pos = table.getSelectedRow();
			nombre = (String)table.getValueAt(pos, 0);
		}
		textField.setText(nombre);
		return nombre;
	}
	
	public void limpiar() {
		textField.setText("");
	}
}
