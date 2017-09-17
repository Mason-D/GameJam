package team.ljm.game.objects;

import org.lwjgl.input.Keyboard;

import team.ljm.display.TextureManager;
import team.ljm.game.Location;

public class Player extends CollisionObject {
	
	private boolean jumping = false;
	private float jumpingDif;
	
	
	public Player(Location startLocation) {
		super(startLocation, TextureManager.getTexture("player"));
	}
	
	/*
	 * Method that handles player movement
	 */
	public void handleMovement() {
		
		if(jumping) {
			this.goUp();
		} else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jumpingDif = 30f;
			this.jump();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.moveToLeft();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.moveToRight();
		} 
	
	}
	
	
	public void moveToRight() {
		this.setX(this.getX() + 5f);
	}
	
	public void moveToLeft() {
		this.setX(this.getX() - 5f);
	}
	
	public void jump() {
		jumping = true;
	}
	
	public void goUp() {
		this.setY(this.getY() - jumpingDif);
		jumpingDif--;
		if(jumpingDif < -30f)
			jumping = false;
	}
	
	/*
	 * If the player is killed, the location is set back to the 
	 * start location.
	 */
	public void kill() {
		this.setX(100);
		this.setY(500);	
	}
}
