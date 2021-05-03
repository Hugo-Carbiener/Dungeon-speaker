package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class GuiGameWindow implements ActionListener {
	private final static String newline = "\n";
	public GuiGameWindow() throws FontFormatException, IOException {
		 //Creating the Frame
        JFrame frame = new JFrame("Dungeon Speaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(40); // accepts upto 40 characters
        // Text Area at the Center
        JTextArea ta = new JTextArea("Bienvenue " +GuiGameMenu.username);
        ta.setEditable(false);
         
        JScrollPane scroll = new JScrollPane (ta);
        

        
        //JButton send = new JButton("Send");
        JButton send = Toolkit.simpleButton("Send", "pixelArtFont.ttf");
        send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = tf.getText();
				ta.append(newline + str);
				tf.setText("");
			}
        	
        });
       
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(scroll);
        
        frame.getRootPane().setDefaultButton(send);
    

     

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        
        frame.getContentPane().add(BorderLayout.CENTER, ta);

        frame.setVisible(true);
       
	}

}