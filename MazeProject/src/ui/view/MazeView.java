package ui.view;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import ui.Const;
import ui.cards.ParentCard;
import ui.model.MazeModel;

/**
 * The view class of the application. This is what the user will see and use to play with mazes.
 * @author Romain Darous
 *
 */
public class MazeView extends JFrame implements MazeViewInterface {
	
	private MazeModel mazeModel;
	private final ParentCard parentCard;
	private PopUp popUp;
	
	/**
	 * Constructor of the MazeView class.
	 */
	public MazeView() {
		super();
		this.popUp = new PopUp(this,"");
		this.mazeModel = new MazeModel(this);
		this.parentCard = new ParentCard(this);
		
		//setup
		setupView();
	}
	
	/**
	 * Set up of the UI : dimension of the window, appearance of the window, and display of the window and its content.
	 */
	private final void setupView() {
		//Appearance of the window
		this.setPreferredSize(Const.VIEW_DIMENSIONS);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Const.BACKGROUND_COLOR);
		this.setUndecorated(true);
		this.setResizable(false);
		
		//Setup
		this.add(parentCard);
		this.showHomeCard();
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public final MazeModel getMazeModel() {
		return mazeModel;
	}
	
	@Override
	public final void showHomeCard() {
		this.parentCard.showHomeCard();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(mazeModel.getPage()[0] == Const.LEAVE_PAGE) {
			this.dispose();
		}
		
		else if (mazeModel.getMazeMode()[0] == Const.INIT_ERROR && mazeModel.getMazeMode()[2] == Const.UPDATED) {
			popUp.setVisible(false);
			popUp = new PopUp(this, mazeModel.getInitSolvableMessage());
			popUp.setVisible(true);
		}
		
		else {
		popUp.setVisible(false);
		parentCard.notifyForUpdate();
		}
	}
}