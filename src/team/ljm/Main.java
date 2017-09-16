package team.ljm;

import team.ljm.display.DisplayWindow;
import team.ljm.display.TextureManager;
import team.ljm.game.Game;

public class Main {
	private DisplayWindow window;
	private TextureManager textureManager;
	private Game game;

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public Main() {
		this.window = new DisplayWindow(this);
		this.textureManager = new TextureManager(this);
		this.game = new Game(this);
	}

	private void run() {
		this.window.setup();
		this.textureManager.loadTextures();
		this.window.start();
	}
	
	public TextureManager getTextureManager() {
		return this.textureManager;
	}
	
	public DisplayWindow getWindow() {
		return this.window;
	}

}
