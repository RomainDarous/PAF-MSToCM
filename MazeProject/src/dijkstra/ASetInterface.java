package dijkstra;

/**
 * Interface for the A set which stores all marked vertices.
 * @author Romain Darous
 *
 */

public interface ASetInterface {
	
	/**
	 * Adds a vertex to the tree diagram A.
	 * @param pivot vertex to add.
	 */
	public void add(VertexInterface pivot);
	
	/**
	 * Checks if a vertex is in A.
	 * @param sommet the relevant vertex.
	 * @return 1 if the vertex is in A, 0 if it's not.
	 */
	public boolean inA(VertexInterface sommet);
}
