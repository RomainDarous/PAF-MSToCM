package ui.cards.editCard.editButtonMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.Const;
import ui.abstractButtons.MazeAbstractButton;
import ui.view.MazeView;

/**
 * Class that allows to create a modify button that will be used to go back to the editing mode after having solved the maze.
 * @author Romain Darous
 *
 */

public class ModifyButton extends MazeAbstractButton implements MouseListener {
	
	/**
	 * Constructor of the ModifyButton class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public ModifyButton( MazeView mazeView) {
		super(Const.MODIFY_LABEL, mazeView);
		setupModifyButton();
	}
	
	private final void setupModifyButton() {
		this.setBackground(Const.MODIFY_BUTTON_DEFAULT_COLOR);
		this.setPreferredSize(Const.MODIFY_BUTTON_DIMENSIONS);
		setFont(Const.MODIFY_BUTTON_FONT);
		this.addMouseListener(this);
	}
	
	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.setBackground(Const.MODIFY_BUTTON_DEFAULT_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mousePressed(MouseEvent e) {
		this.setBackground(Const.MODIFY_BUTTON_PRESSED_COLOR);
	}

	/**
	 * Changes the color of the button.
	 * Tells the model the action to perform when clicking on the modify button : modifying the maze after having solved it.
	 */
	@Override
	public final void mouseReleased(MouseEvent e) {
		this.setBackground(Const.MODIFY_BUTTON_DEFAULT_COLOR);
		getMazeModel().setMazeMode(Const.MODIFYING);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public final void mouseEntered(MouseEvent e) {
		this.setBackground(Const.MODIFY_BUTTON_OVER_COLOR);
	}

	/**
	 * Changes the color of the button.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(Const.MODIFY_BUTTON_DEFAULT_COLOR);
	}
	
	
}
