package exLuqueBravoAntonio;

import java.util.ArrayList;
import java.util.Iterator;

public class Banco {
	private ArrayList<Persona> clientes = new ArrayList<Persona>();
	private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

	public void annadirCliente(String nombre, String dni, String direccion)
			throws DniInvalidoException, ClienteYaExisteException {
		Persona cliente = new Persona(nombre, dni, direccion);
		if (clientes.contains(cliente)) {
			throw new ClienteYaExisteException("El cliente ya ha sido dado de alta.");
		}
		clientes.add(new Persona(nombre, dni, direccion));
	}

	public void eliminarCliente(String dni)
			throws DniInvalidoException, ClienteNoExisteException, TitularInvalidoException {
		Persona cliente = new Persona(dni);
		Cuenta cuenta = cuentas.get(cuentas.indexOf(new Cuenta(cliente)));
		if (!clientes.contains(new Persona(dni))) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		if (cuentas.contains(cuenta)) {

			cuentas.remove(cuenta);
		}
		clientes.remove(cliente);
	}

	public void annadirCuentaCliente(int saldo, String dniTitular)
			throws DniInvalidoException, ClienteNoExisteException, NumerosRojosException, TitularInvalidoException {
		Persona cliente = new Persona(dniTitular);
		if (!clientes.contains(cliente)) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		cuentas.add(new Cuenta(saldo, clientes.get(clientes.indexOf(cliente))));
	}

	public void eliminarCuentaCliente(String dniTitular)
			throws DniInvalidoException, ClienteNoExisteException, NumerosRojosException, TitularInvalidoException {
		Persona cliente = new Persona(dniTitular);
		if (!clientes.contains(cliente)) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		cuentas.remove(new Cuenta(cliente));
	}

	public String mostrarClientes() {
		Iterator<Persona> it = clientes.iterator();
		String cadena = "";
		int i = 1;
		while (it.hasNext()) {
			cadena += "Cliente número " + i++ + ": " + it.next() + "\n";
		}
		return cadena;
	}

	public String mostrarCuentasYClientes() {
		Iterator<Cuenta> it = cuentas.iterator();
		String cadena = "";
		int i = 1;
		while (it.hasNext()) {
			cadena += "Cuenta número " + i++ + ": " + it.next() + "\n";
		}
		return cadena;
	}

	public Persona mostrarPersonasPorDNI(String dni) throws DniInvalidoException, ClienteNoExisteException {
		Persona cliente = new Persona(dni);
		if (!clientes.contains(cliente))
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		return clientes.get(clientes.indexOf(cliente));
	}

	public Cuenta mostrarCuentasporCodigo(int codigo) throws CuentaNoExisteException {
		Cuenta cuenta = new Cuenta(codigo);
		if (!cuentas.contains(cuenta))
			throw new CuentaNoExisteException("La cuenta no existe.");
		return cuentas.get(cuentas.indexOf(new Cuenta(codigo)));
	}

	public void transferenciaCuenta(int codigoCuentaReintegro, int codigoCuentaIngreso, int cantidad)
			throws NumerosRojosException, CantidadNegativaException, CuentaNoExisteException {
		if (cantidad < 0) {
			throw new CantidadNegativaException("La cantidad no puede ser negativa.");
		}
		if (!cuentas.contains(new Cuenta(codigoCuentaReintegro)) || !cuentas.contains(new Cuenta(codigoCuentaIngreso)))
			throw new CuentaNoExisteException("Esa cuenta no existe.");
		cuentas.get(cuentas.indexOf(new Cuenta(codigoCuentaReintegro))).reintegro(cantidad);
		cuentas.get(cuentas.indexOf(new Cuenta(codigoCuentaIngreso))).ingreso(cantidad);
	}

	public void reintegroCuenta(int codigo, int cantidad)
			throws NumerosRojosException, CuentaNoExisteException, CantidadNegativaException {
		if (cantidad < 0)
			throw new CantidadNegativaException("La cantidad no puede ser negativa.");
		if (!cuentas.contains(new Cuenta(codigo)))
			throw new CuentaNoExisteException("La cuenta no existe.");
		cuentas.get(cuentas.indexOf(new Cuenta(codigo))).reintegro(cantidad);

	}

	public void ingresoCuenta(int codigo, int cantidad)
			throws NumerosRojosException, CuentaNoExisteException, CantidadNegativaException {
		if (cantidad < 0)
			throw new CantidadNegativaException("La cantidad no puede ser negativa.");
		if (!cuentas.contains(new Cuenta(codigo)))
			throw new CuentaNoExisteException("La cuenta no existe.");
		cuentas.get(cuentas.indexOf(new Cuenta(codigo))).ingreso(cantidad);

	}

	public void modificarDireccion(String dni, String direccion) throws DniInvalidoException, ClienteNoExisteException {
		if (!clientes.contains(new Persona(dni))) {
			throw new ClienteNoExisteException("El cliente no está dado de alta.");
		}
		clientes.get(clientes.indexOf(new Persona(dni))).setDireccion(direccion);
	}
}