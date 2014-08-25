import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PokerTableTest {

	/**
	 * NOTE: testCalculator() requires access to many inner variables. To
	 * actually run the test, the variables will need to temporarily be set to
	 * public.
	 */
	@Test
	public void testCalculator() {
		PokerTable t = new PokerTable();
		ArrayList<Card> hand = this.makeStraightFlush();
		PokerTable.Calculator c = t.new Calculator(hand);
		System.out.println("Hand Worth: " + c.handWorth());
		assertEquals(6, c.findFlush());
		assertEquals("Flush", c.crappyDescription);
		assertEquals(2, c.findStraight());
		assertEquals("Straight", c.crappyDescription);
		c.calcHigh();
	}

	@Test
	public void testCard() {

		// Test ace of spades
		Card beginCard = new Card(1, 1);
		assertEquals(1, beginCard.getNumber());
		assertEquals(1, beginCard.getSuit());

		// Test king of clubs
		Card endCard = new Card(13, 4);
		assertEquals(13, endCard.getNumber());
		assertEquals(4, endCard.getSuit());
		
		int tryNumber;
		
		// Try zero
		tryNumber = 0;
		try {
			Card badCard = new Card(tryNumber);
			assertNotNull(badCard);
			fail("badCard shouldn't have initialized");
		} catch (IllegalArgumentException e) {
			System.out.println("Successfully caught bad card " + tryNumber);
		}
		
		tryNumber = 53;
		try {
			Card badCard = new Card(tryNumber);
			assertNotNull(badCard);
			fail("badCard shouldn't have initialized");
		} catch (IllegalArgumentException e) {
			System.out.println("Successfully caught bad card " + tryNumber);
		}
		
		tryNumber = -1;
		try {
			Card badCard = new Card(tryNumber);
			assertNotNull(badCard);
			
			fail("badCard shouldn't have initialized");
		} catch (IllegalArgumentException e) {
			System.out.println("Successfully caught bad card " + tryNumber);
		}
		
		
	}

	private ArrayList<Card> makeStraightFlush() {
		ArrayList<Card> ans = new ArrayList<Card>(7);
		Card shitCard1 = new Card(40);
		Card shitCard2 = new Card(51);
		ans.add(shitCard1);
		ans.add(shitCard2);
		for (int i = 2; i <= 6; i++) {
			Card curr = new Card(i, 2);
			ans.add(curr);
		}
		return ans;
	}
}
