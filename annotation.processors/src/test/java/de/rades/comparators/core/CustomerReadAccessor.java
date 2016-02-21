package de.rades.comparators.core;

import java.util.Date;

public class CustomerReadAccessor {

	/**
	 * Getter f�r die Property {@link #getPreName()}
	 */
	public static final Getter<Customer, String> PRE_NAME_GETTER = new Getter<Customer, String>() {
		@Override
		public String get(final Customer bean) {
			return bean.getPreName();
		}
	};

	/**
	 * Getter f�r die Property {@link #getSurName()}
	 */
	public static final Getter<Customer, String> SUR_NAME_GETTER = new Getter<Customer, String>() {
		@Override
		public String get(final Customer bean) {
			return bean.getSurName();
		}
	};

	/**
	 * Getter f�r die Property {@link #getBirth()}
	 */
	public static final Getter<Customer, Date> BIRTH_GETTER = new Getter<Customer, Date>() {
		@Override
		public Date get(final Customer bean) {
			return bean.getBirth();
		}
	};

}
