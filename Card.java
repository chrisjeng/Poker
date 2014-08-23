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
		initializeCardNumber();
		initializeIcon();
	}
	
	public Card(int cardNumber) {
		this(cardNumber % 13 + 1, (cardNumber / 13 + 1));
	}

	private void initializeCardNumber() {
		this.cardNumber = (suit - 1) * 13 + number;
	}
	
	// Gets the correct icon from the "images" folder
	private void initializeIcon() {
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
		return "HELLO"; //TODO
	}
}
