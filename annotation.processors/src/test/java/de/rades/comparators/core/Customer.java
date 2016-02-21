package de.rades.comparators.core;

import java.util.Date;

/**
 * Beispiel Bean Kunde.
 *
 * @author Heiner K&uuml;cker
 */
public final class Customer {

	/**
	 * Vorname.
	 */
	protected String preName;

	/**
	 * Nachname.
	 */
	protected String surName;

	/**
	 * Geburtsdatum,
	 */
	protected Date birth;

	protected Customer() {

	}

	/**
	 * @return the preName
	 */
	public String getPreName() {
		return this.preName;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return this.surName;
	}

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return this.birth;
	}

}
