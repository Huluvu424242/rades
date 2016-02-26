package de.rades.corelibs.domain.comparators;

import java.util.Comparator;

/**
 * Vorgefertigter {@link Comparator} f�r eine bestimmte Property mit Hilfe eines
 * {@link Getter}-Objekts mit Null-Behandlung. Null-Werte gleten immer als
 * kleiner als Nicht-Null-Werte.
 *
 * @param <B>
 *            Type-Parameter, Bean-Typ
 * @author Heiner K&uuml;cker
 */
public final class NullIsLesserPropertyComparator<B> extends AbstractPropertyComparator<B> {
	/**
	 * Konstruktor.
	 *
	 * @param getter
	 *            {@link Getter}-Objekt zum Zugriff auf die Bean-Property, nach
	 *            welcher zu vergleichen ist
	 */
	public NullIsLesserPropertyComparator(final Getter<B, ? extends Comparable<?>> getter) {
		super(getter);
	}

	/**
	 * @see java.util.Comparator#compare
	 */
	@SuppressWarnings({ "unchecked", "cast", "rawtypes" })
	@Override
	public final int compare(final B bean1, final B bean2) {
		// weil hier zweimal das selbe Getter-Objekt verwendet wird, ist die
		// Typ-Kompatibilit�t der beiden Werte abgesichert
		final Comparable o1 = this.get(bean1);

		final Comparable o2 = this.get(bean2);

		if (o1 == null && o2 == null) {
			return 0;
		}

		if (o1 == null) {
			// null gilt als kleiner
			return -1;
		}
		if (o2 == null) {
			return 1;
		}

		return o1.compareTo(o2);
	}

}
