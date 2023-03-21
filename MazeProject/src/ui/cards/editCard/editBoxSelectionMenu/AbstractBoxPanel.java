package ui.cards.editCard.editBoxSelectionMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Const;
import ui.drawingBoxes.AbstractDrawingBox;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * An abstract class that implements the main methods of the box panels. When the user will click on this panel, the box type of the selected box will be changed to the one the user clicked on.
 * He will then be able to modify the boxes of his maze and change it to the selected box type.
 * @author Romain Darous
 *
 */

public abstract class AbstractBoxPanel extends JPanel implements MouseListener {
	
	private final AbstractDrawingBox box;
	private final JLabel text;
	private final String label;
	
	private final MazeModel mazeModel;
	
	/**
	 * Constructor of the AbstractBoxPanel class.
	 * @param mazeView the view in which objects that extend this class will be displayed.
	 * @param box the box that will be added to the box panel.
	 * @param text the text displayed to describe the panel.
	 */
	public AbstractBoxPanel (MazeView mazeView, AbstractDrawingBox box, String text) {
		super();
		this.box = box;
		this.text = new JLabel(text);
		this.label = box.getLabel();
		this.mazeModel = mazeView.getMazeModel();
		
		setupAbstractBoxPanel();
	}
	
	private final void setupAbstractBoxPanel() {
		//UI
		setBackground(Const.EDIT_SOLVED_MENU_COLOR);
		text.setFont(Const.BOX_TEXT_FONT);
		text.setForeground(Const.BOX_FONT_COLOR);
		
		//Layout Manager
		setLayout(Const.BOX_PANEL_LAYOUT);
		
		//Adding Components
		add(box);
		add(text);
		addMouseListener(this);
	}
	
	/**
	 * Nothing done.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**
	 * Nothing done.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	/**
	 * Sets the selected box type to this box type.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		mazeModel.setSelectedBox(label);
	}
	
	/**
	 * Nothing done.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	/**
	 * Nothing done.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	/**
	 * Refreshes the view depending on the user actions.
	 */
	public void notifyForUpdate() {
		if(mazeModel.getSelectedBox()[0].equals(label) && mazeModel.getSelectedBox()[1].equals(Const.SELECTION_UPDATED)) {
			setOpaque(false);
			text.setForeground(Const.SELECTED_BOX_FONT_COLOR);
			updateUI();
		}
		else if (!mazeModel.getSelectedBox()[0].equals(label) && mazeModel.getSelectedBox()[1].equals(Const.SELECTION_UPDATED)) {
			setOpaque(true);
			text.setForeground(Const.BOX_FONT_COLOR);
			updateUI();
		}
	}
}
