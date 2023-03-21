package ui.cards.homeCard;

import java.awt.BorderLayout;

import ui.cards.Card;
import ui.cards.homeCard.homeButtonMenu.HomeButtonMenu;
import ui.cards.homeCard.homeTitle.TitlePanel;
import ui.view.MazeView;

/**
 * The home card of the maze application. It will display the home page of the application.
 * @author Romain Darous
 *
 */
public class HomeCard extends Card {
	
	private final HomeButtonMenu buttonMenu;
	private final TitlePanel titlePanel;
		
	/**
	 * Constructor of the HomeCard class.
	 * @param mazeView the view in which is displayed the card.
	 */
	public HomeCard(MazeView mazeView) {
		super(mazeView);
		
		buttonMenu = new HomeButtonMenu(mazeView);
		titlePanel = new TitlePanel();
		
		setupCard();
	}	
	
	private final void setupCard() {
		
		//Layout manager
		this.setLayout(new BorderLayout());
		
		//Adding buttonMenu Panel to the HomeCard Panel.
		this.add(buttonMenu, BorderLayout.SOUTH);
		this.add(titlePanel, BorderLayout.NORTH);
	}

	@Override
	public void notifyForUpdate() {
		buttonMenu.notifyForUpdate();
	}
}
