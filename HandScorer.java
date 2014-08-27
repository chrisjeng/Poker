import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that handles the determination of a hand strength. Returns an int
 * between 1 mil and 10 mil, the higher the number, the better the hand.
 * 
 * @author Chris
 *
 */
public class HandScorer {

	// These are rank assignments for each of the hand types.
	public static final int HIGH_CARD = 1;
	public static final int PAIR = 2;
	public static final int TWO_PAIR = 3;
	public static final int THREE_OF_A_KIND = 4;
	public static final int STRAIGHT = 5;
	public static final int FLUSH = 6;
	public static final int FULL_HOUSE = 7;
	public static final int FOUR_OF_A_KIND = 8;
	public static final int STRAIGHT_FLUSH = 9;

	/**
	 * Calculates how strong a hand is. The higher, the better.
	 * 
	 * @param fullHand
	 *            An ArrayList of all the available hands to choose from in no
	 *            particular order. It is expected that two of these are the
	 *            player's hands, and five of them are the table's shared cards,
	 *            for a total size of seven cards.
	 * @return A relative score for the current hand. A higher score means it's
	 *         better.
	 */
	public static int handScore(ArrayList<Card> fullHand) {
		HandScorer myCalc = new HandScorer(fullHand);
		return myCalc.getScore();
	}

	public int getScore() {
		if (score == -1) {
			calculateScore();
		}
		return score;
	}

	public String getDescription() {
		if (!myScore.isDone()) {
			throw new RuntimeException("Score not calculated correctly.");
		}
		return myScore.getDescription();
	}

	private Card[] hand;
	private int score = -1;
	private Score myScore;

	// Constructor
	public HandScorer(ArrayList<Card> fullHand) {
		checkInput(fullHand);
		hand = new Card[7];
		for (int i = 0; i < fullHand.size(); i++) {
			hand[i] = fullHand.get(i);
		}
		Arrays.sort(hand);
		myScore = new Score();
	}

	private void checkInput(ArrayList<Card> fullHand) {
		// Check if input is valid
		if (fullHand == null || fullHand.size() != 7) {
			// Only works for seven cards. Throws an exception otherwise
			// Make the custom message
			String msg = "";
			if (fullHand == null) {
				msg = "handWorth called with null parameter!";
			} else if (fullHand.isEmpty()) {
				msg = "handWorth called on an empty array.";
			} else {
				msg = "handWorth method only works for seven cards, ";
				msg += "input size: " + fullHand.size();
			}

			throw new IllegalArgumentException(msg);
		}
	}

	private void calculateScore() {
		if (myScore.isDone()) {
			return;
		}

		// First handle repeated cards
		findRepeatedCards();

		// Next handle straights and flushes
		int straight = findStraight();
		int flush = findFlush();
		if (straight > 0 && flush > 0) {
			// Straight flush
			myScore.setType(STRAIGHT_FLUSH);
			myScore.setTypeLevel(straight);
			myScore.setTieBreaker(0);
		} else {
			if (straight > 0) {
				// Straight
				if (myScore.getType() < STRAIGHT) {
					myScore.setType(STRAIGHT);
				}
				myScore.setTypeLevel(straight);
				myScore.setTieBreaker(0);
			}
			if (flush > 0) {
				// Flush
				if (myScore.getType() < FLUSH) {
					myScore.setType(FLUSH);
				}
				myScore.setTypeLevel(flush);
				myScore.setTieBreaker(0);
			}
		}

		// Return the answer
		this.score = myScore.getScore();
	}

