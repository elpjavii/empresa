package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Clases.Empleado;
import Clases.EmpleadoDAO;
import net.miginfocom.swing.MigLayout;

public class Main_Gui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Empleado> lista;
	private EmpleadoDAO empDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Gui frame = new Main_Gui();
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
	public Main_Gui() {
		empDao = new EmpleadoDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"cod_emp", "cod_dep", "tlf"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargar();
				pulso();
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("mostrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarEditar();
			}
		});
		contentPane.add(btnNewButton_1, "flowx,cell 0 1,alignx center");
		contentPane.add(btnNewButton, "cell 0 1,alignx center");
		
		JButton insertar = new JButton("insestar");
		insertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		contentPane.add(insertar, "cell 0 1");
		
		JButton btnEliminar = new JButton("eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarEmpleado();
			}
		});
		contentPane.add(btnEliminar, "cell 0 1");
	}

	protected void mostrarEditar() {
		Dialogo_Editar dialogoEdit = new Dialogo_Editar();
		dialogoEdit.setModal(true);
		dialogoEdit.setVisible(true);
		
		int seleccionada = table.getSelectedRow();
		if (seleccionada < 0) {
			JOptionPane.showMessageDialog(contentPane, "debe seleccionar una fila");
		}else {
			int codEmpleado = (int) table.getValueAt(seleccionada, 0);
			dialogoEdit.inicializar(codEmpleado);
			dialogoEdit.setVisible(true);
		}
		
	}

	protected void eliminarEmpleado() {
		int seleccionada = table.getSelectedRow();
		if (seleccionada < 0) {
			JOptionPane.showMessageDialog(contentPane, "debe seleccionar una fila");
		}else {
			int codEmpleado = (int) table.getValueAt(seleccionada, 0);
			EmpleadoDAO em = new EmpleadoDAO();
			try {
				em.eliminarEmpleado(codEmpleado);
				cargar();
				pulso();
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "error al eliminar empleado");
			}
		}
	}

	protected void insert() {
		Dialogo_insertar dialogoIns = new Dialogo_insertar();
		dialogoIns.setModal(true);
		dialogoIns.setVisible(true);
	}

	protected void pulso() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		
		for(Empleado empleado : lista) {
			Object fila[] = {
				empleado.getCodEmp(),
				empleado.getCodDpto(),
				empleado.getTlf()
			};
			modelo.addRow(fila);
		}
		
	}
	
	protected void cargar() {
		lista = empDao.getEmpleados();
	}

}
