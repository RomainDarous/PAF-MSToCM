package ui.cards.editCard.editBoxSelectionMenu;


import ui.drawingBoxes.DrawingBoxE;
import ui.view.MazeView;

/**
 * Selection panel of the empty box which extends AbstractBoxPanel.
 * @author Romain Darous
 *
 */
public class BoxPanelE extends AbstractBoxPanel {
	
	/**
	 * Constructor of the BoxPanelE class.
	 * @param mazeView the view in which the panel is displayed.
	 */
	public BoxPanelE(MazeView mazeView) {
		super(mazeView, new DrawingBoxE(mazeView, -1, -1), "Vide");
	}

}
