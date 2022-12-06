package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 1, 1, new Money(10000000, SEK), SweBank, "Hans");
		testAccount.removeTimedPayment("1");
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 1, 1, new Money(10000000, SEK), SweBank, "Hans");
		assertTrue(testAccount.timedPaymentExists("1"));
		assertFalse(testAccount.timedPaymentExists("2"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.deposit(new Money(10000000, SEK));
		assertTrue(new Money(20000000, SEK).equals(testAccount.getBalance()));
		testAccount.withdraw(new Money(10000000, SEK));
		assertTrue(new Money(10000000, SEK).equals(testAccount.getBalance()));
	}
	
	@Test
	public void testGetBalance() {
		assertTrue(new Money(10000000, SEK).equals(testAccount.getBalance()));
	}
}
