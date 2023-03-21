package ui.cards.editCard.editNCQMenu.NewButton;

import ui.Const;
import ui.abstractDimensionMenu.DimensionLabel;

/**
 * Class which extends the DimensionLabel class and simply modifies the font and sets the text to display.
 * @author Romain Darous
 *
 */

public class HeightLabelE extends DimensionLabel {
		
	/**
	 * Constructor of the HeightLabelE class.
	 */
	public HeightLabelE() {
		super(Const.HEIGHT_LABEL);
		setFont(Const.HOME_DIMENSION_FONT);
	}
}
