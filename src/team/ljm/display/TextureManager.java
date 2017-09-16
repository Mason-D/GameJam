package team.ljm.display;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import team.ljm.Main;

public class TextureManager {
	private HashMap<String, Texture> textures;

	private Main main;

	public TextureManager(Main main) {
		this.main = main;
		this.textures = new HashMap<String, Texture>();
	}

	public void loadTextures() {
		textures.put("test", this.loadTexture("test"));
	}

	private Texture loadTexture(String name) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + name + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Texture getTexture(String name) {
		return this.textures.get(name);
	}
}
