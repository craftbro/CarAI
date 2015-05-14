package States;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import API.GameState;
import API.GameStateManager;
import API.MainClass;
import World.Camera;
import World.World;

public class MainState extends GameState{
	
	private World world;
	private Camera camera;

	public MainState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void draw(Graphics2D g) {
		BufferedImage img = world.draw();
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
	}

	@Override
	public void init() {
		world = new World(MainClass.WIDTH, MainClass.HEIGHT);
		camera = new Camera();
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void update() {
		camera.update();
		world.scale = camera.getScale();
	}

}
