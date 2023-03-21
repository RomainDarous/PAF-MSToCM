package ui.cards.editCard.editBoxSelectionMenu;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.view.MazeView;

/**
 * Menu that allows the user to select a box type that he wants to use modify his maze. Gathers four box panels, one for each box type.
 * @author Romain Darous
 *
 */
public class EditBoxSelectionMenu extends JPanel {

	private final BoxPanelA boxPanelA;
	private final BoxPanelD boxPanelD;
	private final BoxPanelE boxPanelE;
	private final BoxPanelW boxPanelW;
	
	/**
	 * Constructor of the EditBoxSelectionMenu class.
	 * @param mazeView the view in which the panel is displayed.
	 */
	public EditBoxSelectionMenu(MazeView mazeView) {
		super();
		boxPanelD = new BoxPanelD(mazeView);
		boxPanelE = new BoxPanelE(mazeView);
		boxPanelW = new BoxPanelW(mazeView);
		boxPanelA = new BoxPanelA(mazeView);
		
		setupEditBoxSelectionMenu();
	}
	
	private final void setupEditBoxSelectionMenu() {
		//UI
		setOpaque(false);
		
		//Layout Manager
		setLayout( new GridLayout(4, 1));
		
		//Adding components
		add(boxPanelD);
		add(boxPanelA);
		add(boxPanelE);
		add(boxPanelW);
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public final void notifyForUpdate() {
		boxPanelA.notifyForUpdate();
		boxPanelD.notifyForUpdate();
		boxPanelE.notifyForUpdate();
		boxPanelW.notifyForUpdate();
	}
}
