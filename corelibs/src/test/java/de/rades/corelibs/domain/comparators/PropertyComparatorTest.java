package de.rades.corelibs.domain.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rades.corelibs.domain.Customer;
import de.rades.corelibs.domain.CustomerBuilder;
import de.rades.corelibs.domain.CustomerReadAccessor;
import de.rades.corelibs.domain.comparators.MultiComparator;
import de.rades.corelibs.domain.comparators.NullIsLesserPropertyComparator;
import de.rades.corelibs.domain.comparators.PropertyComparator;

/**
 * JUnit4-Testcase f�r den verbesserten {@link Comparator}
 * {@link PropertyComparator}.
 *
 * @author Heiner K&uuml;cker
 */
public class PropertyComparatorTest {

	private List<Customer> customerList;

	@Before
	public void setUp() {
		customerList = createCustomerList();
	}

	@After
	public void cleanUp() {
		customerList.clear();
	}

	public List<Customer> createCustomerList() {
		final List<Customer> customerList = new ArrayList<>();

		customerList.add(new CustomerBuilder().withPreName("Anton")
				.withSurName("Meyer").withBirth(new Date(0)).build());

		customerList.add(
				new CustomerBuilder().withPreName("Berta").withSurName("Meier")
						.withBirth(new Date(48L * 60L * 60L * 1000L)).build());

		// Liste durchmischen, damit die Unit-Tests nicht zufällig aufgrund der
		// initialen Reihenfolge erfolgreich sind
		Collections.shuffle(customerList);

		return customerList;
	}

	/**
	 * Test method for {@link PropertyComparator#compare}.
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testCompareSimple() {
		final Comparator<Customer> comparator = new PropertyComparator<>(
				CustomerReadAccessor.SUR_NAME_GETTER);

		// final List<Customer> customerList = createCustomerList();

		Collections.sort(customerList, comparator);

		Assert.assertEquals("Meier", customerList.get(0).getSurName());
		Assert.assertEquals("Meyer", customerList.get(1).getSurName());
	}

	/**
	 * Test Sortieren nach mehreren Properties.
	 *
	 * Test method for {@link PropertyComparator#compare}.
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testCompareMulti() {
		final Comparator<Customer> comparator1 = new MultiComparator<>(
				new PropertyComparator<>(CustomerReadAccessor.SUR_NAME_GETTER),
				new PropertyComparator<>(CustomerReadAccessor.PRE_NAME_GETTER));

		// final List<Customer> customerList = createCustomerList();

		Collections.sort(customerList, comparator1);

		Assert.assertEquals("Meier", customerList.get(0).getSurName());
		Assert.assertEquals("Meyer", customerList.get(1).getSurName());

		// Sortieren nach mehreren Propertys
		final Comparator<Customer> comparator2 = new MultiComparator<>(
				new PropertyComparator<>(CustomerReadAccessor.PRE_NAME_GETTER),
				new PropertyComparator<>(CustomerReadAccessor.SUR_NAME_GETTER));

		Collections.sort(customerList, comparator2);

		Assert.assertEquals("Anton", customerList.get(0).getPreName());
		Assert.assertEquals("Berta", customerList.get(1).getPreName());
	}

	/**
	 * Test null-Wert sortieren, null ist kleiner als nicht-null.
	 *
	 * Test method for {@link NullIsLesserPropertyComparator#compare}.
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testCompareNull() {
		final Comparator<Customer> comparator = new NullIsLesserPropertyComparator<>(
				CustomerReadAccessor.SUR_NAME_GETTER);

		// Setzen der Property, nach welcher zu sortieren ist, auf null
		final Customer customer = new CustomerBuilder().withPreName("Berta")
				.withSurName(null).withBirth(new Date(48L * 60L * 60L * 1000L))
				.build();
		customerList.set(1, customer);

		Collections.sort(customerList, comparator);

		Assert.assertEquals(null, customerList.get(0).getSurName());
	}

	/**
	 * Test absteigend sortieren.
	 *
	 * Test method for {@link PropertyComparator#compare}.
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testCompareInverted() {
		final Comparator<Customer> comparator = Collections.reverseOrder(
				new PropertyComparator<>(CustomerReadAccessor.SUR_NAME_GETTER));

		// final List<Customer> customerList = createCustomerList();

		Collections.sort(customerList, comparator);

		Assert.assertEquals("Meyer", customerList.get(0).getSurName());
		Assert.assertEquals("Meier", customerList.get(1).getSurName());
	}

	/**
	 * Test alles zusammen.
	 *
	 * Test method for {@link PropertyComparator#compare} and
	 * {@link NullIsLesserPropertyComparator#compare}.
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testCompareMultiInvertedNull() {
		// final List<Customer> customerList = createCustomerList();

		{
			final Comparator<Customer> comparator = new MultiComparator<>(
					Collections.reverseOrder(new PropertyComparator<>(
							CustomerReadAccessor.SUR_NAME_GETTER)),
					new PropertyComparator<>(
							CustomerReadAccessor.PRE_NAME_GETTER),
					new PropertyComparator<>(
							CustomerReadAccessor.BIRTH_GETTER));

			Collections.sort(customerList, comparator);

			Assert.assertEquals("Meyer", customerList.get(0).getSurName());
			Assert.assertEquals("Meier", customerList.get(1).getSurName());
		}

		{
			final Comparator<Customer> comparator = new MultiComparator<>(
					new PropertyComparator<>(
							CustomerReadAccessor.PRE_NAME_GETTER),
					Collections.reverseOrder(new PropertyComparator<>(
							CustomerReadAccessor.SUR_NAME_GETTER)),
					new PropertyComparator<>(
							CustomerReadAccessor.BIRTH_GETTER));

			Collections.sort(customerList, comparator);

			Assert.assertEquals("Anton", customerList.get(0).getPreName());
			Assert.assertEquals("Berta", customerList.get(1).getPreName());
		}

		{
			final Comparator<Customer> comparator = new MultiComparator<>(
					new NullIsLesserPropertyComparator<>(
							CustomerReadAccessor.BIRTH_GETTER),
					Collections.reverseOrder(new PropertyComparator<>(
							CustomerReadAccessor.SUR_NAME_GETTER)),
					new PropertyComparator<>(
							CustomerReadAccessor.PRE_NAME_GETTER));

			// Setzen der Property, nach welcher zu sortieren ist, auf null
			final Customer customer = new CustomerBuilder().withPreName("Berta")
					.withSurName("Meier").withBirth(null).build();
			customerList.set(1, customer);

			Collections.sort(customerList, comparator);

			Assert.assertEquals(null, customerList.get(0).getBirth());
		}
	}

}
