package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		//Originally "opens" account when should throw exception
		assertThrows(AccountExistsException.class, ()-> SweBank.openAccount("Ulrika"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Money newMoney = new Money(10000, SEK);
		//Originally throws AccountDoesNotExistException when account indeed exists
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.deposit("s", newMoney));

	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Money newMoney = new Money(10000, SEK);
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.withdraw("s", newMoney));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Money newMoney = new Money(10000, SEK);

		assertEquals(0, (int)SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika", newMoney);
		assertEquals(10000, (int)SweBank.getBalance("Ulrika"));

	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		Money newMoney = new Money(10000, SEK);

		assertEquals(0, (int)Nordea.getBalance("Bob"));
		assertThrows(AccountDoesNotExistException.class, ()-> SweBank.transfer("s", Nordea, "Bob", newMoney));
		assertThrows(AccountDoesNotExistException.class, ()-> SweBank.transfer("Ulrika", Nordea, "s", newMoney));

		SweBank.transfer("Ulrika", Nordea, "Bob", newMoney);

		assertEquals(10000, (int)Nordea.getBalance("Bob"));

		//Originally Bank.transfer() between banks does not throw AccountDoesNotExistException
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.transfer("s", "Ulrika", newMoney));
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.transfer("Ulrika", "s", newMoney));

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		Money newMoney = new Money(10000, SEK);
		//Originally addTimedPayment and removeTimedPayment does not throw AccountDoesNotExistException
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.addTimedPayment("s", "1", 1, 1, newMoney, Nordea, "Bob"));
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.removeTimedPayment("s", "1"));
	}
}
