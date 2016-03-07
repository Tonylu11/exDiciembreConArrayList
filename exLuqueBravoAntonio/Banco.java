package exLuqueBravoAntonio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

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
public class Banco {
	/**
	 * ArrayList de clientes.
	 */
	private ArrayList<Persona> clientes = new ArrayList<Persona>();
	/**
	 * ArrayList de cuentas.
	 */
	private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

	/**
	 * M&eacute;todo para a&ntilde;adir un cliente al banco.
	 * 
	 * @param nombre
	 *            Nombre del cliente.
	 * @param dni
	 *            DNI del cliente.
	 * @param direccion
	 *            Direccion del cliente.
	 * @throws DniInvalidoException
	 *             Cuando el DNI no es correcto.
	 * @throws ClienteYaExisteException
	 *             Cuando el cliente ya esta dado de alta en el banco.
	 */
	public void annadirCliente(String nombre, String dni, String direccion)
			throws DniInvalidoException, ClienteYaExisteException {
		if (clientes.contains(new Persona(nombre, dni, direccion))) {
			throw new ClienteYaExisteException("El cliente ya ha sido dado de alta.");
		}
		Persona cliente = new Persona(nombre, dni, direccion);
		clientes.add(cliente);
	}

	/**
	 * Elimina un cliente del banco.
	 * 
	 * @param dni
	 *            DNI del cliente.
	 * @throws DniInvalidoException
	 *             Cuando el DNI no es correcto.
	 * @throws ClienteYaExisteException
	 *             Cuando el cliente ya esta dado de alta en el banco.
	 * @throws TitularInvalidoException
	 *             Cuando el titular noes v&aacute;lido.
	 */
	public void eliminarCliente(String dni)
			throws DniInvalidoException, ClienteNoExisteException, TitularInvalidoException {
		if (!clientes.contains(new Persona(dni))) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		Persona cliente = new Persona(dni);
		ListIterator<Cuenta> it = cuentas.listIterator();
		while (it.hasNext()) {
			Cuenta cuenta = it.next();
			if (cuentas.get(cuentas.indexOf(cuenta)).getTitular().equals(cliente)) {
				it.remove();
			}
		}
		clientes.remove(cliente);
	}

	/**
	 * A&ntilde;ade la cuenta a un cliente ya dado de alta en el banco.
	 * 
	 * @param saldo
	 *            Saldo de la cuenta.
	 * @param dniTitular
	 *            DNI del titular de a cuenta
	 * @throws DniInvalidoException
	 *             Cuando el DNI no es v&aacute;lido.
	 * @throws ClienteNoExisteException
	 *             Cuando el cliente no existe en el banco.
	 * @throws NumerosRojosException
	 *             Cuando el saldo este por debajo de 0.
	 * @throws TitularInvalidoException
	 *             Cuando el titular es inv&aacute;lido.
	 */
	public void annadirCuentaCliente(int saldo, String dniTitular)
			throws DniInvalidoException, ClienteNoExisteException, NumerosRojosException, TitularInvalidoException {
		if (!clientes.contains(new Persona(dniTitular))) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		Persona cliente = new Persona(dniTitular);
		cuentas.add(new Cuenta(saldo, clientes.get(clientes.indexOf(cliente))));
	}

	/**
	 * Muestra a todos los clientes del banco.
	 * 
	 * @return Cadena con todos los clientes.
	 */
	public String mostrarClientes() {
		Iterator<Persona> it = clientes.iterator();
		String cadena = "";
		int i = 1;
		while (it.hasNext()) {
			cadena += "Cliente número " + i++ + ": " + it.next() + "\n";
		}
		return cadena;
	}

	/**
	 * Muestra todas las cuentas del banco.
	 * 
	 * @return Cadena con todos los clientes.
	 */
	public String mostrarCuentas() {
		Iterator<Cuenta> it = cuentas.iterator();
		String cadena = "";
		int i = 1;
		while (it.hasNext()) {
			cadena += "Cuenta número " + i++ + ": " + it.next() + "\n";
		}
		return cadena;
	}

	/**
	 * Muestra la persona por DNI.
	 * 
	 * @param dni
	 *            dni del titular.
	 * @return Devuelve el cliente buscado por dni.
	 * @throws DniInvalidoException
	 *             Cuando el dni es inv&aacute;lido.
	 * @throws ClienteNoExisteException
	 *             Cuando el cliente no existe en el banco.
	 */
	public Persona mostrarPersonasPorDNI(String dni) throws DniInvalidoException, ClienteNoExisteException {
		if (!clientes.contains(new Persona(dni)))
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		Persona cliente = new Persona(dni);
		return clientes.get(clientes.indexOf(cliente));
	}

