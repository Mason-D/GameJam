package team.ljm.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import team.ljm.Main;
import team.ljm.display.DisplayObject;
import team.ljm.display.TextureManager;
import team.ljm.game.menu.Menu;
import team.ljm.game.objects.Player;
import team.ljm.game.objects.obstacles.Fire;
import team.ljm.game.stage.StageManager;

public class Game {

	private Main main;

	private Menu menu;
	private DisplayObject introBG;
	private DisplayObject gameBG;

	private List<Fire> fire;
	private Player player;

	private GameState gameState;

	private StageManager stageManager;

	public Game(Main main) {
		this.main = main;
		this.stageManager = new StageManager(this);
	}

	public void tick() {
		switch (this.gameState) {
		case MENU:
			if (Mouse.isButtonDown(0)) {
				this.menu.click(new Location(Mouse.getX(), Mouse.getY()));
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy(); // when in the menu key if we click escape then we will exit program
				System.out.println("Exiting game from Menu state");
				System.exit(0);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				setGameState(GameState.PAUSED);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case INTRO: // in this state we only wait for enter or escape keys and display the Text
					// about the game story
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy();
				System.exit(0);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				setGameState(GameState.MENU); // if enter is pressed we go to the next state which is the menu.
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case PAUSED:
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				setGameState(GameState.MENU);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D))
				setGameState(GameState.GAME);

			break;
		case GAME:
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				setGameState(GameState.PAUSED);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			player.handleMovement();
			for (Fire fire : this.fire) {
				fire.burn(this.player);
			}
			break;
		}
	}

	public GameState getState() {
		return this.gameState;
	}

	public void setGameState(GameState state) {
		GameState lastState = this.gameState;
		this.gameState = state;
		if (lastState != null)
			switch (lastState) {
			case MENU:
				this.menu.close();
				break;
			case INTRO:
				this.getMain().getWindow().deregisterDisplayObject(introBG);
				break;
			case PAUSED:
				break;
			case GAME:
				this.getMain().getWindow().deregisterDisplayObject(this.gameBG);
				break;
			default:
				break;
			}

		switch (this.gameState) {
		case MENU:
			System.out.println("Entered Menu State");
			this.menu = new Menu(this);
			this.menu.open();
			break;
		case GAME:
			System.out.println("Entered Game State");
			this.getMain().getWindow().registerDisplayObject(this.gameBG);
			this.getMain().getWindow().registerDisplayObject(this.player);
			break;
		case INTRO:
			System.out.println("Entered Intro State");
			this.stageManager.buildStages();
			this.introBG = new DisplayObject(0, 0, TextureManager.getTexture("introbg"));
			this.getMain().getWindow().registerDisplayObject(introBG);
			break;
		case PAUSED:
			System.out.println("Entered Paused State");
			this.player = new Player(new Location(-235f, 745f));
			this.gameBG = new DisplayObject(0, 0, TextureManager.getTexture("background"));
			this.getMain().getWindow().registerDisplayObject(this.gameBG);
			this.getMain().getWindow().registerDisplayObject(this.player);

			this.fire = new ArrayList<Fire>();
			break;
		default:
			break;
		}
	}

	public void addFire(Fire fire) {
		this.fire.add(fire);
	}

	public void removeFire(Fire fire) {
		this.fire.remove(fire);
	}

	public Main getMain() {
		return this.main;
	}

}
