package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import API.GameState;
import API.GameStateManager;
import API.MainClass;
import States.InitState;
import States.MainState;

public class Main extends MainClass{

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int nativeWidth = (int) screenSize.getWidth();
	static int nativeHeight = (int) screenSize.getHeight();
	
	int opCount = 0;
	long start = 0;
	
	public Main() {
		super(1080, 720, 1);
		//this.FPS = 120;
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
	}
	
	@Override
	protected void update(){
		super.update();		
	
		
//		if(opCount == 0){
//			start = System.nanoTime();
//		}
//		
//		opCount++;
//		
//		if(opCount >= 60){
//			System.out.println(System.nanoTime()-start);
//			start = 0;
//			opCount = 0;
//		}
	
	}

	@Override
	protected GameState getInitState(GameStateManager gsm) {
		return new MainState(gsm);
	}

}
