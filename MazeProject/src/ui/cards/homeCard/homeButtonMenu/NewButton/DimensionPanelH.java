package ui.cards.homeCard.homeButtonMenu.NewButton;

import ui.abstractDimensionMenu.DimensionPanel;
import ui.view.MazeView;

/**
 * Class which extends the DimensionPanel class and simply constructs it with objects of the home page.
 * @author Romain Darous
 *
 */
public class DimensionPanelH extends DimensionPanel {
	
	/**
	 * Constructor of the DimensionPanelH class.
	 * @param mazeView the view in which is displayed the panel.
	 */
	public DimensionPanelH(MazeView mazeView) {
		super(mazeView, new HeightLabelH(), new WidthLabelH(), new HeightInputH(mazeView), new WidthInputH(mazeView), new CancelButtonH(mazeView), new OkButtonH(mazeView));
	}
}
