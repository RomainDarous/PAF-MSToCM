package ui.abstractButtons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.view.MazeView;

/**
 * An abstract class that gathers the main methods of all the cancel buttons, including the action performed when clicking on them.
 * @author Romain Darous
 *
 */

public abstract class CancelButton extends MazeAbstractButton implements MouseListener {
	
	/**
	 * Constructor of the CancelButton class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public CancelButton(MazeView mazeView) {
		super(Const.CANCEL_LABEL, mazeView);
		setupCancelButton();
	}
	
	/**
	 * A private method that sets up the button.
	 */
	private final void setupCancelButton() {
		addMouseListener(this);
		setBackground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
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
	 * Tells the model the action to perform when clicking on the cancel button : going backward.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
		getMazeModel().setPage(Const.DISABLE_CREATE_PAGE);
		
		if(getMazeModel().getPage()[0] == Const.EDIT_PAGE) getMazeModel().setPage(Const.HOME_PAGE);
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
