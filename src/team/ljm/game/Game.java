package team.ljm.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

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
				Display.destroy(); //when in the menu key if we click escape then we will exit program
				System.exit(0); 					
				}
			//TO DO: Add a mouse listener that checks if we click on start button (if true switch state to Frozen state) 
			// if we click exit button do exactly the same as the escape key. 
			break;
			
		case INTRO: // in this state we only wait for enter or escape keys and display the Text about the game story
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy();
				System.exit(0);
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				setGameState(GameState.MENU); //if enter is pressed we go to the next state which is the menu. 
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
		case INTRO:
			
			break;
		default:
			break;
		}
	}

}
