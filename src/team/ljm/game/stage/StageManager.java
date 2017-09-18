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
		Stage testStage = new Stage();
		Fire fire1 = new Fire(new Location(700, 800));
		Fire fire2 = new Fire(new Location(1000, 850));
		Fire fire3 = new Fire(new Location(1400, 800));
		Fire fire4 = new Fire(new Location(400, 850));
		Fire fire5 = new Fire(new Location(1600, 800));
		Brooms broom1 = new Brooms(new Location(400, 900), true, 100);
		Brooms broom2 = new Brooms(new Location(1000, 900), true, 100f);
		Brooms broom3 = new Brooms(new Location(1500, 950), true, 100f);
		Brooms broom5 = new Brooms(new Location(1800, 950), true, 100f);
		Platform platform1 = new Platform(new Location(300, 700));
		Platform platform2 = new Platform(new Location(750, 750));
		Platform platform3 = new Platform(new Location(1000, 700));
		Platform platform4 = new Platform(new Location(1200, 750));
		Platform platform5 = new Platform(new Location(1600, 700));
		testStage.addObject(fire1);
		testStage.addObject(fire2);
		testStage.addObject(fire3);
		testStage.addObject(fire4);
		testStage.addObject(fire5);
		testStage.addObject(broom1);
		testStage.addObject(broom2);
		testStage.addObject(broom3);
		testStage.addObject(broom5);
		testStage.addObject(platform1);
		testStage.addObject(platform2);
		testStage.addObject(platform3);
		testStage.addObject(platform4);
		testStage.addObject(platform5);
		this.stages.add(testStage);
		System.out.println("built stages");
	}
	
	public Stage returnStage() {
		return stages.get(0);
	}
	
	public List<Stage> getStages() {
		return this.stages;
	}
}
