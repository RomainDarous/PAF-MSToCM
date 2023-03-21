package ui.cards.editCard.editNCQMenu.NewButton;

import ui.Const;
import ui.abstractButtons.NewButton;
import ui.view.MazeView;

/**
 * Class which extends the NewButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */

public class NewButtonE extends NewButton {
	

	/**
	 * Constructor of the NewButtonE class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public NewButtonE(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.SOLVED_BUTTON_DIMENSIONS);
		this.setFont(Const.SOLVED_MENU_BUTTON_FONT);
	}
}
