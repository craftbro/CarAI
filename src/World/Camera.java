package World;

import java.awt.Point;
import java.awt.geom.Point2D;

import Util.Mouse;

public class Camera {

	double currentWheelRotation;
	Point2D currentMouseLocation;
	
	float scale;
	float targetScale;
	
	double xOffset = 0;
	double yOffset = 0;
	
	public Camera(){
		currentWheelRotation = Mouse.getScrollPosition();
		currentMouseLocation = (Point2D) Mouse.getMouseLocation().clone();
		
		scale = 1;
		targetScale = 1;
	}
	
	/**
	 * 
	 * @return The scale at which the world should draw
	 */
	public float getScale(){
		return scale;
	}
	
	/**
	 * 
	 * @return The current x offset
	 */
	public double getXOffset(){
		return xOffset;
	}
	
	/**
	 * the current y offset
	 * @return
	 */
	public double getYOffset(){
		return yOffset;
	}
	
	public void onPress(){
		currentMouseLocation = (Point2D) Mouse.getMouseLocation().clone();
	}

	
	/**
	 * Used for updating the offset
	 */
	public void updateOffset(){
		if(!Mouse.isMouseDown()) return;
		Point loc = Mouse.getMouseLocation();
		
		
		if(loc.getX() != currentMouseLocation.getX()){
			xOffset -= currentMouseLocation.getX() - loc.getX();
		}
		
		if(loc.getY() != currentMouseLocation.getY()){
			yOffset -= currentMouseLocation.getY() - loc.getY();
		}
		
		
		
		currentMouseLocation = (Point2D) loc.clone();
	}
	
	/**
	 * Used for updating the scale
	 */
	public void updateScale(){
		
		//Check if the user has scrolled. If so, zoom in
		double scroll = Mouse.getScrollPosition();
		if(scroll != currentWheelRotation){
			int scrollDiff = (int) (scroll - currentWheelRotation);
			scrollDiff = scrollDiff>0?1:-1;
			targetScale-=(.75*scrollDiff);
			scale-=(.25*scrollDiff);
			currentWheelRotation = scroll;
		}
		
		//Make sure the zoom can't be negative
		if(targetScale < 0.25){
			targetScale = 0.25f;
		}
		
		//Update the current zoom
		if(scale > targetScale){
			scale -= .25;
		}else if(scale < targetScale){
			scale += .25;
		}
		
	}
	
}
