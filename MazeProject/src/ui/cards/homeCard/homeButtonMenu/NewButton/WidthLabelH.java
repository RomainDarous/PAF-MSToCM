package ui.cards.homeCard.homeButtonMenu.NewButton;

import ui.Const;
import ui.abstractDimensionMenu.DimensionLabel;

/**
 * Class which extends the DimensionLabel class and simply modifies the font and sets the text to display.
 * @author Romain Darous
 *
 */
public class WidthLabelH extends DimensionLabel {
	
	/**
	 * Constructor of the WidthLabelH class.
	 */
	public WidthLabelH() {
		super(Const.WIDTH_LABEL);
		setFont(Const.HOME_DIMENSION_FONT);
	}

}
