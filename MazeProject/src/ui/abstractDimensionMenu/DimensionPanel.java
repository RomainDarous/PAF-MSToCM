package ui.abstractDimensionMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import ui.Const;
import ui.abstractButtons.CancelButton;
import ui.abstractButtons.OkButton;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * An abstract class that implements the main methods of the panel which will be displayed when the user writes the dimensions of a new maze he wants to create.
 * @author Romain Darous
 *
 */

public abstract class DimensionPanel extends JPanel {


	private final DimensionLabel heightLabel;
	private final DimensionLabel widthLabel;
	
	private final HeightInput heightInput;
	private final WidthInput widthInput;
	
	private String heightText;
	private String widthText;
	
	private final CancelButton cancelButton;
	private final OkButton okButton;
	
	private final JPanel heightPanel;
	private final JPanel widthPanel;
	
	private final MazeModel mazeModel;
	
	/**
	 * Constructor of the DimensionPanel class.
	 * @param mazeView the maze view in which the dimension panel is displayed.
	 * @param heightLabel the height label that will be displayed when the users will write dimensions of a new maze.
	 * @param widthLabel the width label that will be displayed when the users will write dimensions of a new maze.
	 * @param heightInput the text field where the user enters the height of the maze. 
	 * @param widthInput the text field where the user enters the width of the maze.
	 * @param cancelButton the cancel button of the dimension panel.
	 * @param okButton the OK button of the dimension panel.
	 */
	public DimensionPanel(MazeView mazeView, DimensionLabel heightLabel, DimensionLabel widthLabel, HeightInput heightInput, WidthInput widthInput, CancelButton cancelButton, OkButton okButton) {
		super();
		this.heightLabel = heightLabel;
		this.widthLabel = widthLabel;
		this.heightInput = heightInput;
		this.widthInput = widthInput;
		this.cancelButton = cancelButton;
		this.okButton = okButton;
		
		this.heightPanel = new JPanel();
		this.widthPanel = new JPanel();
		
		this.mazeModel = mazeView.getMazeModel();
		
		setupDimensionPanel();
	}
	
	/**
	 * A private method that sets up the dimension panel.
	 */
	private final void setupDimensionPanel() {
		//UI
		setOpaque(false);
		
		setupHeightPanel();
		setupWidthPanel();
		
		//Layout Manager
		this.setLayout(new BorderLayout());
		
		//Adding both panels to the Dimension Panel
		this.add(heightPanel, BorderLayout.NORTH);
		this.add(widthPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * A private method that sets up the height panel.
	 */
	private final void setupHeightPanel() {
		//UI
		heightPanel.setOpaque(false);
		
		//Layout Manager
		heightPanel.setLayout( Const.DIMENSION_PANEL_LAYOUT );
		
		//Adding components to the Label
		heightPanel.add(heightLabel);
		heightPanel.add(heightInput);
		heightPanel.add(okButton);
		
	}
	
	/**
	 * A private method that sets up the width panel.
	 */
	private final void setupWidthPanel() {
		//UI
		widthPanel.setOpaque(false);
		
		//Layout Manager
		widthPanel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		
		//Adding components to the Label
		widthPanel.add(widthLabel);
		widthPanel.add(widthInput);
		widthPanel.add(cancelButton);
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public final void notifyForUpdate() {
		
		//Checking if we are in the HOME_PAGE or in the the SOLVE_PAGE.
		if(mazeModel.getPage()[0] == Const.HOME_PAGE || mazeModel.getPage()[0] == Const.SOLVE_PAGE) {
			
			//Action performed when clicking on OK.
			if(mazeModel.getMazeMode()[0] == Const.CREATING && mazeModel.getMazeMode()[2] == Const.UPDATED) {
				heightText = heightInput.getText();
				widthText = widthInput.getText();
				
				if(heightText.equals("") || widthText.equals("")) {
					mazeModel.setInitSolvableMessage(Const.NO_INPUT);
				}
				
				else {
					try {
					mazeModel.setCreatingMazeHeight(Integer.parseInt(heightText));
					mazeModel.setCreatingMazeWidth(Integer.parseInt(widthText));
					
					heightInput.setText("5");
					widthInput.setText("10");
					
					} catch (NumberFormatException e) {
						mazeModel.setInitSolvableMessage(Const.NOT_INTEGER_INPUT);
						
					}
				}
			}
		}
	}
}
