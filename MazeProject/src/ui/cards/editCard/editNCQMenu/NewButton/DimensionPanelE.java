package ui.cards.editCard.editNCQMenu.NewButton;

import ui.abstractDimensionMenu.DimensionPanel;
import ui.view.MazeView;

/**
 * Class which extends the DimensionPanel class and simply constructs it with objects of the home page.
 * @author Romain Darous
 *
 */
public class DimensionPanelE extends DimensionPanel {
	
	/**
	 * Constructor of the DimensionPanelE class.
	 * @param mazeView the view in which is displayed the panel.
	 */
	public DimensionPanelE(MazeView mazeView) {
		super(mazeView, new HeightLabelE(), new WidthLabelE(), new HeightInputE(mazeView), new WidthInputE(mazeView), new CancelButtonE(mazeView), new OkButtonE(mazeView));
	}
}
