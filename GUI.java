import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
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
	private static JPanel buttonPanel;
//	private static JButton raiseButton;
//	private static JButton callButton;
//	private static JButton foldButton;
	private static JLabel messageLabel;
	private static JLabel balanceLabel = new JLabel("Balance: $0.00");
	
	private static HashMap<Integer, String> cardMap;
	private static PokerTable currTable;
	
	public static void initGUI(){
		mainFrame = new JFrame();
//		mainFrame.setLayout(null);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		mainFrame.setTitle("Sentimental Poker");
		cardMap = new HashMap<Integer, String>();
		
		for(int i = 0; i < 52; i++){
			cardMap.put(i, "images/card_number_" + i + ".png");
		}
		tablePanel = new JPanel();
		cardPanel = new JPanel();
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JButton button = new JButton("Raise");
		button.addActionListener(new Raise());
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(button, c);

		button = new JButton("Call");
		button.addActionListener(new Call());
		c.gridx = 1;
		buttonPanel.add(button, c);
		
		button = new JButton("Fold");
		button.addActionListener(new Fold());
		c.gridx = 2;
		buttonPanel.add(button, c);
		
		JButton button2 = new JButton("New Game");
		button2.setBounds(rect(500, 300, button.getPreferredSize()));
		button2.addActionListener(new NewGame());
		mainFrame.add(button2);
		
		buttonPanel.setBounds(rect(500, 200, buttonPanel.getPreferredSize()));
		mainFrame.add(buttonPanel);
		messageLabel = new JLabel("\n\n\n\n");
		messageLabel.setBounds(rect(450,250, messageLabel.getPreferredSize()));
		messageLabel.setText("");
		
		balanceLabel = new JLabel("Balance: $999999.99");
		balanceLabel.setBounds(rect(475, 150, balanceLabel.getPreferredSize()));
		balanceLabel.setText("Balance: $0.00");
		
		balanceLabel.setVisible(true);
		messageLabel.setVisible((true);
		
		balanceLabel.repaint();
		messageLabel.repaint();
		
		mainFrame.add(balanceLabel);
		mainFrame.add(messageLabel);
		
		mainFrame.repaint();
		mainFrame.validate();
	}
	
	
	private static Rectangle rect(int x, int y, Dimension size) {
		Rectangle result = new Rectangle(new Point(x,y), size);
		return result;
	}


	public static void fillTable(){
		
	}
	
	// method that creates two cards representing a hand for each player
	
	// method that creates an entire table
	
	// method that creates five cards in the middle of the table dealt by the dealer
	
	// method that creates a big blind and a small blind 
	
	private static class Raise implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private static class Call implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class Fold implements ActionListener{

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
