package ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	/**
	 * Funcion que se conecta a una base de datos
	 * @return Conexion a la base de datos
	 */
	public static Connection conectar() {
		Connection con = null;
		String url = "jdbc:mysql://dns11036.phdns11.es:3306/ad2425_diglesias";
		String userName ="diglesias";;
		String password ="12345";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
