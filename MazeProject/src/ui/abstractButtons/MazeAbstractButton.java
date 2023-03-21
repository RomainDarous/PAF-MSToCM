package ui.abstractButtons;

import javax.swing.JButton;

import ui.Const;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * An abstract class which gathers the main methods of all the buttons of the ui.
 * @author Romain Darous
 *
 */


public abstract class MazeAbstractButton extends JButton {

	private final MazeModel mazeModel; // The maze model.
	
	/**
	 * The constructor of the MazeAbstractButton class.
	 * @param label the text of the button.
	 * @param mazeView the view in which the button is displayed.
	 */
	public MazeAbstractButton(String label, MazeView mazeView) {
		super(label);
		this.mazeModel = mazeView.getMazeModel();
		setupAbstractButton();
	}
	
	/**
	 * A private method that sets up the UI of the button.
	 */
	private final void setupAbstractButton() {
		//UI
		this.setForeground(Const.BUTTON_FOREGROUND);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
	}
	
	/**
	 * Gets he maze model to have it accessible for all buttons.
	 * @return the mazeModel.
	 */
	public final MazeModel getMazeModel() {
		return mazeModel;
	}
}
