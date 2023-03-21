package ui.cards.homeCard.homeButtonMenu.NewButton;

import ui.Const;
import ui.abstractButtons.NewButton;
import ui.view.MazeView;

/**
 * Class which extends the NewButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */
public class NewButtonH extends NewButton {
	
	/**
	 * Constructor of the NewButtonH class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public NewButtonH(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.HOME_BUTTON_DIMENSIONS);
		this.setFont(Const.HOME_BUTTON_FONT);
	}
}
