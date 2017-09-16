package team.ljm.display;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class DisplayObject {

	private float x, y;
	private Texture texture;
	private float width, height;

	public DisplayObject() {
		this(0, 0, null);
	}

	public DisplayObject(float x, float y) {
		this(x, y, null);
	}

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

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
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

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
