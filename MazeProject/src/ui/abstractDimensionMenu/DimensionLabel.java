package ui.abstractDimensionMenu;

import javax.swing.JLabel;

import ui.Const;

/**
 * An abstract class that implements the main methods of the labels which will be displayed when the user writes the dimensions of a new maze he wants to create.
 * @author Romain Darous
 *
 */

public abstract class DimensionLabel extends JLabel {
	
	/**
	 * Constructor of the DimensionLabel class.
	 * @param label the text displayed in the label.
	 */
	public DimensionLabel(String label) {
		super(label);
		setupDimensionLabel();
	}

	/**
	 * A private method that sets up the dimension label.
	 */
	private final void setupDimensionLabel() {
		setForeground(Const.NEW_BUTTON_DEFAULT_COLOR);
		this.setOpaque(false);
	}
}
