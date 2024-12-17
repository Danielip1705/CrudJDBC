package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import javax.naming.CommunicationException;

public class Insertar {

	/**
	 * Cuenta la cantidad de registros en una tabla específica.
	 * 
	 * @param tabla Nombre de la tabla de la cual se contarán los registros.
	 * @return La cantidad de registros en la tabla.
	 */
	public static int contadorEntidades(String tabla) {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet res = null;
		int contador = 0;
		try {
			conn = Conexion.conectar();
			query = conn.prepareStatement("SELECT count(id) FROM " + tabla);
			res = query.executeQuery();
			while (res.next()) {
				contador = res.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error: La BD ha producido un error");
		}
		return contador;
	}

	/**
	 * Inserta un nuevo registro en la tabla 'Player'.
	 * 
	 * @param nick  Nombre del jugador.
	 * @param psw   Contraseña del jugador.
	 * @param email Correo electrónico del jugador.
	 * @throws CommunicationException En caso de problemas de conexión con la base
	 *                                de datos.
	 */
	public static void insertarDatosTablaJugador(String nick, String psw, String email) throws CommunicationException {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		int contador = 0;
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			contador = contadorEntidades("Player");
			if (contador <= 0) {
				stmt.executeUpdate("ALTER TABLE Player AUTO_INCREMENT = 1;");
			}
			comando = "insert into Player (nick,password,email) values('" + nick + "','" + psw + "','" + email + "');";
			stmt.executeUpdate(comando);
			System.out.println("Se ha insertado el jugador con éxito");
		} catch (SQLSyntaxErrorException e) {
			System.err.println("Error: No existe la tabla Player");
		} catch (SQLException e) {
			System.err.println("Error: La BD ha producido un error");
		} finally {

			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Inserta un nuevo registro en la tabla 'Games'.
	 * 
	 * @param nick         Nombre del juego.
	 * @param tiempoJugado Tiempo total jugado, representado como un objeto Time.
	 * @throws CommunicationException En caso de problemas de conexión con la base
	 *                                de datos.
	 */
	public static void insertarDatosTablaGames(String nick, Time tiempoJugado) {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		int contador = 0;
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			contador = contadorEntidades("Games");
			if (contador <= 0) {
				stmt.executeUpdate("ALTER TABLE Games AUTO_INCREMENT = 1;");
			}
			comando = "insert into Games (nombre,tiempoJugado) values('" + nick + "','" + tiempoJugado + "');";
			stmt.executeUpdate(comando);
			System.out.println("Se ha insertado el juego con éxito");
		} catch (SQLSyntaxErrorException e) {
			System.err.println("Error: No existe la tabla Games");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			System.err.println("Error: La BD ha producido un error");
		} finally {

			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Inserta un nuevo registro en la tabla 'Compras'.
	 * 
	 * @param idJugador   ID del jugador que realiza la compra.
	 * @param idJuego     ID del juego comprado.
	 * @param cosa        Descripción del objeto comprado.
	 * @param precio      Precio de la compra.
	 * @param fechaCompra Fecha de la compra representada como LocalDate.
	 */
	public static void insertarDatosTablaCompra(int idJugador, int idJuego, String cosa, double precio,
			LocalDate fechaCompra) {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		int contador = 0;
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			contador = contadorEntidades("Compras");
			if (contador <= 0) {
				stmt.executeUpdate("ALTER TABLE Compras AUTO_INCREMENT = 1;");
			}
			comando = "insert into Compras (idPlayer,idGames,cosa,precio,fechaCompra) values('" + idJugador + "','"
					+ idJuego + "','" + cosa + "','" + precio + "','" + fechaCompra + "');";
			stmt.executeUpdate(comando);
			System.out.println("Se ha insertado la compra con éxito");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("No se ha podido añadir compra, el id del jugador o de la compra no existe");
		} catch (SQLSyntaxErrorException e) {
			System.err.println("Error: No existe la tabla Compras");
		} catch (SQLException e) {
			System.err.println("Error: La BD ha producido un error");
		} finally {

			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
