package dijkstra;

//Interface pour les sommets
/**
 * Vertex Interface
 * @author Romain Darous
 *
 */

public interface VertexInterface {
	
	/**
	 * Gives the coordinates of the vertex
	 * @return the coordinates of the vertex using a 2dimension table.
	 */
	public int[] getCoords();
	
	/**
	 * Gives the type of VertexInterface the vertex is.
	 * @return a label : 'W' for WallBoxes, 'E' for EmptyBoxes, 'D' for DepartureBoxes and 'A' for ArrivalBoxes.
	 */
	public String getLabel();
	
	/**
	 * Allows to modify the Label of a vertex. Will be used for the solution of the dijkstra algorithm.
	 * @param label the new value of the Label.
	 */
	public void setLabel(String label);
	
}