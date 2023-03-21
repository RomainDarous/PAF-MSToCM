package ui.cards.editCard.editButtonMenu;

import ui.Const;
import ui.abstractButtons.SaveButton;
import ui.view.MazeView;

/**
 * Class which extends the SaveButton class and simply modifies the size and the font of the button.
 * This is the saved button displayed when the maze is solved.
 * @author Romain Darous
 *
 */

public class SaveSolvedButtonE extends SaveButton {

	/**
	 * Constructor of the SaveSolvedButtonE class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public SaveSolvedButtonE(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.SOLVE_BUTTON_DIMENSIONS);
		setFont(Const.SOLVE_BUTTON_FONT);
	}

}
