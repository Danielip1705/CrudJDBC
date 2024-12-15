package ejercicio2;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class CrearTablas {
	/**
	 * Funcion que dependiendo de la cadena pasada, crea la tabla
	 * @param nombreTabla Cadena que contiene el nombre de la tabla a crear
	 */
	public static void crearTablas(String nombreTabla) {
		Connection con = null;
		Statement stmt = null;
		String comando = "";
		try {
			con = Conexion.conectar();
			stmt = con.createStatement();
			switch (nombreTabla) {
			case "Player":
				try {
					comando = "Create table Player(\r\n"
					+ "idPlayer int Auto_Increment primary key,\r\n"
					+ "nick varchar(45),\r\n" 
					+ "password varchar(45),\r\n" 
					+ "email varchar(100)\r\n" 
					+ ");";
					stmt.executeUpdate(comando);
					System.out.println("tabla Player creada con exito");
				} catch (SQLSyntaxErrorException e) {
					System.err.println("La tabla ya esta creada");
				}
				break;
			case "Games":
				try {
					comando = "Create table Games(\r\n"
							+ "idGames int auto_increment primary key,\r\n"
							+ "nombre varchar(50),\r\n" 
							+ "tiempoJugado Time\r\n" 
							+ ");";
					stmt.executeUpdate(comando);
					System.out.println("Tabla Games creada con exito");
					
				} catch (SQLSyntaxErrorException e) {
					System.err.println("La tabla ya esta creada");
				}
				break;
			case "Compras":
				try {
					comando = "Create table Compras(\r\n"
							+ "idCompra int auto_increment primary key,\r\n"
							+ "idPlayer int,\r\n"
							+ "idGames int,\r\n"
							+ "cosa varchar(25),\r\n"
							+ "precio decimal(6,2),\r\n"
							+ "fechaCompra date,\r\n"
							+ "constraint fk_idPlayer_Player FOREIGN KEY (idPlayer) REFERENCES Player(idPlayer),\r\n"
							+ "constraint fk_idGames_Games FOREIGN KEY (idGames) REFERENCES Games(idGames)"
							+ ");";
					stmt.executeUpdate(comando);
					System.out.println("Tabla Compras creada con exito");
				} catch (SQLSyntaxErrorException e) {
					System.err.println("La tabla ya esta creada");
				} catch (SQLException e) {
					System.err.println("Error al crear la tabla Compra: Se nesesia las tablas Player y Games para su creacion");
				}
				break;
				case "Todas":
					try {
						
						comando = "Create table Player(\r\n"
								+ "idPlayer int Auto_Increment primary key,\r\n"
								+ "nick varchar(45),\r\n" 
								+ "password varchar(45),\r\n" 
								+ "email varchar(100)\r\n" 
								+ ");";
						stmt.executeUpdate(comando);
						comando = "Create table Games(\r\n"
								+ "idGames int auto_increment primary key,\r\n"
								+ "nombre varchar(50),\r\n" 
								+ "tiempoJugado Time\r\n" 
								+ ");";
						stmt.executeUpdate(comando);
						comando = "Create table Compras(\r\n"
								+ "idCompra int auto_increment primary key,\r\n"
								+ "idPlayer int,\r\n"
								+ "idGames int,\r\n"
								+ "cosa varchar(25),\r\n"
								+ "precio decimal(6,2),\r\n"
								+ "fechaCompra date,\r\n"
								+ "constraint fk_idPlayer_Player FOREIGN KEY (idPlayer) REFERENCES Player(idPlayer),\r\n"
								+ "constraint fk_idGames_Games FOREIGN KEY (idGames) REFERENCES Games(idGames)"
								+ ");";
						stmt.executeUpdate(comando);
						System.out.println("Se han creado todas las tablas con exito");
					} catch (SQLSyntaxErrorException e) {
						System.err.println("Algunas de las tablas estan creadas, no se puede crear todas a la vez");
					}
					break;
			}
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
