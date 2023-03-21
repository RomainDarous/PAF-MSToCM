package ui.cards.editCard.editNCQMenu;
import ui.Const;
import ui.abstractButtons.LeaveButton;
import ui.view.MazeView;

/**
 * Class which extends the LeaveButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */

public final class LeaveButtonE extends LeaveButton {

	/**
	 * Constructor of the LeaveButtonE class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public LeaveButtonE(MazeView mazeView) {
		super(mazeView);
		setupLeaveButton();
	}
	
	private final void setupLeaveButton() {
		this.setPreferredSize(Const.SOLVED_BUTTON_DIMENSIONS);
		this.setFont(Const.SOLVED_MENU_BUTTON_FONT);
	}
}
