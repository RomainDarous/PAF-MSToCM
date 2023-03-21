package dijkstra;

/**
 * Class in which the dijkstra algortithm is coded using a static method.
 * @author Romain Darous
 *
 */
public class Dijkstra {
	
	/**
	 * A private method which implements the dijkstra algorithm using 5 parameters.
	 * @param g the graph considered.
	 * @param r the departure vertex.
	 * @param a set which stores all marked vertices
	 * @param pi Pi Object in which each "key" vertex is linked with its Pi value at the current step of the dijkstra algorithm.
	 * @param previous Previous Object in which each "key" vertex is linked with another Vertex : its father.
	 * @return previous modified by the algorithm.
	 */
	private final static PreviousInterface dijkstra(GraphInterface g, VertexInterface r, ASetInterface a, PiInterface pi, PreviousInterface previous) {
		final int n = g.getVerticeNumber();
		a.add(r);
		VertexInterface pivot = r;
		
		for (VertexInterface x : g.getAllVertices()) {
			if (x.equals(r)) {
				pi.setPi(r, 0);
			} else {
			pi.setPi(x, Integer.MAX_VALUE); //MAX_VALUE corresponds to the infinite here.
			}
		}
		//Loop executed n times where n is the number of vertex of the graph.
		for (int j = 0 ; j < n ; j++) {
			for(VertexInterface y : g.getSuccessors(pivot)) {
			
				//Checking if the new distance of the y vertex is below the previous one.
				if (!a.inA(y) && ( pi.getPi(pivot) + g.getWeight(pivot, y) < pi.getPi(y) ) ) {
					pi.setPi(y, pi.getPi(pivot) + g.getWeight(pivot, y) );
					previous.setPrevious(y, pivot);
				}
			}
			
			pivot = pi.getMinPi(a);
			a.add(pivot);
			if (pivot == null) break; // It happends when some vertices of the graphe aren't reached from the departure box.
		}
		return previous;
	}
	
	

	/**
	 * A method which implements the dijkstra algorithm using 2 parameters.
	 * @param g the graph considered.
	 * @param r the departure vertex.
	 * @return a Previous Object in which each "key" vertex is linked with another Vertex : its father.
	 */
	public final static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
		ASetInterface a = new ASet();
		PiInterface pi = new Pi();
		PreviousInterface previous = new Previous();
		return dijkstra(g, r, a, pi, previous);
	}
}
