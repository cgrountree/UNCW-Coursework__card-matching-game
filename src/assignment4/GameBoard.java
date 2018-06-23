package assignment4;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author Cody Rountree
 *
 *A class that extends JFrame for the purpose of making a graphical interface for a simple card matching game.
 *The properties sets, cards, checkerTimer, card1, card2, currentCard, and counter are initialized.
 */
public class GameBoard extends JFrame {
	private int sets;
	private List<Card> cards;
	private Timer checkerTimer;
	private Card card1;
	private Card card2;
	private Card currentCard;
	private static int counter;

	/**
	 * A main that initiates a new gameboard of a desired size
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter a number of sets: ");
		String numberOfSets = s.next();
		int totalSets = Integer.parseInt(numberOfSets);
		new GameBoard(totalSets);
	}

	/**
	 * Constructor for gameboard that takes a number of sets of cards and creates pairs of colored cards and adds them to the content pane.
	 * @param sets represents the number of sets of cards
	 */
	public GameBoard(int sets) {

		this.sets = sets;
		List<Card> cards = new ArrayList<Card>();
		List<Color> colors = new ArrayList<Color>();

		setTitle("Memory Game: 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);

		Container c = getContentPane();
		c.setLayout(new GridLayout(4, sets));

		for (int i = 0; i < sets; i++) {
			
			int current = i % 6;
			System.out.println(current);
			if (current == 1) {
				colors.add(Color.RED);
				colors.add(Color.RED);
			}
			if (current == 2) {
				colors.add(Color.YELLOW);
				colors.add(Color.YELLOW);
			}
			if (current == 3) {
				colors.add(Color.GREEN);
				colors.add(Color.GREEN);
			}
			if (current == 4) {
				colors.add(Color.BLUE);
				colors.add(Color.BLUE);
			}
			if (current == 5) {
				colors.add(Color.MAGENTA);
				colors.add(Color.MAGENTA);
			}
			if (current == 0) {
				colors.add(Color.ORANGE);
				colors.add(Color.ORANGE);
			}
		}


		Collections.shuffle(colors);

		for (Color aColor : colors) {
			Card aCard = new Card(aColor);
			aCard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					currentCard = aCard;
					flip(currentCard);
					counter++;
				}
			});
			cards.add(aCard);
		}
		this.cards = cards;

		checkerTimer = new Timer(600, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checker();
			}
		});

		checkerTimer.setRepeats(false);

		for (Card theCard : cards) {
			c.add(theCard);
		}

		setVisible(true);
	}

	/**
	 * A public method that flips the clicked card, then checks if it is the first card clicked. If it is the second card clicked, the the timer will start and run the checker method.
	 * @param currentCard is the most recently clicked card
	 */
	public void flip(Card currentCard) {
		currentCard.faceUp();
		if (card1 == null && card2 == null) {
			card1 = currentCard;
		}
		if (card1 != null && card1 != currentCard && card2 == null) {
			card2 = currentCard;
			checkerTimer.start();
		}
	}

	/**
	 * A public method that checks whether the most recently clicked card matches the previously clicked card. If they match, then they are disabled and left facing up. If they do not match, they are flipped face down.
	 */
	public void checker() {
		if (card1.getColor() == card2.getColor()) {
			card1.setEnabled(false);
			card2.setEnabled(false);
			card1.setMatchFound(true);
			card2.setMatchFound(true);
			if (this.wonGame()) {
				System.out.println("You won! It took you " + counter + " clicks to win.");
				System.exit(0);
			}
		} else {
			card1.faceDown();
			card2.faceDown();
		}
		card1 = null;
		card2 = null;
	}

	/**
	 * A public method that checks if the game is won by iterating through the card list to see if each card has found a match. If the game is won, a message tells the user how many times they clicked before winning.
	 * @return a boolean
	 */
	public boolean wonGame() {
		for (Card aCard : this.cards) {
			if (aCard.getMatchFound() == false) {
				return false;
			}
		}
		return true;
	}

}
