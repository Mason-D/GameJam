package team.ljm.game.stage;

import java.util.ArrayList;
import java.util.List;

import team.ljm.game.Game;
import team.ljm.game.Location;
import team.ljm.game.objects.CollisionObject;
import team.ljm.game.objects.obstacles.Fire;

public class StageManager {

	private Game game;
	private List<Stage> stages;

	public StageManager(Game game) {
		this.game = game;
		this.stages = new ArrayList<Stage>();
	}

	public void registerObjects(Stage stage) {
		for (CollisionObject obj : stage.getObjects()) {
			this.game.getMain().getWindow().registerDisplayObject(obj);
			if (obj instanceof Fire)
				this.game.addFire((Fire) obj);
		}
	}

	public void deregisterObjects(Stage stage) {
		for (CollisionObject obj : stage.getObjects()) {
			this.game.getMain().getWindow().deregisterDisplayObject(obj);
			if (obj instanceof Fire)
				this.game.removeFire((Fire) obj);
		}
	}

	public void buildStages() {
		Stage testStage = new Stage();
		Fire fire1 = new Fire(new Location(1920 / 2, 1080 / 2));
		testStage.addObject(fire1);
		this.stages.add(testStage);
	}
	
	public List<Stage> getStages() {
		return this.stages;
	}
}
