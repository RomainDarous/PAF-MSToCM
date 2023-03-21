package ui.cards;

import javax.swing.JPanel;

import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * Abstract class that gives the main methods and properties of the different cards. Each card will extend this class.
 * @author Romain Darous
 *
 */

public abstract class Card extends JPanel {
	
	private final MazeModel mazeModel;
	
	/**
	 * Constructor of the Card class.
	 * @param mazeView the view in which is displayed the card.
	 */
	public Card(MazeView mazeView) {
		super();
		this.mazeModel = mazeView.getMazeModel();
		setOpaque(false);
	}
	
	/**
	 * Gets the maze model of the application.
	 * @return the maze model.
	 */
	public final MazeModel getMazeModel() {
		return mazeModel;
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public abstract void notifyForUpdate();
}
