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

public class GuiTitleScreen implements ActionListener {

	public GuiTitleScreen() throws FontFormatException, IOException {
		
		JFrame frame = new JFrame();
		//Add Start button
		JButton button = Toolkit.simpleButton("Start", "pixelArtFont.ttf");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GuiGameMenu();
				frame.setVisible(false);//prevent windows from overlapping and stop user from reclicking start and generating another menu window
			}
			
		});
		
		//Add titlescreen image
		
		//resize image
		ImageIcon imageIcon = new ImageIcon("src/res/temp_large.jpg");
		Image image = imageIcon.getImage();//turn it into an image
		Image newimg = image.getScaledInstance(1920, 1080,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);//turn it back into an imageIcon
		
		JLabel imgHolder = new JLabel();
		imgHolder.setIcon(imageIcon);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(2, 1));
		panel.add(imgHolder);
		panel.add(button);
		
		
		
		frame.add(panel,BorderLayout.CENTER);
        //frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dungeon Speaker");
		frame.pack();
		frame.setVisible(true);
		
		
	}	
}