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
import team.ljm.game.objects.CollisionObject;
import team.ljm.game.objects.Player;
import team.ljm.game.objects.obstacles.Brooms;
import team.ljm.game.objects.obstacles.Fire;

public class Game {

	private Main main;

	private Menu menu;
	private DisplayObject introBG;
	private List<Fire> fire;
	private List<CollisionObject> scrollables;
	private Player player;
	private Brooms broom0;

	private GameState gameState;

	public Game(Main main) {
		this.main = main;
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

			break;

		case INTRO: // in this state we only wait for enter or escape keys and display the Text
					// about the game story
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy();
				System.exit(0);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				setGameState(GameState.MENU); // if enter is pressed we go to the next state which is the menu.
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
			broom0.sweep();
			break;
		case GAME:
			for (Fire fire : this.fire) {
				fire.burn(this.player);
			}
			
			// handle player movement here
			// handle scrolling here
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
			this.getMain().getWindow().registerDisplayObject(new DisplayObject(0,0, TextureManager.getTexture("background")));
			break;
		case INTRO:
			System.out.println("Entered Intro State");
			this.introBG = new DisplayObject(0, 0, TextureManager.getTexture("introbg"));
			this.getMain().getWindow().registerDisplayObject(introBG);
			break;
		case PAUSED:
			System.out.println("Entered Paused State");
			this.getMain().getWindow().registerDisplayObject(new DisplayObject(0,0, TextureManager.getTexture("background")));
			
			//add broom. 
			broom0 = new Brooms(new Location(50f, 50f), true, 200);
			this.getMain().getWindow().registerDisplayObject(broom0);
			
			this.fire = new ArrayList<Fire>();
			this.scrollables = new ArrayList<CollisionObject>();
			break;
		default:
			break;
		}
	}

	public Main getMain() {
		return this.main;
	}

}
