package team.ljm.game;

public class Location {
	private float x, y;

	public Location(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public Location add(float x, float y) {
		return new Location(this.getX() + x, this.getY() + y);
	}

	public static boolean locationWithinBox(Location loc, Location upperLeftCorner, Location lowerRightCorner) {
		return upperLeftCorner.getX() <= loc.getX() && lowerRightCorner.getX() >= loc.getX()
				&& upperLeftCorner.getY() <= loc.getY() && lowerRightCorner.getY() >= loc.getY();
	}
	
	public Location copy() {
		return new Location(this.getX(), this.getY());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("x: ");
		builder.append(x);
		builder.append(" y: ");
		builder.append(y);
		return builder.toString();
	}
}
