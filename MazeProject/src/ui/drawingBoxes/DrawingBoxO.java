package ui.drawingBoxes;

import ui.Const;
import ui.view.MazeView;

/**
 * Class which extends the AbstractDrawingBox class.
 * It modifies the color of the box in yellow and allows creating a solution drawing box with just the maze view and its coordinates.
 * @author Romain Darous
 *
 */
public class DrawingBoxO extends AbstractDrawingBox {

	/**
	 * Constructor of the DrawingBoxO class.
	 * @param mazeView the view in which the bow will be drawn.
	 * @param x the line of the drawing box.
	 * @param y the column of the drawing box.
	 */
	public DrawingBoxO(MazeView mazeView, int x, int y) {
		super(mazeView, Const.SOLVE_COLOR, "", Const.O_LABEL,x , y);
	}
}
