package team.ljm.game.objects.obstacles;

import org.newdawn.slick.opengl.Texture;

import team.ljm.display.TextureManager;
import team.ljm.game.Location;
import team.ljm.game.objects.CollisionObject;
import team.ljm.game.objects.Player;

public class Fire extends CollisionObject {

	private int animation;

	private Texture fire1, fire2, fire3;

	public Fire(Location location) {
		super(location, TextureManager.getTexture("fire1"), 0);
		animation = 0;
		this.fire1 = TextureManager.getTexture("fire1");
		this.fire2 = TextureManager.getTexture("fire2");
		this.fire3 = TextureManager.getTexture("fire3");
	}

	public void burn(Player player) {
		if (animation == 7)
			this.setTexture(this.fire2);
		else if (animation == 14)
			this.setTexture(this.fire3);
		else if (animation == 21) {
			this.setTexture(this.fire1);
			this.animation = 0;
		}
		if (this.collides(new Location(player.getX(), player.getY()))
				|| this.collides(new Location(player.getX() + player.getTexture().getImageWidth(),
						player.getY() + player.getTexture().getImageHeight()))) {
			player.kill();
		}
		animation++;
	}
}
