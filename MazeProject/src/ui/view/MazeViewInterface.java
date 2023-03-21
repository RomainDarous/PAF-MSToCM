package ui.view;

import javax.swing.event.ChangeListener;

import ui.model.MazeModel;

/**
 * This interface declares all the methods implemented in the maze view.
 * @author Romain Darous
 *
 */
public interface MazeViewInterface extends ChangeListener {
	
	/**
	 * Gets the maze model of the application.
	 * @return the maze model of the application.
	 */
	public MazeModel getMazeModel();
	
	/**
	 * Allows to show the home page of the application.
	 */
	public void showHomeCard();
}
