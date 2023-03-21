package maze;

import dijkstra.VertexInterface;

/**
 * Abstract class which contains all common methods to different box types. It implements the VertexInterface interface.
 * @author Romain Darous
 *
 */
public abstract class MBox implements VertexInterface {
	
	
	private final int[] coords; //Box coordinates.
	private String label; // Label of the box.
	
	/**
	 * Constructor of the MBox class.
	 * @param x abscissa of the box.
	 * @param y ordinate of the box.
	 * @param label label of the box.
	 */
	public MBox(int x, int y, String label) {
		
		coords = new int[2];
		coords[0] = x;
		coords[1] = y;
		this.label = label;
	}

	@Override
	public final int[] getCoords() {
		return coords;
	}
	
	@Override
	public final void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public final String getLabel(){
		return label;
	}
	
	
	
}
