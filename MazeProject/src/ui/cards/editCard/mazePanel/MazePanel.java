package ui.cards.editCard.mazePanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import dijkstra.VertexInterface;
import ui.Const;
import ui.drawingBoxes.AbstractDrawingBox;
import ui.drawingBoxes.DrawingBoxA;
import ui.drawingBoxes.DrawingBoxAO;
import ui.drawingBoxes.DrawingBoxD;
import ui.drawingBoxes.DrawingBoxDO;
import ui.drawingBoxes.DrawingBoxE;
import ui.drawingBoxes.DrawingBoxO;
import ui.drawingBoxes.DrawingBoxW;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * This class allows to create the panel in which will be displayed the maze. Boxes are colored rectangles.
 * @author Romain Darous
 *
 */
public class MazePanel extends JPanel {

	private final MazeModel mazeModel;
	private final MazeView mazeView;
	
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final GridBagLayout gbl = new GridBagLayout();
	
	
	private VertexInterface[][] boxes;
	
	/**
	 * Constructor of the MazePanel class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public MazePanel(MazeView mazeView) {
		super();
		this.mazeModel = mazeView.getMazeModel();
		this.mazeView = mazeView;
		this.setOpaque(false);
	}
	
	
	private final void drawingMaze() {	
		initDrawingMaze();
		
		//Adding Components
		if (mazeModel.getMazeMode()[1] == Const.CREATING || mazeModel.getMazeMode()[1] == Const.MODIFYING || mazeModel.getMazeMode()[1] == Const.LOADING) {
			drawAllMaze();
		}
		
		else if (mazeModel.getMazeMode()[1] == Const.SOLVING) {
			drawSolvedMaze();
		}
		
		else if (mazeModel.getMazeMode()[1] == Const.EDITING) {
			drawBoxMaze();
		}
		
		setBoxDimensions();
	}
	
	private final void initDrawingMaze() {
		boxes = mazeModel.getBoxes();
		
		//Layout manager
		setLayout(gbl);
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(2, 2, 2, 2);
		
	}
	
	private final void drawAllMaze() {
		removeAll();
		
		for (VertexInterface[] vertices : boxes) {
			for (VertexInterface vertex : vertices) {
				String label = vertex.getLabel();
				int[] coords = vertex.getCoords();
				
				gbc.gridy = coords[0];
				gbc.gridx = coords[1];
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				
				if (label.equals(Const.A_LABEL)) add(new DrawingBoxA(mazeView, coords[0], coords[1]), gbc);
				else if (label.equals(Const.D_LABEL)) add(new DrawingBoxD(mazeView, coords[0], coords[1]), gbc);
				else if (label.equals(Const.E_LABEL)) add(new DrawingBoxE(mazeView, coords[0], coords[1]), gbc);
				else add(new DrawingBoxW(mazeView, coords[0], coords[1]), gbc);
			}
		}
	}
	
	private final void drawSolvedMaze() {
		for (VertexInterface[] vertices : boxes) {
			for (VertexInterface vertex : vertices) {
				String label = vertex.getLabel();
				int[] coords = vertex.getCoords();
				
				gbc.gridy = coords[0];
				gbc.gridx = coords[1];
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				
				if (label.equals(Const.A_LABEL)) {
					remove(findBox(coords));
					add(new DrawingBoxAO(mazeView, coords[0], coords[1]), gbc);
				}
				else if (label.equals(Const.D_LABEL)) {
					remove(findBox(coords));
					add(new DrawingBoxDO(mazeView, coords[0], coords[1]), gbc);
				}
				else if (label.equals(Const.O_LABEL)) {
					remove(findBox(coords));
					add(new DrawingBoxO(mazeView,coords[0], coords[1]), gbc);
				}	
			}
		}
	}
	
	private final void drawBoxMaze() {
		boxes = mazeModel.getBoxes();
		
		String label;
		int[] coords;
		AbstractDrawingBox drawingBox;
		
		
		for(Component cpt : getComponents()) {
			drawingBox = (AbstractDrawingBox) cpt;
			label = drawingBox.getLabel();
			coords = drawingBox.getCoords();
			GridBagConstraints gbcTemp = gbl.getConstraints(cpt);
			
			if(!boxes[coords[0]][coords[1]].getLabel().equals(label)) {
				remove(findBox(coords));
				
				if(boxes[coords[0]][coords[1]].getLabel().equals(Const.A_LABEL)) {
					add(new DrawingBoxA(mazeView, coords[0], coords[1]), gbcTemp);
				}
				else if (boxes[coords[0]][coords[1]].getLabel().equals(Const.D_LABEL)) {
					add(new DrawingBoxD(mazeView, coords[0], coords[1]), gbcTemp);
				}
				else if (boxes[coords[0]][coords[1]].getLabel().equals(Const.E_LABEL)) {
					add(new DrawingBoxE(mazeView, coords[0], coords[1]), gbcTemp);
				}
				else {
					add(new DrawingBoxW(mazeView, coords[0], coords[1]), gbcTemp);
				}
			}
		}
	}
	
	private final AbstractDrawingBox findBox(int[] coords) {
		for(Component cpt : getComponents()) {
			GridBagConstraints gbcTemp = gbl.getConstraints(cpt);
			if(coords[0] == gbcTemp.gridy && coords[1] == gbcTemp.gridx) {
				return ((AbstractDrawingBox) cpt);
			}
		}
		return null;
	}
	
	private final void setBoxDimensions() {
		
		for (Component cpt : getComponents()) {
			AbstractDrawingBox drawingBox = (AbstractDrawingBox) cpt;
			drawingBox.notifyForUpdate();
		}
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public final void notifyForUpdate() {
		if (mazeModel.getMazeMode()[0] == Const.DRAWING && mazeModel.getMazeMode()[2] == Const.UPDATED) {
			drawingMaze();
			updateUI();
		}
	}
	
}
