import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI {
	private static JFrame mainFrame;
	private static JPanel tablePanel;
	private static JPanel cardPanel;
	private static JButton raiseButton;
	private static JButton callButton;
	private static JButton foldButton;
	private static JLabel messageLabel;
	
	private static HashMap<Integer, String> cardMap;
	private static PokerTable currTable;
	
	public static void initGUI(){
		mainFrame = new JFrame();
		mainFrame.setLayout(new FlowLayout());
	}
	
	
	public static void fillTable(){
		
	}
	
	// method that creates two cards representing a hand for each player
	
	// method that creates an entire table
	
	// method that creates five cards in the middle of the table dealt by the dealer
	
	// method that creates a big blind and a small blind 
	
	private static class SelectRaise implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private static class SelectCall implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class SelectFold implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class NewGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private static void GameOver(){
		
	}
}
