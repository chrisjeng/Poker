import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PokerTable {
	private Deck theDeck;
	private ArrayList<Player> players;

	// private int maxPlayerNum;

	public static void main(String[] args) {
		GUI.initGUI();
	}

	public void initDeck() {
		theDeck = Deck.makeShuffledDeck();
	}

	public void initPlayers() {
		players = new ArrayList<Player>();
		addPlayer(1000, "Chris", theDeck);
		addPlayer(500, "Don", theDeck);
		for(Player player : players){
			player.drawTwoCards();
		}
	}

	/**
	 * @return the competing players in the game
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(int money, String name, Deck sharedDeck) {
		if (players.size() > 8) {
			System.err.println("Cannot add more than 8 players");
			return;
		}
		players.add(new Player(money, name, sharedDeck));
	}
}