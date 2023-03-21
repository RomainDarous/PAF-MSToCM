package ui.cards.editCard.editNCQMenu.NewButton;

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
	 * @param mazeView the view in which is displayed the button.
	 */
	public CancelButtonE(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.OK_CANCEL_HOME_BUTTON_DIMENSIONS);
		setFont(Const.OK_CANCEL_HOME_BUTTON_FONT);
	}

}
