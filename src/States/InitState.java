package States;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import API.GameState;
import API.GameStateManager;
import API.MainClass;
import Main.Main;

public class InitState extends GameState{

	public InitState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, MainClass.WIDTH, MainClass.HEIGHT);
	}

	@Override
	public void init() {
		
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
		
	}

}
