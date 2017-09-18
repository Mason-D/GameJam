package team.ljm.game.objects;

import org.lwjgl.input.Keyboard;

import team.ljm.display.TextureManager;
import team.ljm.game.Game;
import team.ljm.game.Location;
import team.ljm.game.objects.obstacles.Platform;
import team.ljm.game.stage.StageManager;

public class Player extends CollisionObject {

	private boolean onGround;
	private boolean onPlatform;

	private boolean jumping;
	private int maxJump;

	private float jumpingDif;
	private Location startLocation;
	private Game game;

	public Player(Location startLocation, Game game) {
		super(startLocation, TextureManager.getTexture("player"));
		this.startLocation = startLocation;
		this.game = game;
		this.jumping = false;
		this.onGround = true;
		this.onPlatform = false;
		this.maxJump = 0;
	}

	/*
	 * Method that handles player movement
	 */
	public void handleMovement() {
		if (this.getY() < StageManager.PATH)
			this.onGround = false;
		else {
			this.onGround = true;
			this.jumping = false;
		}

		boolean tempCheck = false;
		for (Platform plat : this.game.getPlatforms()) {
			if (this.getY() == plat.getY() - this.getTexture().getImageHeight()
					&& this.getX() + this.getTexture().getImageWidth() >= plat.getX()
					&& this.getX() <= plat.getX() + plat.getWidth() && this.jumpingDif <= 0) {
				this.onPlatform = true;
				this.jumping = false;
				tempCheck = true;
			}
		}
		if (!tempCheck)
			this.onPlatform = false;

		if (!onGround && !onPlatform) {
			this.handleGravity();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (!this.jumping && (this.onGround || this.onPlatform))
				this.jump();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.moveToLeft();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.moveToRight();
		}
	}

	public void moveToRight() {
		this.setX(this.getX() + 6f);
	}

	public void moveToLeft() {
		this.setX(this.getX() - 6f);
	}

	public void jump() {
		this.maxJump = 0;
		this.onGround = false;
		this.jumping = true;
		this.jumpingDif = 20F;
		this.setY(this.getY() - 1F);
	}

	private void changeY(float distance) {
		if (this.onPlatform || this.onGround)
			return;
		if (this.getY() - distance < 0) {
			this.jumpingDif = 0;
			return;
		}
		outer: for (int i = 0; i < Math.abs(distance); i++) {
			this.setY(this.getY() - (distance >= 0 ? 1 : -1));
			for (Platform plat : this.game.getPlatforms()) {
				if (this.jumpingDif <= 0 && this.getY() == plat.getY() - this.getTexture().getImageHeight()
						&& this.getX() + this.getTexture().getImageWidth() >= plat.getX()
						&& this.getX() <= plat.getX() + plat.getWidth()) {
					this.onPlatform = true;
					this.jumping = false;
					this.jumpingDif = 0;
					this.setY(plat.getY() - this.getTexture().getImageHeight());
					break outer;
				}
			}
		}
	}

	public void handleGravity() {
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && this.maxJump < 15 && this.jumpingDif > 0) {
			this.maxJump++;
		} else {
			this.jumpingDif--;
		}
		this.changeY(jumpingDif);
		if (this.getY() > StageManager.PATH)
			this.setY(StageManager.PATH);
	}

	/*
	 * If the player is killed, the location is set back to the start location.
	 */
	public void kill() {
		this.setX(this.startLocation.getX());
		this.setY(this.startLocation.getY());
		if (!this.onGround) {
			this.onGround = true;
			this.jumpingDif = 0;
		}
	}
}
