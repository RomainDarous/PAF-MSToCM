package ui.abstractButtons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.view.MazeView;

/**
 * An abstract class that gathers the main methods the all the save buttons, including the action performed when clicking on them.
 * @author Romain Darous
 *
 */

public abstract class SaveButton extends MazeAbstractButton implements MouseListener {

	
	/**
	 * Constructor of the SaveButton class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public SaveButton(MazeView mazeView) {
		super(Const.SAVE_LABEL, mazeView);
		setupSaveButton();
	}
	
	/**
	 * A private method that sets up the button.
	 */
	private final void setupSaveButton() {
		setBackground(Const.OK_SAVE_BUTTON_DEFAULT_COLOR);
		addMouseListener(this);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.setBackground(Const.OK_SAVE_BUTTON_DEFAULT_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mousePressed(MouseEvent e) {
		this.setBackground(Const.OK_SAVE_BUTTON_PRESSED_COLOR);
	}

	/**
	 * Changes the color of the button.
	 * Tells the model the action to perform when clicking on the save button : saving the maze.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.OK_SAVE_BUTTON_DEFAULT_COLOR);
		getMazeModel().setMazeMode(Const.SAVING);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseEntered(MouseEvent e) {
		this.setBackground(Const.OK_SAVE_BUTTON_OVER_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseExited(MouseEvent e) {
		this.setBackground(Const.OK_SAVE_BUTTON_DEFAULT_COLOR);
	}
}
