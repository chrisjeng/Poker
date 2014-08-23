import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {

	public static final int SIZE = 52;

	private HashSet<Card> drawnCards;
	private Queue<Card> myCards;

	// Initializes a shuffled deck
	public Deck() {
		this.drawnCards = new HashSet<Card>(SIZE);
		this.myCards = new LinkedList<Card>();
	}

	public static Deck makeDeck() {
		// TODO
		HashSet<Integer> addedNumbers = new HashSet<Integer>();
		int rand52 = (int) (Math.random() * 52 + 1);
		return null;
	}

	// Draws a random card
	public Card draw() {
		Card toRtn = myCards.poll();
		drawnCards.add(toRtn);
		return toRtn;
	}

	// Returns whether or not the collection contains
	public boolean contains(Card c) {
		return false;
	}

	public void shuffle() {
	}

}
