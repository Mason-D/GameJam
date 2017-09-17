package team.ljm.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

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
		switch(this.gameState) {
		case MENU: 
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				try {
					Display.destroy();
				} catch(Exception e){
					e.printStackTrace();	
				}
					
				}
			}
				
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
