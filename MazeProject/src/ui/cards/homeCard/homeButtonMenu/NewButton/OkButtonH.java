package ui.cards.homeCard.homeButtonMenu.NewButton;

import ui.Const;
import ui.abstractButtons.OkButton;
import ui.view.MazeView;


/**
 * Class which extends the OkButton class and simply modifies the size and the font of the button.
 * @author Romain Darous
 *
 */
public class OkButtonH extends OkButton{

	/**
	 * Constructor of the OkButtonH class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public OkButtonH(MazeView mazeView) {
		super(mazeView);
		this.setPreferredSize(Const.OK_CANCEL_HOME_BUTTON_DIMENSIONS);
		setFont(Const.OK_CANCEL_HOME_BUTTON_FONT);
	}
	
	

}
