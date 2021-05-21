package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import NLP.NLPManager;
import game.Game;


public class GuiGameWindow implements ActionListener {
	
	private static JFrame frame;
	private static GridBagConstraints gbc;
	private static JPanel topPanel;
	private static JScrollPane scrollPane;
	private static Font customFontLarge;
	private static Font customFontMedium;
	
	//Variables used with nlp
	private static String[] currentInput;
	private static volatile boolean inputIsUpdated = false;
	
	
	public static void GuiGameDisplay(String string, Color color, boolean skipALine) {
		//Display the string on a single line. For multiple lines use the method multiple times
		String str = ">> " + string;
		JLabel lbl = new JLabel(str);
		lbl.setForeground(color);
		lbl.setFont(customFontMedium);
		
		topPanel.add(lbl, gbc);
		gbc.gridy++;
		
		if (skipALine) {
			//Add empty line
			topPanel.add(new JLabel("<html> <br/> </html>"), gbc);
			gbc.gridy++;
		}
		//Set scollbar focus to the bottom of the page
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		//Update the frame
		frame.validate();
		frame.repaint();
	}
	
	public static void GuiRawDisplay(String string, Color color) {
		//Display the string on a single line, no font, no line skip, no arrows
		JLabel lbl = new JLabel(string);
		lbl.setForeground(color);
		lbl.setFont(new Font("Arial", Font.PLAIN, 25
				));
		topPanel.add(lbl, gbc);
		gbc.gridy++;
		//Set scollbar focus to the bottom of the page
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		//Update the frame
		frame.validate();
		frame.repaint();
	}
	
	public static String[] getCurrentInput() {return currentInput;}
	public static boolean getInputUpdateState() {return inputIsUpdated;}
	public static void setInputState(boolean state) {inputIsUpdated = state;}
	
	public GuiGameWindow() throws FontFormatException, IOException{
		
		//Creating the Frame
        frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        //Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Creating the panel that will contain the text
        topPanel = new JPanel();
        
        //Creating the panel at bottom containing the text field and the button
        JPanel bottomPanel = new JPanel(); // the panel is not visible in output
        JLabel bottomLabel = new JLabel("Describe your action :  ");
        
        //Text field
        JTextField textField = Toolkit.textField("pixelArtFont.ttf");
        //Button
        JButton send = Toolkit.simpleButton("Send", "pixelArtFont.ttf");
        //Add components to the bottom panel
        bottomPanel.add(bottomLabel); // Components Added using Flow Layout
        bottomPanel.add(textField);
        bottomPanel.add(send);
        
        //Fonts
        customFontLarge = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(35f);
        customFontMedium = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(22f);

        //Create the layout for the top panel and allows to have messages properly displayed
        topPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;	//Stick text to the left of the screen
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel temp = new JLabel("The story:");
        temp.setFont(customFontLarge);
        temp.setForeground(Color.WHITE);
        topPanel.add(temp, gbc);//Used as a trick to keep all residual space between this string and the text we display at the bottom
        gbc.gridy++;								//Increment position of writing 
        gbc.weighty = 0;
     
        //Adding Components to the frame.
        frame.getRootPane().setDefaultButton(send);
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        //Scrollbar (It is important to place it here)
        scrollPane = new JScrollPane(topPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.pack();
       
        //Style
        bottomLabel.setFont(customFontMedium);
        bottomLabel.setForeground(Color.WHITE);
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        bottomPanel.setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
      //Print the start message
        String strtMsg = "You take your first step into the dungeon. Its terrifying depth lies in front of you..."; 
        GuiGameDisplay(strtMsg, Color.WHITE, false);
        strtMsg = "Even you, " + GuiGameMenu.username + ", the fearless adventurer, can feel shivers running down your spine. A great challenge stand before you."; 
        GuiGameDisplay(strtMsg, Color.WHITE, false);
        strtMsg = "Today you will either walk out as a hero or remain forgotten within the depths of the dungeon!"; 
        GuiGameDisplay(strtMsg, Color.WHITE, true);
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Get the content of the text field
				String str = textField.getText();
				textField.setText("");
				//Process input using nlp
				try {
					currentInput = NLPManager.startNLP(str);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Print it
				JLabel temp = new JLabel(str);
				temp.setForeground(Color.CYAN);
				temp.setFont(customFontMedium);
				topPanel.add(temp, gbc);
				gbc.gridy++;
				//Add empty line
				topPanel.add(new JLabel("<html> <br/> </html>"), gbc);
				gbc.gridy++;
				//Set scollbar focus to the bottom of the page
				JScrollBar vertical = scrollPane.getVerticalScrollBar();
				vertical.setValue( vertical.getMaximum() );
				//Update the frame
				frame.validate();
				frame.repaint();
				
				//Unfreeze the game loop after waiting for the input
				inputIsUpdated = true;
				synchronized (Game.loopThread) {
					Game.loopThread.notify();
				}
			}
        });
       
	}

}