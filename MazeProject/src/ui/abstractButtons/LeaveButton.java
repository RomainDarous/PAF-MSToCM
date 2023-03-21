package ui.abstractButtons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.view.MazeView;

/**
 * An abstract class that gathers the main methods of all the leave buttons, including the action performed when clicking on them.
 * @author Romain Darous
 *
 */

public abstract class LeaveButton extends MazeAbstractButton implements MouseListener {
	
	/**
	 * Constructor of the LeaveButton class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public LeaveButton (MazeView mazeView) {
		super(Const.LEAVE_LABEL, mazeView);
		setupButton();
	}
	
	/**
	 * A private method that sets up the button.
	 */
	private final void setupButton() {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
		this.addMouseListener(this);
	}
	
	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mousePressed(MouseEvent e) {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_PRESSED_COLOR);
	}

	/**
	 * Changes the color of the button.
	 * Tells the model the action to perform when clicking on the leave button : leaving the app.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
		getMazeModel().setPage(Const.LEAVE_PAGE);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseEntered(MouseEvent e) {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_OVER_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseExited(MouseEvent e) {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
	}
}
