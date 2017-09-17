package team.ljm.game.objects;

import org.lwjgl.input.Keyboard;

import team.ljm.display.TextureManager;
import team.ljm.game.Location;

public class Player extends CollisionObject {
	public Player(Location startLocation) {
		super(startLocation, TextureManager.getTexture("player"));
	}
	
	/*
	 * Method that handles player movement
	 */
	public void handleMovement() {
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.moveToLeft();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.moveToRight();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.jump();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.goDown();
		}
	
	}
	
	
	public void moveToRight() {
		this.setX(this.getX() + 5f);
	}
	
	public void moveToLeft() {
		this.setX(this.getX() - 5f);
	}
	
	public void jump() {
		this.setY(this.getY() - 5f);
	}
	
	public void goDown() {
		this.setY(this.getY() + 5f);
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
