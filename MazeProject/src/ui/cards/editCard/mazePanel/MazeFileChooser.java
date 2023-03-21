package ui.cards.editCard.mazePanel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui.Const;
import ui.model.MazeModel;
import ui.view.MazeView;

/**
 * The file chooser of the application. It will allow loading and saving a maze text file.
 * @author Romain Darous
 *
 */
public class MazeFileChooser extends JFileChooser{
	
	private final MazeModel mazeModel;
	
	/**
	 * Constructor of the MazeFileChooser class.
	 * @param mazeView the view in which the button is displayed.
	 */
	public MazeFileChooser(MazeView mazeView) {
		super(Const.LOAD_MAZE_DEFAULT_PATH);
		
		this.mazeModel = mazeView.getMazeModel();
		
		setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);
	}
	
	/**
	 * Refreshes the view depending on the user actions.
	 */
	public final void notifyForUpdate() {
		
		if (mazeModel.getMazeMode()[2] == Const.UPDATED) {
			
			if(mazeModel.getMazeMode()[0] == Const.LOADING) {
				int response = this.showDialog(null, "Ok");
				if(response == JFileChooser.APPROVE_OPTION) {
					String selectedFilePath = getSelectedFile().getAbsolutePath();
					mazeModel.setMazePath(selectedFilePath);
				}
				
				else {
					mazeModel.setMazePath(Const.VOID_LABEL);
				}
			}
			
			else if (mazeModel.getMazeMode()[0] == Const.SAVING) {
				
				int response = this.showDialog(null, "Sauvegarder");
				
				if(response == JFileChooser.APPROVE_OPTION) {
					String selectedFilePath = getSelectedFile().getAbsolutePath();
					mazeModel.setMazeSavePath(selectedFilePath + ".txt");
				}
			}
		}
	}
}
