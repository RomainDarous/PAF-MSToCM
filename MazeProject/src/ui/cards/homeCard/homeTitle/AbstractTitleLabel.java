package ui.cards.homeCard.homeTitle;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Abstract class which gives the main UI properties of the title labels.
 * @author Romain Darous
 *
 */
public class AbstractTitleLabel extends JLabel{
	
	/**
	 * Constructor of the abstract class AbstractTitlePanel
	 * @param label Text of the JLabel component.
	 * @param font Font of the JLabel component.
	 * @param color Color of the JLabel component.
	 */
	public AbstractTitleLabel(String label, Font font, Color color) {
		super(label);
		
		setupLabel(label, font, color);
	}
	
	/**
	 * Setup of the design of the AbstractTitleLabel label.
	 * @param label Text of the JLabel component.
	 * @param font Font of the JLabel component.
	 * @param color Color of the JLabel component.
	 */
	private final void setupLabel(String label, Font font, Color color) {
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(font);
		this.setForeground(color);
	}

}
