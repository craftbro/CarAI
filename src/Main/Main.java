package Main;

import java.awt.Dimension;

import java.awt.Toolkit;

import API.GameState;
import API.GameStateManager;
import API.MainClass;
import States.MainState;

public class Main extends MainClass{

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int nativeWidth = (int) screenSize.getWidth();
	static int nativeHeight = (int) screenSize.getHeight();
	
	
	public Main() {
		super(nativeWidth, nativeHeight, 1);
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		
		
	}

	@Override
	protected GameState getInitState(GameStateManager gsm) {
		return new MainState(gsm);
	}

}
