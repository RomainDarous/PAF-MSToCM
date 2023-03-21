package maze;

/**
 * Class which extends MBox and corresponds to a wall box of the labyrinth : you cannot walk on this one.
 * @author Romain Darous
 *
 */

public class WBox extends MBox {
	
	/**
	 * Create a Wall Box.
	 * @param x abscissa of the Wall Box.
	 * @param y ordinate of the Wall Box.
	 */
	public WBox (int x, int y) {
		super(x, y, "W");
	}

}
