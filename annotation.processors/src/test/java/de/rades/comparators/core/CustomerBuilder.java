package de.rades.comparators.core;

import java.util.Date;

public final class CustomerBuilder {

	protected Customer customer;

	public CustomerBuilder() {
		customer = new Customer();
	}

	public CustomerBuilder(final Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param preName
	 *            the preName to set
	 */
	public CustomerBuilder withPreName(final String preName) {
		customer.preName = preName;
		return this;
	}

	/**
	 * @param surName
	 *            the surName to set
	 */
	public CustomerBuilder withSurName(final String surName) {
		customer.surName = surName;
		return this;
	}

	/**
	 * @param birth
	 *            the birth to set
	 */
	public CustomerBuilder withBirth(final Date birth) {
		customer.birth = birth;
		return this;
	}

	public Customer build() {
		final Customer customer = this.customer;
		this.customer = new Customer();
		return customer;

	}

}
