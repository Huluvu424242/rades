package de.rades.corelibs.domain.comparators;

/**
 * Spezialisiertes Interface {@link Getter} mit Festlegung des Property-Typs auf
 * {@link Comparable}.
 *
 * @param <B>
 *            Type-Parameter, Bean-Typ
 * @author Heiner K&uuml;cker
 */
interface ComparableGetter<B> extends Getter<B, Comparable<?>> {
	// no implementation
}
