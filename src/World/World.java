package World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import Car.Car;
import Util.ImageLoader;

public class World {
	
	BufferedImage backgroundImage;
	BufferedImage worldImage;
	Color backgroundColor = new Color(233, 203, 52);
	
	List<Car> cars = new ArrayList<Car>();
	
	int WIDTH, HEIGHT = 0;
	

	
	
	public World(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		
		//setup the background
		this.setupBackground();
		
		cars.add(new Car());
	}
	
	/**
	 * Creates the background image. To be used on creation only
	 */
	private void setupBackground(){
		backgroundImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) backgroundImage.getGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	
	public void update(){
		for(Car c : cars) c.update();
	}
	
	/**
	 * Draws to worldImage
	 */
	public BufferedImage draw(float scale, double xOffset, double yOffset){
		worldImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);	
		Graphics2D g = (Graphics2D) worldImage.getGraphics();
		
		g.drawImage(backgroundImage, 0, 0, null);

		for(Car c : cars){
			c.draw(g, scale, xOffset, yOffset);
		}
	
		
	
		
		return worldImage;
	}
}
