package exLuqueBravoAntonio;

/**
 * Modifica el ejercicio anterior para manejar cuentas con titulares,
 * reintegros, transferencias...
 * 
 * Un interfaz podr�a ser este (no implementes la eliminaci�n en ning�n
 * ArrayList)
 *
 * Men� principal
 * 
 * (1) Men� cuentas
 * 
 * (2) Men� Personas
 * 
 * (3) Salir
 * 
 * 2
 *
 * Personas
 * 
 * (1) Crear persona nueva
 * 
 * (2) Modificar direcci�n
 * 
 * (3) Mostrar persona
 * 
 * (4) Mostrar personas
 * 
 * (5) Salir
 * 
 * 5
 *
 * Men� principal
 * 
 * (1) Men� cuentas
 * 
 * (2) Men� Personas
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
public class CantidadNegativaException extends Exception {
	public CantidadNegativaException(String message) {
		super(message);
	}
}
