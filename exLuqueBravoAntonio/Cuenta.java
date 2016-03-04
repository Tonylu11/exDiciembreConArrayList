package exLuqueBravoAntonio;

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
public class Cuenta {
	/**
	 * C&oacute;digo est&aacute;tico del c&oacute;digo de la cuenta.
	 */
	private static int codigoCuenta = 1;
	/**
	 * id que se incrementar&aacute; con el n&uacute;mero de cuenta.
	 */
	private int id;
	/**
	 * Saldo inicial de la cuenta.
	 */
	private double saldo;
	/**
	 * Titular de la cuenta.
	 */
	private Persona titular;

	/**
	 * Constructor Cuenta.
	 * 
	 * @param saldoInicial
	 *            Saldo inicial de la cuenta.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 * @throws TitularInvalidoException
	 *             Cuando el titular es inv&aacute;lido.
	 */
	Cuenta(int saldoInicial, Persona titular) throws NumerosRojosException, TitularInvalidoException {
		setId(codigoCuenta++);
		setSaldo(saldoInicial);
		setTitular(titular);
	}

	/**
	 * Constructor por c&oacute;digo.
	 * 
	 * @param codigo
	 *            C&oacute;digo de la cuenta.
	 */
	Cuenta(int codigo) {
		setId(codigo);
	}

	/**
	 * Modifica el estado del campo titular.
	 * 
	 * @param titular
	 *            titular de la cuenta.
	 * @throws TitularInvalidoException
	 *             Si el titular es inv&aacute;lido.
	 */
	private void setTitular(Persona titular) throws TitularInvalidoException {
		if (titular == null)
			throw new TitularInvalidoException("El titular no puede ser inválido.");
		this.titular = titular;

	}

	/**
	 * Obtiene el titular de la cuenta.
	 * 
	 * @return Titular de la cuenta.
	 */
	public Persona getTitular() {
		return titular;
	}

	/**
	 * Setter de la id de la cuenta.
	 * 
	 * @param id
	 *            id de la cuenta.
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter del saldo inicial.
	 * 
	 * @return Devuelve el saldo inicial de la cuenta.
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Setter del Saldo Inicial.
	 * 
	 * @param saldo
	 *            Saldo inicial de la cuenta.
	 * @throws NumerosRojosException
	 *             Cuando el saldo es negativo.
	 */
	private void setSaldo(double saldo) throws NumerosRojosException {
		if (saldo < 0)
			throw new NumerosRojosException("El saldo de la cuenta no puede estar en números rojos.");
		this.saldo = saldo;
	}

	/**
	 * Ingresar una cantidad a la cuenta.
	 * 
	 * @param cantidad
	 *            Cantidad ingresar.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 * @throws CantidadNegativaException
	 *             Cuando la cantidad es negativa.
	 */
	void ingreso(int cantidad) throws NumerosRojosException, CantidadNegativaException {
		if (cantidad < 0)
			throw new CantidadNegativaException("La cantidad no puede ser negativa.");

		setSaldo(getSaldo() + cantidad);
	}

	/**
	 * Retira una cantidad de la cuenta.
	 * 
	 * @param cantidad
	 *            Cantidad a retirar de la cuenta.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 * @throws CantidadNegativaException
	 *             Cuando la cantidad sea negativa.
	 */
	void reintegro(int cantidad) throws NumerosRojosException, CantidadNegativaException {
		if (cantidad < 0)
			throw new CantidadNegativaException("La cantidad no puede ser negativa.");
		setSaldo(getSaldo() - cantidad);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cuenta other = (Cuenta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Saldo: " + saldo + "€ \n\tTitular: " + titular;
	}

}
