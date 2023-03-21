package ui.cards.editCard.editBoxSelectionMenu;


import ui.drawingBoxes.DrawingBoxW;
import ui.view.MazeView;

/**
 * Selection panel of the wall box which extends AbstractBoxPanel.
 * @author Romain Darous
 *
 */
public class BoxPanelW extends AbstractBoxPanel {

	/**
	 * Constructor of the BoxPanelW class.
	 * @param mazeView the view in which the panel is displayed.
	 */
	public BoxPanelW(MazeView mazeView) {
		super(mazeView, new DrawingBoxW(mazeView, -1, -1), "Mur");
	}
}
