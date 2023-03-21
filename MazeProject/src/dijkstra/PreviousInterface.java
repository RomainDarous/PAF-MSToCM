package dijkstra;

/**
 * Previous Interface.
 * @author Romain Darous
 *
 */

public interface PreviousInterface {
	
	/**
	 * Allows to modify the Previous vertex of another vertex.
	 * @param son the vertex you want to modify the Previous vertex.
	 * @param father the newest Previous vertex of the vertex "son".
	 */
	public void setPrevious(VertexInterface son, VertexInterface father);
	
	/**
	 * Gives the current previous vertex of a vertex.
	 * @param son the vertex you want to get the current Previous vertex.
	 * @return the current Previous vertex of the father son.
	 */
	public VertexInterface getPrevious(VertexInterface son);
	
}