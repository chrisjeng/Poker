public interface CardCollection{
	
	// Draws a random card
	public Card draw();

	// Discards the current hand and returns true if at least
	// one card was discarded.
	public boolean clear();

	// Returns whether or not the collection contains
	public boolean contains(Card c);

	// Adds the card to the hand. Returns true if it is
	// unique
	public boolean add(Card c);
}
