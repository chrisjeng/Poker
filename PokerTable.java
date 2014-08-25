import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PokerTable {
	private ArrayList<Player> players;
	/**
	 * 
	 * @return the competing players in the game
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}
	/**
	 * Calculates how strong a hand is. The higher, the better.
	 * 
	 * @param fullHand
	 *            An ArrayList of all the available hands to choose from in no
	 *            particular order. It is expected that two of these are the
	 *            player's hands, and five of them are the table's shared cards.
	 * @return A relative score for the current hand. A higher score means it's
	 *         better.
	 */
	public int handWorth(ArrayList<Card> fullHand) {
		Calculator myCalc = new Calculator(fullHand);
		return myCalc.handWorth();
	}

	// #testTemp
	public class Calculator {

		/**
		 * The ArrayList representation of a full hand. "Full" means the two
		 * cards of the player are included, and also the shared public cards
		 * are also included. At showdown, a fullHand should have seven cards
		 * exactly.
		 */
		private ArrayList<Card> fullHand;
		private int rank;
		private int miniRank;
		public String crappyDescription = null;

		public Calculator(ArrayList<Card> fullHand) {
			this.fullHand = fullHand;
		}

		public int handWorth() {

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
			rank = 0;
			miniRank = 0;
			int straight = findStraight();
			int flush = findFlush();
			if (rank < 9 && straight > 0 && flush > 0) {
				// Straight flush
				crappyDescription = "Straight flush";
				rank = 9;
				miniRank = straight;
			}
			if (rank < 5 && straight > 0) {
				// Straight
				rank = 5;
				miniRank = straight;
			}
			if (rank < 6 && flush > 0) {
				// Flush
				rank = 6;
				miniRank = flush;
			}
			// check others
			findRepeatedCards();

			return rank * 1000 + miniRank;
		}

		// #testTemp
		/**
		 * 
		 * @return The rank of the straight (the higher the better). If there is
		 *         no straight, returns -1.
		 */
		public int findStraight() {
			ArrayList<StraightSet> arr = new ArrayList<StraightSet>(
					fullHand.size());
			for (Card c : fullHand) {
				StraightSet curr = new StraightSet(c);
				arr.add(curr);
			}
			int bestStraight = -1;
			for (Card c : fullHand) {
				for (StraightSet currSet : arr) {
					currSet.tryToAdd(c);
					if (currSet.hasStraight()) {
						if (currSet.startingNum > bestStraight) {
							bestStraight = currSet.startingNum;
							crappyDescription = "Straight";
						}
					}
				}
			}
			// Rank the straight by the straight
			return bestStraight;
		}

		// #testTemp
		/**
		 * 
		 * @return The rank of the flush (the higher the better). If there is no
		 *         flush, returns -1.
		 */
		public int findFlush() {
			int[] numSuit = new int[4];
			int[] highestCard = new int[4];
			for (Card c : fullHand) {
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
				if (++numSuit[currSuit - 1] == 5) {
					// We have a flush!
					crappyDescription = "Flush";
					return highestCard[currSuit - 1];
				}
			}
			return -1;
		}

		private void findRepeatedCards() {
			int[] repeats = new int[13];
			int repeatMax = 0;
			Card maxCard;
			int secondMax = 0;
			Card secondMaxCard;
			for (Card c : fullHand) {
				int num = c.getNumber();
				int newNum = repeats[num - 1]++;
				if (newNum > repeatMax) {
					repeatMax = newNum;
					maxCard = c;
				} else {
					if (newNum > secondMax) {
						secondMax = newNum;
						secondMaxCard = c;
					}
				}
			}

			if (rank < 1 && repeatMax == 1) {
				// Got nothing, high card
				crappyDescription = "High card";
				rank = 1;
				miniRank = tieBreakValue(-1);
			}
			if (rank < 2 && repeatMax == 2) {
				// One pair
				crappyDescription = "Pair";
				rank = 2;
				//TODO: Taking a break
			}
			if (rank < 3 && repeatMax == 2 && secondMax == 2) {
				// Two pair
				crappyDescription = "Two pair";
				rank = 3;
			}
			if (rank < 4 && repeatMax == 3) {
				// Three of a kind
				crappyDescription = "Three of a kind";
				rank = 4;
			}
			if (rank < 7 && repeatMax == 3 && secondMax == 2) {
				// Full house
				crappyDescription = "Full house";
				rank = 7;
			}
			if (rank < 8 && repeatMax == 4) {
				// Four of a kind
				crappyDescription = "Four of a kind";
				rank = 8;
			}
		}

		/**
		 * Calculates the mini-rank of a high card hand. For tiebreakers, only
		 * considers the five largest cards.
		 */
		public void calcHigh() {
			boolean DEBUGGINGPRINT = true;
			int size = fullHand.size();
			Card[] arr = new Card[size];
			for (int i = 0; i < size; i++) {
				arr[i] = fullHand.get(i);
			}
			Arrays.sort(arr);
			if (DEBUGGINGPRINT) {
				for (int i = 0; i < size; i++) {
					Card curr = arr[i];
					System.out.println(curr.toString());
				}
			}

			// Now we have the cards sorted in desc order
			int newMiniRank = 0;
			for (int i = 0; i < size; i++) {
				Card curr = arr[i];
				int num = curr.getNumber();

				// Aces are high, #ace
				if (num == 1) {
					num = 14;
				}
				int scale = (int) Math.pow(14, 4 - i);
				newMiniRank += scale * num;
			}
			this.miniRank = newMiniRank;
		}

		public int tieBreakValue(int ignoreThisNum) {
			ArrayList<Card> temp = new ArrayList<Card>(7);
			for (Card c : fullHand) {
				if (c.getNumber() != ignoreThisNum) {
					temp.add(c);
				}
			}
			int size = temp.size();
			Card[] tieBreakCards = new Card[size];
			int pos = 0;
			for (Card c : temp) {
				tieBreakCards[pos++] = c;
			}
			Arrays.sort(tieBreakCards);
			// Now we have the card we want to calculate
			int newMiniRank = 0;
			for (int i = 0; i < size; i++) {
				Card curr = tieBreakCards[i];
				int num = curr.getNumber();

				// Aces are high, #ace
				if (num == 1) {
					num = 14;
				}
				int power = size - 1 - i;
				int scale = (int) Math.pow(14, power);
				newMiniRank += scale * num;
			}
			return newMiniRank;
		}

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

	}

}
