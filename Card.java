import javax.swing.ImageIcon;
import java.io.File;

public class Card implements Comparable<Card>{
	
	public static void main(String[] args) {
	}
	private int suit;
	private int number;
	private int cardID;
	private ImageIcon myIcon;
	
	public static final int SPADE = 1;
	public static final int HEART = 2; // Maybe change later 
	public static final int DIAMOND= 3;
	public static final int CLUB = 4;
		
	public Card(int number, int suit) {
		this.number = number;
		this.suit = suit;
		this.cardID = (suit - 1) * 13 + number;
		this.checkInput();
		initializeIcon();
	}
	
	public Card(int cardNumber) {
		this.cardID = cardNumber;
		this.suit = cardNumber / 13 + 1;
		if (cardNumber == 52) {
			// Edge case for suit = 5
			suit--;
		}
		this.number = cardNumber % 13;
		if (cardNumber % 13 == 0) {
			this.number = 13;
		}
		this.checkInput();
		initializeIcon();
	}
	
	/**
	 * Throws an exception if input suit/number is bad
	 */
	public void checkInput() {
		if (number < 1 || number > 13) {
			throw new IllegalArgumentException("Can't make " + cardID);
		}
		if (suit < 1 || suit > 4) {
			throw new IllegalArgumentException("Can't make " + cardID);
		}
		if (cardID == 0) {
			// Special edge case
			throw new IllegalArgumentException("Can't make " + cardID);
		}
	}
	
	// Gets the correct icon from the "images" folder
	private void initializeIcon() {
		//TODO
	}
	
	public ImageIcon getIcon() {
		return myIcon;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getCardID(){
		return cardID;
	}
	
	public String toString() {
		String num = "" + number;
		if (number == 11) {
			num = "Jack";
		} else if (number == 12) {
			num = "Queen";
		} else if (number == 13) {
			num = "King";
		} else if (number == 1) {
			num = "Ace";
		}
		String inBetween = " of ";
		String displaySuit = "";
		switch (suit) {
		case SPADE:
			displaySuit = "Spades";
			break;
		case HEART:
			displaySuit = "Hearts";
			break;
		case DIAMOND:
			displaySuit = "Diamonds";
			break;
		case CLUB:
			displaySuit = "Clubs";
			break;
		}
		return num + inBetween + displaySuit;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Card == false) {
			return false;
		}
		Card other = (Card) o;
		if (other.suit == this.suit) {
			if (other.number == this.number) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Just compares the numbers. This is used in ordering cards for
	 * tie-breakers, and thus aces are treated as high
	 */
	public int compareTo(Card other) {
		int otherNum = other.number;
		int thisNum = this.number;
		if (thisNum == 1)
			thisNum = 14;
		if (otherNum == 1)
			otherNum = 14;
		return otherNum - thisNum;
	}
}
