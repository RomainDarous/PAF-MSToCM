package ui.cards;

import java.awt.CardLayout;

import ui.Const;
import ui.cards.editCard.EditCard;
import ui.cards.editCard.mazePanel.MazeFileChooser;
import ui.cards.homeCard.HomeCard;
import ui.view.MazeView;

/**
 * This class defines the parent container of all other cards.
 * @author Romain Darous
 *
 */
public class ParentCard extends Card {
	
	private final CardLayout cLayout; //The CardLayout that manages the window.
	
	private final EditCard editCard;
	private final HomeCard homeCard;
	
	private final MazeFileChooser mazeFileChooser;

	/**
	 * Constructor of the ParentCard class.
	 * @param mazeView the view in which is displayed the parent card.
	 */
	public ParentCard(MazeView mazeView) {
		super(mazeView);
		this.mazeFileChooser = new MazeFileChooser(mazeView);
		this.cLayout = new CardLayout();
		this.editCard = new EditCard(mazeView);
		this.homeCard = new HomeCard(mazeView);
		
		setupCard();
	}
	
	private final void setupCard() {
		this.setLayout(cLayout);
		
		this.add(this.homeCard, Const.HOME_LABEL);
		this.add(this.editCard, Const.EDIT_LABEL);
	}
	
	/**
	 * Allows to show the home card ( = home page).
	 */
	public final void showHomeCard() {
		this.cLayout.show(this, Const.HOME_LABEL);
	}
	
	/**
	 * Allows to show the edit card (= edit page + solve page).
	 */
	public final void showEditCard() {
		this.cLayout.show(this, Const.EDIT_LABEL);
	}

	@Override
	public void notifyForUpdate() {
		mazeFileChooser.notifyForUpdate();
		editCard.notifyForUpdate();
		homeCard.notifyForUpdate();
		
		if (getMazeModel().getPage()[2] == Const.UPDATED) {
			if(getMazeModel().getPage()[0] == Const.EDIT_PAGE) {
				this.showEditCard();			
			}
			else if(getMazeModel().getPage()[0] == Const.HOME_PAGE) {
				this.showHomeCard();
			}
		}
	}
}
