package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;

public class GuiTitleScreen implements ActionListener {

	public GuiTitleScreen() {
		JFrame frame = new JFrame();
		JButton button = new JButton("Start");
		ImageIcon img = new ImageIcon("src/resources/temp.jpeg");
		JLabel imgHolder = new JLabel();
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GuiGameMenu();
			}
			
		});
		
		imgHolder.setIcon(img);
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