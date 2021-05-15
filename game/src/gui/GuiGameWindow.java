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


public class GuiGameWindow implements ActionListener {
	
	private static String newline = "<br/";
	private static JFrame frame;
	private static GridBagConstraints gbc;
	private static JPanel topPanel;
	private static Font customFontLarge;
	private static Font customGameFont;
	private static Font customPlayerFont;
	
	public static void GuiGameDisplay(String string) {
			String str = ">>" + string;
			JLabel lbl = new JLabel(str);
			lbl.setForeground(Color.WHITE);
			lbl.setFont(customGameFont);
			topPanel.add(lbl, gbc);
			gbc.gridy++;
			frame.validate();
			frame.repaint();
	}
	
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
        customGameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(22f);
        customPlayerFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(20f);


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
        //Print the start message
        String strtMsg = "You take your first step into the dungeon. Its terrifying depth lies in front of you.."; 
        GuiGameDisplay(strtMsg);
        /*temp = new JLabel(strtMsg);
        topPanel.add(temp, gbc);
        gbc.gridy++;
        temp.setFont(customGameFont);
        temp.setForeground(Color.WHITE);*/
        strtMsg = "Even you, " + GuiGameMenu.username + ", the fearless adventurer, can feel shivers running down your spine. A great challenge stand before you. Today you will either walk out as a hero or remain forgotten within the depths of the dungeon"; 
        GuiGameDisplay(strtMsg);
        /*temp = new JLabel(strtMsg);
        temp.setFont(customGameFont);
        temp.setForeground(Color.WHITE);
        topPanel.add(temp, gbc);
        gbc.gridy++;*/

     
        //Adding Components to the frame.
        frame.getRootPane().setDefaultButton(send);
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        //Scrollbar (It is important to place it here)
        JScrollPane scrollPane = new JScrollPane(topPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.pack();
       
        //Style
        bottomLabel.setFont(customGameFont);
        bottomLabel.setForeground(Color.WHITE);
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        bottomPanel.setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Get the content of the text field
				String str = textField.getText();
				textField.setText("");

				JLabel temp = new JLabel(str);
				temp.setForeground(Color.CYAN);
				temp.setFont(customPlayerFont);
				topPanel.add(temp, gbc);
				gbc.gridy++;
				topPanel.add(new JLabel("<html> "+newline+" </html>"), gbc);//Add empty line
				frame.validate();
				frame.repaint();
			}
        });
       
	}

}