package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoDAO {
	private Conexion conexion;
	private Statement sentencia = null;
	private PreparedStatement sententia_preparada;
	private ResultSet resultado = null;
	private Empleado empleado = null;
	private ArrayList<Empleado> emp;
	private Connection y;
	
	public EmpleadoDAO() {
		conexion = new Conexion();
	}
	public ArrayList<Empleado> getEmpleados(){
		
		try {
			emp = new ArrayList<>();
			y = conexion.getConexion();
			 sentencia = null;
			resultado = null;
			sentencia = y.createStatement();
			// select * codigo, nombre, precio, codigo_fabricante, canonfrom producto
			 resultado = sentencia.executeQuery("select cod_empleado, cod_departamento, telefono from empleados");
			System.out.println("select * from empleados");
			System.out.println("---------------------------------------------------------------");
			while(resultado.next()) {
				empleado = new Empleado(0, 0, 0, null, null, 0, 0, 0, null);
				empleado.setCodEmp(resultado.getInt("cod_empleado"));
				empleado.setCodDpto(resultado.getInt("cod_departamento"));
				empleado.setTlf(resultado.getInt("telefono"));	
//				int Codigo = resultado.getInt("cod_empleado");
//				int Departamento = resultado.getInt("cod_departamento");
//				int Precio = resultado.getInt("telefono");
				emp.add(empleado);
			}
		} catch (SQLException e) {
			System.out.println("Error en la base de datos "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				resultado.close();
				sentencia.close();
			} catch (SQLException e) {
				System.out.println("error liberando recursos");
				e.printStackTrace();
			}
			
		}
		conexion.desconectar();
		return emp;
		
	}
	
	// con david
	
	public int insertar(Empleado e) throws SQLException {
		int res = 0;
		y = this.conexion.getConexion();
		try {
			String consulta = "insert into empleados values(?, ? ,? , ?, ?, ?, ?, ?, ?)";
			sentencia = y.prepareStatement(consulta);
			// inicializamos la sentencia indicando valor que sustitulle a cada ?"
			sententia_preparada = y.prepareStatement(consulta);
			
			sententia_preparada.setInt(1, e.getCodEmp());
			sententia_preparada.setInt(2, e.getCodDpto());
			sententia_preparada.setInt(3, e.getTlf());
			sententia_preparada.setDate(4, e.getFechaNaci());
			sententia_preparada.setDate(5, e.getFechaIngreso());
			sententia_preparada.setDouble(6, e.getSalario());
			sententia_preparada.setDouble(7, e.getComision());
			sententia_preparada.setInt(8, e.getNumHijos());
			sententia_preparada.setString(9, e.getNombre());
			
			res = sententia_preparada.executeUpdate();
		}catch(SQLException e1) {
			System.out.println("Error al insertar "+ e1.getMessage());
			throw e1;
		}
		return res;
	}
	public int eliminarEmpleado(int codEmpleado) throws SQLException {
		int res = 0;
		y = this.conexion.getConexion();
		try {
			String consulta = "delete from empleados where cod_empleado = ?";
			sentencia = y.prepareStatement(consulta);
			// inicializamos la sentencia indicando valor que sustitulle a cada ?"
			sententia_preparada = y.prepareStatement(consulta);
			
			sententia_preparada.setInt(1, codEmpleado);
			
			
			res = sententia_preparada.executeUpdate();
		}catch(SQLException e1) {
			System.out.println("Error al insertar "+ e1.getMessage());
			throw e1;
		}
		return res;
	}
}
