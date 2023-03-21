package maze;

/**
 * Class which extends MBox and corresponds to the departure box of the labyrinth : you can walk on this one.
 * @author Romain Darous
 *
 */

public class DBox extends MBox {
	
	/**
	 * Creates a Departure Box.
	 * @param x abscissa of the Departure Box.
	 * @param y ordinate of the Arrival Box.
	 */
	public DBox(int x, int y) {
		super(x,y,"D");
	}

}
