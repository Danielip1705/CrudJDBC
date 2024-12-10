package ejercicio2;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class CrearTablas {
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
					System.out.println("La tabla ya esta creada");
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
					System.out.println("tabla creada");
					
				} catch (SQLSyntaxErrorException e) {
					System.out.println("La tabla ya esta creada");
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
					System.out.println("La tabla ya esta creada");
				} catch (SQLSyntaxErrorException e) {
					System.out.println("La tabla ya esta creada");
				} catch (SQLException e) {
					System.out.println("Error al crear la tabla Compra: " + e.getMessage());
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
						System.out.println("Se han creado todas las tablas");
					} catch (SQLSyntaxErrorException e) {
						System.out.println("Algunas de las tablas estan creadas, no se puede crear todas a la vez");
					}
					break;
			}
		} catch (SQLException e) {
			// TODO: handle exception
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
