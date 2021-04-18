package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuiTitleScreen implements ActionListener {

	public GuiTitleScreen() {
		JFrame frame = new JFrame();
		frame.setSize(1920, 1080);
		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GuiGameMenu();
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);
		
		frame.add(panel,BorderLayout.CENTER);
        frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dungeon Speaker");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
}