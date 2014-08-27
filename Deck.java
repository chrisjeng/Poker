import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a 52-card deck.
 * @author Chris
 *
 */
public class Deck {

	public static final int SIZE = 52;

	/**
	 * Keeps track of all cards that have been drawn.
	 */
	private HashSet<Card> drawnCards;
	private Queue<Card> myCards;

	// Initializes a shuffled deck
	public Deck() {
		this.drawnCards = new HashSet<Card>(SIZE);
		this.myCards = new LinkedList<Card>();
	}

	public static Deck makeShuffledDeck() {
		Card[] toShuffle = new Card[SIZE];
		for (int i = 0; i < SIZE; i++) {
			toShuffle[i] = new Card(i + 1);
		}

		for (int i = 0; i < SIZE; i++) {
			int j = (int) (Math.random() * SIZE);
			Card temp = toShuffle[j];
			toShuffle[j] = toShuffle[i];
			toShuffle[i] = temp;
		}

		Deck myDeck = new Deck();
		for (Card shuffledCard : toShuffle) {
			myDeck.add2Queue(shuffledCard);
		}

		return myDeck;
	}

	/**
	 * Draws a random card
	 * @return
	 * A random card.
	 */
	public Card draw() {
		Card toRtn = myCards.poll();
		drawnCards.add(toRtn);
		return toRtn;
	}

	
	private void add2Queue(Card c) {
		myCards.add(c);
	}

	// Returns whether or not the collection contains
	public boolean contains(Card c) {
		return !drawnCards.contains(c);
	}
}
