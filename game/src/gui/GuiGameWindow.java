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
        //Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Creating the panel that will contain the text
        JPanel topPanel = new JPanel();
        
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
        

        //Create the layout for the top panel and allows to have messages properly displayed
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;	//Stick text to the left of the screen
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("The story:"), gbc);//Used as a trick to keep all residual space between this string and the text we display at the bottom
        gbc.gridy++;								//Increment position of writing 
        gbc.weighty = 0;
        
        
        
        frame.setBackground(Color.PINK);

        String strtMsg = "<html>You take your first step into the dungeon. Its terrifying depth lies in front of you.." + newline + "Even you, " + GuiGameMenu.username + ", the fearless adventurer, can feel shivers running down your spine. A great challenge stand before you. Today you will either walk out as a hero or remain forgotten within the depths of the dungeon</html>";
        topPanel.add(new JLabel(strtMsg), gbc);
        gbc.gridy++;
        gbc.gridy++;
     
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
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(35f);
        bottomLabel.setFont(customFont);
        bottomLabel.setForeground(Color.WHITE);
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        bottomPanel.setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
//        for (int index = 0; index < 100; index++) {
//            topPanel.add(new JLabel("Row " + index), gbc);
//            gbc.gridy++;
//        }
        
        
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				textField.setText("");
				JLabel temp = new JLabel(str);
				temp.setBackground(Color.PINK);
				temp.setVerticalAlignment(JLabel.TOP);
				topPanel.add(temp, gbc);
				gbc.gridy++;
				frame.validate();
				frame.repaint();
			}
        });
       
	}

}