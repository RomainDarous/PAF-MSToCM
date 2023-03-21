package ui.cards.homeCard.homeButtonMenu.NewButton;

import ui.Const;
import ui.abstractDimensionMenu.DimensionLabel;

/**
 * Class which extends the DimensionLabel class and simply modifies the font and sets the text to display.
 * @author Romain Darous
 *
 */
public class HeightLabelH extends DimensionLabel {
	
	/**
	 * Constructor of the HeightLabelH class.
	 */
	public HeightLabelH() {
		super(Const.HEIGHT_LABEL);
		setFont(Const.HOME_DIMENSION_FONT);
	}
}
