package team.ljm.display;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class DisplayObject {

	private float x, y;
	private Texture texture;

	public DisplayObject(float x, float y, Texture texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
	}

	public void draw() {
		this.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(this.x, this.y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(this.x + texture.getTextureWidth(), this.y);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(this.x + texture.getTextureWidth(), this.y + texture.getTextureHeight());
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(this.x, this.y + texture.getTextureHeight());
		GL11.glEnd();
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public void setX(float x) {
		if (x < 0)
			x = 0;
		if (x > Display.getWidth())
			x = Display.getWidth();
		this.x = x;
	}

	public void setY(float y) {
		if (y < 0)
			y = 0;
		if (y > Display.getHeight())
			y = Display.getHeight();
		this.y = y;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
