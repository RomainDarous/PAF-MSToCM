package ui.drawingBoxes;

import ui.Const;
import ui.view.MazeView;

/**
 * Class which extends the AbstractDrawingBox class.
 * It modifies the color of the box in red and allows creating an arrival drawing box with just the maze view and its coordinates.
 * @author Romain Darous
 *
 */
public class DrawingBoxA extends AbstractDrawingBox{
	
	/**
	 * Constructor of the DrawingBoxA class.
	 * @param mazeView the view in which the bow will be drawn.
	 * @param x the line of the drawing box.
	 * @param y the column of the drawing box.
	 */
	public DrawingBoxA(MazeView mazeView, int x, int y) {
		super(mazeView, Const.ARRIVAL_COLOR, Const.A_LABEL, Const.A_LABEL, x, y);
	}

}
