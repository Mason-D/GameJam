package team.ljm.game.stage;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import team.ljm.display.TextureManager;
import team.ljm.game.Game;
import team.ljm.game.Location;
import team.ljm.game.objects.CollisionObject;
import team.ljm.game.objects.obstacles.Brooms;
import team.ljm.game.objects.obstacles.Fire;
import team.ljm.game.objects.obstacles.Platform;

public class StageManager {

	public static final int PATH = 925;

	private boolean gameOver;

	private Game game;
	private Stage[] stages;

	private int stageIndex;

	public StageManager(Game game) {
		this.game = game;
		this.stages = new Stage[5];
		this.stageIndex = 0;
		this.gameOver = false;
	}

	public void registerObjects(Stage stage) {
		for (CollisionObject obj : stage.getObjects()) {
			this.game.getMain().getWindow().registerDisplayObject(obj);
			if (obj instanceof Fire)
				this.game.addFire((Fire) obj);
			if (obj instanceof Brooms)
				this.game.addBrooms((Brooms) obj);
			if (obj instanceof Platform)
				this.game.addPlatform((Platform) obj);
		}
	}

	public void deregisterObjects(Stage stage) {
		for (CollisionObject obj : stage.getObjects()) {
			this.game.getMain().getWindow().deregisterDisplayObject(obj);
			if (obj instanceof Fire)
				this.game.removeFire((Fire) obj);
			if (obj instanceof Brooms)
				this.game.removeBrooms((Brooms) obj);
			if (obj instanceof Platform)
				this.game.removePlatform((Platform) obj);
		}
	}

	public void buildStages() {
		System.out.println("Built Stages");
		Stage s1 = new Stage();
		Brooms b1 = new Brooms(
				new Location((Display.getWidth() / 2) - (TextureManager.getTexture("broom").getImageWidth() / 2),
						PATH - 95),
				true, 100F);
		Platform p1 = new Platform(new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("platform").getImageWidth() / 2), PATH - 150));
		s1.addObject(b1);
		s1.addObject(p1);
		this.stages[0] = s1;

		Stage s2 = new Stage();
		s2.addObject(new Fire(new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("fire1").getImageWidth() / 2), PATH)));
		s2.addObject(p1);
		this.stages[1] = s2;

		Stage s3 = new Stage();
		s3.addObject(new Brooms(new Location(300, PATH - 95), true, 100F));
		s3.addObject(new Platform(new Location(300, PATH - 150)));
		s3.addObject(new Platform(new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("platform").getImageWidth() / 2) - 300,
				PATH - 500)));
		s3.addObject(new Platform(new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("platform").getImageWidth() / 2) - 300,
				PATH - 150)));
		s3.addObject(new Fire(
				new Location((Display.getWidth() / 2) - (TextureManager.getTexture("fire1").getImageWidth() / 2) - 300,
						PATH - 150 - TextureManager.getTexture("fire1").getImageHeight())));
		s3.addObject(new Brooms(new Location((Display.getWidth() * 2) / 3, PATH - 95), true, 100F));
		s3.addObject(new Platform(new Location(
				(Display.getWidth() / 2) - (TextureManager.getTexture("platform").getImageWidth() / 2) + 100, PATH - 250)));
		this.stages[2] = s3;
	}

	public Stage getCurrentStage() {
		return this.stages[this.stageIndex];
	}

	public void nextStage() {
		if (this.stageIndex == this.stages.length - 1)
			this.gameOver = true;
		else
			this.stageIndex++;
	}

	public Stage[] getStages() {
		return this.stages;
	}

	public boolean isGameOver() {
		return this.gameOver;
	}
}
