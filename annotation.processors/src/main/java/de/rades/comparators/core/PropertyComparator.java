package de.rades.comparators.core;

import java.util.Comparator;

/**
 * Vorgefertigter {@link Comparator} f�r eine bestimmte Property mit Hilfe eines
 * {@link Getter}-Objekts ohne Null-Behandlung.
 *
 * @param <B>
 *            Type-Parameter, Bean-Typ
 * @author Heiner K&uuml;cker
 */
public final class PropertyComparator<B> extends AbstractPropertyComparator<B> {
	/**
	 * Konstruktor.
	 *
	 * @param getter
	 *            {@link Getter}-Objekt zum Zugriff auf die Bean-Property, nach
	 *            welcher zu vergleichen ist
	 */
	public PropertyComparator(final Getter<B, ? extends Comparable<?>> getter) {
		super(getter);
	}

	/**
	 * @see java.util.Comparator#compare
	 */
	@SuppressWarnings({ "unchecked", "cast", "rawtypes" })
	@Override
	public final int compare(final B bean1, final B bean2) {
		return
		// weil hier zweimal das selbe Getter-Objekt verwendet wird, ist die
		// Typ-Kompatibilit�t der beiden Werte abgesichert
		((Comparable) this.get(bean1)).compareTo((this.get(bean2)));
	}

}
