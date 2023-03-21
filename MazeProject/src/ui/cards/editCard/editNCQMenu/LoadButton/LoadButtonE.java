package ui.cards.editCard.editNCQMenu.LoadButton;

import ui.Const;
import ui.abstractButtons.LoadButton;
import ui.view.MazeView;

/**
 * Class which extends the LoadButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */

public class LoadButtonE extends LoadButton {
	
	/**
	 * Constructor of the LoadButtonE class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public LoadButtonE(MazeView mazeView) {
		super(mazeView);
		setupLoadButtonE();
	}
	
	private final void setupLoadButtonE() {
		this.setPreferredSize(Const.SOLVED_BUTTON_DIMENSIONS);
		this.setFont(Const.SOLVED_MENU_BUTTON_FONT);
	}
}
