import java.util.ArrayList;

//TODO: implement moods
public class Player {

	private int cents; // in cents
	private String name;
	private Hand myHand;
	
	public Player(int money, String name, Deck sharedDeck) {
		this.cents = money;
		this.name = name;
		myHand = new Hand(sharedDeck);
	}
	
	public int getCents() {
		return cents;
	}
	
	public Card c1() {
		return myHand.c1;
	}
	
	public Card c2() {
		return myHand.c2;
	}
	
	private class Hand {
		
		private Deck sharedDeck;
		private Card c1;
		private Card c2;
		
		public Hand(Deck d) {
			this.sharedDeck = d;
		}
		
		public void drawFromDeck() {
			this.c1 = sharedDeck.draw();
			this.c2 = sharedDeck.draw();
		}
	}
}
