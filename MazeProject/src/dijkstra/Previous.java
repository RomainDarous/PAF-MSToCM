package dijkstra;
import java.util.Hashtable;

/**
 * Class which implements PreviousInterface.
 * @author Romain Darous
 *
 */

public class Previous implements PreviousInterface {
	
	private Hashtable<VertexInterface, VertexInterface> previousTable; //hash table : each "key" vertex is linked with another Vertex : its father.
	
	/**
	 * Constructor of the Previous class.
	 * Initializes a new empty hash table : each "key" vertex is linked with another Vertex : its father.
	 */
	public Previous() {
		previousTable = new Hashtable<VertexInterface,VertexInterface>();
	}
	
	@Override
	public final void setPrevious(VertexInterface son, VertexInterface father) {
		previousTable.put(son, father);
	}
	
	@Override
	public final VertexInterface getPrevious(VertexInterface son) {
		return previousTable.get(son);
	}
	
	
}
