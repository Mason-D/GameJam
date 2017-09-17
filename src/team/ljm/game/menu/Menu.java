package team.ljm.game.menu;

import org.lwjgl.opengl.Display;

import team.ljm.display.DisplayObject;
import team.ljm.display.TextureManager;
import team.ljm.game.Game;
import team.ljm.game.GameState;
import team.ljm.game.Location;

public class Menu {

	private Game game;

	private MenuObject start;
	private MenuObject exit;

	private DisplayObject background;

	public Menu(Game game) {
		this.game = game;
		Location startLocation = new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("start").getTextureWidth() / 2),
				(Display.getHeight() / 2) - (TextureManager.getTexture("start").getTextureHeight() / 2));
		Location exitLocation = startLocation.add(0, TextureManager.getTexture("start").getTextureHeight() + 25);
		this.start = new MenuObject(startLocation, true);
		this.exit = new MenuObject(exitLocation, false);
		background = new DisplayObject(0, 0, TextureManager.getTexture("background"));
	}

	public void open() {
		this.game.getMain().getWindow().registerDisplayObject(this.background);
		this.game.getMain().getWindow().registerDisplayObject(this.start);
		this.game.getMain().getWindow().registerDisplayObject(this.exit);

	}

	public void close() {
		this.game.getMain().getWindow().deregisterDisplayObject(this.background);
		this.game.getMain().getWindow().deregisterDisplayObject(this.start);
		this.game.getMain().getWindow().deregisterDisplayObject(this.exit);
	}

	public void click(Location location) {
		System.out.println(location + "\n" + this.start.getUpperLeft() + "\n" + this.start.getLowerRight() + "\n");
		if (Location.locationWithinBox(location, this.start.getUpperLeft(), this.start.getLowerRight())) {
			this.game.setGameState(GameState.GAME);
		} else if (Location.locationWithinBox(location, this.exit.getUpperLeft(), this.exit.getLowerRight())) {
			Display.destroy();
			System.exit(0);
		}
	}
}
