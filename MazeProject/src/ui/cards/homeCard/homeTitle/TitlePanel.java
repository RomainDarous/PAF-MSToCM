package ui.cards.homeCard.homeTitle;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.Const;

/**
 * Panel that gathers all the components of the title of the home page : 
 * title of the application,
 * name of the creator (me haha !),
 * the motto.
 * @author Romain Darous
 *
 */
public class TitlePanel extends JPanel{
	
	private final Motto motto;
	private final Name name;
	private final Title title;

	/**
	 * Constructor of the TitlePanel class.
	 */
	public TitlePanel() {
		super();
		this.motto = new Motto();
		this.name = new Name();
		this.title = new Title();
		
		setupTitlePanel();
	}
	
	private final void setupTitlePanel() {
		
		//UI
		setBackground(Const.BACKGROUND_COLOR);
		
		
		//Layout manager
		this.setLayout( new GridLayout(3, 1, 0, 60) );
		
		//Adding labels to the TitlePanel
		this.add(title);
		this.add(name);
		this.add(motto);
	}
}
