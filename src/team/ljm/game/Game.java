package team.ljm.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import team.ljm.Main;
import team.ljm.game.menu.Menu;

public class Game {

	private Main main;

	private Menu menu;

	private GameState gameState;

	public Game(Main main) {
		this.main = main;
	}

	public void tick() {
		switch (this.gameState) {
		case MENU:
			if (Mouse.isButtonDown(0)) {
				this.menu.click(new Location(Mouse.getX(), Mouse.getY()));
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy(); // when in the menu key if we click escape then we will exit program
				System.exit(0);
			}
			// TO DO: Add a mouse listener that checks if we click on start button (if true
			// switch state to Frozen state)
			// if we click exit button do exactly the same as the escape key.
			break;

		case INTRO: // in this state we only wait for enter or escape keys and display the Text
					// about the game story
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy();
				System.exit(0);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				setGameState(GameState.MENU); // if enter is pressed we go to the next state which is the menu.
			break;
		case PAUSED:
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
				setGameState(GameState.MENU);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}

	}

	public GameState getState() {
		return this.gameState;
	}

	public void setGameState(GameState state) {
		GameState lastState = this.gameState;
		this.gameState = state;

		if (lastState == GameState.MENU)
			this.menu.close();
		switch (this.gameState) {
		case MENU:
			this.menu = new Menu(this);
			this.menu.open();
			break;
		case GAME:

			break;
		case INTRO:

			break;
		default:
			break;
		}
	}

	public Main getMain() {
		return this.main;
	}

}
