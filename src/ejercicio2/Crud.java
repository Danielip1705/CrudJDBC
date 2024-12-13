package ejercicio2;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import javax.naming.CommunicationException;

public class Crud {
	
	static Scanner sc = new Scanner(System.in);
	static String nick = "";
	static String pwd = "";
	static String email = "";
	static String nombre = "";
	static LocalTime tiempoJugado;
	static int hora = 0;
	static int minutos = 0;
	static int idPlayer = 0;
	static int idGames = 0;
	static int idCompra=0;
	static String cosa = "";
	static double precio = 0;
	
	public static void main(String[] args) {

		int num = -1;
		final String OPCION_INVALIDA="ESTA OPCION NO EXISTE";
		

		while (num != 0) {
			menu();
			num = sc.nextInt();
			switch (num) {
			// Para mostrar la conexion a la base de datos
			case 1:
				System.out.println(Conexion.conectar());
				break;
			// Para crear las tablas
			case 2:
				subMenuTabla();
				num = sc.nextInt();
				crearTablas(num);
				break;
			case 3:
				subMenuInsertar();
				num = sc.nextInt();
				sc.nextLine();
				switch (num) {
				case 1:
					insertarDatosPlayer();
					break;
				case 2:
					insertarDatoCompras();
					break;
				case 3:
					insertarDatoJuego();
					break;

				}
				break;
			// Listado de las tablas
			case 4:
				subMenuMostrarTablas();
				num = sc.nextInt();
				sc.nextLine();
				switch (num) {
				// Jugadores
				case 1:
					subMenuListadoJugador();
					num = sc.nextInt();
					sc.nextLine();
					visualizacionTablaJugador(num);
					break;
				// Compras
				case 2:
					subMenuCompra();
					num = sc.nextInt();
					num = visualizarCompra(num, OPCION_INVALIDA);
					break;
				// Games
				case 3:
					subMenuListadogGame();
					num = sc.nextInt();
					sc.nextLine();
					visualizacionTablaGames(num);
					break;
				}

				break;
			//MODIFICAR 
			case 5:
				subMenuMostrarTablas();
				num = sc.nextInt();
				sc.nextLine();
				switch(num) {
				//MODIFICAR JUGADOR
				case 1:
					subMenuFiltradoJugador();
					num = sc.nextInt();
					sc.nextLine();
					switch(num) {
					//CASO 1, MODIFICAR POR ID
					case 1:
						System.out.println("Inserte el id del jugador que quieras modificar");
						idPlayer = sc.nextInt();
						sc.nextLine();
						System.out.println("Indica el nombre nuevo");
						nick = sc.nextLine();
						System.out.println("Indica la contraseña nueva");
						pwd = sc.nextLine();
						System.out.println("Indica el nombre del correo nuevo");
						email = sc.nextLine();
						Modificar.modificarJugadorPorId(idPlayer, "", "", nick, pwd, email);
						break;
					case 2:
						String nombreBuscar="";
						System.out.println("Inserte el nombre del jugador que quieras modificar");
						nombreBuscar= sc.nextLine();
						System.out.println("Indica el nombre nuevo");
						nick = sc.nextLine();
						System.out.println("Indica la contraseña nueva");
						pwd = sc.nextLine();
						System.out.println("Indica el nombre del correo nuevo");
						email = sc.nextLine();
						Modificar.modificarJugadorPorId(0, nombreBuscar, "", nick, pwd, email);
						break;
					case 3:
						String emailBuscar="";
						System.out.println("Inserte el nombre del correo del jugador que quieras modificar");
						emailBuscar= sc.nextLine();
						System.out.println("Indica el nombre nuevo");
						nick = sc.nextLine();
						System.out.println("Indica la contraseña nueva");
						pwd = sc.nextLine();
						System.out.println("Indica el nombre del correo nuevo");
						email = sc.nextLine();
						Modificar.modificarJugadorPorId(0, "", emailBuscar, nick, pwd, email);
						break;
					default:							
					}
					break;
				//Compra
				case 2:
					break;
					
				case 3:
					break;
				default:
				System.out.println(OPCION_INVALIDA);		
				}
				break;
			}
		}

		sc.close();

	}

	private static void subMenuFiltradoJugador() {
		System.out.println("¿QUIERES FILTRARLO?");
		System.out.println("1. POR ID");
		System.out.println("2. POR NOMBRE");
		System.out.println("3. POR EMAIL");
	}

