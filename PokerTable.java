import java.util.ArrayList;

public class PokerTable {
	
	/**
	 * Calculates how strong a hand is. The higher, the better.
	 * 
	 * @param fullHand
	 *            An ArrayList of all the available hands to choose from in
	 *            no particular order. It is expected that two of these are
	 *            the player's hands, and five of them are the table's
	 *            shared cards.
	 * @return A relative score for the current hand. A higher score means
	 *         it's better.
	 */
	public int handWorth(ArrayList<Card> fullHand) {
		Calculator myCalc = new Calculator(fullHand);
		return myCalc.handWorth();
	}

	private class Calculator {

		private ArrayList<Card> fullHand;
		private int rank;
		private int miniRank;

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
			int repeated = findRepeatedCards();

			return rank * 200 + miniRank;
		}

		private int findRepeatedCards() {
			int[] repeats = new int[13];
			int repeatMax = 0;
			for (Card c : fullHand) {
				int num = c.getNumber();
				int newNum = repeats[num]++;
				if (newNum > repeatMax) {
					repeatMax = newNum;
				}
			}

			return repeatMax;
		}
	}
}
