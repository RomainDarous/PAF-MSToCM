package ui.model;

import dijkstra.VertexInterface;

/**
 * The MazeModelInterface declares all the methods used by the maze model.
 * @author Romain Darous
 *
 */
public interface MazeModelInterface {
	
	/*
	 * ************************************
	 * MAZE PARAMETERS GETTERS AND SETTERS.
	 * ************************************
	 */

	/**
	 * Gets the current selected box.
	 * @return the current selected box.
	 */
	public String[] getSelectedBox();

	/**
	 * Sets a new selected box.
	 * @param label label of the new selected box.
	 */
	public void setSelectedBox(String label);

	/**
	 * Gets the maze height.
	 * @return the maze height.
	 */
	public int getMazeHeight();

	
	/**
	 * Gets the maze width.
	 * @return the maze width.
	 */
	public int getMazeWidth();

	/**
	 * Gets the the box table of the maze.
	 * @return the box table of the maze (VertexInterface[][] object).
	 */
	public VertexInterface[][] getBoxes();

	/**
	 * Sets the height of a created maze.
	 * @param createHeight the height of a created maze.
	 */
	public void setCreatingMazeHeight(int createHeight);

	/**
	 * Sets the width of a created maze.
	 * @param createWidth the width of a created maze.
	 */
	public void setCreatingMazeWidth(int createWidth);

	/**
	 * Sets the path of the maze text file.
	 * @param mazePath the path.
	 */
	public void setMazePath(String mazePath);

	/**
	 * Sets the path used to save the maze.
	 * @param mazeSavePath the path.
	 */
	public void setMazeSavePath(String mazeSavePath);

	/**
	 * Gets the message that tells whether the maze has been successfully initialized / solved or not.
	 * @return an ok message if the maze has been successfully initialized / solved, the appropriate error message if this is note the case.
	 */
	public String getInitSolvableMessage();

	/**
	 * Sets the message that tells whether the maze has been successfully initialized / solved or not
	 * @param initSolvableMessage the new message.
	 */
	public void setInitSolvableMessage(String initSolvableMessage);

	
	/*
	 * ***********************
	 * SWITCHING PAGE METHODS.
	 * **********************
	 */
	
	/**
	 * Gets the current page displayed by the application. It can be the home page, the edit page or the solve page.
	 * @return the current page displayed.
	 */
	public int[] getPage();

	/**
	 * Sets the page of application.
	 * @param page the new page. This is an integer but pages are defined par constants defined in the Const class to make it easier to understand.
	 */
	public void setPage(int page);

	
	
	/*
	 * ****************************
	 * SWITCHING MAZE MODE METHODS.
	 * ****************************
	 */
	
	/**
	 * Gets the current maze mode. All modes are detailed in the Constant class.
	 * @return an integer which corresponds to a maze mode.
	 */
	public int[] getMazeMode();

	/**
	 * Sets the maze mode.
	 * @param mazeMode the new mode. This is an integer but modes are defined par constants defined in the Const class to make it easier to understand.
	 */
	public void setMazeMode(int mazeMode);

	
	/*
	 * *******************
	 * EDITING BOX METHOS.
	 * ********************
	 */
	
	/**
	 * Allows to modify a box of the maze.
	 * @param x the line of the box.
	 * @param y the column of the box.
	 */
	public void editBox(int x, int y);

	
	
	/*
	 * *************************************
	 * METHODS RELATIVE TO THE OBSERVER.
	 * *************************************
	 */
	
	/**
	 * Notifies the maze view that the model has changed.
	 */
	public void stateChanges();

	
	
	
}
