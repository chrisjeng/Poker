import java.util.ArrayList;

import sun.java2d.loops.DrawGlyphList;

//TODO: implement moods
public class Player {

	private int cents; // in cents
	private String name;
	private Hand myHand;
	private boolean isBigBlind;
	private boolean isSmallBlind;

	public Player(int money, String name, Deck sharedDeck) {
		this.cents = money;
		this.name = name;
		myHand = new Hand(sharedDeck);
	}

	/**
	 * 
	 * @return The balance of the player in cents.
	 */
	public int getCents() {
		return cents;
	}

	/**
	 * 
	 * @return The first card. NOTE: Modification to this card can damage the
	 *         integrity of the game!
	 */
	public Card c1() {
		return myHand.c1;
	}

	/**
	 * 
	 * @return The second card. NOTE: Modification to this card can damage the
	 *         integrity of the game!
	 */
	public Card c2() {
		return myHand.c2;
	}

	public void drawTwoCards() {
		myHand.drawFromDeck();
	}

	/**
	 * 
	 * @param s
	 *            The player's new name.
	 * @return Previous name that was overwritten.
	 */
	public String setName(String s) {
		String ans = this.name;
		this.name = s;
		return ans;
	}

	public String getName() {
		return this.name;
	}
	
	public ArrayList<Card> getTwoCards() {
		return myHand.getHand();
	}

	public boolean isSmallBlind() {
		return isSmallBlind;
	}

	public void setSmallBlind(boolean isSmallBlind) {
		this.isSmallBlind = isSmallBlind;
	}

	public boolean isBigBlind() {
		return isBigBlind;
	}

	public void setBigBlind(boolean isBigBlind) {
		this.isBigBlind = isBigBlind;
	}

	private class Hand {

		// TODO: Link an instance variable to the shared cards.
		private Deck sharedDeck;
		private Card c1;
		private Card c2;
		private ArrayList<Card> myHand;
		private boolean hasDrawn;

		public Hand(Deck d) {
			this.sharedDeck = d;
			this.c1 = null;
			this.c2 = null;
			this.hasDrawn = false;
			myHand = new ArrayList<Card>(7);
		}

		/**
		 * 
		 * @return Whether or not the player already had a hand. If so, the
		 *         player will discard the previous cards and draw new ones.
		 */
		public boolean drawFromDeck() {
			boolean wasHoldingCards = hasDrawn;
			this.c1 = sharedDeck.draw();
			this.c2 = sharedDeck.draw();
			this.hasDrawn = true;
			return wasHoldingCards;
		}

		public ArrayList<Card> getHand() {
			if (!hasDrawn) {
				String msg = "Call to getHand on a hand that hasn't drawn yet!";
				throw new RuntimeException(msg);
			}
			myHand.add(c1);
			myHand.add(c2);
//			this.addSharedCards();
			return myHand;
		}

		public void addSharedCards() {
			// TODO: Implement
			/**
			 * Add the five shared cards to the player's current hand. Don't
			 * clean up too early or else the other players won't get a chance
			 * to use them in the score calculation.
			 */
		}
	}
}
