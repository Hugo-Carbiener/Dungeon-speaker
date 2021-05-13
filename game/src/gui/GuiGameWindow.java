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
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import NLP.NLPManager;
import game.Game;


public class GuiGameWindow implements ActionListener {
	
	private static String newline = "\n";
	private static StyledDocument doc;
	private static int pos = 0;
	private static Style defaultStyle;
	private static Style userInputStyle;
	private static Style gameInputStyle;
	private static String currentInput;
	private static volatile boolean inputIsUpdated = false;

	public static void GuiDefaultDisplay(String string) {
		try {
			doc.insertString(pos, newline + string, defaultStyle);
			pos += string.length() + 1;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public static void GuiGameDisplay(String string) {
		try {
			String arrows = ">>";
			doc.insertString(pos, newline + arrows, defaultStyle);
			pos += arrows.length() + 1;
			doc.insertString(pos,string, gameInputStyle);
			pos += string.length();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrentInput() {return currentInput;}
	public static boolean getInputUpdateState() {return inputIsUpdated;}
	public static void setInputState(boolean state) {inputIsUpdated = state;}
	
	public GuiGameWindow() throws FontFormatException, IOException{
		//Creating the Frame
        JFrame frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Describe your action :  ");        
        // Text Area at the Center
        JTextPane textPane = new JTextPane();
        doc = (StyledDocument)textPane.getDocument();
        textPane.setEditable(false);
        
        //Scrollbar
        JScrollPane sp = new JScrollPane(textPane);
        
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
        
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        sp.setSize(10, 800);
        
        Style defaut= textPane.getStyle("default");
        
        //Create style for arrows
        defaultStyle = textPane.addStyle("defaultStyle", defaut);
        StyleConstants.setForeground(defaultStyle, Color.WHITE);
        StyleConstants.setFontSize(defaultStyle, 22);
        
        //Create style for user inputs
        userInputStyle = textPane.addStyle("userInputStyle", defaut);
        StyleConstants.setForeground(userInputStyle, Color.CYAN);
        StyleConstants.setFontSize(userInputStyle, 18);

        //Create style for game displays
        gameInputStyle = textPane.addStyle("gameInputStyle", defaut);
        StyleConstants.setForeground(gameInputStyle, Color.RED);
        StyleConstants.setFontSize(gameInputStyle, 22);
        
        frame.getRootPane().setDefaultButton(send);
        frame.setBackground(Color.PINK);

     
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, textPane);
        frame.setVisible(true);
       
        //Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        GuiGameDisplay("You take your first step into the dungeon. Its terrifying depth lies in front of you.." + newline + "Even you, " + GuiGameMenu.username + ", the fearless adventurer, can feel shivers running down your spine. A great challenge stand before you. Today you will either walk out as a hero or remain forgotten within the depths of the dungeon");
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				
				//Process input using nlp
				try {
					currentInput = NLPManager.startNLP(str);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Display the user's input
				try {
					doc.insertString(pos,newline + str, userInputStyle);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				
			    pos += str.length() + 1;
				textField.setText("");
				
				//Unfreeze the game loop after waiting for the input
				inputIsUpdated = true;
				synchronized (Game.loopThread) {
					Game.loopThread.notify();
				}
			}
        });
       
	}

}