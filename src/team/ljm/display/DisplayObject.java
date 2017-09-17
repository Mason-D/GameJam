package team.ljm.display;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class DisplayObject {

	private float x, y;
	private Texture texture;
	private boolean flipped;

	public DisplayObject(float x, float y, Texture texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.flipped = false;
	}

	public void draw() {
		this.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		if (flipped) {
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(this.x + texture.getTextureWidth(), this.y);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(this.x, this.y);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(this.x, this.y + texture.getTextureHeight());
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(this.x + texture.getTextureWidth(), this.y + texture.getTextureHeight());
		} else {
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(this.x, this.y);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(this.x + texture.getTextureWidth(), this.y);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(this.x + texture.getTextureWidth(), this.y + texture.getTextureHeight());
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(this.x, this.y + texture.getTextureHeight());
		}
		GL11.glEnd();
	}

	public void flip() {
		this.flipped = !this.flipped;
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
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
