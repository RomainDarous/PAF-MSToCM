package ui.drawingBoxes;

import ui.Const;
import ui.view.MazeView;

/**
 * Class which extends the AbstractDrawingBox class.
 * It modifies the color of the box in yellow and allows creating a departure box with just the maze view and its coordinates.
 * This departure box is the one that you see in the solution.
 * @author Romain Darous
 *
 */
public class DrawingBoxDO extends AbstractDrawingBox {

	/**
	 * Constructor of the DrawingBoxDO class.
	 * @param mazeView the view in which the bow will be drawn.
	 * @param x the line of the drawing box.
	 * @param y the column of the drawing box.
	 */
	public DrawingBoxDO(MazeView mazeView, int x, int y) {
		super(mazeView, Const.SOLVE_COLOR, Const.D_LABEL, Const.D_LABEL, x, y);
	}
}
