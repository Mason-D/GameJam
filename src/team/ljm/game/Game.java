package team.ljm.game;

import team.ljm.Main;
import team.ljm.display.DisplayObject;
import team.ljm.display.TextureManager;

public class Game {

	private Main main;


	private GameState gameState;

	public Game(Main main) {
		this.main = main;
	}

	public void tick() {
	}

	public GameState getState() {
		return this.gameState;
	}

	public void setGameState(GameState state) {
		this.gameState = state;
		
		switch (this.gameState) {
		case MENU:
			DisplayObject background = new DisplayObject(0, 0, TextureManager.getTexture("background"));
			this.main.getWindow().registerDisplayObject(background);
			break;
		case GAME:
			
			break;
		default:
			break;
		}
	}

}
