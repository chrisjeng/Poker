import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class PokerTableTest {

	@Test
	public void testCalculator() {
		PokerTable t = new PokerTable();
		ArrayList<Card> hand = this.makeFlush();
		PokerTable.Calculator c = t.new Calculator(hand);
		System.out.println("Hand Worth: " + c.handWorth());
		assertEquals(6, c.findFlush());
		assertEquals("Flush", c.crappyDescription);
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
	}
	
	private ArrayList<Card> makeFlush() {
		ArrayList<Card> ans = new ArrayList<Card>(7);
		Card shitCard1 = new Card(52);
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
