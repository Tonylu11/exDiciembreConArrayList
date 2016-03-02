package exLuqueBravoAntonio;

import java.util.ArrayList;

import utiles.Menu;
import utiles.Teclado;

/**
 * Previamente a las instrucciones, recuerda:
 * 
 * El código no sólo ha de compilar y funcionar según estas disposiciones, sino
 * que ha de ser entendible y eficiente, sin redundancia y con una finalidad
 * clara. Empaqueta todos los ficheros .java en el paquete
 * exNombreApellido1Apellido2 Recuerda mantener la visibilidadmás adecuada Dale
 * al código un formato legible. Entrega la carpeta completa del código fuente
 * comprimida en un fichero exNombreApellido1Apellido2 Genera la documentación
 * javadoc y entrégala junto con el resto de los documentos. Para ello: Cada
 * clase ha de tener su comentario junto con el nombre del autor correctamente
 * identificado según javadoc Documenta en cada clase al menos un campo un
 * método, con sus argumentos
 * 
 * Implementa al menos tres clases: TestCuentas, Persona y Cuenta y añádele los
 * campos y métodos que estimes oportunos según estas instrucciones. Se nos pide
 * implementar el comportamiento de una cuenta corriente. Queremos hacer
 * hincapié en el número de cuenta, que ha de ser único. En este caso el número
 * de cuenta se generará mediante un contador común a todas las cuentas. La
 * primera cuenta deberá tener el código de cuenta con valor 1. La cuenta
 * permitirá al menos las siguientes operaciones:
 * 
 * Ingreso a la cuenta de una cantidad de dinero. Reintegro de la cuenta de una
 * cantidad de dinero. La cuenta no puede llegar a números rojos. En tal caso,
 * el reintegro no puede llevarse a cabo. Transferencia de una cantidad de
 * dinero desde una cuenta a otro. La cuenta origen de la transferencia no puede
 * llegar a números rojos. En tal caso, la transferencia no puede llevarse a
 * cabo. Mostrar el estado de la cuenta, donde aparezcan todos los
 * atributos(toString())
 * 
 * Crea una clase TestCuentas donde estará el método main que lleve a cabo las
 * siguientes operaciones:
 * 
 * Ha de crear dos cuentas con las siguientes características: Primera cuenta:
 * de "Mortadelo", con dni 11223344 y dirección Mikasa. Saldo inicial de la
 * cuenta: 1000 euros Segunda cuenta: de Filemon, con dni 55667788 y dirección
 * Calle del Medio. Saldo inicial: 2000 euros. Una vez creadas las dos cuentas,
 * sobre la cuenta de Mortadelo han de realizarse las siguientes operaciones: Un
 * reintegro de 500 euros Un cambio de domicilio a la nueva dirección 13, Rue
 * del Percebe Una transferencia de la cuenta de Mortadelo a la de Filemón, de
 * una cantidad de 500 euros. Una segunda transferencia de la cuenta de
 * Mortadelo a la de Filemón, de una cantidad de 500 euros Un reintegro de la
 * cuenta de Mortadelo de 7 euros.
 * 
 * Como resultado, el programa mostrará los siguientes mensajes: Cuenta: 1,
 * 1000.0, 11223344, Mortadelo, Mikasa. Cuenta: 2, 2000.0, 55667788, Filemon,
 * Calle del Medio.
 * 
 * --Después de reintegro(500) Cuenta: 1, 500.0, 11223344, Mortadelo, Mikasa.
 * 
 * --Después de setDireccion("13, Rue del Percebe") 11223344, Mortadelo, 13, Rue
 * del Percebe.
 * 
 * --Después de transferencia(500) Cuenta: 1, 0.0, 11223344, Mortadelo, 13, Rue
 * del Percebe. Cuenta: 2, 2500.0, 55667788, Filemon, Calle del Medio. ERROR. No
 * se ha podido realizar la transferencia.
 * 
 * --Después de transferencia(500) Cuenta: 1, 0.0, 11223344, Mortadelo, 13, Rue
 * del Percebe. Cuenta: 2, 2500.0, 55667788, Filemon, Calle del Medio. ERROR. No
 * se ha podido realizar el reintegro
 * 
 * --Después de reintegro(7) Cuenta: 1, 0.0, 11223344, Mortadelo, 13, Rue del
 * Percebe.
 * 
 * Asegúrate de que:
 * 
 * El DNI del titular es válido (expresión regular y algoritmo) En caso de
 * introducir un DNI erróneo o sin letra, se le indicará al usario previamente a
 * la creación del titular. Siempre se le exigirá la letra correcta. Lanza una
 * excepción en el caso en que el dni no sea correcto o el resto de los datos no
 * cumplan un mínimo de formato. (regex)
 * 
 * @author Antonio Luque Bravo
 * @version 1.0
 *
 */
public class TestCuentas {
	private static Banco banco = new Banco();

	public static void main(String[] args) {
		gestionarBanco();
	}

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

	private static void mostrarTodosClientes() {
		System.out.println(banco.mostrarClientes());
	}

	private static void mostrarCuenta() {
		try {
			System.out.println(banco.mostrarCuentasporCodigo(Teclado.leerEntero("Introduce el código de la cuenta.")));
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void mostrarCliente() {
		try {
			System.out.println(banco.mostrarPersonasPorDNI(Teclado.leerCadena("Introduce el DNI del cliente.")));
		} catch (DniInvalidoException e) {
			System.err.println(e.getMessage());
		} catch (ClienteNoExisteException e) {
			System.err.println(e.getMessage());
		}

	}

	private static void mostrarTodasCuentas() {
		System.out.println(banco.mostrarCuentasYClientes());
	}

	private static void transferenciaCuenta() {
		mostrarTodasCuentas();
		try {
			banco.transferenciaCuenta(
					Teclado.leerEntero("Introduce el código de la cuenta de la que se transferira el dinero."),
					Teclado.leerEntero("Introduce el código de la cuenta en la que se ingresará la transferencia."),
					Teclado.leerEntero("Cantidad a transferir:"));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (CantidadNegativaException e) {
			System.err.println(e.getMessage());
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void reintegroCuenta() {
		try {
			mostrarTodasCuentas();
			banco.reintegroCuenta(Teclado.leerEntero("Introduce el código de la cuenta."),
					Teclado.leerEntero("Introduce la cantidad a retirar."));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		} catch (CantidadNegativaException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void ingresoCuenta() {
		try {
			mostrarTodasCuentas();
			banco.ingresoCuenta(Teclado.leerEntero("Introduce el código de la cuenta."),
					Teclado.leerEntero("Introduce la cantidad a ingresar."));
		} catch (NumerosRojosException e) {
			System.err.println(e.getMessage());
		} catch (CuentaNoExisteException e) {
			System.err.println(e.getMessage());
		} catch (CantidadNegativaException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void annadirCuenta() {
		try {
			banco.annadirCuentaCliente(Teclado.leerEntero("Introduce el saldo inicial de la cuenta."),
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