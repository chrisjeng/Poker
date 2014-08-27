import java.util.ArrayList;

public class PokerTable {
	private Deck theDeck;
	private ArrayList<Player> players;

	public static final int MAX_PLAYERS = 8;

	public static void main(String[] args) {
		// Just runs the GUI
		GUI.initGUI();
	}

	public void initDeck() {
		theDeck = Deck.makeShuffledDeck();
	}

	public void initPlayers() {
		players = new ArrayList<Player>();
		addPlayer(1000, "Chris", theDeck);
		addPlayer(500, "Don", theDeck);
		for (Player player : players) {
			player.drawTwoCards();
		}
	}

	/**
	 * @return All the competing players in the game.
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Attempts to add a new player. May throw an exception.
	 * @param money
	 * Starting balance
	 * @param name
	 * ...yeah
	 * @param sharedDeck
	 * The shared Deck
	 */
	public void addPlayer(int money, String name, Deck sharedDeck) {
		if (players.size() == MAX_PLAYERS) {
			String msg = "Cannot add more than 8 players";
			throw new RuntimeException(msg);
		}
		players.add(new Player(money, name, sharedDeck));
	}
}