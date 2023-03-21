package ui.model;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.VertexInterface;
import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.Maze;
import maze.WBox;
import ui.Const;
import ui.view.MazeView;

/**
 * Model of the app. It implements the MazeModelInterface.
 * @author Romain Darous
 *
 */
public class MazeModel implements MazeModelInterface {
	
	private final ChangeListener listener;
	
	// Maze parameters.
	private Maze maze;
	private int creatingMazeHeight = 0;
	private int creatingMazeWidth = 0;
	
	private String mazePath = Const.VOID_LABEL;
	private String mazeSavePath = Const.VOID_LABEL;
	private String initSolvableMessage = Const.VOID_LABEL;
		
	private String[] selectedBox = {Const.VOID_LABEL, Const.SELECTION_MAINTAINED};

	
	//Page parameter. page[0] is the current page. page[1] is the oldest page. page[2] tells if the page has been modified or not.
	private int[] page = {Const.HOME_PAGE, Const.HOME_PAGE, Const.MAINTAINED};
	
	//Maze mode parameter. mazeMode[0] is the current page. mazeMode[1] is the oldest page. mazeMode[2] tells if the page has been modified or not.
	private int[] mazeMode = {Const.NONE, Const.NONE, Const.MAINTAINED};
	
	/**
	 * Constructor of the maze model.
	 * @param view The Observer.
	 */
	public MazeModel(MazeView view) {
		this.listener = view;	
		this.maze = new Maze();
		
	}
	
	/*
	 * ************************************
	 * MAZE PARAMETERS GETTERS AND SETTERS.
	 * ************************************
	 */
	
	@Override
	public final String[] getSelectedBox() {
		return selectedBox;
	}
	
	@Override
	public final void setSelectedBox(String label) {
		if(!selectedBox[0].equals(label)) {
			selectedBox[0] = label;
			selectedBox[1] = Const.SELECTION_UPDATED;
			stateChanges();
			selectedBox[1] = Const.SELECTION_MAINTAINED;
		}
	}
	
	@Override
	public final int getMazeHeight() {
		return maze.getMazeHeight();
	}
	
	@Override
	public final int getMazeWidth() {
		return maze.getMazeWidth();
	}
	
	@Override
	public final VertexInterface[][] getBoxes() {
		return maze.getBoxes();
	}
	
	@Override
	public final void setCreatingMazeHeight(int createHeight) {
		this.creatingMazeHeight = createHeight;
	}
	
	@Override
	public final void setCreatingMazeWidth(int createWidth) {
		this.creatingMazeWidth = createWidth;
	}
	
	@Override
	public final void setMazePath(String mazePath) {
		if(!mazePath.equals(this.mazePath)) {
			this.mazePath = mazePath;
		}
	}
	
	@Override
	public final void setMazeSavePath(String mazeSavePath) {
		this.mazeSavePath = mazeSavePath;
	}
	
	@Override
	public final String getInitSolvableMessage() {
		return initSolvableMessage;
	}
	
	@Override
	public final void setInitSolvableMessage(String initSolvableMessage) {
		this.initSolvableMessage = initSolvableMessage;
	}
	
	
	/*
	 * ***********************
	 * SWITCHING PAGE METHODS.
	 * **********************
	 */
	
	@Override
	public final int[] getPage() {
		return page;
	}
	
	@Override
	public final void setPage(int page) {
		if (page != this.page[0]) {
			int oldestPage = this.page[1];
			int oldPage = this.page[0];
		
			this.page[0] = page;
			this.page[1] = oldPage;
			this.page[2] = Const.UPDATED;
			
			stateChanges();
			
			this.page[2] = Const.MAINTAINED;
			
			switch(page) {
				case Const.ENABLE_CREATE_PAGE :
					this.page[0] = oldPage;
					this.page[1] = oldestPage;
					break;
				case Const.DISABLE_CREATE_PAGE :
					this.page[0] = oldPage;
					this.page[1] = oldestPage;
					break;
				case Const.EDIT_PAGE :
					setSelectedBox(Const.W_LABEL);
					break;
			}
		}
	}
	
	
	/*
	 * ****************************
	 * SWITCHING MAZE MODE METHODS.
	 * ****************************
	 */
	
	@Override
	public final int[] getMazeMode() {
		return mazeMode;
	}
	
