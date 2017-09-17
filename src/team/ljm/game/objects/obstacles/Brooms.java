package team.ljm.game.objects.obstacles;

import org.newdawn.slick.opengl.Texture;

import team.ljm.display.TextureManager;
import team.ljm.game.Location;
import team.ljm.game.objects.CollisionObject;

public class Brooms extends CollisionObject {

	private Location broomLoc; // Location of broom on map in order to update it
	private boolean broomDir; // If the broom is moving to the right broomDir is true
	private Texture broomR, broomL; //broomR texture loaded when moving to right and broomL for left movement. 
	private float distance;
	
	public Brooms(Location location, boolean broomDir, float distance) {
		super(location, TextureManager.getTexture("broom"));
		this.distance = distance-1; //set the x distance to travel
		this.broomR = TextureManager.getTexture("broom");
		this.broomL = TextureManager.getTexture("BroomFlip");
		this.broomLoc = location; // set location so we can keep track of where it was
		this.broomDir = broomDir; // set the direction of the brooms movement.
	}

	public void sweep() {
		if (broomDir) {
			if (this.getX() >= (broomLoc.getX() + distance)) {
				broomDir = false;
				this.setTexture(broomL);
			} else {
				this.setX(this.getX() + 1f);
			}
		} else {
			if (this.getX() <= broomLoc.getX()) {
				broomDir = true;
				this.setTexture(broomR);
			} else
				this.setX(this.getX() - 1f);
		}

	}
}
