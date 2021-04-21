package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;


public class Toolkit {

	public static JButton simpleButton(String text, String fontFileName) throws FontFormatException, IOException {
		//allow to create a button with a uniform style
		JButton button = new JButton(text);
		String fontFilePath = "src/res/" + fontFileName;
		
		try {
		    //create the font to use. Specify the size!
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath)).deriveFont(60f);
		    //register the font
		    button.setFont(customFont);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setMargin(new Insets(30, 80, 15, 80));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		
		return button;
	}
}
