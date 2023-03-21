package maze;

/**
 * Class which extends MBox and corresponds to the arrival box of the labyrinth : you can walk on this one.
 * @author Romain Darous
 *
 */
public class ABox extends MBox {

	/**
	 * Creates an Arrival Box.
	 * @param x abscissa of the Arrival Box.
	 * @param y ordinate of the Arrival Box.
	 */
	public ABox(int x, int y) {
		super(x, y, "A");
	}

}
