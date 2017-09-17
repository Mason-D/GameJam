package team.ljm.game.objects;

import org.newdawn.slick.opengl.Texture;

import team.ljm.display.DisplayObject;
import team.ljm.game.Location;

public class CollisionObject extends DisplayObject {
	private int additionalHitbox;

	public CollisionObject(Location location, Texture texture, int additionalHitbox) {
		super(location.getX(), location.getY(), texture);
		this.additionalHitbox = additionalHitbox;
	}

	public CollisionObject(Location location, Texture texture) {
		this(location, texture, 5);
	}

	public boolean collides(Location location) {
		return (this.getX() + additionalHitbox) >= location.getX()
				&& (this.getY() + additionalHitbox) >= location.getY()
				&& (this.getX() - additionalHitbox) <= (location.getX() + this.getTexture().getWidth())
				&& (this.getY() - additionalHitbox) <= (location.getY() + this.getTexture().getHeight());
	}
}
