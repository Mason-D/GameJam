package team.ljm.game.objects;

import team.ljm.display.TextureManager;
import team.ljm.game.Location;

public class Player extends CollisionObject {
	public Player(Location startLocation) {
		super(startLocation, TextureManager.getTexture("player"));
	}
}
