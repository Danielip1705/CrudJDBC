package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.naming.CommunicationException;

public class Insertar {

	public static int contadorEntidades(String tabla) {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet res = null;
		int contador = 0;
		try {
			conn = Conexion.conectar();
			query = conn.prepareStatement("SELECT count(id) FROM "+tabla);
			res = query.executeQuery();
			while (res.next()) {
				contador = res.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return contador;
	}
	
	public static void insertarDatosTablaJugador(String nick,String psw,String email) throws CommunicationException {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		int contador = 0;
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			contador = contadorEntidades("Player");
			if(contador<=0) {
				stmt.executeUpdate("ALTER TABLE Player AUTO_INCREMENT = 1;");
			}
			comando = "insert into Player (nick,password,email) values('"+nick+"','"+psw+"','"+email+"');";
			stmt.executeUpdate(comando);
			System.out.println("Se ha insertado el jugador con exito");
		
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void insertarDatosTablaGames(String nick,LocalTime tiempoJugado) throws CommunicationException {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		int contador = 0;
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			contador = contadorEntidades("Games");
			if(contador<=0) {
				stmt.executeUpdate("ALTER TABLE Games AUTO_INCREMENT = 1;");
			}
			comando = "insert into Games (nombre,tiempoJugado) values('"+nick+"','"+tiempoJugado+"');";
			stmt.executeUpdate(comando);
			System.out.println("Se ha insertado el juego con exito");
		
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void insertarDatosTablaCompra(int idJugador,int idJuego,String cosa,double precio,LocalDate fechaCompra) {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		int contador = 0;
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			contador = contadorEntidades("Compras");
			if(contador<=0) {
				stmt.executeUpdate("ALTER TABLE Compras AUTO_INCREMENT = 1;");
			}
			comando = "insert into Compras (idPlayer,idGames,cosa,precio,fechaCompra) values('"+idJugador+"','"+idJuego+"','"+cosa+"','"+precio+"','"+fechaCompra+"');";
			stmt.executeUpdate(comando);
			System.out.println("Se ha insertado la compra con exito");
		
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("No se ha podido añadir compra, el id del jugador o de la compra no existe");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