	private static int visualizarCompra(int num, final String OPCION_INVALIDA) {
		switch(num) {
		//Todos
		case 1:
			Listado.listadoCompra();
			break;
		//Id compra
		case 2:
			boolean superoId = true;
			int id = 0;
			int contador = 0;
			while (superoId) {

				System.out.println("INDICA LA ID");
				idCompra = sc.nextInt();
				contador = Listado.contarId("Compras");
				if (idCompra > contador) {
					System.out.println("NO EXISTE COMPRA CON ESE ID");
				} else {
					superoId = false;
				}
			}
			sc.nextLine();
			Listado.listadoCompraPorId(idCompra, "IdCompra");
			break;
		//Id jugador
		case 3:
			superoId = true;
			id = 0;
			contador = 0;
			while (superoId) {

				System.out.println("INDICA LA ID");
				id = sc.nextInt();
				contador = Listado.contarId("Player");
				if (id > contador) {
					System.out.println("NO EXISTE JUGADOR CON ESE ID");
				} else {
					superoId = false;
				}
			}
			sc.nextLine();
			Listado.listadoCompraPorId(id, "idPlayer");
			break;
		//IdCompra
		case 4:
			superoId = true;
			id = 0;
			contador = 0;
			while (superoId) {

				System.out.println("INDICA LA ID");
				id = sc.nextInt();
				contador = Listado.contarId("Games");
				if (id > contador) {
					System.out.println("NO EXISTE JUEGO CON ESE ID");
				} else {
					superoId = false;
				}
			}
			sc.nextLine();
			Listado.listadoCompraPorId(id, "idGames");
			break;
			//Por precio
		case 5:
			double precio = 0;
			System.out.println("INDIQUE EL PRECIO");
			precio = sc.nextDouble();
			subMenuPrecio();
			num = sc.nextInt();
			visualizarPrecio(num, OPCION_INVALIDA, precio);
			break;
		case 6:
			System.out.println("1. POR AÑO");
			System.out.println("2. POR AÑO Y MES");
			num=sc.nextInt();
			sc.nextLine();
			visuzalizarFecha(num, OPCION_INVALIDA);
			break;
		default:
			System.out.println(OPCION_INVALIDA);
		}
		return num;
	}

	private static void subMenuPrecio() {
		System.out.println("================================");
		System.out.println("¿QUE TIPO DE ORDEN QUIERES?");
		System.out.println("1. MAYOR QUE ESE PRECIO");
		System.out.println("2. MENOR QUE ESE PRECIO");
		System.out.println("================================");
	}

	private static void visualizarPrecio(int num, final String OPCION_INVALIDA, double precio) {
		switch(num) {
		case 1:
			Listado.listadoCompraPorPrecio(precio, num);
			break;
			
		case 2:
			Listado.listadoCompraPorPrecio(precio, num);
			break;
			default:
				System.out.println(OPCION_INVALIDA);
		}
	}

	private static void visuzalizarFecha(int num, final String OPCION_INVALIDA) {
		switch(num) {
		case 1:
			int año = 0;
			System.out.println("INDICA EL AÑO A BUSCAR");
			año = sc.nextInt();
			Listado.listadoCompraPorFecha(año, 0, num);
			break;							
		case 2:
			año = 0;
			int mes =0;
			System.out.println("INDICA EL AÑO A BUSCAR");
			año = sc.nextInt();
			sc.nextLine();
			System.out.println("INDICA EL MES");
			mes = sc.nextInt();
			sc.nextLine();
			Listado.listadoCompraPorFecha(año, mes, num);
			break;
		default:
			System.out.println(OPCION_INVALIDA);
		}
	}

	private static void subMenuCompra() {
		System.out.println("1. TODOS");
		System.out.println("2. POR ID");
		System.out.println("3. POR ID DEL JUGADOR");
		System.out.println("4. POR ID DEL JUEGO");
		System.out.println("5. POR PRECIO");
		System.out.println("6. POR FECHA");
	}

	private static void visualizacionTablaGames(int num) {
		switch (num) {
		case 1:
			Listado.mostrarListadoGames();
			break;
		case 2:
			boolean idSuperado = true;
			int id = 0;
			int contador = Listado.contarId("Games");
			while (idSuperado) {

				System.out.println("Inserte el id");
				id = sc.nextInt();
				sc.nextLine();
				if (id > contador) {
					System.out.println("No existe un usuario con ese id");
				} else {
					idSuperado = false;
				}
			}
			Listado.listadoGamesPorId(id);
			break;
		case 3:
			String nombre = "";
			System.out.println("INDIQUE EL NOMBRE");
			nombre = sc.nextLine();
			Listado.listadoGamesPorNombre(nombre);
			break;

		}
	}

