package ui.cards.editCard.editNCQMenu.NewButton;

import ui.Const;
import ui.abstractDimensionMenu.WidthInput;
import ui.view.MazeView;

/**
 * Class which extends the WidthInput class and simply modifies the size and the font of the text.
 * @author Romain Darous
 *
 */
public class WidthInputE extends WidthInput {
		
	/**
	 * Constructor of the WidthInputE class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public WidthInputE(MazeView mazeView) {
		super();
		this.setPreferredSize(Const.DIMENSION_TEXT_FIELD_DIMENSIONS);
		this.setFont(Const.HOME_DIMENSION_FONT);
	}
}