package team.ljm.game.stage;

import java.util.ArrayList;
import java.util.List;

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
