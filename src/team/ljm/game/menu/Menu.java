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

	private DisplayObject title;
	private DisplayObject background;

	public Menu(Game game) {
		this.game = game;
		Location startLocation = new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("start").getImageWidth() / 2),
				(Display.getHeight() / 2));
		Location exitLocation = startLocation.add(0, 1.95F * TextureManager.getTexture("start").getImageHeight());
		this.start = new MenuObject(startLocation, true);
		this.exit = new MenuObject(exitLocation, false);
		background = new DisplayObject(0, 0, TextureManager.getTexture("background"));
		this.title = new DisplayObject(
				(Display.getWidth() / 2F) - (TextureManager.getTexture("title").getImageWidth() / 2F),
				TextureManager.getTexture("title").getImageHeight() * 1.5F, TextureManager.getTexture("title"));
	}

	public void open() {
		this.game.getMain().getWindow().registerDisplayObject(this.background);
		this.game.getMain().getWindow().registerDisplayObject(this.start);
		this.game.getMain().getWindow().registerDisplayObject(this.exit);
		this.game.getMain().getWindow().registerDisplayObject(this.title);

	}

	public void close() {
		this.game.getMain().getWindow().deregisterDisplayObject(this.background);
		this.game.getMain().getWindow().deregisterDisplayObject(this.start);
		this.game.getMain().getWindow().deregisterDisplayObject(this.exit);
		this.game.getMain().getWindow().deregisterDisplayObject(this.title );

	}

	public void click(Location location) {
		location.setY(Display.getHeight() - location.getY());
		if (Location.locationWithinBox(location, this.start.getUpperLeft(), this.start.getLowerRight())) {
			this.game.setGameState(GameState.PAUSED);
		} else if (Location.locationWithinBox(location, this.exit.getUpperLeft(), this.exit.getLowerRight())) {
			Display.destroy();
			System.exit(0);
		}
	}
}
