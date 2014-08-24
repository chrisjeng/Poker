import javax.swing.ImageIcon;
import java.io.File;

public class Card {
	
	public static void main(String[] args) {
	}
	private int suit;
	private int number;
	private int cardNumber;
	private ImageIcon myIcon;
	
	public static final int SPADE = 1;
	public static final int HEART = 2; // Maybe change later 
	public static final int DIAMOND= 3;
	public static final int CLUB = 4;
		
	public Card(int number, int suit) {
		this.number = number;
		this.suit = suit;
		this.checkInput();
		this.cardNumber = (suit - 1) * 13 + number;
		initializeIcon();
	}
	
	public Card(int cardNumber) {
		this.cardNumber = cardNumber;
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
			System.out.println(number);
			throw new IllegalArgumentException("Can't make " + cardNumber);
		}
		if (suit < 1 || suit > 4) {
			throw new IllegalArgumentException("Can't make " + cardNumber);
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
	
	public String toString() {
		return "( " + suit + " : " + number + " )";
	}
}
