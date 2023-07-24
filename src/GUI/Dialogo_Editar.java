package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import Clases.Empleado;
import Clases.EmpleadoDAO;
import net.miginfocom.swing.MigLayout;

public class Dialogo_Editar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField textCod_Emp;
	private JTextField textCod_Dep;
	private JTextField texttlf;
	private JTextField textFech_Nac;
	private JTextField textSalario;
	private JTextField textComision;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialogo_Editar dialog = new Dialogo_Editar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialogo_Editar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("editar empleado");
			contentPanel.add(lblNewLabel, "cell 0 0 4 1,alignx center");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("nombre*");
			contentPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		}
		{
			txtNombre = new JTextField();
			contentPanel.add(txtNombre, "cell 1 1 3 1,growx");
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("cod emp*");
			contentPanel.add(lblNewLabel_2, "cell 0 2,alignx trailing");
		}
		{
			textCod_Emp = new JTextField();
			textCod_Emp.setEnabled(false);
			textCod_Emp.setEditable(false);
			contentPanel.add(textCod_Emp, "cell 1 2,growx");
			textCod_Emp.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("cod_dep*");
			contentPanel.add(lblNewLabel_3, "cell 2 2,alignx trailing");
		}
		{
			textCod_Dep = new JTextField();
			contentPanel.add(textCod_Dep, "cell 3 2,growx");
			textCod_Dep.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("tlf");
			contentPanel.add(lblNewLabel_4, "cell 0 3,alignx trailing");
		}
		{
			texttlf = new JTextField();
			contentPanel.add(texttlf, "cell 1 3,growx");
			texttlf.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("fecha_nac");
			contentPanel.add(lblNewLabel_5, "cell 2 3,alignx trailing");
		}
		{
			textFech_Nac = new JTextField();
			contentPanel.add(textFech_Nac, "cell 3 3,growx");
			textFech_Nac.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("salario");
			contentPanel.add(lblNewLabel_6, "cell 0 4,alignx trailing");
		}
		{
			textSalario = new JTextField();
			contentPanel.add(textSalario, "cell 1 4,growx");
			textSalario.setColumns(10);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("comision");
			contentPanel.add(lblNewLabel_7, "cell 2 4,alignx trailing");
		}
		{
			textComision = new JTextField();
			contentPanel.add(textComision, "cell 3 4,growx");
			textComision.setColumns(10);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("n hijos");
			contentPanel.add(lblNewLabel_8, "cell 0 5");
		}
		{
			 spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
			contentPanel.add(spinner, "cell 1 5");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pulso();
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void pulso() {
		try {
			String nombre = txtNombre.getText();
			int cod_emp = Integer.parseInt(textCod_Emp.getText());
			int cod_dep = Integer.parseInt(textCod_Dep.getText());
			
			int tlf = Integer.parseInt(texttlf.getText());
			
//			DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//			
//			
//			Date fechaNac = (Date) formateador.parse(textFech_Nac.getText());
			
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			Date fechaNac = Date.valueOf(LocalDate.parse(textFech_Nac.getText(), formateador));
			
			Double salario = Double.parseDouble(textSalario.getText());
			Double comision = Double.parseDouble(textComision.getText());
			int n_hijos = (int) spinner.getValue();
			
			LocalDate fecha_ac = LocalDate.now();
			
			Empleado e = new Empleado(cod_emp, cod_dep, tlf, Date.valueOf(fecha_ac) ,fechaNac, salario, comision, n_hijos, nombre);
			EmpleadoDAO daoEmp = new EmpleadoDAO();
			daoEmp.insertar(e);
		} catch(SQLException e1) {
			JOptionPane.showMessageDialog(contentPanel, "no se ha podido insertar");
		} catch (DateTimeParseException e2) {
				JOptionPane.showMessageDialog(contentPanel, "debe introducir fecha correcta");
		}catch (NumberFormatException e3) {
			JOptionPane.showMessageDialog(contentPanel, "controla datos");
		}
		
		
	}
	
	public void inicializar(int codEmp) {
		EmpleadoDAO daoEmp = new EmpleadoDAO();
		Empleado emp = daoEmp.getEmpleado(codEmp);
		
		txtNombre.setText(emp.getNombre());
		textCod_Emp.setText(""+emp.getCodEmp());
		textCod_Dep.setText(""+emp.getCodDpto());
		texttlf.setText(""+emp.getTlf());
		textFech_Nac.setText(""+emp.getFechaNaci());
		textSalario.setText(""+emp.getSalario());
		textComision.setText(""+emp.getComision());
		spinner.setValue(emp.getNumHijos());
	}

}