	@Override
	public final void setMazeMode(int mazeMode) {
		if (mazeMode != this.mazeMode[0]) {
			int oldestMode = this.mazeMode[1];
			int oldMode = this.mazeMode[0];
			
			this.mazeMode[0] = mazeMode;
			this.mazeMode[1] = oldMode;
			this.mazeMode[2] = Const.UPDATED;
			switch(mazeMode) {
			
				case Const.NONE :
					this.mazeMode[2] = Const.MAINTAINED;
					break;
			
				case Const.CREATING : 
					createMaze();
					this.mazeMode[2] = Const.MAINTAINED;
					break;
				
				case Const.LOADING : 
					loadMaze(oldMode, oldestMode);
					this.mazeMode[2] = Const.MAINTAINED;
					break;
				
				case Const.EDITING :
					this.mazeMode[2] = Const.MAINTAINED;
					break;
								
				case Const.SOLVING :
					solveMaze();
					this.mazeMode[0] = oldMode;
					this.mazeMode[1] = oldestMode;
					this.mazeMode[2] = Const.MAINTAINED;
					break;
					
				case Const.MODIFYING :
					modifyMaze();
					this.mazeMode[2] = Const.MAINTAINED;
					break;
				
				case Const.SAVING : 
					saveMaze(oldMode, oldestMode);
					this.mazeMode[2] = Const.MAINTAINED;
					break;
				
				default : 
					stateChanges();
					this.mazeMode[2] = Const.MAINTAINED;
					break;
			}
		}
	}
	
	private final void createMaze() {
		//Getting maze dimensions.
		stateChanges();
		mazeMode[2] = Const.MAINTAINED;
		
		if (initSolvableMessage.equals(Const.NO_INPUT) || initSolvableMessage.equals(Const.NOT_INTEGER_INPUT)) {
			setMazeMode(Const.INIT_ERROR);
			initSolvableMessage = Const.VOID_LABEL;
		}
		
		else if (creatingMazeHeight < 0 || creatingMazeWidth < 0) {
			initSolvableMessage = Const.NEGATIVE_INPUT;
			setMazeMode(Const.INIT_ERROR);
		}
		
		//Checking that there is at least two boxes.
		else if(creatingMazeHeight == 1 && creatingMazeWidth == 1 || creatingMazeHeight == 0 || creatingMazeWidth == 0) {
			initSolvableMessage = Const.ONE_CASE_CREATION;
			setMazeMode(Const.INIT_ERROR);
		}
		
		else if (creatingMazeHeight > Const.MAX_HEIGHT_4 || creatingMazeWidth > Const.MAX_WIDTH_4) {
			initSolvableMessage = Const.TOO_MANY_CASES;
			setMazeMode(Const.INIT_ERROR);
		}
		
		else {
			// Creating a box table using default position of blocks.
			VertexInterface[][] boxes = new VertexInterface[creatingMazeHeight][creatingMazeWidth];
			
			for (int m = 0 ; m < creatingMazeHeight ; m++) {
				for (int n = 0 ; n < creatingMazeWidth ; n++) {
					boxes[m][n] = new EBox(m, n);
				}
			}
			
			//Adding departure and arrival boxes.
			boxes[0][0] = new DBox(0, 0);
			if (creatingMazeWidth > 1) boxes[0][1] = new ABox(0, 1);
			else boxes[1][0] = new ABox(1, 0);
			
			//Saving the box table to a text file.
			maze.setBoxes(boxes);
			mazePath = Const.SAVE_TEMP_MAZE_PATH;
			maze.saveToTextFile(mazePath);
			maze.initFromTextFile(mazePath);
			
			//Drawing the maze.
			setMazeMode(Const.DRAWING);
			
			//Next maze page : editing page.
			setPage(Const.EDIT_PAGE);
		}
		

	}
	
	private final void loadMaze(int oldMode, int oldestMode) {
		//Open the MazeFileChooser. Getting the maze path.
		stateChanges();
		mazeMode[2] = Const.MAINTAINED;
		
		if(!mazePath.equals(Const.VOID_LABEL)) {
			
			initSolvableMessage = maze.initFromTextFile(mazePath);
			
			if(!initSolvableMessage.equals(Const.VOID_LABEL)) {
				setMazeMode(Const.INIT_ERROR);
			}
			
			else {
				//Drawing the maze.
				setMazeMode(Const.DRAWING);
				
				//Next maze page : editing page.
				setPage(Const.EDIT_PAGE);
			}
			
		}
		
		else {
			this.mazeMode[0] = oldMode;
			this.mazeMode[1] = oldestMode;
		}
	}

	
	private final void solveMaze() {
		
		maze.saveToTextFile(Const.SAVE_TEMP_MAZE_PATH);
		mazePath = Const.SAVE_TEMP_MAZE_PATH;
		boolean solvable = maze.solveMaze();
		
		if (solvable) {
			//Drawing the maze.
			setMazeMode(Const.DRAWING);
			
			//Displaying the result.
			setPage(Const.SOLVE_PAGE);
		} else {
			initSolvableMessage = Const.UNSOLVABLE_MAZE;
			setMazeMode(Const.INIT_ERROR);
		}

	}
	
