package team.ljm;

import team.ljm.display.DisplayWindow;
import team.ljm.display.TextureManager;
import team.ljm.game.Game;
import team.ljm.game.GameState;

public class Main {
	private DisplayWindow window;
	private TextureManager textureManager;
	private Game game;

	public Main() {
		this.window = new DisplayWindow(this);
		this.textureManager = new TextureManager(this);
		this.game = new Game(this);
	}

	private void run() {
		this.window.setup();
		this.textureManager.loadTextures();
		this.game.setGameState(GameState.INTRO);
		this.window.start();

	}

	public Game getGame() {
		return this.game;
	}

	public TextureManager getTextureManager() {
		return this.textureManager;
	}

	public DisplayWindow getWindow() {
		return this.window;
	}

	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
}
