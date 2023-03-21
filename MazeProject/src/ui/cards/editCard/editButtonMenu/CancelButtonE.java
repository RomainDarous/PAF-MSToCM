package ui.cards.editCard.editButtonMenu;

import ui.Const;
import ui.abstractButtons.CancelButton;
import ui.view.MazeView;

/**
 * Class which extends the CancelButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */
public class CancelButtonE extends CancelButton {

	/**
	 * Constructor of the CancelButtonE class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public CancelButtonE(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.CANCEL_EDIT_BUTTON_DIMENSIONS);
		setFont(Const.CANCEL_EDIT_BUTTON_FONT);
	}
}
