package Main;

import java.sql.Connection;
import java.util.ArrayList;

import Clases.Conexion;
import Clases.Empleado;
import Clases.EmpleadoDAO;

public class Main {

	public static void main(String[] args) {
	Conexion conexion = new Conexion();
		
		System.out.println("Conectamos con la base de datos: ");
		Connection y = conexion.getConexion();
		System.out.println("liberado");
		
		EmpleadoDAO daoEmp = new EmpleadoDAO();
		
		ArrayList<Empleado> lista= daoEmp.getEmpleados();
		
		System.out.println(lista);
		
		conexion.desconectar();
	}

}
