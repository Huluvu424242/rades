package de.rades.corelibs.domain.comparators;

import java.util.Comparator;

/**
 * Vorgefertigter {@link Comparator} f�r eine bestimmte Property mit Hilfe eines
 * {@link Getter}-Objekts.
 *
 * @param <B>
 *            Type-Parameter, Bean-Typ
 * @author Heiner K&uuml;cker
 */
abstract class AbstractPropertyComparator<B> implements Comparator<B>, ComparableGetter<B> {
	/**
	 * {@link Getter}-Objekt zum Zugriff auf die Bean-Property, nach welcher zu
	 * vergleichen ist
	 */
	private final Getter<B, ? extends Comparable<?>> getter;

	/**
	 * Konstruktor.
	 *
	 * @param getter
	 *            {@link Getter}-Objekt zum Zugriff auf die Bean-Property, nach
	 *            welcher zu vergleichen ist
	 */
	protected AbstractPropertyComparator(final Getter<B, ? extends Comparable<?>> getter) {
		this.getter = getter;
	}

	/**
	 * @see Getter#get
	 */
	@Override
	public final Comparable<?> get(final B bean) {
		return this.getter.get(bean);
	}

}
