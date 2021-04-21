package main;


import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dungeon.Map;
import dungeon.Room;
import player.Hero;
import player.Item;
import player.Monster;
import player.Weapon;
import gui.*;

public class Main {
	
	public static void main(String[] args) throws FontFormatException, IOException {
		
		new GuiTitleScreen();
	}
}
	
