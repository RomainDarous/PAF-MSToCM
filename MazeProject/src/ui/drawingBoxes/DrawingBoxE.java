package ui.drawingBoxes;

import ui.Const;
import ui.view.MazeView;

/**
 * Class which extends the AbstractDrawingBox class.
 * It modifies the color of the box in blue and allows creating an empty drawing box with just the maze view and its coordinates.
 * @author Romain Darous
 *
 */
public class DrawingBoxE extends AbstractDrawingBox {

	
	/**
	 * Constructor of the DrawingBoxE class.
	 * @param mazeView the view in which the bow will be drawn.
	 * @param x the line of the drawing box.
	 * @param y the column of the drawing box.
	 */
	public DrawingBoxE(MazeView mazeView, int x, int y) {
		super(mazeView, Const.EMPTY_COLOR, "", Const.E_LABEL, x, y);
	}
}
