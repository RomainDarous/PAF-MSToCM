package ui.cards.editCard.editBoxSelectionMenu;


import ui.drawingBoxes.DrawingBoxD;
import ui.view.MazeView;

/**
 * Selection panel of the departure box which extends AbstractBoxPanel.
 * @author Romain Darous
 *
 */
public class BoxPanelD extends AbstractBoxPanel {

	/**
	 * Constructor of the BoxPanelD class.
	 * @param mazeView the view in which the panel is displayed.
	 */
	public BoxPanelD(MazeView mazeView) {
		super(mazeView, new DrawingBoxD(mazeView, -1, -1), "Départ");
	}
}
