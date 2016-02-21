package de.rades.comparators.core;

/**
 * Interface f�r Property-Getter ohne Reflection.
 *
 * @param <B>
 *            Type-Parameter, Typ der Bean
 * @param
 * 			<P>
 *            Type-Parameter, Typ der Property
 * @author Heiner K&uuml;cker
 */
public interface Getter<B, P> {
	/**
	 * Zu implementierende Methode zum Abfragen eines Property-Wertes von der
	 * �bergebenen Bean.
	 * 
	 * @param bean
	 *            abzufragende Bean
	 * @return Property-Wert
	 */
	public P get(final B bean);
}
