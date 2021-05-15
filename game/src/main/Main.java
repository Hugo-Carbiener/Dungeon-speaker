package main;


import NLP.NLPManager;

import dungeon.Map;
import dungeon.Room;
import game.Game;
import player.Hero;
import player.Item;
import player.Weapon;
import gui.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//Game.start();
		String string = "I walk back";
		String[] currentInput = NLPManager.startNLP(string);
		for (String each : currentInput) {
			System.out.println(each);
			}
		}
	}
	