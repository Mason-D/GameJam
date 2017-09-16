package team.ljm;

import team.ljm.display.DisplayWindow;

public class Main {
	private DisplayWindow window;

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public Main() {
		this.window = new DisplayWindow();
	}

	private void run() {
		this.window.setup();
		this.window.start();
	}

}
