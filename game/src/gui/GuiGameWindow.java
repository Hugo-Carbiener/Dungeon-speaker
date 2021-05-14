package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class GuiGameWindow implements ActionListener {
	
	private static String newline = "\n";
	

	//public static void GuiDefaultDisplay(String string) {
		//try {
			//doc.insertString(pos,string, defaultStyle);
			//pos += string.length();
		//} catch (BadLocationException e) {
			//e.printStackTrace();
		//}
	//}
	
	//public static void GuiGameDisplay(String string) {
		//try {
			//String arrows = ">>";
			//doc.insertString(pos, newline + arrows, defaultStyle);
			//pos += arrows.length() + 1;
			//doc.insertString(pos,string, gameInputStyle);
			//pos += string.length();
		//} catch (BadLocationException e) {
			//e.printStackTrace();
		//}
	//}
	
	public GuiGameWindow() throws FontFormatException, IOException{
		//Creating the Frame
        JFrame frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Describe your action :  ");        
        // Text Area at the Center
        TextArea textPane = new TextArea("", 0 , 0 , TextArea.SCROLLBARS_VERTICAL_ONLY);
        textPane.setEditable(false);
       
        
        

        
        //Text field
        JTextField textField = Toolkit.textField("pixelArtFont.ttf");
        //Button
        JButton send = Toolkit.simpleButton("Send", "pixelArtFont.ttf");
        panel.add(label); // Components Added using Flow Layout
        panel.add(textField);
        panel.add(send);
        
        //Style
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(35f);
        label.setFont(customFont);
        label.setForeground(Color.WHITE);
        textPane.setFont(customFont);
        textPane.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        
        textPane.setForeground(Color.WHITE);
        
        
        
        frame.getRootPane().setDefaultButton(send);
        frame.setBackground(Color.PINK);

     
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, textPane);
        frame.setVisible(true);
       
        //Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        textPane.setText("You take your first step into the dungeon. Its terrifying depth lies in front of you.." + newline + "Even you, " + GuiGameMenu.username + ", the fearless adventurer, can feel shivers running down your spine. A great challenge stand before you. Today you will either walk out as a hero or remain forgotten within the depths of the dungeon" + newline);
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				textField.setText("");
				textPane.append(str + newline);
				
			}
        });
       
	}

}