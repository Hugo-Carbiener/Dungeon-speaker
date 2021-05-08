package main;


import NLP.NLPManager;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//new GuiTitleScreen();
		String str1 = "I grab the sword";
		String str2 = "I grab the apple";
		String str3 = "I walk back";
		String str4 = "I attack the monster";
		System.out.println(NLPManager.startNLP(str1));
		System.out.println(NLPManager.startNLP(str2));
		System.out.println(NLPManager.startNLP(str3));
		System.out.println(NLPManager.startNLP(str4));
		
		
	}
}
	