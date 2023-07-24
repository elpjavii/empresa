package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	// DATOS DE LA CONEXION
		private static String database = "empresa";
		private static String user = "elpjavii";
		private static String pass = "123456";
		private static String url = "jdbc:mysql://localhost:3306/"+database;
		
		// OBJETO CONECTION
		
		private Connection conexion = null;
		
		
		
		// VAMOS A CREAR LA CONEXION
		public Connection getConexion() {
			if (conexion != null) {
				return this.conexion;
			}
			// REGISTRAMOS EL DRIVER
			try {
				// LINEA IMPORTANTE PORQUE SE REPITE SIEMPRE EN LAS CONEXIONES
				Class.forName("com.mysql.cj.jdbc.Driver");
				// SOLICITAMOS UN OBJETO CONEXION
				this.conexion = DriverManager.getConnection(url, user, pass);
				return this.conexion;
			} catch (ClassNotFoundException e) {
				System.out.println("No se ha podido registrar el driver"+e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("No se ha podido conectar. "+ e.getMessage());
				e.printStackTrace();
			}
			return this.conexion;	
		}
		
		public void desconectar() {
			try {
				this.conexion.close();
				this.conexion=null;
			} catch (SQLException e) {
				System.out.println("error liberando conexion");
				e.printStackTrace();
			}
		}
}
