package team.ljm.display;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import team.ljm.Main;

public class DisplayWindow {
	private static final int WIDTH = 1920, HEIGHT = 1080;
	private static final String TITLE = "CHANGEME";

	private Main main;
	private List<DisplayObject> displayObjects;

	public DisplayWindow(Main main) {
		this.main = main;
		this.displayObjects = new ArrayList<DisplayObject>();
	}

	private void setupWindow() {
		try {
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			Display.setFullscreen(true);
			Display.setTitle(TITLE);
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException ex) {
			ex.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}

	private void setupOpenGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
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
		for (DisplayObject displayObj : this.displayObjects)
			displayObj.draw();
	}

	public void registerDisplayObject(DisplayObject displayObject) {
		this.displayObjects.add(displayObject);
	}

	public void deregisterDisplayObject(DisplayObject displayObject) {
		if (!this.displayObjects.contains(displayObject))
			throw new IllegalArgumentException("That displayObject is not registered!");
		this.displayObjects.remove(displayObject);
	}
}