	private static void visualizacionTablaJugador(int num) {
		switch (num) {
		case 1:
			Listado.listadoPlayer();
			break;
		case 2:
			boolean superoId = true;
			int id = 0;
			int contador = 0;
			while (superoId) {

				System.out.println("INDICA LA ID");
				id = sc.nextInt();
				contador = Listado.contarId("Player");
				if (id > contador) {
					System.out.println("No existe jugador con ese id");
				} else {
					superoId = false;
				}
			}
			sc.nextLine();

			Listado.listadoPlayerPorId(id);
			break;

		case 3:
			String nombre = "";
			System.out.println("INSERTE EL NOMBRE DEL JUGADOR");
			nombre = sc.nextLine();
			Listado.listadoPlayerPorNombre(nombre);
			break;
		case 4:
			String email="";
			System.out.println("INSERTE EL EMAIL DEL JUGADOR");
			email = sc.nextLine();
			Listado.listadoPlayerPorCorreo(email);
			break;
		}
	}

	private static void subMenuListadogGame() {
		System.out.println("1. TODOS");
		System.out.println("2. POR ID");
		System.out.println("3. POR NOMBRE");
	}
	private static void subMenuListadoJugador() {
		System.out.println("1. TODOS");
		System.out.println("2. POR ID");
		System.out.println("3. POR NOMBRE");
		System.out.println("4. POR CORREO");
	}

	private static void subMenuMostrarTablas() {
		System.out.println("================");
		System.out.println("¿ELIGE QUE TABLA VAS HA USAR?");
		System.out.println("1. PLAYER");
		System.out.println("2. COMPRAS");
		System.out.println("3. GAMES");
		System.out.println("================");
	}

	private static void insertarDatoCompras() {

		LocalDate fechaCompra;
		System.out.println("Inserte el id del jugador que haya comprado algo");
		idPlayer = sc.nextInt();
		System.out.println("Inserte el id del juego al que esta jugando");
		idGames = sc.nextInt();
		sc.nextLine();
		System.out.println("Inserte el objeto que ha comprado el jugador");
		cosa = sc.nextLine();
		System.out.println("Inserte el precio del objeto");
		precio = sc.nextDouble();
		sc.nextLine();
		fechaCompra = LocalDate.now();

		Insertar.insertarDatosTablaCompra(idPlayer, idGames, cosa, precio, fechaCompra);

	}

	private static void crearTablas(int num) {
		String nombreTabla;
		if (num == 1) {
			nombreTabla = "Player";
			CrearTablas.crearTablas(nombreTabla);
		} else if (num == 2) {
			nombreTabla = "Compras";
			CrearTablas.crearTablas(nombreTabla);
		} else if (num == 3) {
			nombreTabla = "Games";
			CrearTablas.crearTablas(nombreTabla);
		} else if (num == 4) {
			nombreTabla = "Todas";
			CrearTablas.crearTablas(nombreTabla);
		} else {
			System.out.println("Elige entre el numero 1,2,3 o 4. No me seas Pelelín");
		}
	}

	private static void insertarDatoJuego() {
		System.out.println("Nombre del juego al que has jugado");
		nombre = sc.nextLine();
		System.out.println("Indica las horas que has jugado");
		hora = sc.nextInt();
		sc.nextLine();
		System.out.println("Indica los minutos que has jugado");
		minutos = sc.nextInt();
		sc.nextLine();
		tiempoJugado = LocalTime.of(hora, minutos);
		try {
			Insertar.insertarDatosTablaGames(nombre, tiempoJugado);
		} catch (CommunicationException e) {
			System.out.println("No se ha podido comunicar a la base de datos");
			e.printStackTrace();
		}
	}

	private static void insertarDatosPlayer() {
		System.out.println("Indica el nombre del jugador");
		nick = sc.nextLine();
		System.out.println("Indica la contraseña");
		pwd = sc.nextLine();
		System.out.println("Indica el nombre del correo");
		email = sc.nextLine();
		try {
			Insertar.insertarDatosTablaJugador(nick, pwd, email);
		} catch (CommunicationException e) {
			System.out.println("No se ha podido comunicar a la base de datos");
		}
	}

	private static void subMenuInsertar() {
		System.out.println("=================");
		System.out.println("¿A QUE TABLA QUIERES INSERTAR LOS DATOS?");
		System.out.println("1. PLAYER");
		System.out.println("2. COMPRAS");
		System.out.println("3. GAMES");
		System.out.println("=================");
	}

	private static void subMenuTabla() {
		System.out.println("==================");
		System.out.println("¿QUE TABLA QUIERES CREAR?");
		System.out.println("1. PLAYER");
		System.out.println("2. COMPRAS");
		System.out.println("3. GAMES");
		System.out.println("4. TODAS");
		System.out.println("==================");
	}

	private static void menu() {
		System.out.println("============================");
		System.out.println("1. CONECTAR BASE DE DATOS");
		System.out.println("2. CREAR TABLAS");
		System.out.println("3. INSERTAR");
		System.out.println("4. LISTAR");
		System.out.println("5. MODIFICAR");
		System.out.println("6. BORRAR");
		System.out.println("============================");
	}

}
