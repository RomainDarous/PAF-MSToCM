package dijkstra;
import java.util.HashSet;

/**
 * Class which implements the ASetInterface.
 * @author Romain Darous
 *
 */

public class ASet implements ASetInterface{
	
	private HashSet<VertexInterface> Set; //Set which stores all marked vertices.
	
	/**
	 * Constructor of the ASet class.
	 * Creates an empty set.
	 */
	public ASet() {
		this.Set = new HashSet<VertexInterface>();
	}
	
	@Override
	public final void add(VertexInterface pivot) {
		this.Set.add(pivot);
	}
	
	@Override
	public final boolean inA(VertexInterface sommet) {
		return this.Set.contains(sommet);
	}
}
