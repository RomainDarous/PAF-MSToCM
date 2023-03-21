package ui.cards.homeCard.homeButtonMenu.LoadButton;

import ui.Const;
import ui.abstractButtons.LoadButton;
import ui.view.MazeView;

/**
 * Class which extends the LoadButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */
public class LoadButtonH extends LoadButton  {
	
	/**
	 * Constructor of the LoadButtonH class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public LoadButtonH(MazeView mazeView) {
		super(mazeView);
		setupLoadButtonH();
	}
	
	private final void setupLoadButtonH() {
		this.setPreferredSize(Const.HOME_BUTTON_DIMENSIONS);
		this.setFont(Const.HOME_BUTTON_FONT);
	}
}
