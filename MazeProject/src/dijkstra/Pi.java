package dijkstra;
import java.util.Hashtable;

/**
 * Class which implements PiInterface.
 * @author Romain Darous
 *
 */
public class Pi implements PiInterface {

	private Hashtable<VertexInterface , Integer> piTable; //hash table : for each vertex of the graph is linked with its current weight.
	
	/**
	 * Constructor of the Pi class.
	 * Initializes an empty hash table : each "key" vertex is linked with its Pi value at the current step of the dijkstra algorithm.
	 */
	public Pi() {
		piTable = new Hashtable<VertexInterface , Integer>();
	}
	
	@Override
	public final void setPi(VertexInterface pivot , Integer pi) {
		piTable.put(pivot, pi);
	}
	
	@Override
	public final int getPi(VertexInterface pivot) {
		return piTable.get(pivot);
	}
	
	@Override
	public final VertexInterface getMinPi(ASetInterface a) {
		Integer minPi = Integer.MAX_VALUE;
		VertexInterface minVertex = null;
		
		for (VertexInterface vertex : this.piTable.keySet()) {
			if (!a.inA(vertex) && this.piTable.get(vertex) < minPi) {
				minVertex = vertex;
				minPi = this.piTable.get(vertex);
			}
		}
		return minVertex;
	}
}
