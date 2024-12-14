package ejercicio2;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Scanner;

public class Modificar {

	static Scanner sc = new Scanner(System.in);

	public static void modificarDatosJugador(int id, String nombreFiltro, String emailFiltro, String nick,
			String password, String email) {
		Connection conn = null;
		PreparedStatement stmtMod = null;
		PreparedStatement stmtSelectNew = null;
		PreparedStatement stmtSelectAnt = null;
		int idPlayer = 0;
		String comando = "";
		String confirmacion = "";
		int modificado = 0;
		ResultSet res = null;

		try {
			// Conectamos a la bd
			conn = Conexion.conectar();
			// Quitamos el autoCommit
			conn.setAutoCommit(false);

			if (!nombreFiltro.equals("")) {
				comando = "SELECT idPlayer FROM Player WHERE nick = ?";
				stmtSelectAnt = conn.prepareStatement(comando);
				stmtSelectAnt.setString(1, nombreFiltro);
				res = stmtSelectAnt.executeQuery();
			} else {
				comando = "SELECT idPlayer FROM Player WHERE email = ?";
				stmtSelectAnt = conn.prepareStatement(comando);
				stmtSelectAnt.setString(1, emailFiltro);
				res = stmtSelectAnt.executeQuery();
			}
			if (res.next()) {
				idPlayer = res.getInt("IdPlayer");
			}

			if (id != 0) {
				comando = "UPDATE Player SET nick = ?,password = ?,email = ? where idPlayer = ?;";
				stmtMod = conn.prepareStatement(comando);
				stmtMod.setString(1, nick);
				stmtMod.setString(2, password);
				stmtMod.setString(3, email);
				stmtMod.setInt(4, id);
				modificado = stmtMod.executeUpdate();

			} else if (!nombreFiltro.equals("")) {
				comando = "UPDATE Player SET nick = ?,password = ?,email = ? where nick = ?;";
				stmtMod = conn.prepareStatement(comando);
				stmtMod.setString(1, nick);
				stmtMod.setString(2, password);
				stmtMod.setString(3, email);
				stmtMod.setString(4, nombreFiltro);
				modificado = stmtMod.executeUpdate();
			} else {
				comando = "UPDATE Player SET nick = ?,password = ?,email = ? where email = ?;";
				stmtMod = conn.prepareStatement(comando);
				stmtMod.setString(1, nick);
				stmtMod.setString(2, password);
				stmtMod.setString(3, email);
				stmtMod.setString(4, emailFiltro);
				modificado = stmtMod.executeUpdate();
			}

			if (modificado > 0) {
				System.out.println("Jugador modificado con exito");
				if (id != 0) {
					comando = "SELECT * FROM Player WHERE idPlayer = ?";
					stmtSelectNew = conn.prepareStatement(comando);
					stmtSelectNew.setInt(1, id);
					res = stmtSelectNew.executeQuery();

				} else if (!nombreFiltro.equals("")) {
					comando = "SELECT * FROM Player WHERE idPlayer = ?";
					stmtSelectNew = conn.prepareStatement(comando);
					stmtSelectNew.setInt(1, idPlayer);
					res = stmtSelectNew.executeQuery();
				} else {
					comando = "SELECT * FROM Player WHERE idPlayer = ?";
					stmtSelectNew = conn.prepareStatement(comando);
					stmtSelectNew.setInt(1, idPlayer);
					res = stmtSelectNew.executeQuery();
				}
				if (res.next()) {
					System.out.println("=======================================");
					System.out.println("ID: " + res.getInt("idPlayer"));
					System.out.println("Nick: " + res.getString("nick"));
					System.out.println("Password: " + res.getString("password"));
					System.out.println("Email: " + res.getString("email"));
					System.out.println("=======================================");
				}

				System.out.println("¿QUIERES CONFIRMAR LA TRANSACCION? Indique S(Si) o N(No)");
				confirmacion = sc.nextLine().toLowerCase();

				while (!confirmacion.equals("s") && !confirmacion.equals("n")) {
					System.out.println("Indique S o N, no es tan conplicado");
					confirmacion = sc.nextLine();
				}
				if (confirmacion.equals("s")) {
					conn.commit();
					System.out.println("TRANSACCION CONFIRMADA");
				} else {
					System.out.println("TRANSACCION CANCELADA");
					conn.rollback();
				}
			} else {
				if(id!=0) {
					System.err.println("Error: no se ha podido modificar el jugador, error en la BD o el jugador con id: "
							+ id + " no existe");
					
				} else if(!nombreFiltro.equals("")) {
					System.err.println("Error: no se ha podido modificar el jugador, error en la BD o el jugador con nick: "
							+ nombreFiltro + " no existe");
				} else {
					System.err.println("Error: no se ha podido modificar el jugador, error en la BD o el jugador con email: "
							+ emailFiltro + " no existe");
				}
			}

		} catch (SQLException e) {
			System.err.println("Error: No se ha podido realizar la consulta");
		}
	}

