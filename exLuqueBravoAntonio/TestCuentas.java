package exLuqueBravoAntonio;

import utiles.Menu;
import utiles.Teclado;

/**
 * Modifica el ejercicio anterior para manejar cuentas con titulares,
 * reintegros, transferencias...
 * 
 * Un interfaz podría ser este (no implementes la eliminación en ningún
 * ArrayList)
 *
 * Menú principal
 * 
 * (1) Menú cuentas
 * 
 * (2) Menú Personas
 * 
 * (3) Salir
 * 
 * 2
 *
 * Personas
 * 
 * (1) Crear persona nueva
 * 
 * (2) Modificar dirección
 * 
 * (3) Mostrar persona
 * 
 * (4) Mostrar personas
 * 
 * (5) Salir
 * 
 * 5
 *
 * Menú principal
 * 
 * (1) Menú cuentas
 * 
 * (2) Menú Personas
 * 
 * (3) Salir
 * 
 * 1
 *
 * Cuentas corrientes
 * 
 * (1) Crear cuenta nueva
 * 
 * (2) Ingreso
 * 
 * (3) Reintegro
 * 
 * (4) Transferencia
 * 
 * (5) Mostrar cuenta
 * 
 * (6) Mostrar cuentas
 * 
 * (7) Salir
 * 
 * @author Antonio Luque Bravo
 * @version 1.0
 *
 */
public class TestCuentas {
	/**
	 * Banco.
	 */
	private static Banco banco = new Banco();

	public static void main(String[] args) {
		gestionarBanco();
	}

	/**
	 * M&eacute;todo para gestionar el banco.
	 */
	private static void gestionarBanco() {
		int opcion;
		Menu menuCuentas = new Menu("Banca Online.",
				new String[] { "Añadir cliente.", "Modificar la dirección.", "Eliminar cliente.", "Mostrar cliente.",
						"Añadir cuenta.", "Mostrar cuenta.", "Ingreso a cuenta.", "Reintegro de cuenta.",
						"Transferencia.", "Mostrar todas las cuentas.", "Mostrar todos los clientes.", "Salir." });
		do {
			opcion = menuCuentas.gestionar();
			switch (opcion) {
			case 1:
				annadirCliente();
				break;
			case 2:
				modificarDireccionCliente();
				break;
			case 3:
				eliminarCliente();
				break;
			case 4:
				mostrarCliente();
				break;
			case 5:
				annadirCuenta();
				break;
			case 6:
				mostrarCuenta();
				break;
			case 7:
				ingresoCuenta();
				break;
			case 8:
				reintegroCuenta();
				break;
			case 9:
				transferenciaCuenta();
				break;
			case 10:
				mostrarTodasCuentas();
				break;
			case 11:
				mostrarTodosClientes();
				break;
			case 12:
				System.out.println("Adiooos..");
				break;
			}
		} while (opcion != 12);
	}

	/**
	 * Modifica la direcci&oacute;n del cliente.
	 */
	private static void modificarDireccionCliente() {
		try {
			banco.modificarDireccion(Teclado.leerCadena("Introduce el DNI del cliente."),
					Teclado.leerCadena("Introduce la nueva dirección."));
		} catch (DniInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (ClienteNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Muestra todos los clientes del banco.
	 */
	private static void mostrarTodosClientes() {
		System.out.println(banco.mostrarClientes());
	}

	/**
	 * Muestra la cuenta introduciendo el c&oacute;digo de la misma.
	 */
	private static void mostrarCuenta() {
		try {
			System.out.println(banco.mostrarCuentasporCodigo(Teclado.leerEntero("Introduce el código de la cuenta.")));
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Muestra el cliente introduciendo el DNI del cliente.
	 */
	private static void mostrarCliente() {
		try {
			System.out.println(banco.mostrarPersonasPorDNI(Teclado.leerCadena("Introduce el DNI del cliente.")));
		} catch (DniInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (ClienteNoExisteException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Muestra todas las cuentas del banco.
	 */
	private static void mostrarTodasCuentas() {
		System.out.println(banco.mostrarCuentas());
	}

	/**
	 * Transfiere saldo de una cuenta a otra.
	 */
	private static void transferenciaCuenta() {
		mostrarTodasCuentas();
		try {
			banco.transferenciaCuenta(
					Teclado.leerEntero("Introduce el código de la cuenta de la que se transferira el dinero."),
					Teclado.leerEntero("Introduce el código de la cuenta en la que se ingresará la transferencia."),
					Teclado.leerDecimal("Cantidad a transferir:"));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (CantidadNegativaException e) {
			System.err.println(e.getMessage());
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Retira dinero de una cuenta.
	 */
	private static void reintegroCuenta() {
		try {
			mostrarTodasCuentas();
			banco.reintegroCuenta(Teclado.leerEntero("Introduce el código de la cuenta."),
					Teclado.leerDecimal("Introduce la cantidad a retirar."));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		} catch (CantidadNegativaException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Ingreso de saldo en una cuenta.
	 */
	private static void ingresoCuenta() {
		try {
			mostrarTodasCuentas();
			banco.ingresoCuenta(Teclado.leerEntero("Introduce el código de la cuenta."),
					Teclado.leerDecimal("Introduce la cantidad a ingresar."));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		} catch (CantidadNegativaException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * A&ntilde;ade una cuenta al cliente ya dado de alta.
	 */
	private static void annadirCuenta() {
		try {
			banco.annadirCuentaCliente(Teclado.leerDecimal("Introduce el saldo inicial de la cuenta."),
					Teclado.leerCadena("Introduce el DNI del titular existente."));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (TitularInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (DniInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (ClienteNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Elimina tanto el cliente como sus cuentas asociadas al mismo.
	 */
	private static void eliminarCliente() {
		try {
			banco.eliminarCliente(Teclado.leerCadena("Introduce el DNI del cliente a eliminar."));
		} catch (DniInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (ClienteNoExisteException e) {
			System.err.println(e.getMessage());
		} catch (TitularInvalidoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * A&ntilde;ade un cliente al banco.
	 */
	private static void annadirCliente() {
		try {
			banco.annadirCliente(Teclado.leerCadena("Introduce el nombre del cliente."),
					Teclado.leerCadena("Introduce un DNI válido."),
					Teclado.leerCadena("Introduce la dirección del cliente."));
		} catch (DniInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (ClienteYaExisteException e) {
			System.err.println(e.getMessage());
		}
	}
}