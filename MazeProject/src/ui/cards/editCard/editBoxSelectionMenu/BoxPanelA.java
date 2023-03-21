package ui.cards.editCard.editBoxSelectionMenu;

import ui.drawingBoxes.DrawingBoxA;
import ui.view.MazeView;

/**
 * Selection panel of the arrival box which extends AbstractBoxPanel.
 * @author Romain Darous
 *
 */

public class BoxPanelA extends AbstractBoxPanel {

	/**
	 * Constructor of the BoxPanelA class.
	 * @param mazeView the view in which the panel is displayed.
	 */
	public BoxPanelA(MazeView mazeView) {
		super(mazeView, new DrawingBoxA(mazeView, -1, -1), "Arrivée");
	}
}
