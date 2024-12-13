package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.naming.CommunicationException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class Modificar {

	static Scanner sc = new Scanner(System.in);

	private static String crearCommand(int id, String nombreId, String emailId, String nick, String psw, String email) {

		String command = null;

		if (id != 0 && nombreId.equals("") && emailId.contentEquals("")) {

			command = "UPDATE Player SET nick = '" + nick + "',password = '" + psw + "',email = '" + email
					+ "' where IdPlayer = " + id + ";";

		} else if (id == 0 && !nombreId.equals("") && emailId.equals("")) {

			command = "UPDATE Player SET nick = '" + nick + "',password = '" + psw + "',email = '" + email
					+ "' where nick like '" + nombreId + "';";

		} else if (id == 0 && nombreId.equals("") && !emailId.equals("")) {
			command = "UPDATE Player SET nick = '" + nick + "',password = '" + psw + "',email = '" + email
					+ "' where email like '%" + emailId + "%';";
		}
		return command;
	}

	private static String crearCommandListado(int id, String nombreId, String emailId) {
		String command = "";
		if (id != 0) {
			// Preparar la sentencia SQL para la consulta
			command = "SELECT * FROM Player WHERE idPlayer = " + id + ";";

		} else if (!nombreId.equals("")) {
			// Preparar la sentencia SQL para la consulta
			command = "SELECT * FROM Player WHERE nick like '" + nombreId + "'";

		} else if (!emailId.equals("")) {
			// Preparar la sentencia SQL para la consulta
			command = "SELECT * FROM Player WHERE email like '%" + emailId + "%'";
		}
		return command;
	}

	public static void modificarJugadorPorId(int id, String nombreId, String emailId, String nick, String psw,
			String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String command = "";
		String confirmarCad = "";
		ResultSet rs = null;
		int idBuscar = 0;

		try {
			// Conectar a la base de datos
			conn = Conexion.conectar();

			if (!nombreId.equals("")) {
				command = "SELECT idPlayer FROM Player WHERE nick = ?";
				stmt = conn.prepareStatement(command);
				stmt.setString(1, nombreId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					idBuscar = rs.getInt("idPlayer");
				}
				
				
			} else if (!emailId.equals("")) {
				command = "SELECT idPlayer FROM Player WHERE email = ?";
				stmt = conn.prepareStatement(command);
				stmt.setString(1, emailId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					idBuscar = rs.getInt("idPlayer");
				}
			}
			// Desactivar autocommit para iniciar una transacción manual
			conn.setAutoCommit(false);


			// Preparar la sentencia SQL para la actualización
			command = crearCommand(id, nombreId, emailId, nick, psw, email);
			stmt = conn.prepareStatement(command);
			
			// Ejecutar la actualización
			stmt.executeUpdate();

			System.out.println("=======================================");
			System.out.println("ID: " + (idBuscar!=0?idBuscar:id));
			System.out.println("Nick: " + nick);
			System.out.println("Password: " + psw);
			System.out.println("Email: " + email);
			System.out.println("=======================================");

			do {
				System.out.println("¿QUIERES CONFIRMAR LA TRANSACCION? SI O NO");

				confirmarCad = sc.nextLine().toLowerCase();
			} while (!confirmarCad.equals("si") && !confirmarCad.equals("no"));

			if (confirmarCad.equals("si")) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (CommunicationsException e) {
			System.out.println("No se ha podido conectar a la base de datos");
		} 	catch (SQLException e) { 
			
			e.printStackTrace();
		}
	}
}
