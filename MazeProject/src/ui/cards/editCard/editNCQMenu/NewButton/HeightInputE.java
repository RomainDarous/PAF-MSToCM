package ui.cards.editCard.editNCQMenu.NewButton;
import ui.Const;
import ui.abstractDimensionMenu.HeightInput;
import ui.view.MazeView;

/**
 * Class which extends the HeightInput class and simply modifies the size and the font of the text field.
 * @author Romain Darous
 *
 */
public class HeightInputE extends HeightInput {
		
	/**
	 * Constructor of the HeightInputE class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public HeightInputE(MazeView mazeView) {
		super();
		this.setPreferredSize(Const.DIMENSION_TEXT_FIELD_DIMENSIONS);
		this.setFont(Const.HOME_DIMENSION_FONT);
	}
}