	/**
	 * Finds and attempts to upgrade a hand's current quality label for all
	 * hands that are pairs or triples or high cards.
	 */
	private void findRepeatedCards() {
		int[] repeats = new int[13];
		for (Card c : hand) {
			++repeats[c.getNumber() - 1];
		}
		int rep1 = -1;
		int rep2 = -1;
		int card1 = -1;
		int card2 = -1;
		for (int i = 12; i >= 0; i--) {
			int currCount = repeats[i];
			if (currCount > rep1) {
				rep1 = currCount;
				card1 = currCount + 1;
			} else if (currCount > rep2) {
				rep2 = currCount;
				card2 = currCount + 1;
			}
		}

		// Now we have the two most repeated cards. Let's set the type (pair)
		// and the typeLevel (of 8's).
		boolean secondCardImportant = false;
		if (myScore.getType() < FOUR_OF_A_KIND && rep1 == 4) {
			// Four of a kind
			myScore.setType(FOUR_OF_A_KIND);
			myScore.setTypeLevel(card1);
		} else if (myScore.getType() < FULL_HOUSE && rep1 == 3 && rep2 == 2) {
			// Full house
			myScore.setType(FULL_HOUSE);
			myScore.setTypeLevel(card1 * 14 + card2);
			secondCardImportant = true;
		} else if (myScore.getType() < THREE_OF_A_KIND && rep1 == 3) {
			// Three of a kind
			myScore.setType(THREE_OF_A_KIND);
			myScore.setTypeLevel(card1);
		} else if (myScore.getType() < TWO_PAIR && rep1 == 2 && rep2 == 2) {
			// Two pair
			myScore.setType(TWO_PAIR);
			myScore.setTypeLevel(card1 * 14 + card2);
			secondCardImportant = true;
		} else if (myScore.getType() < PAIR && rep1 == 2) {
			// One pair
			myScore.setType(PAIR);
			myScore.setTypeLevel(card1);
		} else if (myScore.getType() < HIGH_CARD && rep1 == 1) {
			// Got nothing, high card
			myScore.setType(HIGH_CARD);
			myScore.setTypeLevel(card1);
		} else {
			throw new RuntimeException("Couldn't determine hand.");
		}

		// Proceed to set tieBreaker value.
		ArrayList<Card> tieBreakHand = new ArrayList<Card>(7);
		for (Card c : hand) {
			boolean add2tieBreak = true;
			if (c.getNumber() == card1) {
				add2tieBreak = false;
			}
			if (secondCardImportant && c.getNumber() == card2) {
				add2tieBreak = false;

			}
			if (add2tieBreak) {
				tieBreakHand.add(c);
			}
		}

		// Now we have tieBreakHand. Set the tieBreaker score
		int tieBreakSize = tieBreakHand.size();
		Card[] tieBreak = new Card[tieBreakSize];
		int pos = 0;
		for (Card c : tieBreakHand) {
			tieBreak[pos++] = c;
		}
		Arrays.sort(tieBreak);
		int tieBreakValue = 0;
		for (int i = 0; i < tieBreakSize; i++) {
			Card c = tieBreak[i];
			int currNum = c.getNumber();
			int scale = (int) Math.pow(14, tieBreakSize - 1 - i);
			tieBreakValue += currNum * scale;
		}
		myScore.setTieBreaker(tieBreakValue);
	}

	/**
	 * Attempts to find a flush
	 * 
	 * @return The rank of the flush (the higher the better). If there is no
	 *         flush, returns -1.
	 */
	private int findFlush() {
		int[] numSuit = new int[4];
		int[] highestCard = new int[4];
		int ans = -1;
		for (Card c : hand) {
			int currSuit = c.getSuit();
			int currNum = c.getNumber();
			if (currNum == 1) {
				// Replace with Ace high #ace
				currNum = 14;
			}
			int highCardOfThisSuit = highestCard[currSuit - 1];
			if (currNum > highCardOfThisSuit) {
				highestCard[currSuit - 1] = currNum;
			}
			if (++numSuit[currSuit - 1] >= 5) {
				// We have a flush!
				ans = highestCard[currSuit - 1];
			}
		}
		return ans;
	}

	/**
	 * 
	 * @return The rank of the straight (the higher the better). If there is no
	 *         straight, returns -1.
	 */
	private int findStraight() {
		ArrayList<StraightSet> arr = new ArrayList<StraightSet>(hand.length);
		for (Card c : hand) {
			StraightSet curr = new StraightSet(c);
			arr.add(curr);
		}
		int bestStraight = -1;
		for (Card c : hand) {
			for (StraightSet currSet : arr) {
				currSet.tryToAdd(c);
				if (currSet.hasStraight()) {
					if (currSet.startingNum > bestStraight) {
						bestStraight = currSet.startingNum;
					}
				}
			}
		}
		// Rank the straight by the straight
		return bestStraight;
	}

