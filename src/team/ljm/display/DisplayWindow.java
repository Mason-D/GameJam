package team.ljm.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayWindow {
	private static final int WIDTH = 1920, HEIGHT = 1080;
	private static final String TITLE = "CHANGEME";

	public DisplayWindow() {
	}

	private void setupWindow() {
		try {
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			Display.setFullscreen(true);
			Display.setTitle(TITLE);
			Display.create();
		} catch (LWJGLException ex) {
			ex.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}

	private void setupOpenGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public void setup() {
		this.setupWindow();
		this.setupOpenGL();
	}

	public void start() {
		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			this.render();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}

	private void render() {
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(100, 100);
		GL11.glEnd();
	}
}
