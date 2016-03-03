package exLuqueBravoAntonio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class Persona {
	/**
	 * Nombre de la persona.
	 */
	private String nombre;
	/**
	 * DNI de la persona.
	 */
	private String dni;
	/**
	 * Direcci&oacute;n del hogar de la persona.
	 */
	private String direccion;
	/**
	 * Patr&oacute;n para el DNI.
	 */
	private static final String DNI_PATTERN = "^[0-9]{8}[A-Z&&[^IOU]]$";
	/**
	 * Patr&oacute;n compilado del DNI.
	 */
	private static Pattern patron = Pattern.compile(DNI_PATTERN);
	/**
	 * Cadena donde se almacenan las letras del DNI ordenadas.
	 */
	private static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";

	/**
	 * Costructor de Persona.
	 * 
	 * @param nombre
	 *            Nombre de la persona.
	 * @param dni
	 *            DNI de la persona.
	 * @param direccion
	 *            Direcci&oacute;n del hogar de la persona.
	 */
	Persona(String nombre, String dni, String direccion) throws DniInvalidoException {
		setNombre(nombre);
		setDni(dni);
		setDireccion(direccion);
	}

	/**
	 * Constructor de persona por dni.
	 * 
	 * @param dni
	 *            DNI de la persona.
	 * @throws DniInvalidoException
	 *             Cuando el DNI no es v&aacute;lido.
	 */
	Persona(String dni) throws DniInvalidoException {
		setDni(dni);
	}

	/**
	 * Setter de la direcci&oacute;n.
	 * 
	 * @param direccion
	 *            Direcci&oacute;n de la persona.
	 */
	void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Getter de la Direcci&oacute;n.
	 * 
	 * @return Devuelve el estado del campo direcci&oacute;n.
	 */
	String getDireccion() {
		return direccion;
	}

	/**
	 * Getter del DNI.
	 * 
	 * @return Devuelve el estado del campo DNI.
	 */
	String getDni() {
		return dni;
	}

	/**
	 * Setter del campo DNI.
	 * 
	 * @param dni
	 *            DNI de la persona.
	 */
	private void setDni(String dni) throws DniInvalidoException {
		Matcher matcher = patron.matcher(dni);
		if (!matcher.matches())
			throw new DniInvalidoException("Introduce ocho caracteres y una letra (12345678Z).");
		if (!coincidenLetras(dni))
			throw new DniInvalidoException("Introduce una letra correcta (12345678Z).");
		this.dni = dni;
	}

	/**
	 * M&eacute;todo para comprobar la letra del DNI.
	 * 
	 * @param dni
	 *            dni a comprobar.
	 * @return Devuelve false si no coincide, true de lo contrario.
	 */
	private boolean coincidenLetras(String dni) {
		int numerosDNI = Integer.parseInt(dni.substring(0, 8));
		char letra = dni.charAt(8);
		if (letra != NIF_STRING_ASOCIATION.charAt(numerosDNI % 23)) {
			return false;
		}
		return true;
	}

	/**
	 * Getter del nombre.
	 * 
	 * @return Devuelve el nombre de la persona.
	 */
	String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre.
	 * 
	 * @param nombre
	 *            Nombre de la persona.
	 */
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n\tDNI: " + dni + "\n\tNombre: " + nombre + "\n\tDirección: " + direccion + ".";
	}
}
