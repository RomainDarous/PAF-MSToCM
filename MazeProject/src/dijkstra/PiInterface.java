package dijkstra;

/**
 * Pi Interface. Pi is the minimum distance between a vertex and the departure vertex of the graph.
 * @author Romain Darous
 *
 */

public interface PiInterface {
	
	/**
	 * Allows to modify the Pi value of a vertex.
	 * @param pivot the vertex you want to modify the Pi value.
	 * @param pi the newest Pi value.
	 */
	public void setPi(VertexInterface pivot, Integer pi);
	
	/**
	 * Allows to get the current Pi value of a vertex.
	 * @param pivot the vertex you want its Pi value.
	 * @return the Pi value of the vertex.
	 */
	public int getPi(VertexInterface pivot);

	/**
	 * Gives the vertex which has the current minimum distance with the departure vertex, excluding vertices in the A set.
	 * @param A set of vertices that aren't considered.
	 * @return the relevant vertex.
	 */
	public VertexInterface getMinPi(ASetInterface A);
}
