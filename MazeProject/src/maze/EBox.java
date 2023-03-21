package maze;

/**
 * Class which extends MBox and corresponds to an empty box of the labyrinth : you can walk on this one.
 * @author Romain Darous
 *
 */

public class EBox extends MBox {
	
	/**
	 * Create an Empty Box.
	 * @param x abscissa of the Empty Box.
	 * @param y ordinate of the Empty Box.
	 */
	public EBox (int x, int y) {
		super(x, y, "E");
	}

}
