package ui.cards.editCard.editNCQMenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import ui.Const;
import ui.cards.editCard.editNCQMenu.LoadButton.LoadButtonE;
import ui.cards.editCard.editNCQMenu.NewButton.NewButtonE;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * Class that allows creating the menu made with the New, Load and Leave buttons in the solve page.
 * @author Romain Darous
 *
 */
public class EditNCQMenu extends JPanel {
	private final static int MARGIN = 30;
	private final GridBagConstraints gbc = new GridBagConstraints();
	
	private final LeaveButtonE leaveButton;
	private final LoadButtonE loadButton;
	private final NewButtonE newButton;
	
	
	private final MazeModel mazeModel;

	/**
	 * Constructor of the EditNCQMenu class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public EditNCQMenu(MazeView mazeView) {
		leaveButton = new LeaveButtonE(mazeView);
		loadButton = new LoadButtonE(mazeView);
		newButton = new NewButtonE(mazeView);
		
		mazeModel = mazeView.getMazeModel();
		
		setupMenu();
	}
	
	private final void setupMenu() {
		//UI
		setBackground(Const.EDIT_SOLVED_MENU_COLOR);		
		
		//Layout Manager
		this.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		
		//Adding buttons
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 5, 5);
		this.add(newButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(leaveButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 5, 5, 0);
		this.add(loadButton, gbc);

	}
}
