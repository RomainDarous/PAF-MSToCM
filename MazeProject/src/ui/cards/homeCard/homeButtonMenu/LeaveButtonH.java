package ui.cards.homeCard.homeButtonMenu;
import ui.Const;
import ui.abstractButtons.LeaveButton;
import ui.view.MazeView;
/**
 * Class which extends the LeaveButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */
public final class LeaveButtonH extends LeaveButton{

	/**
	 * Constructor of the LeaveButtonH class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public LeaveButtonH(MazeView mazeView) {
		super(mazeView);
		setupLeaveButton();
	}

	
	private final void setupLeaveButton() {
		this.setPreferredSize(Const.HOME_BUTTON_DIMENSIONS);
		this.setFont(Const.HOME_BUTTON_FONT);
	}
}
