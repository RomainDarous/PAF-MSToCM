package ui.drawingBoxes;

import ui.Const;
import ui.view.MazeView;

/**
 * Class which extends the AbstractDrawingBox class.
 * It modifies the color of the box in green and allows creating a departure drawing box with just the maze view and its coordinates.
 * @author Romain Darous
 *
 */
public class DrawingBoxD extends AbstractDrawingBox {
	
	/**
	 * Constructor of the DrawingBoxD class.
	 * @param mazeView the view in which the bow will be drawn.
	 * @param x the line of the drawing box.
	 * @param y the column of the drawing box.
	 */
	public DrawingBoxD(MazeView mazeView, int x, int y) {
		super(mazeView, Const.DEPARTURE_COLOR, Const.D_LABEL, Const.D_LABEL, x, y);
	}
}
