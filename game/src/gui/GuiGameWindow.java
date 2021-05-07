package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class GuiGameWindow implements ActionListener {
	
	private final static String newline = "\n";
	public static int pos = 0;
	
	public GuiGameWindow() throws FontFormatException, IOException {
		//Creating the Frame
        JFrame frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Describe your action :  ");        
        JTextField tf = new JTextField(40); // accepts upto 40 characters
        // Text Area at the Center
        JTextPane textPane = new JTextPane();
        StyledDocument doc = (StyledDocument)textPane.getDocument();
        textPane.setEditable(false);

        //Button
        JButton send = Toolkit.simpleButton("Send", "pixelArtFont.ttf");
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        
        //Style
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(60f);
        label.setFont(customFont);
        label.setForeground(Color.WHITE);
        textPane.setBackground(Color.BLACK);
        textPane.setFont(customFont);
        panel.setBackground(Color.BLACK);
        
        Style defaut = textPane.getStyle("default");
        
        //Create style for user inputs
        Style userInputStyle = textPane.addStyle("userInputStyle", defaut);
        StyleConstants.setForeground(userInputStyle, Color.CYAN);
        StyleConstants.setFontSize(userInputStyle, 15);

        //Create style for game displays
        Style gameInputStyle = textPane.addStyle("gameInputStyle", defaut);
        StyleConstants.setForeground(gameInputStyle, Color.RED);
        StyleConstants.setFontSize(gameInputStyle, 15);
        
        frame.getRootPane().setDefaultButton(send);
        frame.setBackground(Color.PINK);

     
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        
        frame.getContentPane().add(BorderLayout.CENTER, textPane);

        frame.setVisible(true);
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = tf.getText();
			    try {
			    	doc.insertString(pos,newline + str, userInputStyle);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			    
			    pos += str.length() + 1;
				tf.setText("");
			}
        });
       
	}

}