package ui.cards.editCard.editButtonMenu;

import javax.swing.JPanel;

import ui.Const;
import ui.cards.editCard.editNCQMenu.EditNCQMenu;
import ui.cards.editCard.editNCQMenu.NewButton.DimensionPanelE;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * This class is the button menu of the editing Card. It gathers the buttons that are displayed when the maze is in editing mode and when it is soved mode.
 * @author Romain Darous
 *
 */

public class EditButtonMenu extends JPanel {
	
	private final CancelButtonE cancelButton;
	private final SaveEditButtonE saveEditButton;
	private final SaveSolvedButtonE saveSolvedButton;
	private final SolveButton solveButton;
	private final ModifyButton modifyButton;
	
	private final EditNCQMenu editNCQMenu;
	private final DimensionPanelE dimensionPanel;
	
	private final MazeModel mazeModel;
	
	/**
	 * Constructor of the EditButtonMenu class.
	 * @param mazeView the view in which the menu is displayed.
	 */
	public EditButtonMenu(MazeView mazeView) {
		super();
		
		cancelButton = new CancelButtonE(mazeView);
		saveEditButton = new SaveEditButtonE(mazeView);
		saveSolvedButton = new SaveSolvedButtonE(mazeView);
		solveButton = new SolveButton(mazeView);
		modifyButton = new ModifyButton(mazeView);
		
		editNCQMenu = new EditNCQMenu(mazeView);
		dimensionPanel = new DimensionPanelE(mazeView);
		
		mazeModel = mazeView.getMazeModel();
		
		setupEditButtonMenu();
	}
	
	private final void setupEditButtonMenu() {
		
		//UI
		setBackground(Const.EDIT_SOLVED_MENU_COLOR);
		
		//Layout Manager
		this.setLayout(Const.EDIT_BUTTON_MENU_LAYOUT);
		
		//Adding components
		this.add(solveButton);
		add(saveEditButton);
		add(cancelButton);
		
	}

	private final void setupSolveMode() {
		removeAll();
		setLayout(Const.SOLVED_BUTTON_MENU_LAYOUT);
		add(saveSolvedButton);
		add(modifyButton);
		add(editNCQMenu);
		updateUI();
	}
	
	private final void setupEditMode() {
		removeAll();
		setLayout(Const.EDIT_BUTTON_MENU_LAYOUT);
		add(solveButton);
		add(saveEditButton);
		add(cancelButton);;
		updateUI();
	}
	
	private final void enableCreatePage() {
		remove(modifyButton);
		remove(editNCQMenu);
		add(dimensionPanel);
		updateUI();
	}
	
	private final void disableCreatePage() {
		remove(dimensionPanel);
		add(modifyButton);
		add(editNCQMenu);
		updateUI();
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public final void notifyForUpdate () {
		
		if (mazeModel.getPage()[0] == Const.SOLVE_PAGE) {
			dimensionPanel.notifyForUpdate();
		}
		
		if(mazeModel.getPage()[2] == Const.UPDATED) {
			if(mazeModel.getPage()[0] == Const.SOLVE_PAGE) {
				setupSolveMode();
			}
			
			else if (mazeModel.getPage()[0] == Const.EDIT_PAGE) {
				setupEditMode();
			}
			
			else if (mazeModel.getPage()[1] == Const.SOLVE_PAGE) {
				
				if (mazeModel.getPage()[0] == Const.ENABLE_CREATE_PAGE) {
					enableCreatePage();
				}
				else if (mazeModel.getPage()[0] == Const.DISABLE_CREATE_PAGE) {
					disableCreatePage();
				}
				
			}
		}
	}
	
}
