package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() { //Test get amount of a money object.
		assertEquals(10000, (int)SEK100.getAmount());
	}

	@Test
	public void testGetCurrency() { //Test get currency of a money object.
		assertEquals(SEK, SEK100.getCurrency());
	}

	@Test
	public void testToString() { //Test to string of a money object.
		assertEquals("10000 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() { //Test conversion to universal value of a money object.
		assertEquals(1500, (int)SEK100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		//Test equality of two money objects in universal value
		assertEquals(true, SEK100.equals(EUR10));
	}

	@Test
	public void testAdd() {
		//Test adding different money objects to eachother
		Money newMoney = SEK100.add(EUR10);
		assertEquals(20000, (int)newMoney.getAmount());
	}

	@Test
	public void testSub() {
		//Test subtracting different money objects from eachother
		Money newMoney = SEK100.sub(EUR10);
		assertEquals(0, (int)newMoney.getAmount());
	}

	@Test
	public void testIsZero() {
		//Test if amount of money in money object is equal to 0;
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
		assertFalse(EUR20.isZero());
	}

	@Test
	public void testNegate() {
		//Test if negate() for money object works
		SEK100.negate();
		assertEquals(-10000, (int)SEK100.getAmount());
	}

	@Test
	public void testCompareTo() {
		//Test if comparison between two different money objects works
		//relative to their universal value
		assertEquals(0, SEK100.compareTo(EUR10));
		assertEquals(-1, SEK100.compareTo(EUR20));
		assertEquals(1, SEK100.compareTo(EUR0));
	}
}
