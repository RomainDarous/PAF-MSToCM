package ui.drawingBoxes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Const;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * Abstract class that gathers the main methods used to draw boxes. Sets up the major properties of the drawing.
 * Boxes will be represented by colored rectangles.
 * Arrival box : red rectangle.
 * Departure box : green rectangle.
 * Empty box : blue rectangle.
 * Wall box : brawn rectangle.
 * Solution box : yellow rectangle.
 * @author Romain Darous
 *
 */
public class AbstractDrawingBox extends JPanel implements MouseListener {
	
	private final Color backgroundColor;
	private final JLabel textLabel;
	private final String label;
	private final int[] coords = new int[2];
	
	private final MazeModel mazeModel;

	/**
	 * Constructor of the AbstractDrawingBox class.
	 * @param mazeView the view in which will be displayed the boxes.
	 * @param bg the color of the drawingBox.
	 * @param text the text displayed on the drawingBox.
	 * @param label the drawingBox label.
	 * @param x the line of the drawingBox.
	 * @param y the column of the drawingBox.
	 */
	public AbstractDrawingBox(MazeView mazeView, Color bg, String text, String label, int x, int y) {
		super();
		backgroundColor = bg;
		textLabel = new JLabel(text);
		this.label = label;
		coords[0] = x;
		coords[1] = y;
		mazeModel = mazeView.getMazeModel();
		
		setupAbstractBox();
	}
	
	/**
	 * Gets the label of the drawingBox.
	 * @return the label of the drawingBox.
	 */
	public final String getLabel() {
		return label;
	}
	
	private final void setupAbstractBox() {
		//UI
		setPreferredSize(Const.BOX_DIMENSIONS_0);
		textLabel.setFont(Const.BOX_FONT_0);
		textLabel.setForeground(Const.BOX_FONT_COLOR);
		
		//Layout manager
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Adding Components
		add(textLabel);
		addMouseListener(this);
	}
	
	/**
	 * Gets the coordinates of the drawingBox.
	 * @return the coordinates of the drawingBox.
	 */
	public final int[] getCoords() {
		return coords;
	}
	
	/**
	 * Gets the maze model of the application.
	 * @return the maze model of the application.
	 */
	public final MazeModel getMazeModel() {
		return mazeModel;
	}
	
	@Override
	public final void paintComponent (Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Const.BOX_FONT_COLOR);
	}
	

	@Override
	public final void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void mousePressed(MouseEvent e) {
		if(getCoords()[0] < 0 && getCoords()[1] < 0) {
			mazeModel.setSelectedBox(label);
		}
		else {
			getMazeModel().editBox(getCoords()[0], getCoords()[1]);
		}
	}

	@Override
	public final void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public final void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public final void notifyForUpdate() {
		
		int mazeHeight = mazeModel.getMazeHeight();
		int mazeWidth = mazeModel.getMazeWidth();
		
		if(mazeHeight > Const.MAX_HEIGHT_3 || mazeWidth > Const.MAX_WIDTH_3) {
			setPreferredSize(Const.BOX_DIMENSIONS_4);
			textLabel.setFont(Const.BOX_FONT_4);
		}
		
		else if(mazeHeight > Const.MAX_HEIGHT_2 || mazeWidth > Const.MAX_WIDTH_2) {
			setPreferredSize(Const.BOX_DIMENSIONS_3);
			textLabel.setFont(Const.BOX_FONT_3);
		}
		
		else if(mazeHeight > Const.MAX_HEIGHT_1 || mazeWidth > Const.MAX_WIDTH_1) {
			setPreferredSize(Const.BOX_DIMENSIONS_2);
			textLabel.setFont(Const.BOX_FONT_2);
		}
		
		else if (mazeHeight > Const.MAX_HEIGHT_0 || mazeWidth > Const.MAX_WIDTH_0) {
			setPreferredSize(Const.BOX_DIMENSIONS_1);
			textLabel.setFont(Const.BOX_FONT_1);
		}
		
		else{
			setPreferredSize(Const.BOX_DIMENSIONS_0);
			textLabel.setFont(Const.BOX_FONT_0);
		}
	}
}
