package ui.view;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import ui.Const;

/**
 * Class that generates a pop up page when throwing an exception related to the maze.
 * @author Romain Darous
 *
 */

public class PopUp extends JDialog {

	private final JTextArea messageArea;
	
	/**
	 * Constructor of the PopUp class.
	 * @param mazeView the parent view of the PopUp class.
	 * @param message the message displayed in the pop up window.
	 */
	public PopUp(MazeView mazeView, String message) {
		super(mazeView);
		
		//Setup.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(Const.EDIT_SOLVED_MENU_COLOR);
		this.setLocation(Const.POP_UP_X, Const.POP_UP_Y);
		this.setResizable(false);
		this.setMinimumSize(Const.POP_UP_DIMENSION);
		
		//Setup message.
		this.messageArea = new JTextArea(message);
		messageArea.setFont(Const.POP_UP_FONT);
		messageArea.setForeground(Const.LEAVE_CANCEL_BUTTON_DEFAULT_COLOR);
		messageArea.setEditable(false);
		messageArea.setOpaque(false);
		add(this.messageArea);
	}
}
