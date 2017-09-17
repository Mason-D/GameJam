package team.ljm.game.objects.obstacles;
import team.ljm.display.TextureManager;
import team.ljm.game.Location;
import team.ljm.game.objects.CollisionObject;

public class Platform extends CollisionObject {

	/*
	 * Constructor for objects of type platform.
	 */
	public Platform (Location location) {
		super(location, TextureManager.getTexture("Platform"));	

	}
	
	/*
	 * Returns width of the platform.
	 */
	public float getWidth() {
		return this.getTexture().getWidth();
	}
	
	/*
	 * Returns height of the platform.
	 */
	public float getHeight() {
		return this.getTexture().getHeight();
	}
}
