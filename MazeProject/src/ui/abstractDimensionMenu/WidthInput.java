package ui.abstractDimensionMenu;

import javax.swing.JTextField;

import ui.Const;

/**
 * An abstract class that implements the main methods of the width text field which will allow the user to write the dimensions of a new maze he wants to create.
 * @author Romain Darous
 *
 */

public abstract class WidthInput extends JTextField {
	
	/**
	 * Constructor of the HeightInput class. Sets a default value for the text field and its dimensions.
	 */
	public WidthInput() {
		super("10");
		setupDimensionText();
	}
	
	/**
	 * A private method that sets up the width input text field.
	 */
	private final void setupDimensionText() {
		this.setForeground(Const.NEW_BUTTON_DEFAULT_COLOR);
		this.setBackground(Const.DIMENSION_TEXT_FIELD_BACKGROUND_COLOR);
		this.setBorder(null);
		this.setHorizontalAlignment(JTextField.CENTER);
	}
}
