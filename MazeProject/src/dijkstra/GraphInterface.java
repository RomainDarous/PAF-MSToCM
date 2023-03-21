package dijkstra;

import java.util.ArrayList;

/**
 * Graph Interface.
 * @author Romain Darous
 *
 */

public interface GraphInterface {
	/**
	 * Allows to get all vertices of the graph.
	 * @return an ArrayList which contains all vertices of the graph.
	 */
	public ArrayList<VertexInterface> getAllVertices();
	
	/**
	 * Gives all successors of a graph's vertex.
	 * @param vertex the vertex you want the successors.
	 * @return an ArrayList which contains all successors of the vertex.
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
	
	/**
	 * Give the weight between two vertices of the graph.
	 * @param src the source vertex.
	 * @param dst the destination vertex.
	 * @return 1 if the vertices are successors of each other, 0 otherwise.
	 */
	public int getWeight(VertexInterface src , VertexInterface dst);
	
	/**
	 * Gives the number of vertices of the graph.
	 * @return the number of vertices of the graph.
	 */
	public int getVerticeNumber();
}
	
