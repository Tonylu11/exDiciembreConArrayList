package exLuqueBravoAntonio;

/**
 * Previamente a las instrucciones, recuerda:
 * 
 * El c�digo no s�lo ha de compilar y funcionar seg�n estas disposiciones, sino
 * que ha de ser entendible y eficiente, sin redundancia y con una finalidad
 * clara. Empaqueta todos los ficheros .java en el paquete
 * exNombreApellido1Apellido2 Recuerda mantener la visibilidadm�s adecuada Dale
 * al c�digo un formato legible. Entrega la carpeta completa del c�digo fuente
 * comprimida en un fichero exNombreApellido1Apellido2 Genera la documentaci�n
 * javadoc y entr�gala junto con el resto de los documentos. Para ello: Cada
 * clase ha de tener su comentario junto con el nombre del autor correctamente
 * identificado seg�n javadoc Documenta en cada clase al menos un campo un
 * m�todo, con sus argumentos
 * 
 * Implementa al menos tres clases: TestCuentas, Persona y Cuenta y a��dele los
 * campos y m�todos que estimes oportunos seg�n estas instrucciones. Se nos pide
 * implementar el comportamiento de una cuenta corriente. Queremos hacer
 * hincapi� en el n�mero de cuenta, que ha de ser �nico. En este caso el n�mero
 * de cuenta se generar� mediante un contador com�n a todas las cuentas. La
 * primera cuenta deber� tener el c�digo de cuenta con valor 1. La cuenta
 * permitir� al menos las siguientes operaciones:
 * 
 * Ingreso a la cuenta de una cantidad de dinero. Reintegro de la cuenta de una
 * cantidad de dinero. La cuenta no puede llegar a n�meros rojos. En tal caso,
 * el reintegro no puede llevarse a cabo. Transferencia de una cantidad de
 * dinero desde una cuenta a otro. La cuenta origen de la transferencia no puede
 * llegar a n�meros rojos. En tal caso, la transferencia no puede llevarse a
 * cabo. Mostrar el estado de la cuenta, donde aparezcan todos los
 * atributos(toString())
 * 
 * Crea una clase TestCuentas donde estar� el m�todo main que lleve a cabo las
 * siguientes operaciones:
 * 
 * Ha de crear dos cuentas con las siguientes caracter�sticas: Primera cuenta:
 * de "Mortadelo", con dni 11223344 y direcci�n Mikasa. Saldo inicial de la
 * cuenta: 1000 euros Segunda cuenta: de Filemon, con dni 55667788 y direcci�n
 * Calle del Medio. Saldo inicial: 2000 euros. Una vez creadas las dos cuentas,
 * sobre la cuenta de Mortadelo han de realizarse las siguientes operaciones: Un
 * reintegro de 500 euros Un cambio de domicilio a la nueva direcci�n 13, Rue
 * del Percebe Una transferencia de la cuenta de Mortadelo a la de Filem�n, de
 * una cantidad de 500 euros. Una segunda transferencia de la cuenta de
 * Mortadelo a la de Filem�n, de una cantidad de 500 euros Un reintegro de la
 * cuenta de Mortadelo de 7 euros.
 * 
 * Como resultado, el programa mostrar� los siguientes mensajes: Cuenta: 1,
 * 1000.0, 11223344, Mortadelo, Mikasa. Cuenta: 2, 2000.0, 55667788, Filemon,
 * Calle del Medio.
 * 
 * --Despu�s de reintegro(500) Cuenta: 1, 500.0, 11223344, Mortadelo, Mikasa.
 * 
 * --Despu�s de setDireccion("13, Rue del Percebe") 11223344, Mortadelo, 13, Rue
 * del Percebe.
 * 
 * --Despu�s de transferencia(500) Cuenta: 1, 0.0, 11223344, Mortadelo, 13, Rue
 * del Percebe. Cuenta: 2, 2500.0, 55667788, Filemon, Calle del Medio. ERROR. No
 * se ha podido realizar la transferencia.
 * 
 * --Despu�s de transferencia(500) Cuenta: 1, 0.0, 11223344, Mortadelo, 13, Rue
 * del Percebe. Cuenta: 2, 2500.0, 55667788, Filemon, Calle del Medio. ERROR. No
 * se ha podido realizar el reintegro
 * 
 * --Despu�s de reintegro(7) Cuenta: 1, 0.0, 11223344, Mortadelo, 13, Rue del
 * Percebe.
 * 
 * Aseg�rate de que:
 * 
 * El DNI del titular es v�lido (expresi�n regular y algoritmo) En caso de
 * introducir un DNI err�neo o sin letra, se le indicar� al usario previamente a
 * la creaci�n del titular. Siempre se le exigir� la letra correcta. Lanza una
 * excepci�n en el caso en que el dni no sea correcto o el resto de los datos no
 * cumplan un m�nimo de formato. (regex)
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
	private Persona titular;

	/**
	 * Constructor Cuenta.
	 * 
	 * @param saldoInicial
	 *            Saldo inicial de la cuenta.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 * @throws TitularInvalidoException
	 */
	Cuenta(int saldoInicial, Persona titular) throws NumerosRojosException, TitularInvalidoException {
		setId(codigoCuenta++);
		setSaldo(saldoInicial);
		setTitular(titular);
	}

	Cuenta(Persona cliente) throws TitularInvalidoException {
		setTitular(cliente);
	}

	Cuenta(int codigo) {
		setId(codigo);
	}

	private void setTitular(Persona titular) throws TitularInvalidoException {
		if (titular == null)
			throw new TitularInvalidoException("El titular no puede ser inv�lido.");
		this.titular = titular;

	}

	/**
	 * Getter de la id de la cuenta.
	 * 
	 * @return Devuelve el estado del campo id.
	 */
	public int getId() {
		return id;
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
			throw new NumerosRojosException("El saldo de la cuenta no puede estar en n�meros rojos.");
		this.saldo = saldo;
	}

	/**
	 * Ingresar una cantidad a la cuenta.
	 * 
	 * @param cantidad
	 *            Cantidad ingresar.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 */
	void ingreso(int cantidad) throws NumerosRojosException {
		setSaldo(getSaldo() + cantidad);
	}

	/**
	 * Retira una cantidad de la cuenta.
	 * 
	 * @param cantidad
	 *            Cantidad a retirar de la cuenta.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 */
	void reintegro(int cantidad) throws NumerosRojosException {
		setSaldo(getSaldo() - cantidad);
	}

	/**
	 * Transfiere una cantidad de una cuenta a otra.
	 * 
	 * @param cuenta
	 *            Cuenta a donde se ingresar&aacute; el dinero.
	 * @param cantidad
	 *            Cantidad a extraer de la cuenta.
	 * @throws NumerosRojosException
	 *             Cuando el saldo de la cuenta es negativo.
	 * @throws TrasferenciaFallidaException
	 *             Cuando el saldo de la cuenta a la que se le extrae el dinero
	 *             es 0
	 */
	void transferencia(Cuenta cuenta, int cantidad) throws NumerosRojosException, TrasferenciaFallidaException {
		if (this.getSaldo() == 0) {
			throw new TrasferenciaFallidaException("La transferencia no se ha podido realizar.");
		}
		// this.setSaldo(this.getSaldo() - cantidad);
		// cuenta.setSaldo(cuenta.getSaldo() + cantidad);
		this.reintegro(cantidad);
		cuenta.ingreso(cantidad);
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
		return "Saldo: " + saldo + "� \n\tTitular: " + titular;
	}

}
