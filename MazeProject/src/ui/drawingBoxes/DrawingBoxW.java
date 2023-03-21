package ui.drawingBoxes;

import ui.Const;
import ui.view.MazeView;

/**
 * Class which extends the AbstractDrawingBox class.
 * It modifies the color of the box in brawn and allows creating a wall drawing box with just the maze view and its coordinates.
 * @author Romain Darous
 *
 */
public class DrawingBoxW extends AbstractDrawingBox {
	
	/**
	 * Constructor of the DrawingBoxW class.
	 * @param mazeView the view in which the bow will be drawn.
	 * @param x the line of the drawing box.
	 * @param y the column of the drawing box.
	 */
	public DrawingBoxW(MazeView mazeView, int x, int y){
		super(mazeView, Const.WALL_COLOR, "", Const.W_LABEL,x , y);
	}
}
