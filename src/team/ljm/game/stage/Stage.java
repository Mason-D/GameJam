package team.ljm.game.stage;

import java.util.ArrayList;
import java.util.List;

import team.ljm.game.objects.CollisionObject;

public class Stage {

	private List<CollisionObject> objects;

	public Stage() {
		this.objects = new ArrayList<CollisionObject>();
	}

	public void addObject(CollisionObject obj) {
		this.objects.add(obj);
	}

	public void removeObject(CollisionObject obj) {
		this.objects.remove(obj);
	}

	public List<CollisionObject> getObjects() {
		return this.objects;
	}
}
