package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		NOK = new Currency("NOK",2.);
	}

	@Test
	public void testGetName() { //Check names of currencies
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
		assertEquals("NOK", NOK.getName());
	}
	
	@Test
	public void testGetRate() { //Check rates of currencies
		assertEquals(0.15, SEK.getRate(), 2);
		assertEquals(0.20, DKK.getRate(), 2);
		assertEquals(1.5, EUR.getRate(), 2);
		assertEquals(2., NOK.getRate(), 2);
	}
	
	@Test
	public void testSetRate() { //Check if setRate for a currency works
		SEK.setRate(0.3);
		assertEquals(0.3, (double)SEK.getRate(), 2);
	}
	
	@Test
	public void testGlobalValue() { //Check if universalValue() works for currencies
		assertEquals(15, (int)SEK.universalValue(100));
		assertEquals(20, (int)DKK.universalValue(100));
		assertEquals(150, (int)EUR.universalValue(100));
		assertEquals(200, (int)NOK.universalValue(100));
	}
	
	@Test
	public void testValueInThisCurrency() { //Check conversion of currencies into a different currency.
		assertEquals(1000, (int)NOK.valueInThisCurrency(100, DKK));
		assertEquals(750, (int)EUR.valueInThisCurrency(100, DKK));
		assertEquals(100, (int)DKK.valueInThisCurrency(100, DKK));
	}

}
