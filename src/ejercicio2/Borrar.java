package ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Borrar {

	static Scanner sc = new Scanner(System.in);
	
	/**
	 * Funcion que borrar todas las tablas de la base de datos
	 */
	public static void borrarTodasTablas() {

		Connection conn = null;
		PreparedStatement stmt = null;
		String comando = "";
		int borrado = 0;

		try {
			conn = Conexion.conectar();
			comando = "DROP TABLE Compras,Games,Player;";
			stmt = conn.prepareStatement(comando);
			borrado = stmt.executeUpdate();
			if (borrado == 0) {
				System.out.println("Se ha borrado con exito todas las tablas");
			} else {
				System.out.println("No se ha podido borrar todas las tablas");
			}
		} catch (SQLException e) {
			System.err.println("Error: Error en la consulta");
		}finally {
			
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Funcion que borra una tabla en concreto
	 * @param tabla Cadena que contiene el nombre de la tabla a borrar
	 */
	public static void borrarTablas(String tabla) {

		Connection conn = null;
		PreparedStatement stmt = null;
		String comando = "";
		int borrado = -1;

		try {
			conn = Conexion.conectar();
			comando = "DROP TABLE " + tabla + " ;";
			stmt = conn.prepareStatement(comando);
			borrado = stmt.executeUpdate();
			if (borrado == 0) {
				System.out.println("Se ha borrado con exito la tabla " + tabla);
			} else {
				System.out.println("Error");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("No se puede eliminar la tabla " + tabla + ", primero hay que borrar la tabla Compras");
		} catch (SQLSyntaxErrorException e) {
			System.err.println("No existe la tabla " + tabla + " en la BD");
		} catch (SQLException e) {

			System.err.println("Error: La BD ha producido un error");
		} finally {
			
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * Elimina registros de una tabla específica en la base de datos según los filtros proporcionados.
	 * 
	 * @param id Valor entero que representa el ID del registro a eliminar.
	 * @param nombreFiltro  Cadena que representa un filtro basado en el nombre.
	 * @param emailFiltro   Cadena que representa un filtro basado en el email. 
	 * @param tablaEliminar Cadena que indica el nombre de la tabla desde la que se eliminará el registro.
	 * @param filtro        Cadena que indica el nombre de la columna que será utilizada como filtro.
	 * 
	 */

	public static void borrarDatos(int id, String nombreFiltro, String emailFiltro, String tablaEliminar,
			String filtro) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String confirmacion = "";
		String comando = "";
		int borrado = -1;

		try {
			conn = Conexion.conectar();
			conn.setAutoCommit(false);
			if (id != 0) {
				comando = "DELETE FROM " + tablaEliminar + " WHERE " + filtro + "= " + id + " ;";
			} else if (!nombreFiltro.equals("")) {
				comando = "DELETE FROM " + tablaEliminar + " WHERE " + filtro + " like '" + nombreFiltro + "' ;";
			} else {
				comando = "DELETE FROM " + tablaEliminar + " WHERE " + filtro + " like '" + emailFiltro + "' ;";
			}
			stmt = conn.prepareStatement(comando);
			borrado = stmt.executeUpdate();
			if (borrado > 0) {

				if (tablaEliminar.equals("Player")) {

					System.out.println("Se ha eliminado el jugador con exito");
				} else if (tablaEliminar.equals("Games")) {

					System.out.println("Se ha eliminado el juego con exito");
				} else {

					System.out.println("Se ha eliminado la compra con exito");
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
				System.err.println("Error: No se ha podido eliminar el dato");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("No se puede eliminar la tabla , primero hay que borrar la tabla Compras");
		} catch (SQLSyntaxErrorException e) {
			System.err.println("No existe la tabla   en la BD");
		} catch (SQLException e) {

			System.err.println("Error: La BD ha producido un error");
		} finally {
			
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error: La BD ha producido un error");
				e.printStackTrace();
			}
		}

	}
}
