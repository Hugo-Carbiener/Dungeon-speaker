package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import game.Game;


public class GuiGameMenu implements ActionListener{
	
	public static String username; 
	
	public GuiGameMenu() throws FontFormatException, IOException {
		//Creating the Frame
        JFrame frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Username");
        
        //Text field
        JTextField tf = Toolkit.textField("pixelArtFont.ttf"); // accepts upto 10 characters
        //Button
        JButton send = Toolkit.simpleButton("Send", "pixelArtFont.ttf");
        
        send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				username = tf.getText();
				Game.init();

				try {
					new GuiGameWindow();
				} catch (FontFormatException | IOException e) {
					
					e.printStackTrace();
				}
				
				frame.setVisible(false);//prevent windows from overlapping and stop user from reclicking start and generating another menu window
				
			}	
        });
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        
        //Style 
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/pixelArtFont.ttf")).deriveFont(35f);
        panel.setBackground(Color.BLACK);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setFont(customFont);
        frame.getRootPane().setDefaultButton(send);
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        //frame.getContentPane().add(BorderLayout.SOUTH, ta);
        frame.setVisible(true);
	}
}

