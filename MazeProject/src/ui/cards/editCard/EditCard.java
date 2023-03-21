package ui.cards.editCard;

import java.awt.BorderLayout;

import ui.Const;
import ui.cards.Card;
import ui.cards.editCard.editBoxSelectionMenu.EditBoxSelectionMenu;
import ui.cards.editCard.editButtonMenu.EditButtonMenu;
import ui.cards.editCard.mazePanel.MazePanel;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * The edit card of the maze application. It will display the editing page of the maze, but also the solving page.
 * @author Romain Darous
 *
 */
public class EditCard extends Card {
	
	private final EditButtonMenu editButtonMenu;
	private final EditBoxSelectionMenu editBoxSelectionMenu;
	
	private final MazePanel mazePanel;
	
	private final MazeModel mazeModel;

	/**
	 * Constructor of the EditCard class.
	 * @param mazeView the view in which is displayed the card.
	 */
	public EditCard(MazeView mazeView) {
		super(mazeView);
		editButtonMenu = new EditButtonMenu(mazeView);
		mazeModel = mazeView.getMazeModel();
		editBoxSelectionMenu = new EditBoxSelectionMenu(mazeView);
		mazePanel = new MazePanel(mazeView);
		setupCard();
	}

	private final void setupCard() {
		//UI
		setOpaque(false);
		
		//Layout manager
		setLayout(new BorderLayout());
		
		//Adding components
		add(editButtonMenu, BorderLayout.SOUTH);
		add(editBoxSelectionMenu, BorderLayout.WEST);
		add(mazePanel, BorderLayout.CENTER);
		
	}

	@Override
	public void notifyForUpdate() {
		
		editButtonMenu.notifyForUpdate();
		editBoxSelectionMenu.notifyForUpdate();
		mazePanel.notifyForUpdate();
		
		if(mazeModel.getPage()[2] == Const.UPDATED) {
			if(mazeModel.getPage()[0] == Const.SOLVE_PAGE) {
				remove(editBoxSelectionMenu);
				updateUI();
			}
			else if (mazeModel.getPage()[0] == Const.EDIT_PAGE) {
				add(editBoxSelectionMenu, BorderLayout.WEST);
				updateUI();
			}
			
		}
	}
}
