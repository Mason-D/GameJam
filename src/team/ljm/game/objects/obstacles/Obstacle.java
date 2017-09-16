package team.ljm.game.objects.obstacles;

import team.ljm.display.TextureManager;
import team.ljm.game.Location;
import team.ljm.game.objects.CollisionObject;

public class Obstacle extends CollisionObject {

	public Obstacle(Location location, ObstacleType type) {
		super(location, TextureManager.getTexture(type.getFileName()));
	}

}
