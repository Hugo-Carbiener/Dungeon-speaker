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

        //Creating the panel at bottom and adding components
        JPanel topPanel = new JPanel();
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Describe your action :  ");        
        // Text Area at the Center
       
        
        
        

        
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

        topPanel.setBackground(Color.RED);
        panel.setBackground(Color.BLUE);
        
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("The story:"), gbc);
        gbc.gridy++;
        gbc.weighty = 0;
        frame.getRootPane().setDefaultButton(send);
        frame.setBackground(Color.PINK);

        String strtMsg = "<html>You take your first step into the dungeon. Its terrifying depth lies in front of you.." + newline + "Even you, " + GuiGameMenu.username + ", the fearless adventurer, can feel shivers running down your spine. A great challenge stand before you. Today you will either walk out as a hero or remain forgotten within the depths of the dungeon</html>";
        topPanel.add(new JLabel(strtMsg), gbc);
        gbc.gridy++;
        gbc.gridy++;
     
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        //frame.getContentPane().add(BorderLayout.CENTER, textPane);
        JScrollPane scrollPane = new JScrollPane(topPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
//        for (int index = 0; index < 100; index++) {
//            topPanel.add(new JLabel("Row " + index), gbc);
//            gbc.gridy++;
//        }
        
        //Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Action listener of the button
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				textField.setText("");
				JLabel temp = new JLabel(str);
				temp.setBorder(BorderFactory.createBevelBorder(1));
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