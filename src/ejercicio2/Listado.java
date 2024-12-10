package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class Listado {
	/**
	 * Funcion que muestra todos los jugadores de la base de datos
	 */
	public static void listadoPlayer() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet lector = null;
		try {
			conn = Conexion.conectar();
			stmt = conn.prepareStatement("SELECT * FROM Player;");
			lector = stmt.executeQuery();
			System.out.println("=======================================");
			while (lector.next()) {
				System.out.println("ID: " + lector.getInt("idPlayer"));
				System.out.println("Nick: " + lector.getString("nick"));
				System.out.println("Password: " + lector.getString("password"));
				System.out.println("Email: " + lector.getString("email"));
				System.out.println("=======================================");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	/**
	 * Funcion que muestra un jugador por id
	 * 
	 * @param id Numero entero que indica el id del jugador ha buscar
	 */
	public static void listadoPlayerPorId(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet lector = null;
		try {
			conn = Conexion.conectar();
			stmt = conn.prepareStatement("SELECT * FROM Player where idPlayer = " + id + ";");
			lector = stmt.executeQuery();
			System.out.println("=======================================");
			while (lector.next()) {
				System.out.println("ID: " + lector.getInt("idPlayer"));
				System.out.println("Nick: " + lector.getString("nick"));
				System.out.println("Password: " + lector.getString("password"));
				System.out.println("Email: " + lector.getString("email"));
				System.out.println("=======================================");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	/**
	 * Funcion que muestra un jugador por su nombre
	 * 
	 * @param nombre Cadena que contiene el nombre a buscar en la base de datos
	 */
	public static void listadoPlayerPorNombre(String nombre) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet lector = null;
		try {
			conn = Conexion.conectar();
			stmt = conn.prepareStatement("SELECT * FROM Player where nick like '" + nombre + "';");
			lector = stmt.executeQuery();
			System.out.println("=======================================");
			while (lector.next()) {
				System.out.println("ID: " + lector.getInt("idPlayer"));
				System.out.println("Nick: " + lector.getString("nick"));
				System.out.println("Password: " + lector.getString("password"));
				System.out.println("Email: " + lector.getString("email"));
				System.out.println("=======================================");
			}
		} catch (SQLSyntaxErrorException e) {
			System.out.println("El nombre indicado (" + nombre + ") no exite en la tabla Player");

		} catch (SQLException e) {
			// TODO: handle exception

		}

	}

	/**
	 * Funcion que buscar en la base de datos jugadores con el mismo correo
	 * 
	 * @param email Cadena que contiene el correo a buscar
	 */
	public static void listadoPlayerPorCorreo(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet lector = null;
		try {
			conn = Conexion.conectar();
			stmt = conn.prepareStatement("SELECT * FROM Player where email like '%" + email + "%';");
			lector = stmt.executeQuery();
			System.out.println("=======================================");
			while (lector.next()) {
				System.out.println("ID: " + lector.getInt("idPlayer"));
				System.out.println("Nick: " + lector.getString("nick"));
				System.out.println("Password: " + lector.getString("password"));
				System.out.println("Email: " + lector.getString("email"));
				System.out.println("=======================================");
			}
		} catch (SQLSyntaxErrorException e) {
			System.out.println("El nombre indicado (" + email + ") no exite en la tabla Player");

		} catch (SQLException e) {
			// TODO: handle exception

		}

	}

	
	///////////////////////////////////////////////////////////////////////////////
	// Games

	/**
	 * Funcion que muestra todos los juegos de la base de datos
	 */
	public static void mostrarListadoGames() {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet res = null;

		try {
			conn = Conexion.conectar();
			query = conn.prepareStatement("SELECT * FROM Games");
			res = query.executeQuery();
			System.out.println("=======================================");
			while (res.next()) {
				System.out.println("IdGame: " + res.getInt("idgames"));
				System.out.println("Nombre: " + res.getString("Nombre"));
				System.out.println("Tiempo jugado: " + res.getTime("tiempoJugado"));
				System.out.println("=======================================");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	/**
	 * Funcion que muestra un juego por su id
	 * 
	 * @param id Numero entero que indica el id del juego a buscar
	 */
	public static void listadoGamesPorId(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = Conexion.conectar();
			stmt = conn.prepareStatement("SELECT * FROM Games where idGames = " + id + ";");
			res = stmt.executeQuery();
			System.out.println("=======================================");
			while (res.next()) {
				System.out.println("IdGame: " + res.getInt("idgames"));
				System.out.println("Nombre: " + res.getString("Nombre"));
				System.out.println("Tiempo jugado: " + res.getTime("tiempoJugado"));
				System.out.println("=======================================");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	/**
	 * Funcion que muestra un juego de la base de datos por su nombre
	 * 
	 * @param nombre Cadena que indica el nombre del juego a buscar
	 */
	public static void listadoGamesPorNombre(String nombre) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = Conexion.conectar();
			stmt = conn.prepareStatement("SELECT * FROM Games where Nombre like '" + nombre + "';");
			res = stmt.executeQuery();
			System.out.println("=======================================");
			while (res.next()) {
				System.out.println("ID: " + res.getInt("idgames"));
				System.out.println("Nombre: " + res.getString("Nombre"));
				System.out.println("Tiempo jugado: " + res.getTime("tiempoJugado"));
				System.out.println("=======================================");
			}
		} catch (SQLSyntaxErrorException e) {
			System.out.println("El nombre indicado (" + nombre + ") no exite en la tabla Player");

		} catch (SQLException e) {
			// TODO: handle exception

		}

	}

	/**
	 * Funcion que muestra cuantos juegos hay en la base de datos
	 * 
	 * @return Numero entero que indica el numero de juegos
	 */
	public static int contarId(String tabla) {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet res = null;
		int juegos = 0;
		try {
			conn = Conexion.conectar();
			if(tabla.equals("Games")) {
				
				query = conn.prepareStatement("SELECT count(idGames) FROM "+tabla+";");
			} else if(tabla.equals("Player")) {
				query = conn.prepareStatement("SELECT count(idPlayer) FROM "+tabla+";");
			} else if(tabla.equals("Compras")) {
				query = conn.prepareStatement("SELECT count(idCompra) FROM "+tabla+";");
			}
			res = query.executeQuery();
			while (res.next()) {
				juegos = res.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return juegos;
	}
	
	////////////////////////////////////////////////////
	//COMPRAS
	
	/**
	 * Funcion que muestra todas las compras de la base de datos
	 */
	public static void listadoCompra() {
		Connection conn=null;
		PreparedStatement query= null;
		ResultSet res = null;
		System.out.println("=======================================");
		try {
			conn = Conexion.conectar();
			query = conn.prepareStatement("SELECT * FROM Compras");
			res = query.executeQuery();
			System.out.println("=======================================");
			while(res.next()) {
				System.out.println("ID: "+ res.getInt("idCompra"));
				System.out.println("ID jugador: "+ res.getInt("idPlayer"));
				System.out.println("ID juego: "+ res.getInt("idGames"));
				System.out.println("Cosa: "+ res.getString("cosa"));
				System.out.println("Precio: "+ res.getDouble("precio"));
				System.out.println("Fecha compra: "+res.getDate("fechaCompra"));
				System.out.println("=======================================");

			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void listadoCompraPorId(int id,String atributoId) {
		Connection conn=null;
		PreparedStatement query= null;
		ResultSet res = null;
		System.out.println("=======================================");
		try {
			conn = Conexion.conectar();
			query = conn.prepareStatement("SELECT * FROM Compras where "+atributoId+" = "+id+";");
			res = query.executeQuery();
			System.out.println("=======================================");
			while(res.next()) {
				System.out.println("ID: "+ res.getInt("idCompra"));
				System.out.println("ID jugador: "+ res.getInt("idPlayer"));
				System.out.println("ID juego: "+ res.getInt("idGames"));
				System.out.println("Cosa: "+ res.getString("cosa"));
				System.out.println("Precio: "+ res.getDouble("precio"));
				System.out.println("Fecha compra: "+res.getDate("fechaCompra"));
				System.out.println("=======================================");

			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void listadoCompraPorPrecio(double precio, int opc) {
		Connection conn=null;
		PreparedStatement query= null;
		ResultSet res = null;
		System.out.println("=======================================");
		try {
			conn = Conexion.conectar();
			switch(opc) {
			case 1:
				query = conn.prepareStatement("SELECT * FROM Compras where precio < "+precio+";");
				break;
			case 2:
				query = conn.prepareStatement("SELECT * FROM Compras where precio > "+precio+";");
				break;
			}
			res = query.executeQuery();
			System.out.println("=======================================");
			while(res.next()) {
				System.out.println("ID: "+ res.getInt("idCompra"));
				System.out.println("ID jugador: "+ res.getInt("idPlayer"));
				System.out.println("ID juego: "+ res.getInt("idGames"));
				System.out.println("Cosa: "+ res.getString("cosa"));
				System.out.println("Precio: "+ res.getDouble("precio"));
				System.out.println("Fecha compra: "+res.getDate("fechaCompra"));
				System.out.println("=======================================");

			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void listadoCompraPorFecha(int año,int mes, int opc) {
		Connection conn=null;
		PreparedStatement query= null;
		ResultSet res = null;
		System.out.println("=======================================");
		try {
			conn = Conexion.conectar();
			switch(opc) {
			case 1:
                query = conn.prepareStatement("SELECT * FROM Compras WHERE YEAR(fechaCompra) = ?");
                query.setInt(1, año); 
                break;
            case 2:
                query = conn.prepareStatement("SELECT * FROM Compras WHERE YEAR(fechaCompra) = ? AND MONTH(fechaCompra) = ?");
                query.setInt(1, año); 
                query.setInt(2, mes); 
                break;
			}
			res = query.executeQuery();
			System.out.println("=======================================");
			while(res.next()) {
				System.out.println("ID: "+ res.getInt("idCompra"));
				System.out.println("ID jugador: "+ res.getInt("idPlayer"));
				System.out.println("ID juego: "+ res.getInt("idGames"));
				System.out.println("Cosa: "+ res.getString("cosa"));
				System.out.println("Precio: "+ res.getDouble("precio"));
				System.out.println("Fecha compra: "+res.getDate("fechaCompra"));
				System.out.println("=======================================");

			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
