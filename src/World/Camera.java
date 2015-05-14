package World;

import Util.Mouse;

public class Camera {

	double currentWheelRotation;
	
	float scale;
	float targetScale;
	
	public Camera(){
		currentWheelRotation = Mouse.getScrollPosition();
		
		scale = 1;
		targetScale = 1;
	}
	
	public float getScale(){
		return scale;
	}
	
	public void update(){
		double scroll = Mouse.getScrollPosition();
		
		if(scroll != currentWheelRotation){
			int scrollDiff = (int) (scroll - currentWheelRotation);
			scrollDiff = scrollDiff>0?1:-1;
			targetScale-=(.75*scrollDiff);
			scale-=(.25*scrollDiff);
			currentWheelRotation = scroll;
		}
		
		if(targetScale < 0){
			targetScale = 0;
		}
		
		if(scale > targetScale){
			scale -= .25;
		}else if(scale < targetScale){
			scale += .25;
		}
		
	}
	
}
