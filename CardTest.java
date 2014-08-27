import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testBadCard() {
		try {
			Card c = new Card(-1);
			c.getNumber();
			fail("Shouldn't reach here!");
		} catch (IllegalArgumentException e) {
			System.out.println("Successfully caught bad ID: " + -1);
		}
		try {
			Card c = new Card(0);
			c.getNumber();
			fail("Shouldn't reach here!");
		} catch (IllegalArgumentException e) {
			System.out.println("Successfully caught bad ID: " + 0);
		}
		try {
			Card c = new Card(53);
			c.getNumber();
			fail("Shouldn't reach here!");
		} catch (IllegalArgumentException e) {
			System.out.println("Successfully caught bad ID: " + 53);
		}
	}
	
	@Test
	public void testAceOfSpades() {
			Card c = new Card(1);
			assertEquals(1, c.getNumber());
			assertEquals(1, c.getSuit());
			assertEquals(1, c.getCardID());
	}
	
	@Test
	public void testKingOfSpades() {
			Card c = new Card(13);
			assertEquals(13, c.getNumber());
			assertEquals(1, c.getSuit());
			assertEquals(13, c.getCardID());
	}
	
	@Test
	public void testKingOfClubs() {
			Card c = new Card(52);
			assertEquals(13, c.getNumber());
			assertEquals(4, c.getSuit());
			assertEquals(52, c.getCardID());
	}

}