	/**
	 * A helper class to help find straights.
	 * 
	 * @author Chris
	 *
	 */
	private class StraightSet {

		private int startingNum;
		private Card[] fourOthers;
		private int numAdds;

		public StraightSet(Card c) {
			int startingNum = c.getNumber();
			this.startingNum = startingNum;
			fourOthers = new Card[5];
			fourOthers[0] = c;
			numAdds = 0;
		}

		public void tryToAdd(Card c) {
			int cardNum = c.getNumber();
			int diff = cardNum - startingNum;
			if (cardNum == 1) {
				cardNum = 14;
			}
			if (diff > 0 && diff <= 4) {
				fourOthers[diff] = c;
				numAdds++;
			}
		}

		public boolean hasStraight() {
			if (numAdds < 4) {
				return false;
			}
			for (int i = 1; i < 5; i++) {
				Card curr = fourOthers[i];
				if (curr == null) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * A simple subclass to help keep track of a hand's ranking.
	 * 
	 * @author Chris
	 *
	 */
	private class Score {
		/**
		 * The weight factor of the type of hand. We want the type of card to be
		 * more important than the tiebreaker card or the quality of the type.
		 */
		public static final int TYPE_WEIGHT = 1000000;

		/**
		 * Similar to TYPE_WEIGHT, this weights the quality of the type to be
		 * more than a "kicker" or tie breaking card.
		 */
		public static final int TYPE_LEVEL_WEIGHT = 1000;

		/**
		 * Represents the type of hand. Higher is better. A type of 2 means
		 * pair, whereas a type of 9 means straight flush. Ranges from 0 to 9.
		 */
		private int type;

		/**
		 * Represents the quality of the type of hand. For example, in a full
		 * house, a small type means your full house is something like a 3 and a
		 * 2, whereas a large type means your full house is something like a K
		 * and a Q. Ranges from 0 to 14 * 14 + 14 = 210.
		 */
		private int typeLevel;

		/**
		 * Represents the tieBreaker power of a hand. For example in a pair of
		 * 2's, if you have an ace for a tieBreaker you'll have a high
		 * tieBreaker score, whereas if you have a 3 for a tie breaking card,
		 * your tieBreaker score will be low.
		 */
		private int tieBreaker;
		private String description;

		public Score() {
			type = -1;
			typeLevel = -1;
			tieBreaker = -1;
			description = null;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getType() {
			return this.type;
		}

		/**
		 * 
		 * @return The brief description of the hand, such as "Pair of 8" or
		 *         "Full House of 8 and 2". Only calculates this once and stores
		 *         it in order to be efficient
		 */
		public String getDescription() {
			if (description != null) {
				return description;
			}
			switch (type) {
			case HIGH_CARD:
				description = "High Card";
				break;
			case PAIR:
				description = "Pair";
				break;
			case TWO_PAIR:
				description = "Two Pair";
				break;
			case THREE_OF_A_KIND:
				description = "Triple";
				break;
			case STRAIGHT:
				description = "Straight";
				break;
			case FLUSH:
				description = "Flush";
				break;
			case FULL_HOUSE:
				description = "Full House";
				break;
			case FOUR_OF_A_KIND:
				description = "Quadruple";
				break;
			case STRAIGHT_FLUSH:
				description = "Straight Flush";
				break;
			}
			// TODO: Add semi-description
			return description;
		}

		public void setTypeLevel(int typeLevel) {
			this.typeLevel = typeLevel;
		}

		public void setTieBreaker(int tieBreaker) {
			this.tieBreaker = tieBreaker;
		}

		private boolean isDone() {
			return type > -1 && typeLevel > -1 && tieBreaker > -1;
		}

		public int getScore() {
			int bigWeight = type * TYPE_WEIGHT;
			int mediumWeight = typeLevel * TYPE_LEVEL_WEIGHT;
			return bigWeight + mediumWeight + tieBreaker;
		}
	}

}
