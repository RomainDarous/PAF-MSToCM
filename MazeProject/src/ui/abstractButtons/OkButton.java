package ui.abstractButtons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.view.MazeView;

/**
 * An abstract class that gathers the main methods the all the OK buttons, including the action performed when clicking on them.
 * @author Romain Darous
 *
 */

public abstract class OkButton extends MazeAbstractButton implements MouseListener {

	/**
	 * Constructor of the OkButton class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public OkButton(MazeView mazeView) {
		super(Const.OK_LABEL, mazeView);
		setupOkButton();
	}
	
	/**
	 * A private method that sets up the button.
	 */
	private final void setupOkButton() {
		this.setBackground(Const.OK_SAVE_BUTTON_DEFAULT_COLOR);
		this.addMouseListener(this);
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
	 * Tells the model the action to perform when clicking on the OK button : creating the maze.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.OK_SAVE_BUTTON_DEFAULT_COLOR);
		this.getMazeModel().setMazeMode(Const.CREATING);
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
