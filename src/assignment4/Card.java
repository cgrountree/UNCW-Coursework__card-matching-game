package assignment4;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
// import javax.swing.JComponent;
import javax.swing.JButton;

/**
 * @author Cody Rountree
 * 
 * Card class that extends JButton that offers specific methods for objects of type Card.
 * Initializes the properties width, height, faceColor, backgroundColor, isFaceDown, and matchFound.
 *
 */
public class Card extends JButton {
	private static int width = 75;
	private static int height = 125;

	private Color faceColor;
	private static Color backgroundColor = Color.GRAY;

	private boolean isFaceDown = true;
	private boolean matchFound = false;

	/**
	 * Constructor for Card that takes a Color object as a parameter, sets the dimensions, and the border.
	 * @param fc is a Color object
	 */
	public Card(Color fc) {
		faceColor = fc;
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		faceDown();
	}

	/**
	 * Public method that sets the Card to be face up.
	 */
	public void faceUp() {
		isFaceDown = false;
		changeColor(faceColor);
	}

	/**
	 * Public method that sets the Card to be face down.
	 */
	public void faceDown() {
		isFaceDown = true;
		changeColor(backgroundColor);
	}

	/**
	 * Public method that returns the color of the Card.
	 * @return the faceColor
	 */
	public Color getColor() {
		return this.faceColor;
	}

	/**
	 * Public method that changes the color of the card to a new color.
	 * @param faceColor2 is the new color.
	 */
	private void changeColor(Color faceColor2) {
		// TODO Auto-generated method stub
		setBackground(faceColor2);
		paintImmediately(0, 0, width, height);

	}

	/**
	 * Public method that allows for changing the boolean value of the matchFound property.
	 * @param match is the new boolean value
	 */
	public void setMatchFound(boolean match) {
		this.matchFound = match;
	}

	/**
	 * Public method for getting the boolean value for whether or not a match has been found for the Card.
	 * @return matchFound
	 */
	public boolean getMatchFound() {
		return this.matchFound;
	}

}
