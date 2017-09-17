package team.ljm.game.menu;

import team.ljm.display.DisplayObject;
import team.ljm.display.TextureManager;
import team.ljm.game.Location;

public class MenuObject extends DisplayObject {

	public MenuObject(Location location, boolean startButton) {
		super(location.getX(), location.getY(),
				startButton ? TextureManager.getTexture("startButton") : TextureManager.getTexture("exitButton"));
	}

}
