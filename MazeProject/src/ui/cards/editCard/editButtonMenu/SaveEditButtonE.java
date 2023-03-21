package ui.cards.editCard.editButtonMenu;

import ui.Const;
import ui.abstractButtons.SaveButton;
import ui.view.MazeView;

/**
 * Class which extends the SaveButton class and simply modifies the size and the font of the button.
 * This is the saved button displayed when the maze is in editing mode.
 * @author Romain Darous
 *
 */

public class SaveEditButtonE extends SaveButton {

	/**
	 * Constructor of the SaveEditButtonE class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public SaveEditButtonE(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.SAVE_EDIT_BUTTON_DIMENSIONS);
		setFont(Const.SAVE_EDIT_BUTTON_FONT);
		}
}
