package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Servicio_interfaz2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JCheckBox alquiler_check;
	private JCheckBox planificados_check;
	private JTextField textField_1;
	private JPanel panel_planificados;
	private JPanel panel_alquiler;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Servicio_interfaz2 dialog = new Servicio_interfaz2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Servicio_interfaz2() {
		setTitle("Servicio");
		setBounds(100, 100, 801, 534);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 414, 455);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Pa\u00EDs");
				lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel.setBounds(10, 11, 81, 35);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(148, 11, 256, 35);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Hoja de ruta");
				lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_1.setBounds(10, 57, 81, 35);
				panel.add(lblNewLabel_1);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(148, 57, 256, 35);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("N\u00FAmero de contrato");
				lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(10, 103, 136, 35);
				panel.add(lblNewLabel_2);
			}
			{
				textField = new JTextField();
				textField.setBounds(148, 103, 256, 35);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Fecha de inicio");
				lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 16));
				lblNewLabel_3.setBounds(10, 149, 136, 26);
				panel.add(lblNewLabel_3);
			}
			
			JCalendar calendar = new JCalendar();
			calendar.setBounds(158, 149, 246, 150);
			panel.add(calendar);
			
			JLabel lblNewLabel_4 = new JLabel("Importe");
			lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_4.setBounds(10, 318, 115, 35);
			panel.add(lblNewLabel_4);
			
			JSpinner spinner = new JSpinner();
			spinner.setBounds(148, 318, 256, 35);
			panel.add(spinner);
			
			JLabel lblNewLabel_5 = new JLabel("Combustible");
			lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_5.setBounds(10, 364, 115, 35);
			panel.add(lblNewLabel_5);
			
			JSpinner spinner_1 = new JSpinner();
			spinner_1.setBounds(148, 364, 256, 35);
			panel.add(spinner_1);
			
			JLabel lblNewLabel_6 = new JLabel("Alquiler");
			lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_6.setBounds(10, 407, 55, 37);
			panel.add(lblNewLabel_6);
			
			alquiler_check = new JCheckBox("");
			alquiler_check.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(alquiler_check.isSelected() == true) {
						planificados_check.setSelected(false);
						panel_alquiler.setVisible(true);
						panel_planificados.setVisible(false);
					}
				}
			});
			alquiler_check.setFont(new Font("Tahoma", Font.PLAIN, 16));
			alquiler_check.setBounds(75, 406, 33, 38);
			panel.add(alquiler_check);
			
			JLabel lblNewLabel_7 = new JLabel("Planificados");
			lblNewLabel_7.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_7.setBounds(148, 410, 81, 26);
			panel.add(lblNewLabel_7);
			
			planificados_check = new JCheckBox("");
			planificados_check.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(planificados_check.isSelected() == true) {
						alquiler_check.setSelected(false);
						panel_planificados.setVisible(true);
						panel_alquiler.setVisible(false);
					}
				}
			});
			planificados_check.setBounds(235, 406, 33, 38);
			panel.add(planificados_check);
		}
		{
			panel_alquiler = new JPanel();
			panel_alquiler.setBounds(434, 11, 341, 237);
			contentPanel.add(panel_alquiler);
			panel_alquiler.setLayout(null);
			
			JLabel lblNewLabel_8 = new JLabel("Nombre");
			lblNewLabel_8.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_8.setBounds(10, 11, 86, 32);
			panel_alquiler.add(lblNewLabel_8);
			
			textField_1 = new JTextField();
			textField_1.setBounds(103, 11, 228, 32);
			panel_alquiler.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblNewLabel_9 = new JLabel("Fecha de fin");
			lblNewLabel_9.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_9.setBounds(10, 54, 86, 32);
			panel_alquiler.add(lblNewLabel_9);
			
			JCalendar calendar = new JCalendar();
			calendar.setBounds(103, 54, 184, 150);
			panel_alquiler.add(calendar);
			
			panel_planificados = new JPanel();
			panel_planificados.setBounds(0, 0, 341, 225);
			panel_alquiler.add(panel_planificados);
			panel_planificados.setLayout(null);
			
			JLabel lblNewLabel_10 = new JLabel("C\u00F3digo del servicio");
			lblNewLabel_10.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_10.setBounds(10, 11, 135, 32);
			panel_planificados.add(lblNewLabel_10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Calibri", Font.PLAIN, 16));
			textField_2.setBounds(155, 11, 176, 32);
			panel_planificados.add(textField_2);
			textField_2.setColumns(10);
			
			JLabel lblNewLabel_11 = new JLabel("Nombre");
			lblNewLabel_11.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_11.setBounds(10, 54, 135, 32);
			panel_planificados.add(lblNewLabel_11);
			
			textField_3 = new JTextField();
			textField_3.setBounds(155, 54, 176, 32);
			panel_planificados.add(textField_3);
			textField_3.setColumns(10);
			
			JLabel lblNewLabel_12 = new JLabel("C\u00F3digo del grupo");
			lblNewLabel_12.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_12.setBounds(10, 97, 135, 32);
			panel_planificados.add(lblNewLabel_12);
			
			textField_4 = new JTextField();
			textField_4.setBounds(155, 97, 176, 32);
			panel_planificados.add(textField_4);
			textField_4.setColumns(10);
			
			JLabel lblNewLabel_13 = new JLabel("Lugar de recogida");
			lblNewLabel_13.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_13.setBounds(10, 140, 135, 32);
			panel_planificados.add(lblNewLabel_13);
			
			textField_5 = new JTextField();
			textField_5.setBounds(155, 140, 176, 32);
			panel_planificados.add(textField_5);
			textField_5.setColumns(10);
		}
	}
}
