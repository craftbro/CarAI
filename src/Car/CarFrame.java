package Car;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Util.ImageLoader;

public class CarFrame {
	
	Car car;
	BufferedImage img;
	Dimension SCALE;
	
	Point[] corners = {new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)};
	int[] dist = {0, 0, 0, 0};

	
	public CarFrame(Car c){
		this.car = c;
		img = ImageLoader.loadImage("car.png");
		SCALE = new Dimension(img.getWidth()/5, img.getHeight()/5);
		//this.setupDist();
	}
	
	private void setupDist(){
		Point middle = new Point(img.getWidth()/2, img.getHeight()/2);
		corners[1] = new Point(img.getWidth(), 0);
		corners[2] = new Point(img.getWidth(), img.getHeight());
		corners[3] = new Point(0, img.getHeight());
		
		for(int i=0;i<4;i++){
			Point c = corners[i];
			dist[i] = (int) middle.distance(c);
		}
	}
	
	public void draw(Graphics2D g, float scale2, double xOffset, double yOffset){
		int sizeW = (int) (SCALE.getWidth()*scale2);
		int sizeH = (int) (SCALE.getHeight()*scale2);
		int x = (int)((((car.x)-(sizeW/2))+xOffset)*scale2);
		int y = (int)((((car.y)+(sizeH/2))+yOffset)*scale2);
		drawRot(g, x, y, sizeW, sizeH);
	}
	
	private void drawRot(Graphics2D g, int x, int y, int sizeW, int sizeH){
//		int y1 = (int) (corners[0].getY() - (Math.sin(Math.toRadians(car.rotation)) * dist[0]));
//		int y2 = (int) (corners[2].getY() - (Math.sin(Math.toRadians(car.rotation)) * dist[2]));
//		int x1 = (int) (corners[1].getX() + ((Math.cos(Math.toRadians(car.rotation))-1) * dist[1]));
//		int xy1 = (int) (corners[1].getY() - ((Math.sin(Math.toRadians(car.rotation))-1) * dist[1]));
//		int x2 = (int) (corners[3].getX() + ((Math.cos(Math.toRadians(car.rotation))-1) * dist[3]));
//		int xy2 = (int) (corners[3].getY() - ((Math.sin(Math.toRadians(car.rotation))-1) * dist[3]));
//		
//		System.out.println(corners[1].getX()+", "+(Math.cos(Math.toRadians(car.rotation))-1)+", "+dist[1]);
//		
//		BufferedImage large = new BufferedImage(x1-x2, y2-y1, BufferedImage.TYPE_INT_ARGB);
//		large.getGraphics().setColor(Color.red);
//		large.getGraphics().fillRect(0, 0, large.getWidth(), large.getHeight());
//		
//		System.out.println(large.getWidth()+", "+large.getHeight());

		BufferedImage large = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		
		ImageIcon icon = new ImageIcon(large);
	    BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
	   // g2.drawImage(large, 0, 0,  null);
	    g2.rotate(Math.toRadians(car.rotation), icon.getIconWidth() / 2, icon.getIconHeight() / 2);
	    g2.drawImage(img, (large.getWidth()/2)-(img.getWidth()/2), (large.getHeight()/2)-(img.getHeight()/2),  null);
	    
	    
	    g.drawImage(blankCanvas, x-(sizeW/2), y-(sizeH/2), sizeW, sizeH, null);
	
	}
	
	
	
}
