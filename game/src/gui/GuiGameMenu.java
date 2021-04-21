package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class GuiGameMenu implements ActionListener{
	
	public static String username; 
	
	public GuiGameMenu() throws FontFormatException, IOException {
		//Creating the Frame
        JFrame frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Username");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        //JButton send = new JButton("Send");
        JButton send = Toolkit.simpleButton("Send", "pixelArtFont.ttf");
        
        send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new GuiGameWindow();
				} catch (FontFormatException | IOException e) {
					
					e.printStackTrace();
				}
				
				frame.setVisible(false);//prevent windows from overlapping and stop user from reclicking start and generating another menu window
				username = tf.getText();
				
			}	
        });
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        

        // Text Area at the Center
        //JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        //frame.getContentPane().add(BorderLayout.SOUTH, ta);
        frame.setVisible(true);
	}
}

