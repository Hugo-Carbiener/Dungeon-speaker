package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;

public class GuiVictoryScreen implements ActionListener {

	public GuiVictoryScreen() throws FontFormatException, IOException {
		
		JFrame frame = new JFrame();
		//Add Start button
		JButton button = Toolkit.simpleButton("Restart", "pixelArtFont.ttf");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new GuiGameMenu();
				} catch (FontFormatException | IOException e) {
					e.printStackTrace();
				}
				
				frame.setVisible(false);//prevent windows from overlapping and stop user from reclicking start and generating another menu window
			}
			
		});
		
		//Add titlescreen image
		
		//resize image
		
		ImageIcon imageIcon = new ImageIcon("src/res/dungeon_speaker_victory.png");
		Image image = imageIcon.getImage();//turn it into an image
		Image newimg = image.getScaledInstance(1420, 780,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);//turn it back into an imageIcon
		
		JLabel imgHolder = new JLabel();
		imgHolder.setIcon(imageIcon);
		
		//Create panels for each element
		JPanel buttonPanel = new JPanel();
		JPanel imagePanel = new JPanel();
		buttonPanel.setBackground(Color.black);
		imagePanel.setBackground(Color.black);
		buttonPanel.setSize(400, 400);

		//Add elements to the panels 
		imagePanel.add(imgHolder);
		buttonPanel.add(button);
		
		//Set custom size for the button
		buttonPanel.setPreferredSize(new Dimension(400, 800));
		
		
		JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.add(imagePanel);
	    mainPanel.add(buttonPanel);
	    frame.getRootPane().setDefaultButton(button);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//fullscreen
		frame.add(mainPanel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dungeon Speaker");
		frame.pack();
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}	
}
