package team.ljm.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

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
				try {
					Display.destroy();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
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
		default:
			break;
		}
	}

	public Main getMain() {
		return this.main;
	}

}
