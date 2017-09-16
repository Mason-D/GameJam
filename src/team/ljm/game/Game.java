package team.ljm.game;

import team.ljm.Main;

public class Game {

	private Main main;

	private GameState gameState;

	public Game(Main main) {
		this.main = main;
		gameState = GameState.INTRO;
	}

	public GameState getState() {
		return this.gameState;
	}

	public void setGameState(GameState state) {
		this.gameState = state;
	}

}
