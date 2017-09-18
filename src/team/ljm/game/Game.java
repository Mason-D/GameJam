package team.ljm.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import team.ljm.Main;
import team.ljm.display.DisplayObject;
import team.ljm.display.TextureManager;
import team.ljm.game.menu.Menu;
import team.ljm.game.objects.Player;
import team.ljm.game.objects.obstacles.Brooms;
import team.ljm.game.objects.obstacles.Fire;
import team.ljm.game.objects.obstacles.Platform;
import team.ljm.game.stage.Stage;
import team.ljm.game.stage.StageManager;

public class Game {

	private Main main;

	private Menu menu;
	private DisplayObject introBG;
	private DisplayObject gameBG;

	private List<Brooms> brooms;
	private List<Fire> fire;
	private List<Platform> platform;
	private Player player;

	private GameState gameState;

	private StageManager stageManager;

	private Clip clip;

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
				setGameState(GameState.INTRO);
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
				setGameState(GameState.MENU);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				setGameState(GameState.PAUSED); // if enter is pressed we go to the next state which is the menu.
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
			if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
					|| Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)
					|| Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				setGameState(GameState.GAME);
			}

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

			for (Fire fire : this.fire) {
				// System.out.print("HI");
				fire.burn(this.player);
			}
			for (Brooms br : this.brooms) {
				br.sweep();
			}
			player.handleMovement();
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
				this.clip.stop();
				break;
			case INTRO:
				this.getMain().getWindow().deregisterDisplayObject(introBG);
				this.clip.stop();
				break;
			case PAUSED:
				break;
			case GAME:
				this.getMain().getWindow().deregisterDisplayObject(this.gameBG);
				this.clip.stop();
				break;
			default:
				break;
			}

		switch (this.gameState) {
		case MENU:
			System.out.println("Entered Menu State");
			this.fire = new ArrayList<Fire>();
			this.brooms = new ArrayList<Brooms>();
			this.platform = new ArrayList<Platform>();
			this.stageManager.buildStages();
			// this.stageManager.registerObjects(stageManager.returnStage());
			this.menu = new Menu(this);
			this.menu.open();
			this.playClip("menu");
			break;
		case GAME:
			System.out.println("Entered Game State");
			// this.getMain().getWindow().registerDisplayObject(this.gameBG);
			this.getMain().getWindow().registerDisplayObject(this.player);
			this.stageManager.registerObjects(stageManager.returnStage());
			this.playClip("game");
			break;
		case INTRO:
			System.out.println("Entered Intro State");
			this.playClip("intro");
			this.introBG = new DisplayObject(0, 0, TextureManager.getTexture("introbg"));
			this.getMain().getWindow().registerDisplayObject(introBG);
			break;
		case PAUSED:
			System.out.println("Entered Paused State");
			this.fire = new ArrayList<Fire>();
			Stage s1 = this.stageManager.getStages().get(0);
			this.player = new Player(new Location(50F, 925F));
			this.gameBG = new DisplayObject(0, 0, TextureManager.getTexture("background"));
			// this.getMain().getWindow().registerDisplayObject(this.gameBG);
			this.getMain().getWindow().registerDisplayObject(this.player);
			this.stageManager.registerObjects(stageManager.returnStage());
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

	public void addBrooms(Brooms br) {
		this.brooms.add(br);
	}

	public void removeBrooms(Brooms br) {
		this.brooms.add(br);
	}

	public void addPlatform(Platform pf) {
		this.platform.add(pf);
	}

	public void removePlatform(Platform pf) {
		this.platform.add(pf);
	}

	private void playClip(String name) {
		File f = new File("res/" + name + ".wav");
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		} catch (UnsupportedAudioFileException | IOException e2) {
			e2.printStackTrace();
		}
		this.clip = null;
		try {
			this.clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		try {
			this.clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		this.clip.start();
	}
}
