package game;

public class GameState {
	
	private String gamestate;
	
	
	public static GameState init() {
		GameState gameState = new GameState();
		gameState.gamestate = "TitleScreen";
		return gameState;
	}
	
	public String getGameState() {return this.gamestate;}
	public void setGamestate(String gamestate) {this.gamestate = gamestate;}
}
