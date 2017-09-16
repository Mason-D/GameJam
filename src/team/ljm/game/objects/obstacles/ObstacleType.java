package team.ljm.game.objects.obstacles;

public enum ObstacleType {
	PLATFORM("platform"), INCENERATOR("incenerator"), WALL("wall");

	private String file;

	ObstacleType(String file) {
		this.file = file;
	}

	public String getFileName() {
		return this.file;
	}
}