	public static void modificarDatosGames(int id, String nombreFiltro, String nombre, Time tiempoJugado) {
		Connection conn = null;
		PreparedStatement stmtMod = null;
		PreparedStatement stmtSelectNew = null;
		PreparedStatement stmtSelectOld = null;
		int idGames = 0;
		String comando = "";
		String confirmacion = "";
		int modificado = 0;
		ResultSet res = null;

		try {
			conn = Conexion.conectar();
			conn.setAutoCommit(false);
			// Cogemos el id a traves el nombre, lo tenemos que hacer porque si no ocurre
			// fallos en el select con el nombre(ya que el nombre del juego se borra y no se
			// muestra)
			if (!nombreFiltro.equals("")) {
				comando = "SELECT idGames FROM Games WHERE nombre = ?;";
				stmtSelectOld = conn.prepareStatement(comando);
				stmtSelectOld.setString(1, nombreFiltro);
				res = stmtSelectOld.executeQuery();
				if (res.next()) {
					idGames = res.getInt("idGames");
				}
			}
			
			if(id!=0) {
				comando = "UPDATE Games SET nombre = ?, tiempojugado= ? where idGames = ?;";
				stmtMod = conn.prepareStatement(comando);
				stmtMod.setString(1, nombre);
				stmtMod.setTime(2, tiempoJugado);
				stmtMod.setInt(3, id);
				modificado = stmtMod.executeUpdate();
			} else if(!nombreFiltro.equals("")) {
				comando = "UPDATE Games SET nombre = ?, tiempojugado= ? where nombre = ?";
				stmtMod = conn.prepareStatement(comando);
				stmtMod.setString(1, nombre);
				stmtMod.setTime(2, tiempoJugado);
				stmtMod.setString(3, nombreFiltro);
				modificado = stmtMod.executeUpdate();
			}
			
			if(modificado>0) {
				if(id!=0) {
					comando = "SELECT * FROM Games WHERE idGames = ?";
					stmtSelectNew = conn.prepareStatement(comando);
					stmtSelectNew.setInt(1, id);
					res = stmtSelectNew.executeQuery();
				} else {
					comando = "SELECT * FROM Games WHERE idGames = ?";
					stmtSelectNew = conn.prepareStatement(comando);
					stmtSelectNew.setInt(1, idGames);
					res = stmtSelectNew.executeQuery();
				}
				if(res.next()) {
					System.out.println("=========================");
					System.out.println("ID: "+ res.getInt("idGames"));
					System.out.println("Nombre: " + res.getString("nombre"));
					System.out.println("Tiempo jugado: "+ res.getTime("tiempoJugado"));
					System.out.println("=========================");
				}
				System.out.println("¿QUIERES CONFIRMAR LA TRANSACCION? Indique S(Si) o N(No)");
				confirmacion = sc.nextLine().toLowerCase();

				while (!confirmacion.equals("s") && !confirmacion.equals("n")) {
					System.out.println("Indique S o N, no es tan conplicado");
					confirmacion = sc.nextLine();
				}
				if (confirmacion.equals("s")) {
					conn.commit();
					System.out.println("TRANSACCION CONFIRMADA");
				} else {
					System.out.println("TRANSACCION CANCELADA");
					conn.rollback();
				}
			} else {
				if(id!=0) {
					System.err.println("Error: no se ha podido modificar el juego, error en la BD o el juego con id: "
							+ id + " no existe");					
				} else {
					System.err.println("Error: no se ha podido modificar el juego, error en la BD o el juego con nombre : "
							+ nombre + " no existe");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
