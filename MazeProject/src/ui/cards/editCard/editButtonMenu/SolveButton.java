package ui.cards.editCard.editButtonMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.abstractButtons.MazeAbstractButton;
import ui.view.MazeView;

/**
 * Class that allows to create a solve button. By clicking on it, the user gets the shortest way to go from the departure box to the arrival box, if there is one.
 * @author Romain Darous
 *
 */

public class SolveButton extends MazeAbstractButton implements MouseListener{
	
	/**
	 * Constructor of the SolveButton class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public SolveButton( MazeView mazeView) {
		super(Const.SOLVE_LABEL, mazeView);
		setupSolveButton();
	}
	
	private final void setupSolveButton() {
		this.setBackground(Const.SOLVE_BUTTON_DEFAULT_COLOR);
		this.setPreferredSize(Const.SOLVE_BUTTON_DIMENSIONS);
		setFont(Const.SOLVE_BUTTON_FONT);
		this.addMouseListener(this);
		
	}
	
	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.setBackground(Const.SOLVE_BUTTON_DEFAULT_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mousePressed(MouseEvent e) {
		this.setBackground(Const.SOLVE_BUTTON_PRESSED_COLOR);
	}

	/**
	 * Changes the color of the button.
	 * Tells the model the action to perform when clicking on the solve button : solving the maze.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.SOLVE_BUTTON_DEFAULT_COLOR);
		getMazeModel().setMazeMode(Const.SOLVING);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseEntered(MouseEvent e) {
		this.setBackground(Const.SOLVE_BUTTON_OVER_COLOR);
	}
	
	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseExited(MouseEvent e) {
		this.setBackground(Const.SOLVE_BUTTON_DEFAULT_COLOR);
	}
}
