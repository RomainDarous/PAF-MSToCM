package ui.abstractButtons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.view.MazeView;

/**
 * An abstract class that gathers the main methods of all the load buttons, including the action performed when clicking on them.
 * @author Romain Darous
 *
 */

public abstract class LoadButton extends MazeAbstractButton implements MouseListener {	
	
	/**
	 * Constructor of the LoadButton class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public LoadButton (MazeView mazeView) {
		super(Const.LOAD_LABEL, mazeView);
		setupLoadButton();
	}
	
	/**
	 * A private method that sets up the button.
	 */
	private final void setupLoadButton() {
		this.setBackground(Const.LOAD_BUTTON_DEFAULT_COLOR);
		this.addMouseListener(this);
	}
	
	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.setBackground(Const.LOAD_BUTTON_DEFAULT_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mousePressed(MouseEvent e) {
		this.setBackground(Const.LOAD_BUTTON_PRESSED_COLOR);
	}

	/**
	 * Changes the color of the button.
	 * Tells the model the action to perform when clicking on the load button : loading a maze.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.LOAD_BUTTON_DEFAULT_COLOR);
		this.getMazeModel().setMazeMode(Const.LOADING);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseEntered(MouseEvent e) {
		this.setBackground(Const.LOAD_BUTTON_OVER_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseExited(MouseEvent e) {
		this.setBackground(Const.LOAD_BUTTON_DEFAULT_COLOR);
	}
}

