package Main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Window {
	static GraphicsDevice vc = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];

	public static void main(String[] args){
		JFrame frame = new JFrame("CarAI");
		Main panel = new Main();

		
		//Setting up the window
		//frame.setUndecorated(true);
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		//vc.setFullScreenWindow(frame);
	}
	
}
