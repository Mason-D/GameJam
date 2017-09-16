package team.ljm.game.objects;

import org.newdawn.slick.opengl.Texture;

import team.ljm.display.DisplayObject;
import team.ljm.game.Location;

public class CollisionObject extends DisplayObject {
	public CollisionObject(Location location, Texture texture) {
		super(location.getX(), location.getY(), texture);
	}
}
