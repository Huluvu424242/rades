package de.rades.comparators.core;

import java.util.Comparator;

/**
 * {@link Comparator}-Implementierung f�r die Kombination mehrerer
 * {@link Comparator}en in einer festgelegten Rangfolge.
 *
 * @param <T>
 *            Type-Parameter, Typ der zu vergleichenden Objekte
 * @author Heiner K&uuml;cker
 */
public final class MultiComparator<T> implements Comparator<T> {
	/**
	 * Array untergeordneter {@link Comparator}en
	 */
	private final Comparator<T>[] comparatorArr;

	/**
	 * Konstruktor.
	 *
	 * @param comparatorArr
	 *            Array untergeordneter {@link Comparator}en
	 */
	@SafeVarargs
	public MultiComparator(final Comparator<T>... comparatorArr) {
		this.comparatorArr = comparatorArr;
	}

	/**
	 * @see java.util.Comparator#compare
	 */
	@Override
	public int compare(final T o1, final T o2) {
		for (final Comparator<T> subComparator : this.comparatorArr) {
			final int subResult = subComparator.compare(o1, o2);

			if (subResult != 0) {
				return subResult;
			}
		}

		return 0;
	}

}