	private final void modifyMaze() {
		maze.initFromTextFile(mazePath);
		
		//Drawing maze
		setMazeMode(Const.DRAWING);
		
		//Next maze page : editing page.
		setPage(Const.EDIT_PAGE);
	}
	
	private final void saveMaze(int oldMode, int oldestMode) {
		//Open the MazeFileChooser. Getting the maze path.
		stateChanges();
		
		if(!mazeSavePath.equals(Const.VOID_LABEL)) {
			maze.saveToTextFile(mazeSavePath);
			mazeSavePath = Const.VOID_LABEL;
		}
		this.mazeMode[0] = oldMode;
		this.mazeMode[1] = oldestMode;
	}
	
	/*
	 * *******************
	 * EDITING BOX METHOS.
	 * ********************
	 */
	
	@Override
	public final void editBox(int x, int y) {
		
		String clickedLabel = maze.getBox(x, y).getLabel();		
		
		if (page[0] == Const.EDIT_PAGE && !selectedBox[0].equals(clickedLabel)) {
			
			if (getSelectedBox()[0].equals(Const.A_LABEL)) {
				int[] oldABoxCoords = maze.getArrivee().getCoords();
				
				VertexInterface newBox = newBox(clickedLabel, oldABoxCoords[0], oldABoxCoords[1]);
				
				maze.setBox(oldABoxCoords[0], oldABoxCoords[1], newBox);
				
				if(clickedLabel.equals(Const.D_LABEL)) {
					maze.setDepart(newBox);
				}
				
				VertexInterface arrivalBox = new ABox(x, y);
				
				maze.setBox(x, y, arrivalBox);
				maze.setArrivee(arrivalBox);
			}
			
			else if (getSelectedBox()[0].equals(Const.D_LABEL)) {
				
				int[] oldDBoxCoords = maze.getDepart().getCoords();
				
				VertexInterface newBox = newBox(clickedLabel, oldDBoxCoords[0], oldDBoxCoords[1]);
				
				maze.setBox(oldDBoxCoords[0], oldDBoxCoords[1], newBox);
				
				if(clickedLabel.equals(Const.A_LABEL)) {
					maze.setArrivee(newBox);
				}
				
				VertexInterface departureBox = new DBox(x, y);
				
				maze.setBox(x, y, departureBox);
				maze.setDepart(departureBox);
			}
		
			else if (getSelectedBox()[0].equals(Const.E_LABEL) && !clickedLabel.equals(Const.A_LABEL) && !clickedLabel.equals(Const.D_LABEL)) {
				maze.setBox(x, y, new EBox(x, y));
			}
			else if (getSelectedBox()[0].equals(Const.W_LABEL) && !maze.getBox(x, y).getLabel().equals(Const.A_LABEL) && !maze.getBox(x, y).getLabel().equals(Const.D_LABEL)) {
				maze.setBox(x, y, new WBox(x, y));
			}
			
			//Drawing maze
			setMazeMode(Const.EDITING);
			setMazeMode(Const.DRAWING);
			
		}
	}
	
	private final VertexInterface newBox(String label, int x, int y) {
		VertexInterface box;
		
		if(label.equals(Const.D_LABEL)) {
			box = new DBox(x, y);
		}
		
		else if (label.equals(Const.A_LABEL)) {
			box = new ABox(x, y);
		}
		
		else if (label.equals(Const.E_LABEL)) {
			box = new EBox(x, y);
		}
		else {
			box = new WBox(x, y);
		}
		
		return box;
	}
	
	
	/*
	 * *************************************
	 * METHODS RELATIVE TO THE OBSERVER.
	 * *************************************
	 */

	@Override
	public final void stateChanges() {
		listener.stateChanged( new ChangeEvent(this) );
	}


}
