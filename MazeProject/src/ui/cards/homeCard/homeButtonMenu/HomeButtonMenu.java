package ui.cards.homeCard.homeButtonMenu;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import ui.Const;
import ui.cards.homeCard.homeButtonMenu.LoadButton.LoadButtonH;
import ui.cards.homeCard.homeButtonMenu.NewButton.DimensionPanelH;
import ui.cards.homeCard.homeButtonMenu.NewButton.NewButtonH;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * Class that allows to create the button menu of the home page. It is compose of a new, a load and a leave button.
 * When clicking on the new button, a dimension panel appears to put the dimensions of the maze the users wants to create.
 * @author Romain Darous
 *
 */
public class HomeButtonMenu extends JPanel {
	
	private final LeaveButtonH leaveButton;
	private final LoadButtonH loadButton;
	private final NewButtonH newButton;
	
	private final DimensionPanelH dimensionPanel;

	
	private final MazeModel mazeModel;

	/**
	 * Constructor of the HomeButtonMenu class.
	 * @param mazeView the view in which is displayed the button.
	 */
	public HomeButtonMenu(MazeView mazeView) {
		leaveButton = new LeaveButtonH(mazeView);
		loadButton = new LoadButtonH(mazeView);
		newButton = new NewButtonH(mazeView);
		
		dimensionPanel = new DimensionPanelH(mazeView);

		mazeModel = mazeView.getMazeModel();
		
		setupMenu();
	}
	
	private final void setupMenu() {
		//UI
		setOpaque(false);
		
		//Layout Manager
		this.setLayout(Const.HOME_BUTTON_MENU_DEFAULT_LAYOUT);
		
		//Adding buttons
		this.add(newButton);
		this.add(loadButton);
		this.add(leaveButton);
	}

	/**
	 * Refreshes the view depending on the user actions.
	 */
	public void notifyForUpdate() {
		
		if (mazeModel.getPage()[0] == Const.HOME_PAGE) {
			dimensionPanel.notifyForUpdate();
		}
		
		if (mazeModel.getPage()[2] == Const.UPDATED) {
			
			if(mazeModel.getPage()[0] == Const.ENABLE_CREATE_PAGE) {
				remove(newButton);
				this.setLayout(Const.HOME_BUTTON_MENU_CREATE_LAYOUT);
				this.add(dimensionPanel, FlowLayout.LEFT);
				updateUI();
			}
			else if (mazeModel.getPage()[0] == Const.DISABLE_CREATE_PAGE) {
				remove(dimensionPanel);
				this.setLayout(Const.HOME_BUTTON_MENU_DEFAULT_LAYOUT);
				this.add(newButton, FlowLayout.LEFT);
				updateUI();
			}
		}
	}
}