	/**
	 * Muestra la cuenta por el c&oacute;digo.
	 * 
	 * @param codigo
	 *            c&oacute;digo de la cuenta.
	 * @return Devuelve la cuenta buscada por codigo.
	 * @throws CuentaNoExisteException
	 *             Cuando la cuenta no existe en el banco.
	 */
	public Cuenta mostrarCuentasporCodigo(int codigo) throws CuentaNoExisteException {
		if (!cuentas.contains(new Cuenta(codigo)))
			throw new CuentaNoExisteException("La cuenta no existe.");
		Cuenta cuenta = new Cuenta(codigo);
		return cuentas.get(cuentas.indexOf(cuenta));
	}

	/**
	 * M&eacute;todo para la transferencia de saldo de una cuenta a otra del
	 * banco.
	 * 
	 * @param codigoCuentaReintegro
	 *            El c&oacute;digo de la cuenta a la que se le realizar&aacute;
	 *            el reintegro.
	 * @param codigoCuentaIngreso
	 *            El c&oacute;digo de la cuenta a la que se le realizar&aacute;
	 *            el ingreso.
	 * @param cantidad
	 *            Cantidad a transferir.
	 * @throws NumerosRojosException
	 *             Cuando una cuenta se quede en numeros rojos.
	 * @throws CantidadNegativaException
	 *             Cuando la cantidad introducida sea negativa.
	 * @throws CuentaNoExisteException
	 *             Cuando la cuenta no existe en el banco.
	 */
	public void transferenciaCuenta(int codigoCuentaReintegro, int codigoCuentaIngreso, int cantidad)
			throws NumerosRojosException, CantidadNegativaException, CuentaNoExisteException {
		if (!cuentas.contains(new Cuenta(codigoCuentaReintegro)) || !cuentas.contains(new Cuenta(codigoCuentaIngreso)))
			throw new CuentaNoExisteException("Esa cuenta no existe.");
		cuentas.get(cuentas.indexOf(new Cuenta(codigoCuentaReintegro))).reintegro(cantidad);
		cuentas.get(cuentas.indexOf(new Cuenta(codigoCuentaIngreso))).ingreso(cantidad);
	}

	/**
	 * Reintegro de una cuenta por su c&oacute;digo.
	 * 
	 * @param codigo
	 *            codigo de la cuenta.
	 * @param cantidad
	 *            Cantidad a retirar.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta sea negativo.
	 * @throws CuentaNoExisteException
	 *             Cuando la cuenta no exista en el banco.
	 * @throws CantidadNegativaException
	 *             Cuando la cantidad introducida sea negativa.
	 */
	public void reintegroCuenta(int codigo, int cantidad)
			throws NumerosRojosException, CuentaNoExisteException, CantidadNegativaException {
		if (!cuentas.contains(new Cuenta(codigo)))
			throw new CuentaNoExisteException("La cuenta no existe.");
		cuentas.get(cuentas.indexOf(new Cuenta(codigo))).reintegro(cantidad);

	}

	/**
	 * Ingreso de una cantidad de una cuenta por su c&oacute;digo.
	 * 
	 * @param codigo
	 *            c&oacute;digo de la cuenta.
	 * @param cantidad
	 *            Cantidad a ingresar.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta sea negativo.
	 * @throws CuentaNoExisteException
	 *             Cuando la cuenta no exista en el banco.
	 * @throws CantidadNegativaException
	 *             Cuando la cantidad introducida sea negativa.
	 */
	public void ingresoCuenta(int codigo, int cantidad)
			throws NumerosRojosException, CuentaNoExisteException, CantidadNegativaException {
		if (!cuentas.contains(new Cuenta(codigo)))
			throw new CuentaNoExisteException("La cuenta no existe.");
		cuentas.get(cuentas.indexOf(new Cuenta(codigo))).ingreso(cantidad);

	}

	/**
	 * Modifica la direcci&oacute;n de la cuenta.
	 * 
	 * @param dni
	 *            DNI del titular.
	 * @param direccion
	 *            Nueva direcci&oacute;n.
	 * @throws DniInvalidoException
	 *             Cuando el dni es inv&aacute;lido.
	 * @throws ClienteNoExisteException
	 *             Cuando el cliente no existe en el banco.
	 */
	public void modificarDireccion(String dni, String direccion) throws DniInvalidoException, ClienteNoExisteException {
		if (!clientes.contains(new Persona(dni))) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		clientes.get(clientes.indexOf(new Persona(dni))).setDireccion(direccion);
	}
}